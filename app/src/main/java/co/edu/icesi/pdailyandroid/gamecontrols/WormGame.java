package co.edu.icesi.pdailyandroid.gamecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import co.edu.icesi.pdailyandroid.R;

public class WormGame extends AppCompatActivity {

    private ImageView gusanito;

    private int[] normalStateFrames;
    private int[] exploteStateFrames;
    private ConstraintLayout root;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worm_game);

        gusanito = findViewById(R.id.gusanito);
        root = findViewById(R.id.root);
        normalStateFrames = new int[23];
        normalStateFrames[0] = R.drawable.gusano0;
        normalStateFrames[1] = R.drawable.gusano1;
        normalStateFrames[2] = R.drawable.gusano2;
        normalStateFrames[3] = R.drawable.gusano3;
        normalStateFrames[4] = R.drawable.gusano4;
        normalStateFrames[5] = R.drawable.gusano5;
        normalStateFrames[6] = R.drawable.gusano6;
        normalStateFrames[7] = R.drawable.gusano7;
        normalStateFrames[8] = R.drawable.gusano8;
        normalStateFrames[9] = R.drawable.gusano9;
        normalStateFrames[10] = R.drawable.gusano10;
        normalStateFrames[11] = R.drawable.gusano11;
        normalStateFrames[12] = R.drawable.gusano12;
        normalStateFrames[13] = R.drawable.gusano13;
        normalStateFrames[14] = R.drawable.gusano14;
        normalStateFrames[15] = R.drawable.gusano15;
        normalStateFrames[16] = R.drawable.gusano16;
        normalStateFrames[17] = R.drawable.gusano17;
        normalStateFrames[18] = R.drawable.gusano18;
        normalStateFrames[19] = R.drawable.gusano19;
        normalStateFrames[20] = R.drawable.gusano20;
        normalStateFrames[21] = R.drawable.gusano21;
        normalStateFrames[22] = R.drawable.gusano22;
        exploteStateFrames = new int[11];
        exploteStateFrames[0] = R.drawable.wormexplode1;
        exploteStateFrames[1] = R.drawable.wormexplode2;
        exploteStateFrames[2] = R.drawable.wormexplode3;
        exploteStateFrames[3] = R.drawable.wormexplode4;
        exploteStateFrames[4] = R.drawable.wormexplode5;
        exploteStateFrames[5] = R.drawable.wormexplode6;
        exploteStateFrames[6] = R.drawable.wormexplode7;
        exploteStateFrames[7] = R.drawable.wormexplode8;
        exploteStateFrames[8] = R.drawable.wormexplode9;
        exploteStateFrames[9] = R.drawable.wormexplode10;
        exploteStateFrames[10] = R.drawable.wormexplode11;

        gusanito.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isNormalState = false;
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        });

        initializeGame();
    }


    private int frame = 0;
    private boolean signal = false;
    private boolean isNormalState = true;


    private void initializeGame() {
        new Thread(
                () -> {
                    while (true) {


                        while (isNormalState) {
                            runOnUiThread(() -> {
                                gusanito.setBackgroundResource(normalStateFrames[frame]);
                                signal = false;
                            });

                            frame++;
                            if (frame >= normalStateFrames.length) {
                                frame = 2;
                            }

                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        frame = 0;
                        signal = false;

                        while (!isNormalState) {
                            while (signal) {
                            }
                            signal = true;
                            runOnUiThread(() -> {
                                if (!isNormalState && frame>=0 && frame<exploteStateFrames.length) {
                                    gusanito.setBackgroundResource(exploteStateFrames[frame]);
                                    Log.e(">>>", "Explote: " + frame);
                                }
                                signal = false;
                            });
                            frame++;
                            if (frame >= exploteStateFrames.length) {
                                isNormalState = true;
                                frame = 0;


                                runOnUiThread(()->{
                                    gusanito.setVisibility(View.GONE);
                                });

                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                runOnUiThread(
                                        () -> {
                                            double areaX = (root.getWidth()-gusanito.getWidth())*Math.random();
                                            gusanito.setX((int) areaX);
                                            double areaY = (root.getHeight()-gusanito.getHeight())*Math.random();
                                            gusanito.setY((int) areaY);
                                            gusanito.setBackgroundResource(normalStateFrames[0]);
                                            gusanito.setVisibility(View.VISIBLE);
                                        }
                                );
                                break;
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }
        ).start();
    }
}
