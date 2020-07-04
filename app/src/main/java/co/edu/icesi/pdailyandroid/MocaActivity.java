package co.edu.icesi.pdailyandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Go;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Letters;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Speech;
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

    Letters letters;

    private Button next;

    private int index;

    private ArrayList<String> words_answers_selected;
    private ArrayList<String> words_answers_approved;
    private ArrayList<String> words_answers_mistakes;

    private Boolean isFirstTime;
    private long startTime = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moca);

        type = getIntent().getStringExtra("EXTRA_FILENAME");

        words_answers_selected = new ArrayList<>();
        words_answers_approved = new ArrayList<>();
        words_answers_mistakes = new ArrayList<>();

        isFirstTime = true;

        next = findViewById(R.id.ButtonNext);
        next.setEnabled(false);

        next.setText("Continuar");

        switch (type) {
            case "TMT":
                updateFragmentTMT();
                break;
            case "WordsA":
                updateFragmentWordsA();
                break;
            case "Subtract":
                updateFragmentSubtract();
                break;
            case "Letras":
                updateFragmentLetters();
                break;
            case "WordsB":
                updateFragmentWordsB();
                next.setText("Siguiente");
                break;
        }

        updateListener();

        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent;
                next.setBackgroundResource(R.drawable.buttons_cognosis_disable);

                switch (type) {
                    case "TMT":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsA");
                        dataScore.setMoca_time_response_tmt_total(System.currentTimeMillis() - startTime);
                        startActivity(intent);
                        break;
                    case "WordsA":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "Subtract");
                        startActivity(intent);
                        break;
                    case "Subtract":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "Letras");
                        dataScore.setMoca_time_response_substract_total(System.currentTimeMillis() - startTime);

                        startActivity(intent);
                        break;

                    case "Letras":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsB");
                        dataScore.setMoca_time_response_letters_total(System.currentTimeMillis() - startTime);
                        startActivity(intent);
                        break;

                    case "WordsB":
                        if (index + 1 <= dataScore.getMoca_selected_words().size() - 1) {
                            index += 1;
                            updateFragmentWordsB();
                        }

                        if (next.getText().equals("Finalizar")) {
                            intent = new Intent(getBaseContext(), ScoreTestActivity.class);
                            intent.putExtra("EXTRA_TYPE", "MOCA");
                            dataScore.setMoca_time_response_words_total(System.currentTimeMillis() - startTime);
                            startActivity(intent);
                        }

                        Log.i("AAAAAAA", Integer.valueOf(index).toString());
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
                        next.setBackgroundResource(R.drawable.buttons_cognosis_able);
                        Log.i("SCORE_TMT", score_tmt);
                    }
                }
            });
        }

        if (words_a != null) {
            words_a.setListener(new WordsA.FragmentListener() {
                @Override
                public void onWordsFinished(Boolean b) {
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.buttons_cognosis_able);
                }
            });
        }

        if (words_b != null) {
            words_b.setListener(new WordsB.FragmentListener() {
                @Override
                public void onButtonSelected(Boolean b) {
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.buttons_cognosis_able);
                }
            });
        }

        if (subtract != null) {
            subtract.setListener(new Subtract.FragmentListener() {
                @Override
                public void onTestFinished(boolean b) {
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.buttons_cognosis_able);
                }
            });
        }

        if (letters != null) {
            letters.setListener(new Letters.FragmentListener() {
                @Override
                public void onLettersFinished(Boolean b) {
                    next.setEnabled(true);
                    next.setBackgroundResource(R.drawable.buttons_cognosis_able);
                }
            });
            dataScore.setMoca_time_response_letters_total(System.currentTimeMillis() - startTime);
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

        if (index + 1 == dataScore.getMoca_selected_words().size()) {
            next.setText("Finalizar");
        }

        if (next.getText().equals("Finalizar")) {
            dataScore.setMoca_time_response_words_total(System.currentTimeMillis() - startTime);
            for (int i = 0; i < words_answers_selected.size(); i++) {
                if (dataScore.getMoca_selected_words().contains(words_answers_selected.get(i))) {
                    words_answers_approved.add(words_answers_selected.get(i));
                    Log.i("RESPONSE", words_answers_approved.toString());
                    Log.i("index", String.valueOf(i));
                } else {
                    words_answers_mistakes.add(words_answers_selected.get(i));
                    dataScore.setMoca_mistakes_words(words_answers_mistakes);
                }
            }
            Log.i("WORDS SCORE", String.valueOf(words_answers_selected.size() - words_answers_mistakes.size()));
            dataScore.setMoca_score_words(words_answers_approved.size());
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

    protected void updateFragmentLetters() {
        letters = new Letters();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, letters);
        transaction.commit();
    }

    protected void updateFragmentWordsB() {
        words_b = new WordsB();
        if (isFirstTime) {

            switch (dataScore.getMoca_selector_words()) {

                case 0:
                    dataScore.setMoca_selected_words_noise(words_b.getWords_noise_one());
                    words_b.setAnswer_one_text(words_b.getWords_noise_one().get(index).get(0));
                    words_b.setAnswer_two_text(words_b.getWords_noise_one().get(index).get(1));
                    words_b.setAnswer_three_text(words_b.getWords_noise_one().get(index).get(2));
                    break;

                case 1:
                    dataScore.setMoca_selected_words_noise(words_b.getWords_noise_two());
                    words_b.setAnswer_one_text(words_b.getWords_noise_two().get(index).get(0));
                    words_b.setAnswer_two_text(words_b.getWords_noise_two().get(index).get(1));
                    words_b.setAnswer_three_text(words_b.getWords_noise_two().get(index).get(2));
                    break;

                case 2:
                    dataScore.setMoca_selected_words_noise(words_b.getWords_noise_three());
                    words_b.setAnswer_one_text(words_b.getWords_noise_three().get(index).get(0));
                    words_b.setAnswer_two_text(words_b.getWords_noise_three().get(index).get(1));
                    words_b.setAnswer_three_text(words_b.getWords_noise_three().get(index).get(2));
                    break;
            }

            isFirstTime = false;
        } else {
            words_b.setAnswer_one_text(dataScore.getMoca_selected_words_noise().get(index).get(0));
            words_b.setAnswer_two_text(dataScore.getMoca_selected_words_noise().get(index).get(1));
            words_b.setAnswer_three_text(dataScore.getMoca_selected_words_noise().get(index).get(2));
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, words_b);
        transaction.commit();
    }
}