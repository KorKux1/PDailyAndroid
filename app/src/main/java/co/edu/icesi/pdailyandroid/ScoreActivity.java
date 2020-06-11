package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class ScoreActivity extends AppCompatActivity {

    private int score;
    private String type;

    private Button chao;

    private TextView scoreNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score = getIntent().getIntExtra("EXTRA_SCORE", score);
        type = getIntent().getStringExtra("EXTRA_TYPE");

        chao = findViewById(R.id.chao);

//        Log.i("EXTRA_TYPE", type);

        scoreNumber = findViewById(R.id.ScoreNumber);
        scoreNumber.setText(Integer.valueOf(score).toString());

        chao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SelectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void scoreEvaluation(){
        switch (type){
            case "A":


        }
    }
}
