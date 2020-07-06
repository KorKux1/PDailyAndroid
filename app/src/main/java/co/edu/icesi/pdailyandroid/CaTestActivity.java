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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import co.edu.icesi.pdailyandroid.cognosis.canvas.ClockDraw;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Clock;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Speech;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsA;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsB;

public class CaTestActivity extends AppCompatActivity {

    private DataScore dataScore = DataScore.getInstance();
    private Date d = new Date();

    boolean w_b;

    Clock clock;
    WordsB words_b;
    WordsA words_a;
    Speech speech;
    private Boolean isFirstTime;
    private int index;
    String type;
    private Button next;

    private final String speech_stimulus_words = "MP";
    private String speech_stimulus;

    private Random speech_random = new Random();

    private ArrayList<String> words_answers_selected;
    private ArrayList<String> words_answers_approved;
    private ArrayList<String> words_answers_mistakes;

    private long startTime = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_test);

        words_answers_selected = new ArrayList<>();
        words_answers_approved = new ArrayList<>();
        words_answers_mistakes = new ArrayList<>();

        isFirstTime = true;

        speech_stimulus = String.valueOf(speech_stimulus_words.charAt(speech_random.nextInt(speech_stimulus_words.length())));
        dataScore.setCatest_selected_speech_stimulus(speech_stimulus);

        next = findViewById(R.id.ButtonNext);
        type = getIntent().getStringExtra("EXTRA_FILENAME");

        w_b = false;

        next.setText("Continuar");

        switch (type) {
            case "WordsACA":
                updateFragmentWordsA();
                break;
            case "clock":
                updateFragmentClock();
                break;
            case "speech":
                updateFragmentSpeech();
                break;
            case "WordsBCA":
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
                    case "WordsACA":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "clock");
                        startActivity(intent);
                        break;
                    case "clock":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "speech");
                        startActivity(intent);
                        break;
                    case "speech":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsBCA");
                        startActivity(intent);
                        break;
                    case "WordsBCA":
                        if (index + 1 < dataScore.getMoca_selected_words().size()) {
                            index += 1;
                            scoreEvaluation();
                            updateFragmentWordsB();
                        }

                        if (index + 1 == dataScore.getMoca_selected_words().size()) {
                            next.setText("Finalizar");
//                            index = dataScore.getMoca_selected_words().size();
                        }


                        if (w_b) {
                            scoreEvaluation();
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

                            intent = new Intent(getBaseContext(), ScoreTestActivity.class);
                            intent.putExtra("EXTRA_TYPE", "CATEST");
                            startActivity(intent);
                        }
                        break;
                }
            }
        });

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
    }

    private void updateListener() {
        if (speech != null) {
            speech.setListener(new Speech.FragmentListener() {
                @Override
                public void onTimerChange(String msg) {
                    TextView tv_display_number = findViewById(R.id.tv_display_number);
                    tv_display_number.setText(msg);
                }
            });
        }

        if (words_b != null) {
            words_b.setListener(new WordsB.FragmentListener() {
                @Override
                public void onButtonSelected(Boolean b) {
                    Log.i("B_Listener", String.valueOf(b));
                    if (b) {
                        next.setEnabled(true);
                        if (next.getText().equals("Finalizar")) {
                            w_b = true;
                        }

                        next.setBackgroundResource(R.drawable.buttons_cognosis_able);
                    } else {
                        next.setEnabled(false);
                    }
                }
            });
        }
    }

    protected void updateFragmentClock() {
        clock = new Clock();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, clock);
        transaction.commit();
    }

    protected void updateFragmentWordsA() {
        words_a = new WordsA();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, words_a);
        transaction.commit();
    }

    protected void updateFragmentSpeech() {
        speech = new Speech();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, speech);
        transaction.commit();
    }

    protected void updateFragmentWordsB() {
        words_b = new WordsB();

        if (words_b != null) {
            words_b.setListener(new WordsB.FragmentListener() {
                @Override
                public void onButtonSelected(Boolean b) {
                    Log.i("B_Listener", String.valueOf(b));
                    if (b) {
                        next.setEnabled(true);
                        if (next.getText().equals("Finalizar")) {
                            w_b = true;
                        }

                        next.setBackgroundResource(R.drawable.buttons_cognosis_able);
                    } else {
                        next.setEnabled(false);
                    }
                }
            });
        }

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