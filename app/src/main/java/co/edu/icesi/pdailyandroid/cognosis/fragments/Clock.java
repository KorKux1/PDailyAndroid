package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.cognosis.canvas.ClockDraw;

public class Clock extends Fragment {
    ClockDraw clockDraw;

    public Clock() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_clock, container, false);

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.canvas);
        linearLayout.addView(clockDraw = new ClockDraw(getActivity()));

        return rootView;
    }
}
