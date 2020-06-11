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

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeA extends Fragment {
    private FragmentListener listener;
    private String formQuestion;

    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;

    private int formTotalNumber;
    private int index;
    private boolean aOne, aTwo, aThree, aFour;

    public TypeA() {
        aOne = false;
        aTwo = false;
        aThree = false;
        aFour = false;

        this.listener = null;
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

                uiUpdateClickedSelect(answerTwo);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerThree);
                uiUpdateClicked(answerFour);

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

                uiUpdateClickedSelect(answerThree);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerFour);

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

                uiUpdateClickedSelect(answerFour);
                uiUpdateClicked(answerOne);
                uiUpdateClicked(answerTwo);
                uiUpdateClicked(answerThree);
                if (listener!= null){
                    listener.onButtonSelected(aFour);
                    Log.i("listeneeeeeeeeeeeeeer", String.valueOf(listener));
                }
            }
        });


        return view;
    }


    // cambio de color de boton seleccionado
    private void uiUpdateClickedSelect(Button a){
        a.setTextColor(Color.rgb(255, 255, 255));
        a.setBackgroundColor(Color.rgb(0, 188, 209));
    }

    //cambio de boton deseleccionado
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

    public int getFormTotalNumber() {
        return formTotalNumber;
    }

    public boolean isaOne() {
        return aOne;
    }

    public boolean isaTwo() {
        return aTwo;
    }

    public boolean isaThree() {
        return aThree;
    }

    public boolean isaFour() {
        return aFour;
    }

    public void setListener(FragmentListener listener) {
        this.listener = listener;
    }

    public interface FragmentListener {
         void onButtonSelected(Boolean b);

    }

}
