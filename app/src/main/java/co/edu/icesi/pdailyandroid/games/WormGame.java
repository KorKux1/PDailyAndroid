package co.edu.icesi.pdailyandroid.games;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import co.edu.icesi.pdailyandroid.R;

public class WormGame extends AppCompatActivity implements WormGameStatus.OnGameStatusObserver{

    private ImageView gusanito;
    private TextView timerText;

    private int[] normalStateFrames;
    private int[] exploteStateFrames;
    private ConstraintLayout root;

    private ImageButton buttonder, buttonizq;

    private WormGameStatus status;
    private Button tryAgainButton;

    private TextView errorsText;
    private TextView pointsText;
    private TextView reactionText;
    private TextView instructionText;

    private RelativeLayout gameContainer;
    private WormGameSurface instructionsSprite;


    
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worm_game);

        status = new WormGameStatus();
        status.setObserver(this);

        instructionsSprite = findViewById(R.id.instructionsSprite);
        errorsText = findViewById(R.id.errorsText);
        pointsText = findViewById(R.id.pointsText);
        reactionText = findViewById(R.id.reactionText);
        gusanito = findViewById(R.id.gusanito);
        gameContainer = findViewById(R.id.gameContainer);
        timerText = findViewById(R.id.timerText);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        root = findViewById(R.id.root);
        instructionText = findViewById(R.id.instructionText);

        buttonder = findViewById(R.id.buttonder);
        buttonizq = findViewById(R.id.buttonizq);



      
        
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
                    if(isNormalState) status.increasePoints();
                    isNormalState = false;
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        });

        tryAgainButton.setOnClickListener(
                (v) -> {
                    status.notifyGameInit();
                }
        );

        buttonder.setOnClickListener(
                (v)->{
                    status.notifyGameInstructionsRight();
                }
        );

        buttonizq.setOnClickListener(
                (v)->{
                    status.notifyGameInstructionsLeft();
                }
        );

        instructionsSprite.setOnClickListener(
                (v)->{
                    instructionsSprite.pause();
                    status.notifyGameStart();
                }
        );


        View.OnClickListener listener = (v) -> {
            status.increaseErrors();
        };
        gameContainer.setOnClickListener(listener);
        timerText.setOnClickListener(listener);

    }


    private int frame = 0;
    private boolean isNormalState = true;


    @Override
    public void onGameInit() {
        findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);
        findViewById(R.id.resultsContainer).setVisibility(View.GONE);
        findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
        findViewById(R.id.gameContainer).setVisibility(View.GONE);
    }

    @Override
    public void onGameInstructionsRight() {
        findViewById(R.id.mainContainer).setVisibility(View.GONE);
        findViewById(R.id.resultsContainer).setVisibility(View.GONE);
        findViewById(R.id.instructionsContainer).setVisibility(View.VISIBLE);
        findViewById(R.id.gameContainer).setVisibility(View.GONE);
        instructionText.setText("Use el dedo índice de la mano derecha");


        instructionsSprite.setSide(WormGameSurface.RIGHT);
        instructionsSprite.start();
        /*
        handFrame = 0;
        instructionsSprite.setBackgroundResource(handFramesRight[handFrame]);
        new Thread(
                ()->{

                    while( status.getGameState() == WormGameStatus.GAME_INSTRUCTIONS_RIGHT ){

                        runOnUiThread(
                                () -> {
                                    instructionsSprite.setBackgroundResource(handFramesRight[(handFrame>=handFramesRight.length || handFrame<0) ? 0 : handFrame]);
                                }
                        );

                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handFrame ++;
                        if(handFrame >= handFramesRight.length){
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
        */

    }

    @Override
    public void onGameInstructionsLeft() {
        findViewById(R.id.mainContainer).setVisibility(View.GONE);
        findViewById(R.id.resultsContainer).setVisibility(View.GONE);
        findViewById(R.id.instructionsContainer).setVisibility(View.VISIBLE);
        findViewById(R.id.gameContainer).setVisibility(View.GONE);
        instructionText.setText("Use el dedo índice de la mano izquierda");

        instructionsSprite.setSide(WormGameSurface.LEFT);
        instructionsSprite.start();

        /*
        handFrame = 0;
        instructionsSprite.setBackgroundResource(handFrames[handFrame]);
        new Thread(
                ()->{

                    while( status.getGameState() == WormGameStatus.GAME_INSTRUCTIONS_LEFT ){

                        runOnUiThread(
                                () -> {
                                    instructionsSprite.setBackgroundResource(handFrames[(handFrame>=handFrames.length || handFrame<0) ? 0 : handFrame]);
                                }
                        );

                        try {
                            Thread.sleep(40);
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
         */
    }


    @Override
    public void onGameStart() {
        findViewById(R.id.mainContainer).setVisibility(View.GONE);
        findViewById(R.id.resultsContainer).setVisibility(View.GONE);
        findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
        findViewById(R.id.gameContainer).setVisibility(View.VISIBLE);

        status.addTimeStamp();
        new Thread(
                () -> {
                    while (status.getGameState() == WormGameStatus.INGAME) {

                        while (isNormalState && status.getGameState() == WormGameStatus.INGAME) {
                            runOnUiThread(() -> {
                                gusanito.setBackgroundResource(normalStateFrames[frame]);
                            });

                            frame++;
                            if (frame >= normalStateFrames.length) {
                                frame = 2;
                            }

                            try {
                                Thread.sleep(25);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        frame = 0;

                        while (!isNormalState && status.getGameState() == WormGameStatus.INGAME) {

                            runOnUiThread(() -> {
                                if (!isNormalState && frame>=0 && frame<exploteStateFrames.length) {
                                    gusanito.setBackgroundResource(exploteStateFrames[frame]);
                                }

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
                                            //double areaX = (gameContainer.getWidth()-gusanito.getWidth()-60)*Math.random();
                                            //double areaY = (gusanito.getHeight()/2+60) + (gameContainer.getHeight()- 1.7*(gusanito.getHeight()) )*Math.random();

                                            float limMinX = 0;
                                            float limMaxX = gameContainer.getWidth()-gusanito.getWidth()-60;
                                            float limMinY = gusanito.getHeight()/2+60;
                                            float limMaxY = (float) ((gusanito.getHeight()/2+60) + (gameContainer.getHeight()- 1.7*(gusanito.getHeight())));

                                            Log.e(">>>","X"+limMinX);
                                            Log.e(">>>","X"+limMaxX);
                                            Log.e(">>>","Y"+limMinY);
                                            Log.e(">>>","Y"+limMaxY);

                                            int radio = 300;


                                            ArrayList<float[]> coords = new ArrayList<>();
                                            for(double angle=0 ; angle<2*Math.PI ; angle = angle + 0.015){
                                                double x = radio * Math.cos(angle);
                                                double y = radio * Math.sin(angle);
                                                float newX = (float) (gusanito.getX()+x);
                                                float newY = (float) (gusanito.getY()+y);

                                                if(newX>limMinX && newX<limMaxX && newY>limMinY && newY<limMaxY){
                                                    coords.add(new float[]{newX,newY});
                                                }
                                            }

                                            /*
                                            do {
                                                double angle = Math.random() * 2 * Math.PI;
                                                double x = radio * Math.cos(angle);
                                                double y = radio * Math.sin(angle);
                                                newX = (float) (gusanito.getX()+x);
                                                newY = (float) (gusanito.getY()+y);
                                                Log.e(">>>","NEWX"+newX);
                                                Log.e(">>>","NEWY"+newY);
                                            }while (newX<limMinX || newX>limMaxX || newY<limMinY || newY>limMaxY);
                                            */

                                            int coordRandom = (int)(Math.random()*coords.size());
                                            gusanito.setX(coords.get(coordRandom)[0]);
                                            gusanito.setY(coords.get(coordRandom)[1]);


                                            gusanito.setBackgroundResource(normalStateFrames[0]);
                                            gusanito.setVisibility(View.VISIBLE);
                                            status.addTimeStamp();
                                        }
                                );
                                break;
                            }
                            try {
                                Thread.sleep(25);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }
        ).start();



        new Thread(
                ()->{
                    while(status.getGameState() == WormGameStatus.INGAME) {
                        runOnUiThread(
                                () -> {
                                    timerText.setText("" + status.getGameTime());
                                    status.decreaseGameTime();
                                }
                        );
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();


    }

    @Override
    protected void onPause() {
        instructionsSprite.pause();
        super.onPause();
    }

    @Override
    public void onGameOver() {
        findViewById(R.id.mainContainer).setVisibility(View.GONE);
        findViewById(R.id.resultsContainer).setVisibility(View.VISIBLE);
        findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
        findViewById(R.id.gameContainer).setVisibility(View.GONE);
        runOnUiThread(
                ()->{
                    errorsText.setText(""+status.getErrors());
                    pointsText.setText(""+status.getPoints());
                    reactionText.setText(""+status.calculateMean());
                }
        );

    }
}
