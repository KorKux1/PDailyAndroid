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
    private TextView tv_score_time_total;
    private TextView tv_score_time_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        type = getIntent().getStringExtra("EXTRA_TYPE");

        chao = findViewById(R.id.chao);

//        Log.i("EXTRA_TYPE", type);

        scoreNumber = findViewById(R.id.firstSection);
        tv_score_time_total = findViewById(R.id.tv_score_time_total);
        tv_score_time_average = findViewById(R.id.tv_score_time_average);

        switch (type) {
            case "PD-NMS":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_pdnms()).toString());
                tv_score_time_total.setText(String.valueOf(dataScore.getForm_time_response_pdnms_total()) + " " + "Min");
                tv_score_time_average.setText(String.valueOf(dataScore.getForm_time_response_pdnms_total()/dataScore.getForm_answers_pdnms().length) + " " + "Min/Pregunta");
                break;

            case "PD-CFRS":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_pdcfrs()).toString());
                tv_score_time_total.setText(String.valueOf(dataScore.getForm_time_response_pdcfrs_total()) + " " + "Min");
                tv_score_time_average.setText(String.valueOf(dataScore.getForm_time_response_pdcfrs_total()/dataScore.getForm_answers_pdcfrs().length) + " " + "Min/Pregunta");
                break;

            case "Congelamiento de la marcha":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_walk()).toString());
                tv_score_time_total.setText(String.valueOf(dataScore.getForm_time_response_walk_total()) + " " + "Min");
                tv_score_time_average.setText(String.valueOf(dataScore.getForm_time_response_walk_total()/dataScore.getForm_answers_walk().length) + " " + "Min/Pregunta");
                break;

            case "PHQ-9":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_phq9()).toString());
                tv_score_time_total.setText(String.valueOf(dataScore.getForm_time_response_phq9_total()) + " " + "Min");
                tv_score_time_average.setText(String.valueOf(dataScore.getForm_time_response_phq9_total()/dataScore.getForm_answers_phq9().length) + " " + "Min/Pregunta");
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
