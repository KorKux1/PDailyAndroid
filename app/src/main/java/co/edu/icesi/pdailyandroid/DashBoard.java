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
import co.edu.icesi.pdailyandroid.viewcontrollers.OthersFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.ProfileFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.RoutineFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.SupportFragment;

public class DashBoard extends AppCompatActivity {

    private SessionManager sessionManager;
    private UserInfoService userInfoService;

    private Button supportButton;
    private Button routineButton;
    private Button foodButton;
    private Button profileButton;
    private Button othersButton;
    private Button levoButton;
    private Button binButton;
    private Button eventsButton;

    private Fragment actualFragment;

    private Fragment binFragment;
    private Fragment levoFragment;
    private Fragment othersFragment;
    private Fragment profileFragment;
    private Fragment foodFragment;
    private Fragment routineFragment;
    private Fragment supportFragment;
    private Fragment eventFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        sessionManager = new SessionManager(getApplicationContext());
        userInfoService = new UserInfoService(sessionManager);

        supportButton = findViewById(R.id.supportButton);
        routineButton = findViewById(R.id.routineButton);
        foodButton = findViewById(R.id.foodButton);
        profileButton = findViewById(R.id.profileButton);
        othersButton = findViewById(R.id.othersButton);
        levoButton = findViewById(R.id.levoButton);
        binButton = findViewById(R.id.binButton);
        eventsButton = findViewById(R.id.eventsButton);

        binFragment = new BinnacleFragment();
        levoFragment = new LevoFragment();
        othersFragment = new OthersFragment();
        profileFragment = new ProfileFragment();
        foodFragment = new FoodFragment();
        routineFragment = new RoutineFragment();
        supportFragment = new SupportFragment();
        eventFragment = new EventFragment();

        loadFragment(profileFragment);
    }

    public void doDashboardAction(View sender) {
        Fragment fragmentToLoad = null;
        Button buttonToEnable = null;
        int backgroundToApply = 0;

        // Load fragment and mark button as selected
        if (sender.equals(binButton)) {
            fragmentToLoad = binFragment;
            buttonToEnable = binButton;
            backgroundToApply = R.drawable.bitacoraactivo;
        } else if (sender.equals(levoButton)) {
            fragmentToLoad = levoFragment;
            buttonToEnable = levoButton;
            backgroundToApply = R.drawable.levodopaactivo;
        } else if (sender.equals(othersButton)) {
            fragmentToLoad = othersFragment;
            buttonToEnable = othersButton;
            backgroundToApply = R.drawable.otrosactivo;
        } else if (sender.equals(profileButton)) {
            fragmentToLoad = profileFragment;
            // the profile page does not have different icons
        } else if (sender.equals(foodButton)) {
            fragmentToLoad = foodFragment;
            buttonToEnable = foodButton;
            backgroundToApply = R.drawable.comidaactivo;
        } else if (sender.equals(routineButton)) {
            fragmentToLoad = routineFragment;
            buttonToEnable = routineButton;
            backgroundToApply = R.drawable.rutinaactivo;
        } else if (sender.equals(supportButton)) {
            fragmentToLoad = supportFragment;
            buttonToEnable = supportButton;
            backgroundToApply = R.drawable.apoyoactivo;
        } else if (sender.equals(eventsButton)) {
            fragmentToLoad = eventFragment;
            buttonToEnable = eventsButton;
            backgroundToApply = R.drawable.eventosactivo;
        }

        loadFragment(fragmentToLoad);

        // Unselect all
        supportButton.setBackgroundResource(R.drawable.apoyoinactivo);
        routineButton.setBackgroundResource(R.drawable.rutinainactivo);
        foodButton.setBackgroundResource(R.drawable.comidainactivo);
        othersButton.setBackgroundResource(R.drawable.levodopainactivo);
        levoButton.setBackgroundResource(R.drawable.levodopainactivo);
        binButton.setBackgroundResource(R.drawable.bitacorainactivo);
        eventsButton.setBackgroundResource(R.drawable.eventosinactivo);

        if (buttonToEnable != null) {
            buttonToEnable.setBackgroundResource(backgroundToApply);
        }
    }

    public void loadFragment(Fragment fragment) {
        if (actualFragment != null && actualFragment.equals(fragment)) return;

        actualFragment = fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, actualFragment);
        ft.commit();
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public Thread getUpdateUserDataThread(Consumer<Boolean> callback) {
        return new Thread(() ->
                callback.accept(userInfoService.updateSchedulesCollectionFromServer()));
    }
}
