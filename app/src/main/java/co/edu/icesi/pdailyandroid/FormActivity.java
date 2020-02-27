package co.edu.icesi.pdailyandroid;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import co.edu.icesi.pdailyandroid.cognosis.builder.Form;
import co.edu.icesi.pdailyandroid.util.JsonReaderUtils;

public class FormActivity extends AppCompatActivity {

    String json_form = JsonReaderUtils.getJsonFromAssets(getApplicationContext(),"pd-cfrs.json");
    Gson gson = new Gson();
    Type form_type = new TypeToken<Form>() { }.getType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Log.i("data", json_form);

        Form form = gson.fromJson(json_form, form_type);

        Log.i("JSON OBJECT", form.toString());
    }
}
