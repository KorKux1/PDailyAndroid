package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.edu.icesi.pdailyandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TypeB extends Fragment {
    private FragmentListener listener;
    private String formQuestion;

    private String answerOne;
    private String answerTwo;

    private int formTotalNumber;
    private int index;
    private boolean aOne, aTwo;

    public TypeB() {
        aOne = false;
        aTwo = false;

        this.listener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_b, container, false);

        TextView formQuestion = view.findViewById(R.id.TextFormQuestion);

        Button answerOne = view.findViewById(R.id.ButtonAnswerOne);
        Button answerTwo = view.findViewById(R.id.ButtonAnswerTwo);

        formQuestion.setText(this.formQuestion);

        answerOne.setText(this.answerOne);
        answerTwo.setText(this.answerTwo);

        answerOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                aOne = true;
                aTwo = false;

                uiUpdateClickedSelect (answerTwo);
                uiUpdateClicked (answerOne);

                if (listener != null){
                    listener.onButtonSelected(aOne);
                    Log.i("LISTENER_BUTTON", String.valueOf(listener));
                }
            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aOne = false;
                aTwo = true;

                uiUpdateClickedSelect (answerOne);
                uiUpdateClicked (answerTwo);

                if (listener != null){
                    listener.onButtonSelected(aTwo);
                    Log.i("LISTENER_BUTTON", String.valueOf(listener));
                }
            }
        });

        return view;
    }

    private void uiUpdateClicked(Button b) {
        b.setTextColor(Color.rgb(255, 255, 255));
        b.setBackgroundColor(Color.rgb(0, 111, 196));
    }

    private void uiUpdateClickedSelect(Button b) {
        b.setTextColor(Color.rgb(0, 0, 0));
        b.setBackgroundColor(Color.rgb(250, 250, 250));
    }


    public interface FragmentListener {
        void onButtonSelected(Boolean b);
    }

    public FragmentListener getListener() {
        return listener;
    }

    public void setListener(FragmentListener listener) {
        this.listener = listener;
    }

    public String getFormQuestion() {
        return formQuestion;
    }

    public void setFormQuestion(String formQuestion) {
        this.formQuestion = formQuestion;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public int getFormTotalNumber() {
        return formTotalNumber;
    }

    public void setFormTotalNumber(int formTotalNumber) {
        this.formTotalNumber = formTotalNumber;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isaOne() {
        return aOne;
    }

    public void setaOne(boolean aOne) {
        this.aOne = aOne;
    }

    public boolean isaTwo() {
        return aTwo;
    }

    public void setaTwo(boolean aTwo) {
        this.aTwo = aTwo;
    }
}
