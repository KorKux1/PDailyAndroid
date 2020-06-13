package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.edu.icesi.pdailyandroid.R;

public class TypeD extends Fragment {
    private FragmentListener listener;

    private String answer_one_text;
    private String answer_two_text;
    private String answer_three_text;

    private int index;
    private boolean b_one, b_two, b_three;

    public TypeD() {
        b_one = false;
        b_two = false;
        b_three = false;

        this.listener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_d, container, false);

        TextView tv_header_words = view.findViewById(R.id.TextFormQuestion);

        Button btn_answer_one = view.findViewById(R.id.ButtonAnswerOne);
        Button btn_answer_two = view.findViewById(R.id.ButtonAnswerTwo);
        Button btn_answer_three = view.findViewById(R.id.ButtonAnswerThree);

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

                uiUpdateClickedSelect(btn_answer_one);
                uiUpdateClicked(btn_answer_two);
                uiUpdateClicked(btn_answer_three);

                if(listener != null){
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

                uiUpdateClickedSelect(btn_answer_two);
                uiUpdateClicked(btn_answer_one);
                uiUpdateClicked(btn_answer_three);

                if(listener != null){
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

                uiUpdateClickedSelect(btn_answer_three);
                uiUpdateClicked(btn_answer_one);
                uiUpdateClicked(btn_answer_two);

                if(listener != null){
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

    private void uiUpdateClickedSelect(Button b) {
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
}
