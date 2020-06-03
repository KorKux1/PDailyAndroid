package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class SelectionActivity extends AppCompatActivity {
    private Button selectionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        selectionButton = findViewById(R.id.SelectionButton);
        selectionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FormActivity.class);
                intent.putExtra("EXTRA_FILENAME", "HAGAMOS DE CUENTA QUE ES UNA PRUEBA QUE SELECCIONA");

                startActivity(intent);
            }
        });
    }
}
