package co.edu.icesi.pdailyandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.function.Consumer;

import co.edu.icesi.pdailyandroid.services.SessionManager;
import co.edu.icesi.pdailyandroid.services.UserInfoService;
import co.edu.icesi.pdailyandroid.viewcontrollers.BinnacleFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.EventFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.FoodFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.LevoFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.ProfileFragment;

public class DashBoard extends AppCompatActivity {

    private SessionManager sessionManager;
    private UserInfoService userInfoService;

    private Button foodButton;
    private Fragment foodFragment;
    private Button profileButton;
    private Fragment profileFragment;
    private Button levoButton;
    private Fragment levoFragment;
    private Button binButton;
    private Fragment binFragment;
    private Button eventsButton;
    private Fragment eventFragment;

    private Fragment actualFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        sessionManager = new SessionManager(getApplicationContext());
        userInfoService = new UserInfoService(sessionManager);

        foodButton = findViewById(R.id.foodButton);
        foodFragment = new FoodFragment();
        profileButton = findViewById(R.id.profileButton);
        profileFragment = new ProfileFragment();
        levoButton = findViewById(R.id.levoButton);
        levoFragment = new LevoFragment();
        binButton = findViewById(R.id.binButton);
        binFragment = new BinnacleFragment();
        eventsButton = findViewById(R.id.eventsButton);
        eventFragment = new EventFragment();

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
        }
    }

    private void navigateToFragment(Fragment fragment, Button button, int icon) {
        loadFragment(fragment);
        profileButton.setBackgroundResource(R.drawable.perfilinactivo);
        foodButton.setBackgroundResource(R.drawable.comidainactivo);
        levoButton.setBackgroundResource(R.drawable.levodopainactivo);
        binButton.setBackgroundResource(R.drawable.bitacorainactivo);
        eventsButton.setBackgroundResource(R.drawable.eventosinactivo);
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
}
