package co.edu.icesi.pdailyandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Words;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TMT;

public class MocaActivity extends AppCompatActivity {
    private DataScore dataScore = DataScore.getInstance();
    TMT tmt;
    Words mr;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moca);

        next = findViewById(R.id.ButtonNext);
        next.setEnabled(false);

        updateFragmentTMT();
//        updateFragmentMR();

        updateListener();

        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ScoreActivity.class);
                intent.putExtra("EXTRA_MOCA", "TMT");
                startActivity(intent);
            }
        });
    }

    private void updateListener() {
        if (tmt != null) {
            tmt.setListener(new TMT.FragmentListener() {
                @Override
                public void onScoreUpdated(String score_tmt) {
                    if (score_tmt != null) {
                        next.setEnabled(true);
                        Log.i("SCORE_TMT", score_tmt);
                    }
                }
            });
        }
    }

    protected void updateFragmentTMT() {
        tmt = new TMT();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, tmt);
        transaction.commit();
    }

    protected void updateFragmentMR() {
        mr = new Words();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, mr);
        transaction.commit();
    }
}