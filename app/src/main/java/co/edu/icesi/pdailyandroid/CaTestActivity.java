package co.edu.icesi.pdailyandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.edu.icesi.pdailyandroid.cognosis.canvas.ClockDraw;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Clock;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Speech;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsA;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsB;

public class CaTestActivity extends AppCompatActivity {

    private DataScore dataScore = DataScore.getInstance();

    Clock clock;
    WordsB words_b;
    WordsA words_a;
    Speech speech;
    private Boolean isFirstTime;
    private int index;
    String type;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_test);

        next = findViewById(R.id.ButtonNext);
        type = getIntent().getStringExtra("EXTRA_FILENAME");
        isFirstTime = true;

        updateFragmentSpeech();

//        switch (type) {
//            case "WordsACA":
//                updateFragmentWordsA();
//                break;
//            case "clock":
//                updateFragmentClock();
//                break;
//            case "WordsBCA":
//                updateFragmentWordsB();
//                next.setText("Siguiente");
//                break;
//        }

        updateListener();

        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent;

                switch (type) {
                    case "WordsACA":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "clock");
                        startActivity(intent);
                        break;
                    case "clock":
                        intent = new Intent(getBaseContext(), ExplainActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsBCA");
                        startActivity(intent);
                        break;
                    case "WordsBCA":
                        if (index + 1 <= dataScore.getMoca_selection_words().size() - 1) {
                            index += 1;
                            updateFragmentWordsB();
                        }

                        if (next.getText().equals("Finalizar")) {
                            intent = new Intent(getBaseContext(), ScoreActivity.class);
                            intent.putExtra("EXTRA_TYPE", "CAtest");
                            startActivity(intent);
                        }

                        Log.i("AAAAAAA", Integer.valueOf(index).toString());
                        break;
                }
            }
        });

    }

    private void updateListener(){
        if(speech != null){
            speech.setListener(new Speech.FragmentListener() {
                @Override
                public void onTimerChange(String msg) {
                    TextView tv_display_number = findViewById(R.id.tv_display_number);
                    tv_display_number.setText(msg);
                }
            });
        }
    }

    protected void updateFragmentClock(){
        clock = new Clock();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, clock);
        transaction.commit();
    }

    protected void updateFragmentWordsA() {
        words_a = new WordsA();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, words_a);
        transaction.commit();
    }

    protected void updateFragmentSpeech(){
        speech = new Speech();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, speech);
        transaction.commit();
    }

    protected void updateFragmentWordsB() {
        words_b = new WordsB();

        if (isFirstTime) {

            switch (dataScore.getMoca_selector_words()) {

                case 0:
                    dataScore.setMoca_selection_words_noise(words_b.getWords_noise_one());
                    words_b.setAnswer_one_text(words_b.getWords_noise_one().get(index).get(0));
                    words_b.setAnswer_two_text(words_b.getWords_noise_one().get(index).get(1));
                    words_b.setAnswer_three_text(words_b.getWords_noise_one().get(index).get(2));
                    break;

                case 1:
                    dataScore.setMoca_selection_words_noise(words_b.getWords_noise_two());
                    words_b.setAnswer_one_text(words_b.getWords_noise_two().get(index).get(0));
                    words_b.setAnswer_two_text(words_b.getWords_noise_two().get(index).get(1));
                    words_b.setAnswer_three_text(words_b.getWords_noise_two().get(index).get(2));
                    break;

                case 2:
                    dataScore.setMoca_selection_words_noise(words_b.getWords_noise_three());
                    words_b.setAnswer_one_text(words_b.getWords_noise_three().get(index).get(0));
                    words_b.setAnswer_two_text(words_b.getWords_noise_three().get(index).get(1));
                    words_b.setAnswer_three_text(words_b.getWords_noise_three().get(index).get(2));
                    break;
            }

            isFirstTime = false;
        } else {
            words_b.setAnswer_one_text(dataScore.getMoca_selection_words_noise().get(index).get(0));
            words_b.setAnswer_two_text(dataScore.getMoca_selection_words_noise().get(index).get(1));
            words_b.setAnswer_three_text(dataScore.getMoca_selection_words_noise().get(index).get(2));
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, words_b);
        transaction.commit();
    }
}