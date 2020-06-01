package co.edu.icesi.pdailyandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Arrays;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.edu.icesi.pdailyandroid.cognosis.builder.Form;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeA;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeB;
import co.edu.icesi.pdailyandroid.services.App;
import co.edu.icesi.pdailyandroid.util.JsonReaderUtils;

public class FormActivity extends AppCompatActivity {

    private int aSelect;
    private int[] aTotal;
    private int scoreFinal;
    int score;

    private String type;

    private TypeA typeA;
    private TypeB typeB;
    private String json_form;
    private Gson gson;
    private LinearLayout fragmentContainer;
    private int index = 10;
    private Form form;
    private Button previous;
    private Button next;
    private boolean buttonSelect;

    private TextView formDescription;
    private TextView formName;
    private TextView formQNumber;
    private TextView formTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        previous = findViewById(R.id.ButtonPrevious);
        next = findViewById(R.id.ButtonNext);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        formDescription = findViewById(R.id.TextFormDescription);
        formName = findViewById(R.id.TextFormName);
        formQNumber = findViewById(R.id.TextFormQNumber);
        formTotal = findViewById(R.id.TextFormTotal);

        gson = new Gson();

        json_form = JsonReaderUtils.getJsonFromAssets(App.getAppContext(), "pd-nms.json");
        Log.i("data", json_form);

        form = gson.fromJson(json_form, Form.class);

        Log.i("JSON OBJECT", form.toString());
        updateButtons();

        //Cargar por primera vez
        // updateFragmentTypeA(form, index);
        updateFragmentTypeB(form, index);

        updateListener();

        aSelect = 0;
        aTotal = new int[typeB.getFormTotalNumber()];
        Log.i("IINDEEEX", Integer.valueOf(index+1).toString());
        Log.i("FOOOORMM", Integer.valueOf(form.getForm_questions().length).toString());

        //ACCION DE LOS BOTONES
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index - 1;
                updateButtons();
                switch (form.getForm_questions()[index].getQuestion_type()) {
                    case "A":
                        type = "A";
                        updateFragmentTypeA(form, index);
                        break;

                    case "B":
                        type = "B";
                        updateFragmentTypeB(form, index);
                        break;
                }

                if (index == 0) {
                    previous.setVisibility(View.GONE);
                }
                if (index > 0) {
                    previous.setVisibility(View.VISIBLE);
                }
                if (index < form.getForm_questions().length) {
                    next.setVisibility(View.VISIBLE);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (index+1  < form.getForm_questions().length){
                    index = index + 1;
                    score = index;
                }

                scoreEvaluation();
                updateButtons();




                switch (form.getForm_questions()[index].getQuestion_type()) {
                    case "A":
                        type = "A";
                        updateFragmentTypeA(form, index);
                        break;

                    case "B":
                        type = "B";
                        updateFragmentTypeB(form, index);
                        break;
                }

                    if (index > 0) {
                        previous.setVisibility(View.VISIBLE);
                }

            }
        });

    }


    private  void  updateListener(){
//        typeA.setListener(new TypeA.FragmentListener(){
//            @Override
//            public void onButtonSelected(Boolean b) {
//                buttonSelect=b;
//                if (buttonSelect){
//                    next.setEnabled(true);
//                }
//            }
//        });

        typeB.setListener(new TypeB.FragmentListener(){
            @Override
            public void onButtonSelected(Boolean b) {
                buttonSelect=b;
                if (buttonSelect){
                    next.setEnabled(true);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void scoreEvaluation(){
        if (typeA.isaOne()){
            aSelect = 0;
        }
        if (typeA.isaTwo()){
            aSelect = 1;
        }
        if (typeA.isaThree()){
            aSelect = 2;
        }
        if (typeA.isaFour()){
            aSelect = 0;
        }

        if (typeB.isaOne()){
            aSelect = 0;
        }
        if (typeB.isaTwo()){
            aSelect = 1;
        }

        if (score  < form.getForm_questions().length){

            aTotal[score-1] = aSelect;

            if (score == form.getForm_questions().length-1){
                score +=1;
            }
        }

        if (next.getText().equals("Finalizar")){
            aTotal[score-1] = aSelect;
            scoreFinal= Arrays.stream(aTotal).sum();
            Intent intent = new Intent(getBaseContext(), ScoreActivity.class);
            intent.putExtra("EXTRA_SCORE", scoreFinal);
            intent.putExtra("EXTRA_TYPE", type);
            startActivity(intent);

            Log.i("ARRAYYYY", Arrays.toString(aTotal));
            Log.i("SUMAAAAA", Integer.valueOf(scoreFinal).toString());
        }else {
            Log.i("ARRAY", Arrays.toString(aTotal));
            Log.i("SCOOOORE", Integer.valueOf(score).toString());
            Log.i("IINDEEEX", Integer.valueOf(index).toString());
            Log.i("FOOOORMM", Integer.valueOf(form.getForm_questions().length).toString());
        }
    }


    private void updateButtons(){
            next.setEnabled(false);
        if (index == 0) {
            previous.setVisibility(View.GONE);
        }
        if (index + 1 == form.getForm_questions().length) {
            next.setText("Finalizar");
        }
    }


    protected void updateFragmentTypeA(Form form, int index) {
        typeA = new TypeA();
        type = "A";
        formDescription.setText(form.getForm_description());
        formName.setText(form.getForm_name());
        formTotal.setText(Integer.valueOf(form.getForm_questions().length).toString());
        formQNumber.setText(Integer.valueOf(index + 1).toString());
        typeA.setFormQuestion(form.getForm_questions()[index].getQuestion_text());

        typeA.setAnswerOne(form.getForm_questions()[index].getQuestion_options()[0]);
        typeA.setAnswerTwo(form.getForm_questions()[index].getQuestion_options()[1]);
        typeA.setAnswerThree(form.getForm_questions()[index].getQuestion_options()[2]);
        typeA.setAnswerFour(form.getForm_questions()[index].getQuestion_options()[3]);

        typeA.setIndex(index);
        typeA.setFormTotalNumber(form.getForm_questions().length);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, typeA);
        transaction.commit();
        updateListener();
    }

    protected void updateFragmentTypeB(Form form, int index) {
        typeB = new TypeB();
        type = "B";
        formDescription.setText(form.getForm_description());
        formName.setText(form.getForm_name());
        formTotal.setText(Integer.valueOf(form.getForm_questions().length).toString());
        formQNumber.setText(Integer.valueOf(index + 1).toString());
        typeB.setFormQuestion(form.getForm_questions()[index].getQuestion_text());

        typeB.setAnswerOne(form.getForm_questions()[index].getQuestion_options()[0]);
        typeB.setAnswerTwo(form.getForm_questions()[index].getQuestion_options()[1]);

        typeB.setIndex(index);
        typeB.setFormTotalNumber(form.getForm_questions().length);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, typeB);
        transaction.commit();
        updateListener();
    }
}




