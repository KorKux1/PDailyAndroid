package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import co.edu.icesi.pdailyandroid.R;

import static android.app.Activity.RESULT_OK;

public class Speech extends Fragment {
    private static final int REQ_CODE_SPEECH_INPUT = 100;

    Button btn_speech_record;

    ListView lv_display_words;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> speech_words;

    FragmentListener listener;

    private final String speech_stimulus_words = "MP";
    private String speech_stimulus;

    private Boolean isFirsTime = true;

    private Random speech_random = new Random();

    public Speech() {
        // Required empty public constructor
        speech_words = new ArrayList<>();

        listener = null;

        speech_stimulus = String.valueOf(speech_stimulus_words.charAt(speech_random.nextInt(speech_stimulus_words.length())));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speech, container, false);

        btn_speech_record = view.findViewById(R.id.btn_speech_record);
        lv_display_words = view.findViewById(R.id.lv_speech_words);

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, speech_words);

        lv_display_words.setAdapter(arrayAdapter);

        btn_speech_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirsTime) {
                    isFirsTime = false;
                    new CountDownTimer(60000, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            listener.onTimerChange(String.valueOf(millisUntilFinished / 1000));
                        }

                        @Override
                        public void onFinish() {
                            listener.onTimerChange("Meh");
                            btn_speech_record.setEnabled(false);
                        }
                    }.start();
                }
                startSpeechRecognition();
            }
        });

        return view;
    }

    private void startSpeechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Recuerda, pronuncia palabras que inicien con la letra" + " " + speech_stimulus);

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    Log.i("VOICE", String.valueOf(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)));
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String[] wordsTemp = result.get(0).split(" ");

                    for (String s : wordsTemp) {
                        if (s.startsWith(speech_stimulus.toLowerCase()) || s.startsWith(speech_stimulus)) {
                            String cast = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                            Log.i("CAST", cast);
                            speech_words.add(cast);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
            break;
        }
    }

    public interface FragmentListener {
        void onTimerChange(String msg);
    }

    public void setListener(FragmentListener listener) {
        this.listener = listener;
    }
}
