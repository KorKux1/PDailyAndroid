package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.R;

public class VideoFragment extends Fragment {

    private VideoView videoView;
    private MediaController mediaController;


    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        videoView = v.findViewById(R.id.videoView);

        mediaController = new MediaController(getActivity());

        videoView.setMediaController(mediaController);

        videoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + R.raw.practica_parkinson_480p);

        videoView.start();

        return v;
    }

}
