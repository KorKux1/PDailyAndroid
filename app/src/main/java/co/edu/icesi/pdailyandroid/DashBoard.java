package co.edu.icesi.pdailyandroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;
import java.util.function.Consumer;

import co.edu.icesi.pdailyandroid.games.BananaGame;
import co.edu.icesi.pdailyandroid.games.WormGame;
import co.edu.icesi.pdailyandroid.receivers.broadcast.NotificationReceiver;
import co.edu.icesi.pdailyandroid.services.SessionManager;
import co.edu.icesi.pdailyandroid.services.UserInfoService;
import co.edu.icesi.pdailyandroid.viewcontrollers.BinnacleFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.EventFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.FoodFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.LevoFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.NotificationPermissionFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.ProfileFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.VideoFragment;


public class DashBoard extends AppCompatActivity {

    private SessionManager sessionManager;
    private UserInfoService userInfoService;

    private Button binButton;
    private Fragment binFragment;

    private Button levoButton;
    private Fragment levoFragment;

    private Button profileButton;
    private Fragment profileFragment;

    private Button foodButton;
    private Fragment foodFragment;

    private Button eventsButton;
    private Fragment eventFragment;

    private Button videoButton;
    private Fragment videoFragment;

    private Fragment actualFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        sessionManager = new SessionManager(getApplicationContext());
        userInfoService = new UserInfoService(sessionManager);

        binButton = findViewById(R.id.binButton);
        binFragment = new BinnacleFragment();

        levoButton = findViewById(R.id.levoButton);
        levoFragment = new LevoFragment();

        profileButton = findViewById(R.id.profileButton);
        profileFragment = new ProfileFragment();

        foodButton = findViewById(R.id.foodButton);
        foodFragment = new FoodFragment();

        eventsButton = findViewById(R.id.eventsButton);
        eventFragment = new EventFragment();

        videoButton = findViewById(R.id.videoButton);
        videoFragment = new VideoFragment();

        Button bananaGameButton = findViewById(R.id.bananaGameButton);
        Button wormGameButton = findViewById(R.id.wormGameButton);

        bananaGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, BananaGame.class);
            startActivity(intent);
        });

        wormGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, WormGame.class);
            startActivity(intent);
        });


        navigateToFragment(profileButton);
    }

    public void doNavigateToActivityAction(View sender) {
        navigateToFragment((Button) sender);
    }

    private void navigateToFragment(Button button) {
        if (button.equals(binButton)) {
            navigateToFragment(binFragment, binButton, R.drawable.bitacoraactivo);
        } else if (button.equals(levoButton)) {
            navigateToFragment(levoFragment, levoButton, R.drawable.levodopaactivo);
        } else if (button.equals(profileButton)) {
            navigateToFragment(profileFragment, profileButton, R.drawable.perfilactivo);
        } else if (button.equals(foodButton)) {
            navigateToFragment(foodFragment, foodButton, R.drawable.comidaactivo);
        } else if (button.equals(eventsButton)) {
            navigateToFragment(eventFragment, eventsButton, R.drawable.eventosactivo);
        } else if (button.equals(videoButton)) {
            navigateToFragment(videoFragment, videoButton, R.drawable.rutinaactivo);
        }
    }

    private void navigateToFragment(Fragment fragment, Button button, int icon) {
        loadFragment(fragment);
        binButton.setBackgroundResource(R.drawable.bitacorainactivo);
        levoButton.setBackgroundResource(R.drawable.levodopainactivo);
        profileButton.setBackgroundResource(R.drawable.perfilinactivo);
        foodButton.setBackgroundResource(R.drawable.comidainactivo);
        eventsButton.setBackgroundResource(R.drawable.eventosinactivo);
        videoButton.setBackgroundResource(R.drawable.rutinainactivo);
        button.setBackgroundResource(icon);
    }

    private void loadFragment(Fragment fragment) {
        if (!fragment.equals(actualFragment)) {
            actualFragment = fragment;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, actualFragment);
            ft.commit();
        }
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public Thread getUpdateUserDataThread(Consumer<Boolean> callback) {
        return new Thread(() ->
            callback.accept(userInfoService.updateSchedulesCollectionFromServer()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                checkNotificationPermissions();
            }
        });
    }

    private void checkNotificationPermissions() {

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        boolean areNotificationsEnabled = notificationManager.areNotificationsEnabled();
        if (!areNotificationsEnabled) {
            // Navega al fragmento que muestra el AlertDialog
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new NotificationPermissionFragment())
                    .commit();
        } else {
            setupRecurringNotification();
        }
    }


    private void setupRecurringNotification(){
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("type", "WAKEUP");
        intent.putExtra("title", "¿Cómo te sientes hoy?");
        intent.putExtra("contextText", "¡Es hora de responder las preguntas!");
        int flag = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            flag = PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT;
        }
        else {
            flag = PendingIntent.FLAG_UPDATE_CURRENT;
        }

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 99999, intent, flag);

        // Obtiene el AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        // long startTime = System.currentTimeMillis(); // tiempo de inicio
        // long repeatTime = 30 * 1000; // repite cada 15 minutos
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, repeatTime, pendingIntent);
    }
}
