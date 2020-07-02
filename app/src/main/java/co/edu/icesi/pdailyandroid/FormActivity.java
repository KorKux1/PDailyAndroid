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

import java.util.Arrays;
import java.util.Date;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.edu.icesi.pdailyandroid.cognosis.builder.Form;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeA;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeB;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeC;
import co.edu.icesi.pdailyandroid.services.App;
import co.edu.icesi.pdailyandroid.util.JsonReaderUtils;

public class FormActivity extends AppCompatActivity {

    DataScore dataScore = DataScore.getInstance();

    private int aSelect;
    private int[] aTotal;
    private int scoreFinal;
    int score;

    private String formSelected;
    private String type, typeForm;

    private TypeA typeA;
    private TypeB typeB;
    private TypeC typeC;

    private String json_form;
    private Gson gson;
    private LinearLayout fragmentContainer;
    private int index = 0;
    private Form form;
    private Button previous;
    private Button next;
    private boolean buttonSelect;

    private TextView formDescription;
    private TextView formName;
    private TextView formQNumber;
    private TextView formTotal;

    private double startTime = System.currentTimeMillis();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        previous = findViewById(R.id.ButtonPrevious);
        next = findViewById(R.id.ButtonNext);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        formDescription = findViewById(R.id.TextFormDescription);
        formName = findViewById(R.id.TextFormName);
        formQNumber = findViewById(R.id.tv_display_number);
        formTotal = findViewById(R.id.TextFormTotal);

        formSelected = getIntent().getStringExtra("EXTRA_FILENAME");

        gson = new Gson();

        switch (formSelected) {
            case "PD-NMS":
                json_form = JsonReaderUtils.getJsonFromAssets(App.getAppContext(), "pd-nms.json");
                break;

            case "PD-CFRS":
                json_form = JsonReaderUtils.getJsonFromAssets(App.getAppContext(), "pd-cfrs.json");
                break;

            case "Congelamiento de la marcha":
                json_form = JsonReaderUtils.getJsonFromAssets(App.getAppContext(), "congelamiento.json");
                break;

            case "PHQ-9":
                json_form = JsonReaderUtils.getJsonFromAssets(App.getAppContext(), "phq-9.json");
                break;
        }

        Log.i("DATA", json_form);
        Log.i("EXTRA_FILENAME", getIntent().getStringExtra("EXTRA_FILENAME"));

        form = gson.fromJson(json_form, Form.class);

        Log.i("JSON_OBJECT", form.toString());
        updateButtons();

        //Cargar por primera vez el cuestionario
        switch (form.getForm_questions()[index].getQuestion_type()) {
            case "A":
                type = "A";
                updateFragmentTypeA(form, index);
                break;

            case "B":
                type = "B";
                updateFragmentTypeB(form, index);
                break;

            case "C":
                type = "C";
                updateFragmentTypeC(form, index);
                break;
        }
//        updateFragmentTypeA(form, index);
//        updateFragmentTypeB(form, index);

        updateListener();

        aSelect = 0;
        if (typeA != null) {
            aTotal = new int[typeA.getFormTotalNumber()];
        }
        if (typeB != null) {
            aTotal = new int[typeB.getFormTotalNumber()];
        }
        if (typeC != null) {
            aTotal = new int[typeC.getFormTotalNumber()];
        }

        Log.i("IINDEEEX", Integer.valueOf(index + 1).toString());
        Log.i("FOOOORMM", Integer.valueOf(form.getForm_questions().length).toString());

        //ACCION DE LOS BOTONES
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index - 1;
                updateButtons();

