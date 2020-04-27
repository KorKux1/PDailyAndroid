package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.edu.icesi.pdailyandroid.cognosis.builder.Form;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeA;
import co.edu.icesi.pdailyandroid.services.App;
import co.edu.icesi.pdailyandroid.util.JsonReaderUtils;

public class FormActivity extends AppCompatActivity {

    private int aSelect;
    private int[] aTotal;


    private TypeA typeA;
    private String json_form;
    private Gson gson;
    private  LinearLayout fragmentContainer;
    private int index = 0;
    private Form form;
    private Button previous;
    private  Button next;

    private TextView formDescription;
    private TextView formName;
    private TextView formQNumber;
    private  TextView formTotal;

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

        json_form = JsonReaderUtils.getJsonFromAssets(App.getAppContext(), "pd-cfrs.json");
        Log.i("data", json_form);

        form = gson.fromJson(json_form, Form.class);

        Log.i("JSON OBJECT", form.toString());
        updateButtons();

        //Cargar por primera vez
        updateFragmentTypeA(form, index);

        aSelect = 0;
        aTotal = new int[typeA.getFormTotalNumber()];



        //ACCION DE LOS BOTONES
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index - 1;
                updateButtons();
                Log.i("yeeeeeeeeeeeeees", Integer.valueOf(index).toString());
                switch (form.getForm_questions()[index].getQuestion_type()) {
                    case "A":
                        updateFragmentTypeA(form, index);
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
            @Override
            public void onClick(View v) {
                index = index + 1;
                socreEvaluation();
                updateButtons();
                Log.i("yeeeeeeeeeeeeees", Integer.valueOf(index).toString());

                switch (form.getForm_questions()[index].getQuestion_type()) {
                    case "A":
                        updateFragmentTypeA(form, index);
                        break;
                }
                if(typeA.isaOne() || typeA.isaTwo() || typeA.isaThree() || typeA.isaFour()){
                    if (index > 0) {
                        previous.setVisibility(View.VISIBLE);
                    }
                    if (index + 1 == form.getForm_questions().length) {
                        next.setVisibility(View.GONE);
                    }
                } else {
                    next.setEnabled(false);
                }

                Log.i("FFFF","KKKKKKKKKKKK");

            }
        });

    }

    public void setNext(Button next) {
        this.next = next;
    }

    private void socreEvaluation(){
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

        aTotal[index-1] = aSelect;

        Log.i("SELECT", Integer.valueOf(aSelect).toString());
        Log.i("ARRAY", Arrays.toString(aTotal));
    }


    private void updateButtons(){
        next.setEnabled(false);
        if (index == 0) {
            previous.setVisibility(View.GONE);
        }
        if (index + 1 == form.getForm_questions().length) {
            next.setVisibility(View.GONE);
        }
    }


    protected void updateFragmentTypeA(Form form, int index) {
        typeA = new TypeA();
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

    }





}
