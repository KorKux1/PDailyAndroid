package co.edu.icesi.pdailyandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.icesi.pdailyandroid.services.MQTTService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void authenticate(View v){
        Intent i = new Intent(this, DashBoard.class);
        startActivity(i);
    }
}
