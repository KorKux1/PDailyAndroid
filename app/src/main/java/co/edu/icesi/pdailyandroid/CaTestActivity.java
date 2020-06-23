package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import co.edu.icesi.pdailyandroid.cognosis.canvas.ClockDraw;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Clock;

public class CaTestActivity extends AppCompatActivity {
    Clock clock;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_test);

        type = getIntent().getStringExtra("EXTRA_FILENAME");

        updateFragmentClock();
    }

    protected void updateFragmentClock(){
        clock = new Clock();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, clock);
        transaction.commit();
    }
}