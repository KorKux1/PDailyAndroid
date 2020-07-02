package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

public class ScoreActivity extends AppCompatActivity {

    DataScore dataScore = DataScore.getInstance();

    private String type;

    private Button chao;

    private TextView scoreNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        type = getIntent().getStringExtra("EXTRA_TYPE");

        chao = findViewById(R.id.chao);

//        Log.i("EXTRA_TYPE", type);

        scoreNumber = findViewById(R.id.firstSection);

        switch (type) {
            case "PD-NMS":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_pdnms()).toString());
                break;

            case "PD-CFRS":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_pdcfrs()).toString());
                break;

            case "Congelamiento de la marcha":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_walk()).toString());
                break;

            case "PHQ-9":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_phq9()).toString());
                break;

            case "MoCA":
                break;
        }


        chao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SelectionActivity.class);
                startActivity(intent);
            }
        });
    }

}
