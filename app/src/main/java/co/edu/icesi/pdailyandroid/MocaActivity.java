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

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Subtract;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsA;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TMT;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsB;

public class MocaActivity extends AppCompatActivity {
    private DataScore dataScore = DataScore.getInstance();
    TMT tmt;
    String type;

    WordsB words_b;
    WordsA words_a;

    Subtract subtract;

    private Button next;

    private int index;

    private ArrayList<String> words_answers_selected;
    private ArrayList<String> words_answers_approved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moca);

        type = getIntent().getStringExtra("EXTRA_FILENAME");

        words_answers_selected = new ArrayList<>();
        words_answers_approved = new ArrayList<>();

        next = findViewById(R.id.ButtonNext);
        next.setEnabled(false);

        switch (type) {
            case "TMT":
                updateFragmentTMT();
                break;
            case "WordsA":
                updateFragmentWordsA();
                break;
            case "Subtract":
                updateFragmentSubtract();
                next.setEnabled(true);
                break;
            case "WordsB":
                updateFragmentWordsB();
                next.setText("Siguiente");
                break;
        }

//        updateFragmentSubtract();

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
                        intent.putExtra("EXTRA_FILENAME", "Subtract");
                        startActivity(intent);
                        break;
                    case "Subtract":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsB");
                        startActivity(intent);
                        break;
                    case "WordsB":
                        if (index+1 < dataScore.getMoca_selection_words().size()){
                            index+=1;
                            updateFragmentWordsB();
                        }
                        if (index+1 >= dataScore.getMoca_selection_words().size()){
                            intent = new Intent(getBaseContext(), ScoreActivity.class);
                            intent.putExtra("EXTRA_TYPE", "MoCa");
                            startActivity(intent);

                        }
                        Log.i("AAAAAAA",Integer.valueOf(index).toString());
                        scoreEvaluation();


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

        if (words_b != null) {
            words_b.setListener(new WordsB.FragmentListener() {
                @Override
                public void onButtonSelected(Boolean b) {
                    next.setEnabled(true);
                }
            });
        }

        if (words_a != null) {
            words_a.setListener(new WordsA.FragmentListener() {
                @Override
                public void onWordsFinished(Boolean b) {
                    next.setEnabled(true);
                }
            });
        }
    }

    private void scoreEvaluation() {
        if (words_b != null) {
            if (words_b.isB_one()) {
                words_answers_selected.add(words_b.getBtn_answer_one().getText().toString());
            }

            if (words_b.isB_two()) {
                words_answers_selected.add(words_b.getBtn_answer_two().getText().toString());
            }

            if (words_b.isB_three()) {
                words_answers_selected.add(words_b.getBtn_answer_three().getText().toString());
            }
        }

        if (index+1 == dataScore.getMoca_selection_words().size()){
            next.setText("Finalizar");
        }

        if (next.getText().equals("Finalizar")) {
            for (int i = 0; i <= words_answers_selected.size() - 1; i++) {
                if (dataScore.getMoca_selection_words().contains(words_answers_selected.get(i))) {
                    words_answers_approved.add(words_answers_selected.get(i));
                }
            }
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

    protected void updateFragmentSubtract() {
        subtract = new Subtract();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, subtract);
        transaction.commit();
    }

    protected void updateFragmentWordsB() {
        words_b = new WordsB();

        switch (dataScore.getMoca_selector_words()){

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