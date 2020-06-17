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

    private ArrayList<Double> letters_response_time;

    private int tap;

    private double time_response;

    private int time_cooldown;
    private int time_execution;

    private TextView tv_display_letters;

    public Letters() {
        letters = new ArrayList<>(Arrays.asList("F", "B", "A", "C", "M", "N", "A", "A", "J", "K", "L", "B", "A", "F", "A", "K", "D", "E", "A", "A", "A", "J", "A", "M", "O", "F", "A", "A", "B"));
        Collections.shuffle(letters);
        dataScore.setMoca_selection_letters(letters);

        letters_pressed = new ArrayList<>(letters.size());
        letters_tapped = new ArrayList<>(letters.size());
        letters_response_time = new ArrayList<>(letters.size());

        for (int i = 0; i < letters.size()-1; i++){
            letters_pressed.add(i, "/");
            letters_tapped.add(i, 0);
            letters_response_time.add(i, (double) 0);
        }

        time_execution = 2000;
        time_response = 0;
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
        for (int i = 0; i <= letters.size() - 1; i++) {
            int index = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    double startTime = System.currentTimeMillis();
                    final boolean[] isFirstClick = {true};

                    tv_display_letters.setText(dataScore.getMoca_selection_letters().get(index));
                    tv_display_letters.setTextColor(Color.BLACK);
                    tv_display_letters.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isFirstClick[0]) {
                                time_response = System.currentTimeMillis() - startTime;
                                letters_response_time.add(index, time_response/1000);
                                Log.i("TIME_RESPONSE", String.valueOf(time_response));

                                letters_pressed.add(index, dataScore.getMoca_selection_letters().get(index));

                                tv_display_letters.setTextColor(Color.rgb(0, 188, 209));
                                tap += 1;

                                isFirstClick[0] = false;
                            } else {
                                tap += 1;
                            }
                        }
                    });
                    letters_tapped.add(index, tap);
                    Log.i("LAST_LETTER_TIME", letters_response_time.get(index).toString());
                }
            }, time_execution * i);
        }
    }
}
