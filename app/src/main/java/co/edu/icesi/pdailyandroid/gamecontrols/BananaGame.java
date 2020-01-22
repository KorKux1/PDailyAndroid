package co.edu.icesi.pdailyandroid.gamecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.icesi.pdailyandroid.R;

public class BananaGame extends AppCompatActivity implements BananaGameStatus.OnGameStatusObserver{



    private ImageView bananita;
    private ImageView instructionSprite;
    private TextView instructionText;
    private int[] bananaFrames;
    private int[] handFrames;

    private BananaGameStatus status;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banana_game);
        bananita = findViewById(R.id.bananita);
        instructionSprite = findViewById(R.id.instructionSprite);
        instructionText = findViewById(R.id.instructionText);
        status = new BananaGameStatus();
        status.setObserver(this);

        bananaFrames = new int[22];
        handFrames = new int[18];

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

        handFrames[0] = R.drawable.one01;
        handFrames[1] = R.drawable.one02;
        handFrames[2] = R.drawable.one03;
        handFrames[3] = R.drawable.one04;
        handFrames[4] = R.drawable.one05;
        handFrames[5] = R.drawable.one06;
        handFrames[6] = R.drawable.one07;
        handFrames[7] = R.drawable.one08;
        handFrames[8] = R.drawable.one09;
        handFrames[9] = R.drawable.one10;
        handFrames[10] = R.drawable.one11;
        handFrames[11] = R.drawable.one12;
        handFrames[12] = R.drawable.one13;
        handFrames[13] = R.drawable.one14;
        handFrames[14] = R.drawable.one15;
        handFrames[15] = R.drawable.one16;
        handFrames[16] = R.drawable.one17;
        handFrames[17] = R.drawable.one18;

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

        instructionSprite.setOnClickListener(
                (v) -> {
                    switch (status.getStatus()) {
                        case BananaGameStatus.INSTRUCTIONS_LEFT:
                            status.notifyGameStartLeft();
                            break;
                        case BananaGameStatus.INSTRUCTIONS_RIGHT:
                            status.notifyGameStartRight();
                            break;
                    }
                }
        );

        status.notifyInit();
    }

    private int frame = 0;
    private long delayTime = 0;
    private boolean signal = false;



    public void kickOffGame(View view){

    }

    public void goToInstructions(View view){
        status.notifyGameInstructionsRight();
    }


    private int handFrame = 0;


    public void tryAgain(View view){
        switch (status.getStatus()){

            case BananaGameStatus.GAME_WON_RIGHT:
                status.notifyGameInstructionsLeft();
                break;

            case BananaGameStatus.GAME_WON_LEFT:
                status.notifyInit();
                break;

        }

    }



    @Override
    public void onGameWonRight() {
        runOnUiThread(()->{
            ((Button) findViewById(R.id.tryagain)).setText("Continuar");
            findViewById(R.id.tryagain).setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onGameWonLeft() {
        runOnUiThread(()->{
            ((Button) findViewById(R.id.tryagain)).setText("Finalizar");
            findViewById(R.id.tryagain).setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onGameInit() {
        frame = 0;
        runOnUiThread(()-> {
            findViewById(R.id.tryagain).setVisibility(View.GONE);
            findViewById(R.id.buttonContainer).setVisibility(View.VISIBLE);
            bananita.setVisibility(View.GONE);

        });
    }

    @Override
    public void onGameStartRight() {
        new Thread(
                () -> {
                    frame = 0;
                    runOnUiThread(()->{
                        findViewById(R.id.instructionText).setVisibility(View.GONE);
                        findViewById(R.id.instructionSprite).setVisibility(View.GONE);
                        findViewById(R.id.buttonContainer).setVisibility(View.GONE);
                        bananita.setVisibility(View.VISIBLE);
                    });

                    while (true) {
                        while(bananita.getVisibility() == View.GONE){}

                        runOnUiThread(
                                () -> bananita.setBackgroundResource(bananaFrames[(frame>=bananaFrames.length || frame<0) ? 0 : frame])
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
                            status.notifyVictoryRight();
                            break;
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

    @Override
    public void onGameStartLeft() {
        new Thread(
                () -> {
                    frame = 0;
                    runOnUiThread(()->{
                        findViewById(R.id.instructionText).setVisibility(View.GONE);
                        findViewById(R.id.instructionSprite).setVisibility(View.GONE);
                        findViewById(R.id.buttonContainer).setVisibility(View.GONE);
                        bananita.setVisibility(View.VISIBLE);
                    });

                    while (true) {
                        while(bananita.getVisibility() == View.GONE){}

                        runOnUiThread(
                                () -> bananita.setBackgroundResource(bananaFrames[(frame>=bananaFrames.length || frame<0) ? 0 : frame])
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
                            status.notifyVictoryLeft();
                            break;
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

    @Override
    public void onGameInstructionsRight() {
        new Thread(
                ()->{
                    runOnUiThread(()->{
                        findViewById(R.id.buttonContainer).setVisibility(View.GONE);
                        instructionSprite.setVisibility(View.VISIBLE);
                        instructionText.setVisibility(View.VISIBLE);
                    });

                    while( status.isInInstructionsRight() ){

                        runOnUiThread(
                                () -> {
                                    instructionSprite.setBackgroundResource(handFrames[(handFrame>=handFrames.length || handFrame<0) ? 0 : handFrame]);
                                }
                        );

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handFrame ++;
                        if(handFrame >= handFrames.length){
                            handFrame=0;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }

    @Override
    public void onGameInstructionsLeft() {
        new Thread(
                ()->{
                    runOnUiThread(()->{
                        findViewById(R.id.tryagain).setVisibility(View.GONE);
                        bananita.setVisibility(View.GONE);
                        findViewById(R.id.buttonContainer).setVisibility(View.GONE);
                        instructionSprite.setVisibility(View.VISIBLE);
                        instructionText.setVisibility(View.VISIBLE);
                        instructionSprite.setScaleX(-1);
                        instructionText.setText("Use el dedo índice\nde la mano izquierda");
                    });

                    while( status.isInInstructionsLeft() ){

                        runOnUiThread(
                                () -> {
                                    instructionSprite.setBackgroundResource(handFrames[(handFrame>=handFrames.length || handFrame<0) ? 0 : handFrame]);
                                }
                        );

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handFrame ++;
                        if(handFrame >= handFrames.length){
                            handFrame=0;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }


}
