package co.edu.icesi.pdailyandroid.cognosis.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.cognosis.canvas.ClockDraw;

public class Clock extends Fragment {
    private final Handler handler = new Handler();
    ClockDraw clockDraw;
    FragmentListener listener;

    public Clock() {
        // Required empty public constructor
        listener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_clock, container, false);

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.canvas);
        linearLayout.addView(clockDraw = new ClockDraw(getActivity()));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onScoreUpdated(true);
            }
        },  3000);

        return rootView;
    }

    public interface FragmentListener {
        void onScoreUpdated(Boolean b);
    }

    public void setListener(FragmentListener listener) {
        this.listener = listener;
    }
}
