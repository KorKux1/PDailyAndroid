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

import java.lang.reflect.Type;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.edu.icesi.pdailyandroid.cognosis.builder.Form;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeA;
import co.edu.icesi.pdailyandroid.services.App;
import co.edu.icesi.pdailyandroid.util.JsonReaderUtils;

public class FormActivity extends AppCompatActivity {

    String json_form = JsonReaderUtils.getJsonFromAssets(App.getAppContext(), "pd-cfrs.json");
    Gson gson = new Gson();
    Type form_type = new TypeToken<Form>() {
    }.getType();
    LinearLayout fragmentContainer;
    int index = 0;
    Form form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Log.i("data", json_form);

        form = gson.fromJson(json_form, form_type);

        Log.i("JSON OBJECT", form.toString());


//        switch (form.getForm_questions()[index].getQuestion_type()) {
//            case "A":
//
//                break;
//        }
        updateFragmentTypeA(form, index);
    }


    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_form);
        updateFragmentTypeA(form, index);
    }


    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_form);

        Button previous = findViewById(R.id.ButtonPrevious);
        Button next = findViewById(R.id.ButtonNext);


        if (index == 0) {
            previous.setVisibility(View.GONE);
        }


        if (index + 1 == form.getForm_questions().length) {
            next.setVisibility(View.GONE);
        }

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = index - 1;
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
                Log.i("yeeeeeeeeeeeeees", Integer.valueOf(index).toString());

                switch (form.getForm_questions()[index].getQuestion_type()) {
                    case "A":
                        updateFragmentTypeA(form, index);
                        break;
                }
                if (index > 0) {
                    previous.setVisibility(View.VISIBLE);
                }
                if (index + 1 == form.getForm_questions().length) {
                    next.setVisibility(View.GONE);
                }
            }
        });
    }

    protected void updateFragmentTypeA(Form form, int index) {
        fragmentContainer = findViewById(R.id.fragmentContainer);
        TextView formDescription = findViewById(R.id.TextFormDescription);
        TextView formName = findViewById(R.id.TextFormName);
        TextView formQNumber = findViewById(R.id.TextFormQNumber);
        TextView formTotal = findViewById(R.id.TextFormTotal);

        TypeA typeA = new TypeA();
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
