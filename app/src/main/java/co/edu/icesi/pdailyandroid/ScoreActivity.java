package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

public class ScoreActivity extends AppCompatActivity {

    private int score;
    private String type;

    private TextView scoreNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score = getIntent().getIntExtra("EXTRA_SCORE", score);
        type = getIntent().getStringExtra("EXTRA_TYPE");

        Log.i("EXTRA_TYPE", type);

        scoreNumber = findViewById(R.id.ScoreNumber);
        scoreNumber.setText(Integer.valueOf(score).toString());
    }

    private void scoreEvaluation(){
        switch (type){
            case "A":


        }
    }
}
