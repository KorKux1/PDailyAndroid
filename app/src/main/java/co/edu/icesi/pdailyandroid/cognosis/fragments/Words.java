package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import co.edu.icesi.pdailyandroid.R;

public class Words extends Fragment {
    private final Handler handler = new Handler();
    // Required empty public constructor
    private TextToSpeech tts;
    private Locale locSpanish;

    private ArrayList<String> words_one;
    private ArrayList<String> words_two;
    private ArrayList<String> words_three;

    private int index;
    private int time;

    private boolean isRunning;

    public Words() {

        words_one = new ArrayList<>(Arrays.asList("Rostro", "Seda", "Iglesia", "Clavel", "Rojo"));
        words_two = new ArrayList<>(Arrays.asList("Camión", "Plátano", "Violín", "Escritorio", "Verde"));
        words_three = new ArrayList<>(Arrays.asList("Tren", "Huevo", "Sombrero", "Silla", "Azul"));

        index = 0;
        time = 5000;

        isRunning = false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    locSpanish = new Locale("es", "CO");
                    tts.setLanguage(Locale.ENGLISH);
                    isRunning = true;
                }
            }
        });

        tts.speak("HOLA MUNDO", TextToSpeech.QUEUE_FLUSH,null);
//        if (isRunning) {
//            for (index = 0; index < words_one.size(); index++) {
//                tts.speak(words_one.get(index), TextToSpeech.QUEUE_FLUSH, null);
//                Log.i("TEXT", words_one.get(index));
//                handlerTimer(time, index);
//
//                if (index == words_one.size()) {
//                    isRunning = false;
//                }
//            }
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memory_recognition, container, false);
    }

    private void handlerTimer(int time, int i) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                index++;
                Log.i("INDEX", String.valueOf(index));
            }
        }, time);
    }
}
