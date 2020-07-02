package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

public class GongoTaskActivity extends AppCompatActivity {
    String type;

    private final Handler handler = new Handler();
    private DataScore dataScore = DataScore.getInstance();

    private ArrayList<ArrayList<String>> go_letters;
    private ArrayList<ArrayList<String>> go_answer_letters;
    private ArrayList<Boolean> go_selection_stimulus;
    private ArrayList<Boolean> go_answer_stimulus;
    private ArrayList<Long> go_time_response;

    private String go_stimulus;

    private Random go_random;
    private int go_stimulus_amount_array;
    private double go_stimulus_amount_occurrence;
    private int go_test_multiplier;

    private int time_execution;
    private int time_cooldown;
    private double time_response;

    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public GongoTaskActivity() {
        go_stimulus = "P";

        go_random = new Random();
        go_stimulus_amount_array = 4;
        go_stimulus_amount_occurrence = 0.2;
        go_test_multiplier = 20;

        time_execution = 2000;
        time_cooldown = 1000;
        time_response = 0;

        go_letters = new ArrayList<>(go_test_multiplier);
        go_answer_letters = new ArrayList<ArrayList<String>>(go_test_multiplier);
        go_selection_stimulus = new ArrayList<>(go_test_multiplier);
        go_answer_stimulus = new ArrayList<>(go_test_multiplier);
        go_time_response = new ArrayList<>(go_test_multiplier);

        for (int i = 0; i < go_test_multiplier; i++) {
            ArrayList<String> as = new ArrayList<>(Arrays.asList(""));
            go_answer_stimulus.add(false);
            go_time_response.add((long) 2);
            go_answer_letters.add(as);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongo_task);

        type = getIntent().getStringExtra("EXTRA_FILENAME");

        dataScore.setGo_stimulus(go_stimulus);

        onStimulusArrayGenerator(go_test_multiplier, go_stimulus_amount_occurrence, go_stimulus_amount_array);

        TextView tv_display_go_one;
        TextView tv_display_go_two;
        TextView tv_display_go_three;
        TextView tv_display_go_four;

        Button btn_answer_one;
        Button btn_answer_two;

        tv_display_go_one = findViewById(R.id.tv_display_go_one);
        tv_display_go_two = findViewById(R.id.tv_display_go_two);
        tv_display_go_three = findViewById(R.id.tv_display_go_three);
        tv_display_go_four = findViewById(R.id.tv_display_go_four);

        btn_answer_one = findViewById(R.id.ButtonAnswerOne);
        btn_answer_two = findViewById(R.id.ButtonAnswerTwo);

        tv_display_go_one.setText("");
        tv_display_go_two.setText("");
        tv_display_go_three.setText("");
        tv_display_go_four.setText("");

        handlerTimer(time_execution, time_cooldown, tv_display_go_one, tv_display_go_two, tv_display_go_three, tv_display_go_four, btn_answer_one, btn_answer_two);
    }

