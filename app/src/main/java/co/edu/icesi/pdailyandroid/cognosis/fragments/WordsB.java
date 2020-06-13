package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

public class WordsB extends Fragment {
    private final Handler handler = new Handler();
    private DataScore dataScore = DataScore.getInstance();

    private ArrayList<ArrayList<String>> words_noise_one, words_noise_two, words_noise_three;

    private ArrayList<String> words_noise_one_a, words_noise_one_b, words_noise_one_c, words_noise_one_d, words_noise_one_e;
    private ArrayList<String> words_noise_two_a, words_noise_two_b, words_noise_two_c, words_noise_two_d, words_noise_two_e;
    private ArrayList<String> words_noise_three_a, words_noise_three_b, words_noise_three_c, words_noise_three_d, words_noise_three_e;

    public WordsB() {
        // Required empty public constructor

        words_noise_one_a = new ArrayList<>(Arrays.asList("Nariz", "Rostro", "Mano"));
        words_noise_one_b = new ArrayList<>(Arrays.asList("Lana", "Algodón", "Seda"));
        words_noise_one_c = new ArrayList<>(Arrays.asList("Iglesia", "Escuela", "Hospital"));
        words_noise_one_d = new ArrayList<>(Arrays.asList("Rosa", "Clavel", "Tulipán"));
        words_noise_one_e = new ArrayList<>(Arrays.asList("Rojo", "Azul", "Verde"));
        Collections.shuffle(words_noise_one_a);
        Collections.shuffle(words_noise_one_b);
        Collections.shuffle(words_noise_one_c);
        Collections.shuffle(words_noise_one_d);
        Collections.shuffle(words_noise_one_e);

        words_noise_one = new ArrayList<>(Arrays.asList(words_noise_one_a, words_noise_one_b, words_noise_one_c, words_noise_one_d, words_noise_one_e));
        Collections.shuffle(words_noise_one);
        Log.i("WORDS_NOISE_ONE", String.valueOf(words_noise_one));

        words_noise_two_a = new ArrayList<>(Arrays.asList("Auto", "Camión", "Avión"));
        words_noise_two_b = new ArrayList<>(Arrays.asList("Pera", "Manzana", "Plátano"));
        words_noise_two_c = new ArrayList<>(Arrays.asList("Violín", "Harpa", "Guitarra"));
        words_noise_two_d = new ArrayList<>(Arrays.asList("Silla", "Mesa", "Cama"));
        words_noise_two_e = new ArrayList<>(Arrays.asList("Verde", "Amarillo", "Negro"));
        Collections.shuffle(words_noise_two_a);
        Collections.shuffle(words_noise_two_b);
        Collections.shuffle(words_noise_two_c);
        Collections.shuffle(words_noise_two_d);
        Collections.shuffle(words_noise_two_e);

        words_noise_two = new ArrayList<>(Arrays.asList(words_noise_two_a, words_noise_two_b, words_noise_two_c, words_noise_two_d, words_noise_two_e));
        Collections.shuffle(words_noise_two);
        Log.i("WORDS_NOISE_TWO", String.valueOf(words_noise_two));

        words_noise_three_a = new ArrayList<>(Arrays.asList("Bicicleta", "Tren", "Barco"));
        words_noise_three_b = new ArrayList<>(Arrays.asList("Pasta", "Arroz", "Huevo"));
        words_noise_three_c = new ArrayList<>(Arrays.asList("Gorro", "Guante", "Bufanda"));
        words_noise_three_d = new ArrayList<>(Arrays.asList("Mesa", "Silla", "Lámpara"));
        words_noise_three_e = new ArrayList<>(Arrays.asList("Azul", "Café", "Naranja"));
        Collections.shuffle(words_noise_three_a);
        Collections.shuffle(words_noise_three_b);
        Collections.shuffle(words_noise_three_c);
        Collections.shuffle(words_noise_three_d);
        Collections.shuffle(words_noise_three_e);

        words_noise_three = new ArrayList<>(Arrays.asList(words_noise_three_a, words_noise_three_b, words_noise_three_c, words_noise_three_d, words_noise_three_e));
        Collections.shuffle(words_noise_three);
        Log.i("WORDS_NOISE_THREE", String.valueOf(words_noise_three));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_words_b, container, false);

        // Inflate the layout for this fragment
        return view;
    }
}
