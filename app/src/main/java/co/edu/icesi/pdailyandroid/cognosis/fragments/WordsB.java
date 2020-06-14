package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

public class WordsB extends Fragment {
    private final Handler handler = new Handler();
    private DataScore dataScore = DataScore.getInstance();

    private FragmentListener listener;

    private ArrayList<ArrayList<String>> words_noise_one, words_noise_two, words_noise_three;

    private ArrayList<String> words_noise_one_a, words_noise_one_b, words_noise_one_c, words_noise_one_d, words_noise_one_e;
    private ArrayList<String> words_noise_two_a, words_noise_two_b, words_noise_two_c, words_noise_two_d, words_noise_two_e;
    private ArrayList<String> words_noise_three_a, words_noise_three_b, words_noise_three_c, words_noise_three_d, words_noise_three_e;

    private int index;

    private boolean b_one, b_two, b_three;
    private String answer_one_text, answer_two_text, answer_three_text;

    private Button btn_answer_one, btn_answer_two, btn_answer_three;

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

        b_one = false;
        b_two = false;
        b_three = false;

        this.listener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_words_b, container, false);

        TextView tv_header_words = view.findViewById(R.id.TextFormQuestion);

        btn_answer_one = view.findViewById(R.id.ButtonAnswerOne);
        btn_answer_two = view.findViewById(R.id.ButtonAnswerTwo);
        btn_answer_three = view.findViewById(R.id.ButtonAnswerThree);

        tv_header_words.setText("¿Cuál de las siguientes palabras se te pidió recordar?");

        btn_answer_one.setText(this.answer_one_text);
        btn_answer_two.setText(this.answer_two_text);
        btn_answer_three.setText(this.answer_three_text);

        btn_answer_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_one = true;
                b_two = false;
                b_three = false;

                uiUpdateClicked(btn_answer_one);
                uiUpdateClickedNonSelected(btn_answer_two);
                uiUpdateClickedNonSelected(btn_answer_three);

                if (listener != null) {
                    listener.onButtonSelected(b_one);
                }
            }
        });

        btn_answer_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_one = false;
                b_two = true;
                b_three = false;

                uiUpdateClicked(btn_answer_two);
                uiUpdateClickedNonSelected(btn_answer_one);
                uiUpdateClickedNonSelected(btn_answer_three);

                if (listener != null) {
                    listener.onButtonSelected(b_two);
                }
            }
        });

        btn_answer_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_one = false;
                b_two = false;
                b_three = true;

                uiUpdateClicked(btn_answer_three);
                uiUpdateClickedNonSelected(btn_answer_two);
                uiUpdateClickedNonSelected(btn_answer_two);

                if (listener != null) {
                    listener.onButtonSelected(b_three);
                }
            }
        });

        return view;
    }

    private void uiUpdateClicked(Button b) {
        b.setTextColor(Color.rgb(255, 255, 255));
        b.setBackgroundColor(Color.rgb(0, 188, 209));
    }

    private void uiUpdateClickedNonSelected(Button b) {
        b.setTextColor(Color.rgb(0, 0, 0));
        b.setBackgroundColor(Color.rgb(250, 250, 250));
    }

    public interface FragmentListener {
        void onButtonSelected(Boolean b);
    }

    public void setListener(FragmentListener listener) {
        this.listener = listener;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isB_one() {
        return b_one;
    }

    public boolean isB_two() {
        return b_two;
    }

    public boolean isB_three() {
        return b_three;
    }

    public void setAnswer_one_text(String answer_one_text) {
        this.answer_one_text = answer_one_text;
    }

    public void setAnswer_two_text(String answer_two_text) {
        this.answer_two_text = answer_two_text;
    }

    public void setAnswer_three_text(String answer_three_text) {
        this.answer_three_text = answer_three_text;
    }

    public ArrayList<ArrayList<String>> getWords_noise_one() {
        return words_noise_one;
    }

    public ArrayList<ArrayList<String>> getWords_noise_two() {
        return words_noise_two;
    }

    public ArrayList<ArrayList<String>> getWords_noise_three() {
        return words_noise_three;
    }

    public Button getBtn_answer_one() {
        return btn_answer_one;
    }

    public Button getBtn_answer_two() {
        return btn_answer_two;
    }

    public Button getBtn_answer_three() {
        return btn_answer_three;
    }
}
