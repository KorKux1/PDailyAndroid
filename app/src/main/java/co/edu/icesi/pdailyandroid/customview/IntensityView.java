package co.edu.icesi.pdailyandroid.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import co.edu.icesi.pdailyandroid.R;


public class IntensityView extends Fragment {

    private float initY = 0;

    public IntensityView() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_intensity_view, container, false);

        ImageView faceView = v.findViewById(R.id.faceView);
        ImageView indicatorView = v.findViewById(R.id.indicatorView);
        faceView.setOnTouchListener(
                (view, event) -> {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            initY = event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:


                            float faceHeight = faceView.getHeight();
                            float indicatorHeight = indicatorView.getHeight();
                            float height = v.getHeight();


                            faceView.setY(view.getY() + event.getY() - initY);
                            indicatorView.setY(faceView.getY() + faceHeight / 2 - indicatorHeight / 2);


                            if (faceView.getY() < 0) {
                                faceView.setY(0);
                                indicatorView.setY(faceView.getY() + faceHeight / 2 - indicatorHeight / 2);
                            }
                            if (faceView.getY() > height - faceHeight) {
                                faceView.setY(height - faceHeight);
                                indicatorView.setY(faceView.getY() + faceHeight / 2 - indicatorHeight / 2);
                            }


                            int value = 11 - 1 - (int) (9 * (faceView.getY()+faceHeight / 4) / (height - faceHeight));

                            Log.e(">>>", "" + value);


                            switch (value) {
                                case 1:
                                    indicatorView.setImageResource(R.drawable.n1);
                                    faceView.setImageResource(R.drawable.rostro1);
                                    break;
                                case 2:
                                    indicatorView.setImageResource(R.drawable.n2);
                                    faceView.setImageResource(R.drawable.rostro2);
                                    break;
                                case 3:
                                    indicatorView.setImageResource(R.drawable.n3);
                                    faceView.setImageResource(R.drawable.rostro3);
                                    break;
                                case 4:
                                    indicatorView.setImageResource(R.drawable.n4);
                                    faceView.setImageResource(R.drawable.rostro4);
                                    break;
                                case 5:
                                    indicatorView.setImageResource(R.drawable.n5);
                                    faceView.setImageResource(R.drawable.rostro5);
                                    break;
                                case 6:
                                    indicatorView.setImageResource(R.drawable.n6);
                                    faceView.setImageResource(R.drawable.rostro6);
                                    break;
                                case 7:
                                    indicatorView.setImageResource(R.drawable.n7);
                                    faceView.setImageResource(R.drawable.rostro7);
                                    break;
                                case 8:
                                    indicatorView.setImageResource(R.drawable.n8);
                                    faceView.setImageResource(R.drawable.rostro8);
                                    break;
                                case 9:
                                    indicatorView.setImageResource(R.drawable.n9);
                                    faceView.setImageResource(R.drawable.rostro9);
                                    break;
                                case 10:
                                    indicatorView.setImageResource(R.drawable.n10);
                                    faceView.setImageResource(R.drawable.rostro10);
                                    break;

                            }

                            break;
                        case MotionEvent.ACTION_UP:

                            break;
                    }
                    return true;
                }
        );

        return v;
    }


}
