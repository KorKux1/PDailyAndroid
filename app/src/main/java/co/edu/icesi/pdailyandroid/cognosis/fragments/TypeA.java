package co.edu.icesi.pdailyandroid.cognosis.fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.time.temporal.Temporal;
import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.cognosis.temporal.TestTemporal;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeA extends Fragment {

//    private String text;
//    private String ideal;

    private String formQuestion;

    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;

    private int aSelect;
    private ArrayList<Integer> aTotal;

    private int formTotalNumber;
    private int index;
    private boolean aOne, aTwo, aThree, aFour;

    public TypeA() {
        // Required empty public constructor
        aSelect = 0;
        aTotal = new ArrayList<Integer>();
        aOne = false;
        aTwo = false;
        aThree = false;
        aFour = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_a, container, false);
//        EditText pregunta1 = view.findViewById(R.id.pregunta1);
//        EditText pregunta2 = view.findViewById(R.id.pregunta2);

//        pregunta1.setText(this.text);
//        pregunta2.setText(this.ideal);

        TextView formQuestion = view.findViewById(R.id.TextFormQuestion);

        Button answerOne = view.findViewById(R.id.ButtonAnswerOne);
        Button answerTwo = view.findViewById(R.id.ButtonAnswerTwo);
        Button answerThree = view.findViewById(R.id.ButtonAnswerThree);
        Button answerFour = view.findViewById(R.id.ButtonAnswerFour);

        formQuestion.setText(this.formQuestion);

        answerOne.setText(this.answerOne);
        answerTwo.setText(this.answerTwo);
        answerThree.setText(this.answerThree);
        answerFour.setText(this.answerFour);


        //Por ejemplo pueden almacenar la respuesta usando el objeto singleton
        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//_______________________________________________________________________________________________________________________________________________________________________
//                TestTemporal.getInstance().getAnswers().add(index, answerOne.getText().toString());
                //Aquí pueden ir insertando las respuestas, usando el índice deben controlar que
                //los índices concuerdan con las respuestas

                //Con esto miran en la consola si efectivamente las respuestas se almacenan
//                for (int i = 0; i < TestTemporal.getInstance().getAnswers().size(); i++) {
////                    Log.e(">>>[" + i + "]", "" + TestTemporal.getInstance().getAnswers().get(i));
////                }
//_______________________________________________________________________________________________________________________________________________________________________

                aOne = true;
                aTwo = false;
                aThree = false;
                aFour = false;

                uiUpdateClickedSelect(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerThree);
                uiUpdateClicked(answerFour);

            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aOne = false;
                aTwo = true;
                aThree = false;
                aFour = false;

                uiUpdateClickedSelect(answerTwo);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerThree);
                uiUpdateClicked(answerFour);
            }
        });

        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aOne = false;
                aTwo = false;
                aThree = true;
                aFour = false;

                uiUpdateClickedSelect(answerThree);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerFour);
            }
        });


        answerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aOne = false;
                aTwo = false;
                aThree = false;
                aFour = true;

                uiUpdateClickedSelect(answerFour);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerThree);
            }
        });


        return view;
    }

    //Lógica de la respues correcta o seleccionada

    private void answerSelect() {

    }

    private void uiUpdateClickedSelect(Button a){
            a.setTextColor(Color.rgb(255, 255, 255));
            a.setBackgroundColor(Color.rgb(0, 188, 209));
    }

    private void uiUpdateClicked(Button a){
            a.setTextColor(Color.rgb(0, 0, 0));
            a.setBackgroundColor(Color.rgb(250, 250, 250));
    }



    public void setFormQuestion(String formQuestion) {
        this.formQuestion = formQuestion;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setFormTotalNumber(int formTotalNumber) {
        this.formTotalNumber = formTotalNumber;
    }
}
