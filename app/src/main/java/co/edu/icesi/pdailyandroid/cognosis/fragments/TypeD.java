package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.icesi.pdailyandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypeD#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TypeD extends Fragment {
//    private FragmentListener listener;
    private String formQuestion;

    private String answerOne;
    private String answerTwo;

    private int formTotalNumber;
    private int index;
    private boolean aOne, aTwo;

    public TypeD() {
        aOne = false;
        aTwo = false;

//        this.listener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type_d, container, false);
    }
}
