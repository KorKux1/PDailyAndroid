package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

import co.edu.icesi.pdailyandroid.R;

import static android.app.Activity.RESULT_OK;

public class Speech extends Fragment {
    private static final int REQ_CODE_SPEECH_INPUT = 100;

    Button btn_speech_record;

    ListView lv_display_words;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> speech_words;

    public Speech() {
        // Required empty public constructor
        speech_words = new ArrayList<>();
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
                startSpeechRecognition();
            }
        });

        return view;
    }

    private void startSpeechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak OUT, BITCH!");

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
                if (requestCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speech_words.add(result.get(0));
                    Log.i("VOICE", result.get(0));
                }
            }
            break;
        }
    }
}
