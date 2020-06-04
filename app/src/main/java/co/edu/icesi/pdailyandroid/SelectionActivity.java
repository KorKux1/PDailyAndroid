package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class SelectionActivity extends AppCompatActivity {
    private Button bsOne, bsTwo, bsThree, bsFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        bsOne = findViewById(R.id.ButtonSelectionOne);
        bsTwo = findViewById(R.id.ButtonSelectionTwo);
        bsThree = findViewById(R.id.ButtonSelectionThree);
        bsFour = findViewById(R.id.ButtonSelectionFour);

        bsOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FormActivity.class);
                intent.putExtra("EXTRA_FILENAME", "PD-NMS");

                startActivity(intent);
            }
        });

        bsTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FormActivity.class);
                intent.putExtra("EXTRA_FILENAME", "PD-CFRS");

                startActivity(intent);
            }
        });

        bsThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FormActivity.class);
                intent.putExtra("EXTRA_FILENAME", "PD-Unified FVL");

                startActivity(intent);
            }
        });

        bsFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
    }
}
