package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.icesi.pdailyandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Subtract extends Fragment {

     TextView numberSub;
     EditText numberResult;
    private int num;

    public Subtract() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subtract, container, false);
        numberSub = view.findViewById(R.id.numberSub);
        numberResult = view.findViewById(R.id.numberResult);

        num = 100;

        numberSub.setText(Integer.valueOf(num).toString());

//        num = numberResult.getText();
        numberResult.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    Log.i("ENTRA", "ENTRAAAAA");
                    num = Integer.valueOf(String.valueOf(numberResult.getText()));
                    numberSub.setText(Integer.valueOf(num).toString());
                    numberResult.setText("");
                return false;
            }
        });
        return view;

    }
}