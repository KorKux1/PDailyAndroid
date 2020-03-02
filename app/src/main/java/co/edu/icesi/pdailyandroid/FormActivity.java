package co.edu.icesi.pdailyandroid;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.LinearLayout;

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

    String json_form = JsonReaderUtils.getJsonFromAssets(App.getAppContext(),"pd-cfrs.json");
    Gson gson = new Gson();
    Type form_type = new TypeToken<Form>() { }.getType();

    LinearLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Log.i("data", json_form);

        Form form = gson.fromJson(json_form, form_type);

        Log.i("JSON OBJECT", form.toString());

        fragmentContainer = findViewById(R.id.fragmentContainer);


        if( form.getForm_questions()[0].getQuestion_type().equals("A") ) {

            TypeA typeA = new TypeA();
            typeA.setText(form.getForm_questions()[0].getQuestion_text());
            typeA.setIdeal(form.getForm_questions()[0].getQuestion_ideal());

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentContainer, typeA);
            transaction.commit();
        }

    }
}
