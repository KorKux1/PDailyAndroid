package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import co.edu.icesi.pdailyandroid.cognosis.canvas.ClockDraw;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;
import co.edu.icesi.pdailyandroid.cognosis.fragments.Clock;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsA;
import co.edu.icesi.pdailyandroid.cognosis.fragments.WordsB;

public class CaTestActivity extends AppCompatActivity {

    private DataScore dataScore = DataScore.getInstance();

    Clock clock;
    WordsB words_b;
    WordsA words_a;
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


        switch (type) {
            case "WordsA":
                updateFragmentWordsA();
                break;
            case "clock":
                updateFragmentClock();
                break;
            case "WordsB":
                updateFragmentWordsB();
                next.setText("Siguiente");
                break;
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