package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

public class WordsA extends Fragment {
    private final Handler handler = new Handler();
    private DataScore dataScore = DataScore.getInstance();

    private Random random = new Random();

//    private TextToSpeech tts;
//    private Locale locSpanish;

    private ArrayList<ArrayList<String>> words;
    private ArrayList<String> words_one;
    private ArrayList<String> words_two;
    private ArrayList<String> words_three;
    private ArrayList<String> words_selected;

    private int time_cooldown;
    private int time_execution;

    private int randomSelector;

    private String word;

    private boolean isRunningCooldown;

    public WordsA() {
        words_one = new ArrayList<>(Arrays.asList("Rostro", "Seda", "Iglesia", "Clavel", "Rojo"));
        words_two = new ArrayList<>(Arrays.asList("Camión", "Plátano", "Violín", "Cama", "Verde"));
        words_three = new ArrayList<>(Arrays.asList("Tren", "Huevo", "Gorro", "Silla", "Azul"));
        words = new ArrayList<>(Arrays.asList(words_one, words_two, words_three));

        randomSelector = random.nextInt(words.size() - 0) + 0;

        words_selected = words.get(randomSelector);
        Log.i("WORDS_SELECTED", words_selected.toString());
        dataScore.setMoca_selection_words(words_selected);

        time_cooldown = 5000;
        time_execution = 3000;

        isRunningCooldown = false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_words, container, false);

        TextView tv_display_words;
        tv_display_words = view.findViewById(R.id.tv_display_words);

        tv_display_words.setText(word);

        handlerTimer(time_execution, time_cooldown, tv_display_words);

        // Inflate the layout for this fragment
        return view;
    }

    private void handlerTimer(int time_execution, int time_cooldown, TextView tv_display_words) {
        for (int j = 0; j <= words_selected.size() - 1; j++) {
            int index = j;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i("INDEX", String.valueOf(index));
                    word = words_selected.get(index);
                    tv_display_words.setText(word);
                    Log.i("WORD", word);
                }
            }, time_execution * j);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_display_words.setText("Vamos otra vez");
            }
        }, time_execution * words_selected.size());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j <= words_selected.size() - 1; j++) {
                    int index = j;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("INDEX", String.valueOf(index));
                            word = words_selected.get(index);
                            tv_display_words.setText(word);
                            Log.i("WORD", word);
                        }
                    }, time_execution * j);
                }
            }
        }, (time_execution * words_selected.size() + time_cooldown));
    }
}
