package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

public class ScoreTestActivity extends AppCompatActivity {
    DataScore dataScore = DataScore.getInstance();

    private String type;

    private Button chao;

    private TextView firstSection, secondSection, thirdSection, fourtSection;
    private TextView firstSectionType, secondSectionType, thirdSectionType, fourtSectionType;
    private TextView firstSectionTitle, secondSectionTitle, thirdSectionTitle, fourtSectionTitle;
    private TextView firstTimeTitle, secondTimeTitle, thirdTimeTitle, fourtTimeTitle;
    private TextView firstSectionTimeNumber, secondSectionTimeNumber, thirdSectionTimeNumber, fourtSectionTimeNumber;
    private TextView firstTimeProTitle, secondTimeProTitle, thirdTimeProTitle, fourtTimeProTitle;
    private TextView firstSectionTimeProNumber, secondSectionTimeProNumber, thirdSectionTimeProNumber, fourtSectionTimeProNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_test);

        type = getIntent().getStringExtra("EXTRA_TYPE");

        chao = findViewById(R.id.chao);

        firstSection = findViewById(R.id.firstSection);
        firstSectionType = findViewById(R.id.firstSectionType);
        firstSectionTitle = findViewById(R.id.firstSectionTitle);
        firstTimeTitle = findViewById(R.id.firstTimeTitle);
        firstSectionTimeNumber = findViewById(R.id.firstSectionTimeNumber);
        firstTimeProTitle = findViewById(R.id.firstTimeProTitle);
        firstSectionTimeProNumber = findViewById(R.id.firstSectionTimeProNumber);

        secondSection = findViewById(R.id.secondSection);
        secondSectionType = findViewById(R.id.secondSectionType);
        secondSectionTitle = findViewById(R.id.secondSectionTitle);
        secondTimeTitle = findViewById(R.id.secondTimeTitle);
        secondSectionTimeNumber = findViewById(R.id.secondSectionTimeNumber);
        secondTimeProTitle = findViewById(R.id.secondTimeProTitle);
        secondSectionTimeProNumber = findViewById(R.id.secondSectionTimeProNumber);

        thirdSection = findViewById(R.id.thirdSection);
        thirdSectionType = findViewById(R.id.thirdSectionType);
        thirdSectionTitle = findViewById(R.id.thirdSectionTitle);
        thirdTimeTitle = findViewById(R.id.thirdTimeTitle);
        thirdSectionTimeNumber = findViewById(R.id.thirdSectionTimeNumber);
        thirdTimeProTitle = findViewById(R.id.thirdTimeProTitle);
        thirdSectionTimeProNumber = findViewById(R.id.thirdSectionTimeProNumber);

        fourtSection = findViewById(R.id.fourtSection);
        fourtSectionType = findViewById(R.id.fourtSectionType);
        fourtSectionTitle = findViewById(R.id.fourtSectionTitle);
        fourtTimeTitle = findViewById(R.id.fourtTimeTitle);
        fourtSectionTimeNumber = findViewById(R.id.fourtSectionTimeNumber);
        fourtTimeProTitle = findViewById(R.id.fourtTimeProTitle);
        fourtSectionTimeProNumber = findViewById(R.id.fourtSectionTimeProNumber);

        int firstMinutes, firstMinutesAvg, firstSeconds, firstSecondsAvg;
        int secondMinutes, secondMinutesAvg, secondSeconds, secondSecondsAvg;
        int thirdMinutes, thirdMinutesAvg, thirdSeconds, thirdSecondsAvg;
        int fourtMinutes, fourtMinutesAvg, fourtSeconds, fourtSecondsAvg;

        switch (type) {
            case "MOCA":
                firstSection.setText(dataScore.getMoca_score_tmt());
                firstSectionType.setText("TMT");
                firstSectionTitle.setText("TMT");
                firstTimeTitle.setText("Tiempo total");
                firstMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getMoca_time_response_tmt_total());
                firstSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getMoca_time_response_tmt_total());
                if (firstMinutes > 0) {
                    firstSectionTimeNumber.setText(firstMinutes + " Min" + "  " + firstSeconds + " Seg");
                } else {
                    firstSectionTimeNumber.setText(firstSeconds + " Seg");
                }
                firstTimeProTitle.setText("Orden");
                firstSectionTimeProNumber.setText(dataScore.getMoca_answers_tmt().toString());


                secondSection.setText(String.valueOf(dataScore.getMoca_score_substract()));
                secondSectionType.setText("Resta");
                secondSectionTitle.setText("Resta");
                secondTimeTitle.setText("Tiempo total");
                secondMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getMoca_time_response_substract_total());
                secondSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getMoca_time_response_substract_total());
                if (secondMinutes > 0) {
                    secondSectionTimeNumber.setText(secondMinutes + " Min" + "  " + secondSeconds + " Seg");
                } else {
                    secondSectionTimeNumber.setText(secondSeconds + " Seg");
                }
                secondTimeProTitle.setText("Tiempo promedio");
                secondMinutesAvg = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getMoca_time_response_substract_total() / dataScore.getMoca_answers_substract().size());
                secondSecondsAvg = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getMoca_time_response_substract_total() / dataScore.getMoca_answers_substract().size());
                if (secondMinutesAvg > 0) {
                    secondSectionTimeProNumber.setText(secondMinutesAvg + " Min" + "  " + secondSecondsAvg + " Seg");
                } else {
                    secondSectionTimeProNumber.setText(secondSecondsAvg + " Seg");
                }


                if (dataScore.getMoca_score_letters() > 0) {
                    thirdSection.setText("Válido");
                } else {
                    thirdSection.setText("No Válido");
                }
                thirdSectionType.setText("Letras");
                thirdSectionTitle.setText("Letras");
                ArrayList<Long> tempTimes = new ArrayList<>();
                long timesSum = 0;
                int timeAvg = 0;
                for (int i = 0; i < dataScore.getMoca_time_response_letters().size(); i++) {
                    if (dataScore.getMoca_time_response_letters().get(i) != 0) {
                        tempTimes.add(dataScore.getMoca_time_response_letters().get(i));
                        timesSum += dataScore.getMoca_time_response_letters().get(i);
                    }
                }
                timeAvg = (int) (timesSum / tempTimes.size());
                thirdTimeTitle.setText("Reacción " + timeAvg + " Ms");
                thirdMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getMoca_time_response_letters_total());
                thirdSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getMoca_time_response_letters_total());
                if (thirdMinutes > 0) {
                    thirdSectionTimeNumber.setText("Tiempo total " + thirdMinutes + " Min" + "  " + thirdSeconds + " Seg");
                } else {
                    thirdSectionTimeNumber.setText("Tiempo total " + thirdSeconds + " Seg");
                }
                ArrayList<Integer> tempTap = new ArrayList<>();
                int tapSum = 0;
                int tapAvg = 0;
                for (int i = 0; i < dataScore.getMoca_tapped_letters().size(); i++) {
                    if (dataScore.getMoca_tapped_letters().get(i) != 0) {
                        tempTap.add(dataScore.getMoca_tapped_letters().get(i));
                        tapSum += dataScore.getMoca_tapped_letters().get(i);
                    }
                }
                tapAvg = tapSum / tempTap.size();
                thirdTimeProTitle.setText("Tap/Reacción " + tapAvg);
                thirdSectionTimeProNumber.setText("Errores " + dataScore.getMoca_mistakes_letters_total());


                fourtSection.setText(dataScore.getMoca_score_words() + "/ " + dataScore.getMoca_selected_words().size());
                fourtSectionType.setText("Palabras");
                fourtSectionTitle.setText("Palabras");
                fourtTimeTitle.setText("Tiempo total ");
                fourtMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(dataScore.getMoca_time_response_words_total());
                fourtSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(dataScore.getMoca_time_response_words_total());
                if (fourtMinutes > 0) {
                    fourtSectionTimeNumber.setText(fourtMinutes + " Min" + "  " + fourtSeconds + " Seg");
                } else {
                    fourtSectionTimeNumber.setText(fourtSeconds + " Seg");
                }
                fourtTimeProTitle.setText("Errores");
                fourtSectionTimeProNumber.setText(String.valueOf(dataScore.getMoca_mistakes_words()));

                break;

            case "CATEST":

                break;

            case "GO":

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