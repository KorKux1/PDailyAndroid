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
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsA;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TMT;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsB;

public class MocaActivity extends AppCompatActivity {
    private DataScore dataScore = DataScore.getInstance();
    TMT tmt;
    String type;

    WordsB words_b;
    WordsA words_a;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moca);

        type = getIntent().getStringExtra("EXTRA_FILENAME");

        next = findViewById(R.id.ButtonNext);
        next.setEnabled(false);

        switch (type) {
            case "TMT":
                updateFragmentTMT();
                break;
            case "WordsA":
                updateFragmentWordsA();
                break;
            case "WordsB":
                updateFragmentWordsB();
                break;
        }


        updateListener();

        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent;

                switch (type) {
                    case "TMT":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsA");
                        startActivity(intent);
                        break;
                    case "WordsA":
                        updateFragmentWordsA();
                        break;
                    case "WordsB":
                        updateFragmentWordsB();
                        break;
                }
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

    protected void updateFragmentWordsA() {
        words_a = new WordsA();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, words_a);
        transaction.commit();
    }

    protected void updateFragmentWordsB() {
        words_b = new WordsB();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, words_b);
        transaction.commit();
    }
}