    private void handlerTimer(int time_execution, int time_cooldown, TextView tv_display_go_one, TextView tv_display_go_two, TextView tv_display_go_three, TextView tv_display_go_four, Button b_one, Button b_two) {
        for (int j = 0; j <= go_letters.size() - 1; j++) {
            int index = j;

            boolean isStimulusContained;

            if (go_letters.get(index).contains(go_stimulus)) {
                isStimulusContained = true;
                go_selection_stimulus.add(index, isStimulusContained);
            } else {
                isStimulusContained = false;
                go_selection_stimulus.add(index, isStimulusContained);
            }

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            double startTime = System.currentTimeMillis();

                            tv_display_go_one.setText(go_letters.get(index).get(0));
                            tv_display_go_two.setText(go_letters.get(index).get(1));
                            tv_display_go_three.setText(go_letters.get(index).get(2));
                            tv_display_go_four.setText(go_letters.get(index).get(3));

                            b_one.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    time_response = (System.currentTimeMillis() - startTime) / 1000;
                                    go_time_response.set(index, (long) time_response);
                                    Log.i("TIME_RESPONSE", String.valueOf(go_time_response));

                                    boolean isStimulusCorrect;

                                    if (go_letters.get(index).contains(go_stimulus)) {
                                        isStimulusCorrect = true;
                                        go_answer_stimulus.set(index, isStimulusCorrect);
                                    } else {
                                        isStimulusCorrect = false;
                                        go_answer_stimulus.set(index, isStimulusCorrect);
                                    }

                                    go_answer_letters.set(index, go_letters.get(index));

                                    Log.i("GO_SELECTION", String.valueOf(go_selection_stimulus));
                                    Log.i("GO_ANSWERS", String.valueOf(go_answer_stimulus));

                                    dataScore.setGo_selected_stimulus(go_selection_stimulus);
                                    dataScore.setGo_answers_stimulus(go_answer_stimulus);
                                    dataScore.setGo_time_response(go_time_response);
                                    dataScore.setGo_answers_letters(go_answer_letters);

                                    uiUpdateClicked(b_one);
                                    uiUpdateClickedNonSelected(b_two);
                                }
                            });

                            b_two.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    time_response = (System.currentTimeMillis() - startTime) / 1000;
                                    go_time_response.set(index, (long) time_response);
                                    Log.i("TIME_RESPONSE", String.valueOf(go_time_response));

                                    boolean isStimulusCorrect;

                                    if (go_letters.get(index).contains(go_stimulus)) {
                                        isStimulusCorrect = true;
                                        go_answer_stimulus.set(index, isStimulusCorrect);
                                    } else {
                                        isStimulusCorrect = false;
                                        go_answer_stimulus.set(index, isStimulusCorrect);
                                    }

                                    Log.i("GO_SELECTION", String.valueOf(go_selection_stimulus));
                                    Log.i("GO_ANSWERS", String.valueOf(go_answer_stimulus));

                                    dataScore.setGo_selected_stimulus(go_selection_stimulus);
                                    dataScore.setGo_answers_stimulus(go_answer_stimulus);
                                    dataScore.setGo_time_response(go_time_response);

                                    uiUpdateClicked(b_two);
                                    uiUpdateClickedNonSelected(b_one);
                                }
                            });
                        }
                    }, time_execution);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uiUpdateClickedNonSelected(b_one);
                            uiUpdateClickedNonSelected(b_two);
                            tv_display_go_one.setText("");
                            tv_display_go_two.setText("");
                            tv_display_go_three.setText("");
                            tv_display_go_four.setText("");
                        }
                    }, time_cooldown);
                }
            }, (time_execution + time_cooldown) * j);
        }
    }

    private void onStimulusArrayGenerator(int go_test_multiplier, double go_stimulus_amount_occurrence, int go_stimulus_amount_array) {
        for (int i = 0; i < go_test_multiplier; i++) {
            if (i <= (int) (go_test_multiplier * go_stimulus_amount_occurrence)) {
                ArrayList<String> go_stimulus_array;
                go_stimulus_array = new ArrayList<>(go_stimulus_amount_array);
                for (int j = 0; j < go_stimulus_amount_array; j++) {
                    if (j == 0) {
                        go_stimulus_array.add(j, go_stimulus);
                    } else {
                        String go_random_letter = String.valueOf(alphabet.charAt(go_random.nextInt(alphabet.length())));
                        go_stimulus_array.add(go_random_letter);
                    }
                }
                Collections.shuffle(go_stimulus_array);
                go_letters.add(go_stimulus_array);
            } else {
                ArrayList<String> go_non_stimulus_array;
                go_non_stimulus_array = new ArrayList<>(go_stimulus_amount_array);
                for (int j = 0; j < go_stimulus_amount_array; j++) {
                    String go_random_letter = String.valueOf(alphabet.charAt(go_random.nextInt(alphabet.length())));
                    Log.i("RANDOM_LETTER", go_random_letter);
                    go_non_stimulus_array.add(go_random_letter);
                }
                Collections.shuffle(go_non_stimulus_array);
                go_letters.add(go_non_stimulus_array);
                Log.i("GO_LETTERS", String.valueOf(go_letters));
                Collections.shuffle(go_letters);
                dataScore.setGo_selected_letters(go_letters);
            }
        }
    }

    private void uiUpdateClicked(Button b) {
        b.setTextColor(Color.rgb(255, 255, 255));
        b.setBackgroundColor(Color.rgb(0, 188, 209));
    }

    private void uiUpdateClickedNonSelected(Button b) {
        b.setTextColor(Color.rgb(0, 0, 0));
        b.setBackgroundColor(Color.rgb(250, 250, 250));
    }
}