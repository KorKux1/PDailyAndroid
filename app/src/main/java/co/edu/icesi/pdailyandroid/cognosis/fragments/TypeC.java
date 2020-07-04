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
 */
public class TypeC extends Fragment {

    private  FragmentListener listener;
    private String formQuestion;

    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String answerFive;

    private int formTotalNumber;
    private int index;
    private boolean aOne, aTwo, aThree, aFour, aFive;

    public TypeC() {
        // Required empty public constructor
        aOne = false;
        aTwo = false;
        aThree = false;
        aFour = false;
        aFive = false;

        this.listener = null;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_type_c, container, false);

        TextView formQuestion = view.findViewById(R.id.TextFormQuestion);

        Button answerOne = view.findViewById(R.id.ButtonAnswerOne);
        Button answerTwo = view.findViewById(R.id.ButtonAnswerTwo);
        Button answerThree = view.findViewById(R.id.ButtonAnswerThree);
        Button answerFour = view.findViewById(R.id.ButtonAnswerFour);
        Button answerFive = view.findViewById(R.id.ButtonAnswerFive);

        formQuestion.setText(this.formQuestion);

        answerOne.setText(this.answerOne);
        answerTwo.setText(this.answerTwo);
        answerThree.setText(this.answerThree);
        answerFour.setText(this.answerFour);
        answerFive.setText(this.answerFive);

        //Por ejemplo pueden almacenar la respuesta usando el objeto singleton
        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aOne = true;
                aTwo = false;
                aThree = false;
                aFour = false;
                aFive = false;

                uiUpdateClickedSelect(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerThree);
                uiUpdateClicked(answerFour);
                uiUpdateClicked(answerFive);


                if (listener!= null){
                    listener.onButtonSelected(aOne);
                    Log.i("listeneeeeeeeeeeeeeer", String.valueOf(listener));
                }

            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aOne = false;
                aTwo = true;
                aThree = false;
                aFour = false;
                aFive = false;

                uiUpdateClickedSelect(answerTwo);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerThree);
                uiUpdateClicked(answerFour);
                uiUpdateClicked(answerFive);

                if (listener!= null){
                    listener.onButtonSelected(aTwo);
                    Log.i("listeneeeeeeeeeeeeeer", String.valueOf(listener));
                }
            }
        });


        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aOne = false;
                aTwo = false;
                aThree = true;
                aFour = false;
                aFive = false;

                uiUpdateClickedSelect(answerThree);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerFour);
                uiUpdateClicked(answerFive);

                if (listener!= null){
                    listener.onButtonSelected(aThree);
                    Log.i("listeneeeeeeeeeeeeeer", String.valueOf(listener));
                }
            }
        });


        answerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aOne = false;
                aTwo = false;
                aThree = false;
                aFour = true;
                aFive = false;


                uiUpdateClickedSelect(answerFour);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerThree);
                uiUpdateClicked(answerFive);


                if (listener!= null){
                    listener.onButtonSelected(aFour);
                    Log.i("listeneeeeeeeeeeeeeer", String.valueOf(listener));
                }
            }
        });

        answerFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aOne = false;
                aTwo = false;
                aThree = false;
                aFour = false;
                aFive = true;


                uiUpdateClickedSelect(answerFive);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerThree);
                uiUpdateClicked(answerFour);


                if (listener!= null){
                    listener.onButtonSelected(aFive);
                    Log.i("listeneeeeeeeeeeeeeer", String.valueOf(listener));
                }
            }
        });

        return view;
    }

    // cambio de color de boton seleccionado
    private void uiUpdateClickedSelect(Button a){
        a.setTextColor(Color.rgb(255, 255, 255));
        a.setBackgroundResource(R.drawable.round_corner_marcha);
    }

    //cambio de boton deseleccionado
    private void uiUpdateClicked(Button a){
        a.setTextColor(Color.rgb(0, 0, 0));
        a.setBackgroundResource(R.drawable.round_corner);
    }


    public FragmentListener getListner() {
        return listener;
    }

    public void setListner(FragmentListener listner) {
        this.listener = listner;
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

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public String getAnswerFive() {
        return answerFive;
    }

    public void setAnswerFive(String answerFive) {
        this.answerFive = answerFive;
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

    public boolean isaThree() {
        return aThree;
    }

    public void setaThree(boolean aThree) {
        this.aThree = aThree;
    }

    public boolean isaFour() {
        return aFour;
    }

    public void setaFour(boolean aFour) {
        this.aFour = aFour;
    }

    public boolean isaFive() {
        return aFive;
    }

    public void setaFive(boolean aFive) {
        this.aFive = aFive;
    }


    public void setListener(TypeC.FragmentListener listener) {
        this.listener = listener;
    }


    public interface FragmentListener {
        void onButtonSelected(Boolean b);

    }
}