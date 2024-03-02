package co.edu.icesi.pdailyandroid;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import co.edu.icesi.pdailyandroid.services.SessionManager;
import co.edu.icesi.pdailyandroid.services.UserInfoService;
import co.edu.icesi.pdailyandroid.viewcontrollers.BinnacleFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.FoodFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.WakeUpQuestionsFragment;

public class WakeUpQuestionsActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private UserInfoService userInfoService;

    private Fragment binFragment;

    private Fragment levoFragment;

    private Fragment profileFragment;

    private Fragment foodFragment;

    private Fragment eventFragment;

    private Fragment videoFragment;

    private Fragment wakeUpQuestionsFragment;

    private Fragment actualFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        binFragment = new BinnacleFragment();

        wakeUpQuestionsFragment = new WakeUpQuestionsFragment();

        String fragmentToOpen = getIntent().getStringExtra("openFragment");

        if ("fragment_wakeup_questions".equals(fragmentToOpen)) {
           //  loadFragment(wakeUpQuestionsFragment);
            // Abrir el fragment
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayout, new WakeUpQuestionsFragment())
//                    .addToBackStack(null)
//                    .commit();
        } else if ("fragment_food".equals(fragmentToOpen)) {
            // loadFragment(binFragment);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayout, new BinnacleFragment()).
//                    addToBackStack(null)
//                    .commit();
        }
    }

    private void loadFragment(Fragment fragment) {
        if (!fragment.equals(actualFragment)) {
            actualFragment = fragment;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, actualFragment);
            ft.commit();
        }
    }
}