                //Carga la siguiente o anterior pregunta dependoendo de su tipo
                switch (form.getForm_questions()[index].getQuestion_type()) {
                    case "A":
                        type = "A";
                        updateFragmentTypeA(form, index);
                        break;

                    case "B":
                        type = "B";
                        updateFragmentTypeB(form, index);
                        break;

                    case "C":
                        type = "C";
                        updateFragmentTypeC(form, index);
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
                //Sumatoria del index del cual depende la posicion del arreglo y la suma total de Score
                if (index + 1 < form.getForm_questions().length) {
                    index = index + 1;
                    score = index;
                }

                scoreEvaluation();
                updateButtons();

                //Carga la siguiente o anterior pregunta dependoendo de su tipo
                switch (form.getForm_questions()[index].getQuestion_type()) {
                    case "A":
                        type = "A";
                        updateFragmentTypeA(form, index);
                        break;

                    case "B":
                        type = "B";
                        updateFragmentTypeB(form, index);
                        break;

                    case "C":
                        type = "C";
                        updateFragmentTypeC(form, index);
                        break;
                }

                if (index > 0) {
                    previous.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    // Actualiza en tiempo real las modificaciones del fragment
    private void updateListener() {
        if (typeA != null) {
            typeA.setListener(new TypeA.FragmentListener() {
                @Override
                public void onButtonSelected(Boolean b) {
                    buttonSelect = b;
                    if (buttonSelect) {
                        next.setEnabled(true);
                    }
                }
            });
        }

        if (typeB != null) {
            typeB.setListener(new TypeB.FragmentListener() {
                @Override
                public void onButtonSelected(Boolean b) {
                    buttonSelect = b;
                    if (buttonSelect) {
                        next.setEnabled(true);
                    }
                }
            });
        }

        if (typeC != null) {
            typeC.setListener(new TypeC.FragmentListener() {
                @Override
                public void onButtonSelected(Boolean b) {
                    buttonSelect = b;
                    if (buttonSelect) {
                        next.setEnabled(true);
                    }
                }
            });
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    // condiciones para evaluar el Score segun el tipo de prueba o pregunta
    private void scoreEvaluation() {

        switch (formSelected) {
            case "PD-NMS":
                if (typeB.isaOne()) {
                    aSelect = 1;
                }
                if (typeB.isaTwo()) {
                    aSelect = 0;
                }
                break;

            case "PD-CFRS":
                if (typeA.isaOne()) {
                    aSelect = 0;
                }
                if (typeA.isaTwo()) {
                    aSelect = 1;
                }
                if (typeA.isaThree()) {
                    aSelect = 2;
                }
                if (typeA.isaFour()) {
                    aSelect = 0;
                }
                break;

            case "Congelamiento de la marcha":
                if (typeC.isaOne()) {
                    aSelect = 0;
                }
                if (typeC.isaTwo()) {
                    aSelect = 1;
                }
                if (typeC.isaThree()) {
                    aSelect = 2;
                }
                if (typeC.isaFour()) {
                    aSelect = 3;
                }
                if (typeC.isaFive()) {
                    aSelect = 4;
                }
                break;

            case "PHQ-9":
                if (typeA.isaOne()) {
                    aSelect = 0;
                }
                if (typeA.isaTwo()) {
                    aSelect = 1;
                }
                if (typeA.isaThree()) {
                    aSelect = 2;
                }
                if (typeA.isaFour()) {
                    aSelect = 3;
                }
                break;
        }


        if (!formSelected.equals("Congelamiento de la marcha")) {
            if (score < form.getForm_questions().length) {

                aTotal[score - 1] = aSelect;

                if (score == form.getForm_questions().length - 1) {
                    score += 1;
                }
            }
        } else {
            aTotal[0] = aSelect;
        }


        if (next.getText().equals("Finalizar")) {
            if (!formSelected.equals("Congelamiento de la marcha")) {
                aTotal[score - 1] = aSelect;
                scoreFinal = Arrays.stream(aTotal).sum();
                Log.i("ARRAYYYY", Arrays.toString(aTotal));
                Log.i("SUMAAAAA", Integer.valueOf(scoreFinal).toString());
            } else {
                aTotal[0] = aSelect;
                scoreFinal = Arrays.stream(aTotal).sum();
            }

            Date d = new Date();

            switch (formSelected) {
                case "PD-NMS":
                    dataScore.setForm_score_pdnms(scoreFinal);
                    dataScore.setForm_answers_pdnms(aTotal);
                    dataScore.setForm_isFinished_pdnms(true);
                    dataScore.setForm_date_response_pdnms(d);
                    dataScore.setForm_time_response_pdnms_total((System.currentTimeMillis() - startTime) / 1000);
                    Log.i("dataScore_score", String.valueOf(dataScore.getForm_score_pdnms()));
                    Log.i("dataScore_answers", String.valueOf(dataScore.getForm_answers_pdnms()));
                    Log.i("dataScore_time", String.valueOf(dataScore.getForm_time_response_pdnms_total()));
                    typeForm = "PD-NMS";
                    break;

                case "PD-CFRS":
                    dataScore.setForm_score_pdcfrs(scoreFinal);
                    dataScore.setForm_answers_pdcfrs(aTotal);
                    dataScore.setForm_isFinished_pdcfrs(true);
                    dataScore.setForm_date_response_pdcfrs(d);
                    dataScore.setForm_time_response_pdcfrs_total((System.currentTimeMillis() - startTime) / 1000);
                    Log.i("dataScore_score", String.valueOf(dataScore.getForm_score_pdcfrs()));
                    Log.i("dataScore_answers", String.valueOf(dataScore.getForm_answers_pdcfrs()));
                    Log.i("dataScore_time", String.valueOf(dataScore.getForm_time_response_pdcfrs_total()));
                    typeForm = "PD-CFRS";
                    break;

                case "Congelamiento de la marcha":
                    dataScore.setForm_answers_walk(aTotal);
                    dataScore.setForm_score_walk(scoreFinal);
                    dataScore.setForm_isFinished_walk(true);
                    dataScore.setForm_date_response_walk(d);
                    dataScore.setForm_time_response_walk_total((System.currentTimeMillis() - startTime) / 1000);
                    Log.i("dataScore_score", String.valueOf(dataScore.getForm_score_walk()));
                    Log.i("dataScore_answers", String.valueOf(dataScore.getForm_answers_walk()));
                    Log.i("dataScore_time", String.valueOf(dataScore.getForm_time_response_walk_total()));
                    typeForm = "Congelamiento de la marcha";
                    break;

                case "PHQ-9":
                    dataScore.setForm_answers_phq9(aTotal);
                    dataScore.setForm_score_phq9(scoreFinal);
                    dataScore.setForm_isFinished_phq9(true);
                    dataScore.setForm_date_response_phq9(d);
                    dataScore.setForm_time_response_phq9_total((System.currentTimeMillis() - startTime) / 1000);
                    Log.i("dataScore_score", String.valueOf(dataScore.getForm_score_phq9()));
                    Log.i("dataScore_answers", String.valueOf(dataScore.getForm_answers_phq9()));
                    Log.i("dataScore_time", String.valueOf(dataScore.getForm_time_response_phq9_total()));
                    typeForm = "PHQ-9";
                    break;
            }

            Intent intent = new Intent(getBaseContext(), ScoreActivity.class);
            intent.putExtra("EXTRA_SCORE", scoreFinal);
            intent.putExtra("EXTRA_TYPE", typeForm);
            startActivity(intent);


        } else {
            Log.i("ARRAY", Arrays.toString(aTotal));
            Log.i("SCOOOORE", Integer.valueOf(score).toString());
        }
    }


    private void updateButtons() {
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

    protected void updateFragmentTypeC(Form form, int index) {
        typeC = new TypeC();
        type = "C";
        formDescription.setText(form.getForm_description());
        formName.setText(form.getForm_name());
        formTotal.setText(Integer.valueOf(form.getForm_questions().length).toString());
        formQNumber.setText(Integer.valueOf(index + 1).toString());
        typeC.setFormQuestion(form.getForm_questions()[index].getQuestion_text());

        typeC.setAnswerOne(form.getForm_questions()[index].getQuestion_options()[0]);
        typeC.setAnswerTwo(form.getForm_questions()[index].getQuestion_options()[1]);
        typeC.setAnswerThree(form.getForm_questions()[index].getQuestion_options()[2]);
        typeC.setAnswerFour(form.getForm_questions()[index].getQuestion_options()[3]);
        typeC.setAnswerFive(form.getForm_questions()[index].getQuestion_options()[4]);

        typeC.setIndex(index);
        typeC.setFormTotalNumber(form.getForm_questions().length);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, typeC);
        transaction.commit();
        updateListener();
    }
}




