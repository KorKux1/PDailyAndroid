package co.edu.icesi.pdailyandroid.misc.customview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import co.edu.icesi.pdailyandroid.R;

public class IntensityView extends Fragment {

    private float initY = 0;
    private View root;
    private int value = 10;
    private int height;
    private int faceHeight;
    private int indicatorHeight;

    private ImageView faceView;
    private ImageView indicatorView;

    private onValueListener listener;

    public IntensityView() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_intensity_view, container, false);

        faceView = root.findViewById(R.id.faceView);
        indicatorView = root.findViewById(R.id.indicatorView);

        faceView.setOnTouchListener(
            (view, event) -> {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initY = event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
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

                        value = 11 - (11 - 1 - (int) (9 * (faceView.getY() + faceHeight / 4) / (height - faceHeight)));
                        refreshView();
                        break;

                    case MotionEvent.ACTION_UP:
                        listener.onValue(value);
                        break;
                }
                return true;
            }
        );

        deselect();

        root.post(() -> {
            faceHeight = faceView.getHeight();
            indicatorHeight = indicatorView.getHeight();
            height = root.getHeight();
        });

        return root;
    }

    private void refreshView() {
        switch (value) {
            case 1:
                setIcons(R.drawable.n1, R.drawable.rostro1);
                break;
            case 2:
                setIcons(R.drawable.n2, R.drawable.rostro2);
                break;
            case 3:
                setIcons(R.drawable.n3, R.drawable.rostro3);
                break;
            case 4:
                setIcons(R.drawable.n4, R.drawable.rostro4);
                break;
            case 5:
                setIcons(R.drawable.n5, R.drawable.rostro5);
                break;
            case 6:
                setIcons(R.drawable.n6, R.drawable.rostro6);
                break;
            case 7:
                setIcons(R.drawable.n7, R.drawable.rostro7);
                break;
            case 8:
                setIcons(R.drawable.n8, R.drawable.rostro8);
                break;
            case 9:
                setIcons(R.drawable.n9, R.drawable.rostro9);
                break;
            case 10:
                setIcons(R.drawable.n10, R.drawable.rostro10);
                break;
        }
    }

    private void setIcons(int indicatorIcon, int faceIcon) {
        indicatorView.setImageResource(indicatorIcon);
        faceView.setImageResource(faceIcon);
    }

    public void setValue(int value) {
        this.value = value;
        float y = -1 / 9f * (((11 - value) - 10) * (height - faceHeight) + 2.25f * faceHeight) + faceHeight / 4;
        faceView.setY(y);
        indicatorView.setY(y + faceHeight / 2 - indicatorHeight / 2);
        refreshView();
    }

    public void deselect() {
        root.animate().alpha(0.1f);
    }

    public void select() {
        root.animate().alpha(1f);
    }

    public void setListener(onValueListener listener) {
        this.listener = listener;
    }

    public interface onValueListener {
        void onValue(int value);
    }
}
