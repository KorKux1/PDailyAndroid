package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

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
        tv_score_time_total = findViewById(R.id.firstSectionTimeNumber);
        tv_score_time_average = findViewById(R.id.firstSectionTimeProNumber);

        int minutes, minutesAvg;
        int seconds, secondsAvg;

        switch (type) {
            case "PD-NMS":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_pdnms()).toString());
                minutes = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getForm_time_response_pdnms_total());
                seconds = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getForm_time_response_pdnms_total());
                if (minutes > 0) {
                    tv_score_time_total.setText(minutes + " Min" + "  " + seconds + " Seg");
                } else {
                    tv_score_time_total.setText(seconds + " Seg");
                }

                minutesAvg = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getForm_time_response_pdnms_total() / dataScore.getForm_answers_pdnms().length);
                secondsAvg = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getForm_time_response_pdnms_total() / dataScore.getForm_answers_pdnms().length);
                if (minutesAvg > 0) {
                    tv_score_time_average.setText(minutesAvg + " Min" + "  " + secondsAvg + " Seg");
                } else {
                    tv_score_time_average.setText(secondsAvg + " Seg");
                }
                break;

            case "PD-CFRS":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_pdcfrs()).toString());
                minutes = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getForm_time_response_pdcfrs_total());
                seconds = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getForm_time_response_pdcfrs_total());
                if (minutes > 0) {
                    tv_score_time_total.setText(minutes + " Min" + "  " + seconds + " Seg");
                } else {
                    tv_score_time_total.setText(seconds + " Seg");
                }

                minutesAvg = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getForm_time_response_pdcfrs_total() / dataScore.getForm_answers_pdcfrs().length);
                secondsAvg = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getForm_time_response_pdcfrs_total() / dataScore.getForm_answers_pdcfrs().length);
                if (minutesAvg > 0) {
                    tv_score_time_average.setText(minutesAvg + " Min" + "  " + secondsAvg + " Seg");
                } else {
                    tv_score_time_average.setText(secondsAvg + " Seg");
                }
                break;

            case "Congelamiento de la marcha":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_walk()).toString());
                minutes = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getForm_time_response_walk_total());
                seconds = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getForm_time_response_walk_total());
                if (minutes > 0) {
                    tv_score_time_total.setText(minutes + " Min" + "  " + seconds + " Seg");
                } else {
                    tv_score_time_total.setText(seconds + " Seg");
                }

                minutesAvg = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getForm_time_response_walk_total() / dataScore.getForm_answers_walk().length);
                secondsAvg = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getForm_time_response_walk_total() / dataScore.getForm_answers_walk().length);
                if (minutesAvg > 0) {
                    tv_score_time_average.setText(minutesAvg + " Min" + "  " + secondsAvg + " Seg");
                } else {
                    tv_score_time_average.setText(secondsAvg + " Seg");
                }
                break;

            case "PHQ-9":
                scoreNumber.setText(Integer.valueOf(dataScore.getForm_score_phq9()).toString());
                minutes = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getForm_time_response_phq9_total());
                seconds = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getForm_time_response_phq9_total());
                if (minutes > 0) {
                    tv_score_time_total.setText(minutes + " Min" + "  " + seconds + " Seg");
                } else {
                    tv_score_time_total.setText(seconds + " Seg");
                }

                minutesAvg = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getForm_time_response_phq9_total() / dataScore.getForm_answers_phq9().length);
                secondsAvg = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getForm_time_response_phq9_total() / dataScore.getForm_answers_phq9().length);
                if (minutesAvg > 0) {
                    tv_score_time_average.setText(minutesAvg + " Min" + "  " + secondsAvg + " Seg");
                } else {
                    tv_score_time_average.setText(secondsAvg + " Seg");
                }
                break;

            case "MoCA":
                break;
        }


        chao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SelectionActivity.class);
                startActivity(intent);
            }
        });
    }

}
