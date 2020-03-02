package co.edu.icesi.pdailyandroid.cognosis.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import co.edu.icesi.pdailyandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeA extends Fragment {

    private String text;
    private String ideal;

    public TypeA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_type_a, container, false);
        EditText pregunta1 = view.findViewById(R.id.pregunta1);
        EditText pregunta2 = view.findViewById(R.id.pregunta2);

        pregunta1.setText(this.text);
        pregunta2.setText(this.ideal);

        return view;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIdeal(String ideal) {
        this.ideal = ideal;
    }
}
