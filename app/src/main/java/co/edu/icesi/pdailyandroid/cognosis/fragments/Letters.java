package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

public class Letters extends Fragment {
    private DataScore dataScore = DataScore.getInstance();
    private final Handler handler = new Handler();

    private ArrayList<String> letters;

    private ArrayList<String> letters_pressed;
    private ArrayList<Integer> letters_tapped;

    private ArrayList<Long> letters_response_time;

    private int tap;
    private int score;

    private double time_response;

    private int time_cooldown;
    private int time_execution;
    private long time_start;

    private TextView tv_display_letters;

    private FragmentListener listener;

    public Letters() {
        letters = new ArrayList<>(Arrays.asList("F", "B", "A", "C", "M", "N", "A", "A", "J", "K", "L", "B", "A", "F", "A", "K", "D", "E", "A", "A", "A", "J", "A", "M", "O", "F", "A", "A", "B"));
        Collections.shuffle(letters);
        dataScore.setMoca_selected_letters(letters);

        listener = null;

        letters_pressed = new ArrayList<>(letters.size());
        letters_tapped = new ArrayList<>(letters.size());
        letters_response_time = new ArrayList<>(letters.size());

        for (int i = 0; i < letters.size(); i++) {
            letters_pressed.add(i, "/");
            letters_tapped.add(i, 0);
            letters_response_time.add(i, (long) 0);
        }

        time_execution = 2000;
        time_response = 0;
        time_start = System.currentTimeMillis();
        score = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letters, container, false);

        tv_display_letters = view.findViewById(R.id.tv_display_letters);

        handlerTimer(time_execution, tv_display_letters);

        return view;
    }

    private void handlerTimer(int time_execution, TextView tv_display_letters) {
        for (int i = 0; i < letters.size(); i++) {
            int index = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    double startTime = System.currentTimeMillis();
                    final boolean[] isFirstClick = {true};

                    tap = 0;

                    tv_display_letters.setText(dataScore.getMoca_selected_letters().get(index));
                    tv_display_letters.setTextColor(Color.BLACK);
                    tv_display_letters.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isFirstClick[0]) {
                                time_response = System.currentTimeMillis() - startTime;
                                letters_response_time.set(index, (long) (time_response));
                                Log.i("TIME_RESPONSE", String.valueOf(time_response));

                                letters_pressed.set(index, dataScore.getMoca_selected_letters().get(index));

                                tv_display_letters.setTextColor(Color.rgb(0, 188, 209));
                                tap += 1;

                                isFirstClick[0] = false;
                            } else {
                                tap += 1;
                            }
                            letters_tapped.set(index, tap);
                        }
                    });
                    Log.i("LAST_LETTER_TIME", letters_response_time.get(index).toString());
                    dataScore.setMoca_time_response_letters(letters_response_time);
                    dataScore.setMoca_tapped_letters(letters_tapped);
                    Log.i("TAP", letters_tapped.toString());
                    dataScore.setMoca_answers_letters(letters_pressed);
                }
            }, time_execution * i);
            if (letters_pressed.get(i) != "A") {
                score += 1;
            }
            dataScore.setMoca_score_letters(score);
            dataScore.setMoca_time_response_letters_total(System.currentTimeMillis() - time_start);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onLettersFinished(true);
                tv_display_letters.setText("");
            }
        }, time_execution*letters.size());
    }

    public interface FragmentListener {
        void onLettersFinished(Boolean b);
    }

    public void setListener(FragmentListener listener) {
        this.listener = listener;
    }

}
