package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.gamecontrols.BananaGame;
import co.edu.icesi.pdailyandroid.gamecontrols.WormGame;

public class SupportFragment extends Fragment {

    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_support, container, false);
        v.findViewById(R.id.gusanoButton).setOnClickListener(
                view -> {
                    Intent i = new Intent(getContext(), WormGame.class);
                    startActivity(i);
                }
        );
        v.findViewById(R.id.bananaButton).setOnClickListener(
                view -> {
                    Intent i = new Intent(getContext(), BananaGame.class);
                    startActivity(i);
                }
        );
        return v;
    }


}
