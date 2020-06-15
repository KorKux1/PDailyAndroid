package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Subtract extends Fragment {
    private DataScore dataScore = DataScore.getInstance();
    private FragmentListener listener;

    private TextView numberSub;
    private EditText numberResult;
    private int num, index, total_Correct;
    private boolean a, e, i, o, u;
    private boolean isTestFinished;
    private ArrayList<Boolean> total_Correct_List;

    public Subtract() {
        // Required empty public constructor
        listener = null;
        isTestFinished = false;

        total_Correct_List = new ArrayList<Boolean>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subtract, container, false);
        numberSub = view.findViewById(R.id.numberSub);
        numberResult = view.findViewById(R.id.numberResult);
        index = 1;
        num = 100;

        numberSub.setText(Integer.valueOf(num).toString());

//        num = numberResult.getText();
        numberResult.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.i("ENTRA", "ENTRAAAAA");

                if (num - 7 == Integer.valueOf(String.valueOf(numberResult.getText()))) {
                    Log.i("INDEX", Integer.valueOf(index).toString());
                    if (index == 1) {
                        a = true;
                        total_Correct_List.add(a);
                    }
                    if (index == 2) {
                        e = true;
                        total_Correct_List.add(e);
                    }
                    if (index == 3) {
                        i = true;
                        total_Correct_List.add(i);
                    }
                    if (index == 4) {
                        o = true;
                        total_Correct_List.add(o);
                    }
                    if (index == 5) {
                        u = true;
                        total_Correct_List.add(u);

                    }
                    Log.i("BOOLEAN A", String.valueOf(a));
                    Log.i("BOOLEAN E", String.valueOf(e));
                    Log.i("BOOLEAN I", String.valueOf(i));
                    Log.i("BOOLEAN O", String.valueOf(o));
                    Log.i("BOOLEAN U", String.valueOf(u));
                    Log.i("ARRAY", String.valueOf(Integer.valueOf(total_Correct_List.size())));
                }

                if (index == 5) {
                    isTestFinished = true;
                    if (listener != null){
                        listener.onTestFinished(isTestFinished);
                    }

                    if (total_Correct_List.size() >= 4) {
                        total_Correct = 3;
                    }

                    if (total_Correct_List.size() >= 2 && total_Correct_List.size() <= 3) {
                        total_Correct = 2;
                    }
                    if (total_Correct_List.size() == 1) {
                        total_Correct = 1;
                    }

                    if (total_Correct_List.size() == 0) {
                        total_Correct = 0;
                    }

                    dataScore.setMoca_total_Correct(total_Correct);
                    dataScore.setMoca_total_Correct_List(total_Correct_List);
                }
                index += 1;
                num = Integer.valueOf(String.valueOf(numberResult.getText()));
                numberSub.setText(Integer.valueOf(num).toString());
                numberResult.setText("");
                return false;
            }
        });

        if (isTestFinished){
            numberResult.setEnabled(false);
        }
        return view;
    }

    public interface FragmentListener {
        void onTestFinished(boolean b);
    }

    public void setListener(FragmentListener listener) {
        this.listener = listener;
    }
}