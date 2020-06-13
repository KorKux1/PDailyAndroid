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

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeD;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsA;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TMT;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsB;

public class MocaActivity extends AppCompatActivity {
    private DataScore dataScore = DataScore.getInstance();
    private TMT tmt;
    private String type;

    private WordsA words_a;
    private WordsB words_b;

    private int index;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moca);

        type = getIntent().getStringExtra("EXTRA_FILENAME");

        index = 0;

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
        updateFragmentWordsA();

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
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsB");
                        startActivity(intent);
                        break;
                    case "WordsB":

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

        if (words_a != null) {
            words_a.setListener(new WordsA.FragmentListener() {
                @Override
                public void onWordsFinished(Boolean b) {
                    if (b) {
                        next.setEnabled(true);
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

        switch (dataScore.getMoca_selector_words()) {
            case 0:
                words_b.setAnswer_one_text(words_b.getWords_noise_one().get(index).get(0));
                words_b.setAnswer_two_text(words_b.getWords_noise_one().get(index).get(1));
                words_b.setAnswer_three_text(words_b.getWords_noise_one().get(index).get(2));
                break;

            case 1:
                words_b.setAnswer_one_text(words_b.getWords_noise_two().get(index).get(0));
                words_b.setAnswer_two_text(words_b.getWords_noise_two().get(index).get(1));
                words_b.setAnswer_three_text(words_b.getWords_noise_two().get(index).get(2));
                break;

            case 2:
                words_b.setAnswer_one_text(words_b.getWords_noise_three().get(index).get(0));
                words_b.setAnswer_two_text(words_b.getWords_noise_three().get(index).get(1));
                words_b.setAnswer_three_text(words_b.getWords_noise_three().get(index).get(2));
                break;
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, words_b);
        transaction.commit();
    }
}