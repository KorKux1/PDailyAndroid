package co.edu.icesi.pdailyandroid.gamecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import co.edu.icesi.pdailyandroid.R;

public class BananaGame extends AppCompatActivity {


    private static final int GAME_WON = 100;
    private static final int GAME_INIT = 101;

    private int gameState = GAME_INIT;
    private ImageView bananita;
    private int[] bananaFrames;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banana_game);
        bananita = findViewById(R.id.bananita);
        bananaFrames = new int[22];
        bananaFrames[0] = R.drawable.banana1;
        bananaFrames[1] = R.drawable.banana2;
        bananaFrames[2] = R.drawable.banana3;
        bananaFrames[3] = R.drawable.banana4;
        bananaFrames[4] = R.drawable.banana5;
        bananaFrames[5] = R.drawable.banana6;
        bananaFrames[6] = R.drawable.banana7;
        bananaFrames[7] = R.drawable.banana8;
        bananaFrames[8] = R.drawable.banana9;
        bananaFrames[9] = R.drawable.banana10;
        bananaFrames[10] = R.drawable.banana11;
        bananaFrames[11] = R.drawable.banana12;
        bananaFrames[12] = R.drawable.banana13;
        bananaFrames[13] = R.drawable.banana14;
        bananaFrames[14] = R.drawable.banana15;
        bananaFrames[15] = R.drawable.banana16;
        bananaFrames[16] = R.drawable.banana17;
        bananaFrames[17] = R.drawable.banana18;
        bananaFrames[18] = R.drawable.banana19;
        bananaFrames[19] = R.drawable.banana20;
        bananaFrames[20] = R.drawable.banana21;
        bananaFrames[21] = R.drawable.banana22;
        bananita.setOnTouchListener((v, event) -> {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    frame++;
                    delayTime = 0;
                    if(frame>=bananaFrames.length-1){
                        frame = bananaFrames.length-1;
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }
            return true;
        });
        initializeAnimation();
    }

    private int frame = 0;
    private long delayTime = 0;
    private boolean signal = false;

    private void initializeAnimation() {

        new Thread(
                () -> {
                    while (true) {
                        runOnUiThread(
                                () -> {
                                    bananita.setBackgroundResource(bananaFrames[frame]);

                                }
                        );

                        delayTime+=1;
                        Log.e(">>>","delay: "+delayTime);
                        if(delayTime>10){
                            frame--;
                            if(frame<=0){
                                frame = 0;
                            }
                        }

                        if(frame >= bananaFrames.length-1){
                            notifyFinish(GAME_WON);
                            while(gameState == GAME_WON){ }
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

    }

    private void notifyFinish(int code) {
        gameState = GAME_WON;
        runOnUiThread(()->{
            findViewById(R.id.tryagain).setVisibility(View.VISIBLE);
        });
    }

    public void kickOffGame(View view){
        findViewById(R.id.buttonContainer).setVisibility(View.GONE);
        bananita.setVisibility(View.VISIBLE);
    }

    public void goToInstructions(View view){

    }

    public void tryAgain(View view){
        findViewById(R.id.tryagain).setVisibility(View.GONE);
        gameState = GAME_INIT;
        findViewById(R.id.buttonContainer).setVisibility(View.VISIBLE);
        bananita.setVisibility(View.GONE);
        frame = 0;
    }

}
