package co.edu.icesi.pdailyandroid.games;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.icesi.pdailyandroid.R;

public class BananaGame extends AppCompatActivity implements BananaGameStatus.OnGameStatusObserver {

    private ImageView bananita;
    private BananaGameSurface instructionSprite;
    private TextView instructionText;
    private int[] bananaFrames;

    private BananaGameStatus status;

    private boolean leftFirst = false;
    private int frame = 0;
    private long delayTime = 0;
    private final boolean signal = false;
    private final int handFrame = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banana_game);
        bananita = findViewById(R.id.bananita);
        instructionSprite = findViewById(R.id.instructionsSprite);
        instructionText = findViewById(R.id.instructionText);
        status = new BananaGameStatus();
        status.setObserver(this);

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
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    frame++;
                    delayTime = 0;
                    if (frame >= bananaFrames.length - 1) {
                        frame = bananaFrames.length - 1;
                    }
                    status.increaseRightPoints();
                    status.increaseLeftPoints();
                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }
            return true;
        });

        instructionSprite.setOnClickListener(
                (v) -> {
                    instructionSprite.pause();
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

    public void kickOffGame(View view) {

    }

    public void goToInstructionsRight(View view) {
        leftFirst = false;
        status.notifyGameInstructionsRight();
    }

    public void goToInstructionsLeft(View view) {
        leftFirst = true;
        status.notifyGameInstructionsLeft();
    }

    public void tryAgain(View view) {
        switch (status.getStatus()) {

            case BananaGameStatus.GAME_WON_RIGHT:
                if (leftFirst) {
                    status.notifyFinish();
                } else {
                    status.notifyGameInstructionsLeft();
                }
                break;

            case BananaGameStatus.GAME_WON_LEFT:
                if (leftFirst) {
                    status.notifyGameInstructionsRight();
                } else {
                    status.notifyFinish();
                }
                break;

            case BananaGameStatus.GAME_FINISHED:
                status.notifyInit();
                break;

        }

    }

    @Override
    public void onGameWonRight() {
        runOnUiThread(() -> {
            findViewById(R.id.gameContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.resultsContainer).setVisibility(View.GONE);
            findViewById(R.id.buttonContainer).setVisibility(View.GONE);
            findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
            findViewById(R.id.gameOverContainer).setVisibility(View.VISIBLE);
            ((Button) findViewById(R.id.tryagain)).setText("Continuar");
        });
    }

    @Override
    public void onGameWonLeft() {
        runOnUiThread(() -> {
            findViewById(R.id.gameContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.resultsContainer).setVisibility(View.GONE);
            findViewById(R.id.buttonContainer).setVisibility(View.GONE);
            findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
            findViewById(R.id.gameOverContainer).setVisibility(View.VISIBLE);
            ((Button) findViewById(R.id.tryagain)).setText("Finalizar");
        });
    }

    @Override
    public void onGameInit() {
        frame = 0;
        runOnUiThread(() -> {
            findViewById(R.id.gameContainer).setVisibility(View.GONE);
            findViewById(R.id.resultsContainer).setVisibility(View.GONE);
            findViewById(R.id.buttonContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
            findViewById(R.id.gameOverContainer).setVisibility(View.GONE);
        });
    }

    @Override
    public void onGameStartRight() {
        status.restoreRightPoints();

        runOnUiThread(() -> {
            findViewById(R.id.gameContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.resultsContainer).setVisibility(View.GONE);
            findViewById(R.id.buttonContainer).setVisibility(View.GONE);
            findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
            findViewById(R.id.gameOverContainer).setVisibility(View.GONE);
        });

        new Thread(
                () -> {
                    frame = 0;
                    while (status.getStatus() == BananaGameStatus.INGAMERIGHT) {
                        while (bananita.getVisibility() == View.GONE) {
                        }

                        runOnUiThread(
                                () -> bananita.setBackgroundResource(bananaFrames[(frame >= bananaFrames.length || frame < 0) ? 0 : frame])
                        );
                        delayTime += 1;
                        Log.e(">>>", "delay: " + delayTime);
                        if (delayTime > 10) {
                            frame--;
                            if (frame <= 0) {
                                frame = 0;
                            }
                        }

                        if (frame >= bananaFrames.length - 1) {
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
        runOnUiThread(() -> {
            findViewById(R.id.gameContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.resultsContainer).setVisibility(View.GONE);
            findViewById(R.id.buttonContainer).setVisibility(View.GONE);
            findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
            findViewById(R.id.gameOverContainer).setVisibility(View.GONE);
        });

        status.restoreLeftPoints();
        new Thread(
                () -> {
                    frame = 0;
                    while (status.getStatus() == BananaGameStatus.INGAMELEFT) {
                        while (bananita.getVisibility() == View.GONE) {
                        }

                        runOnUiThread(
                                () -> bananita.setBackgroundResource(bananaFrames[(frame >= bananaFrames.length || frame < 0) ? 0 : frame])
                        );
                        delayTime += 1;
                        Log.e(">>>", "delay: " + delayTime);
                        if (delayTime > 10) {
                            frame--;
                            if (frame <= 0) {
                                frame = 0;
                            }
                        }

                        if (frame >= bananaFrames.length - 1) {
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

        findViewById(R.id.gameContainer).setVisibility(View.GONE);
        findViewById(R.id.resultsContainer).setVisibility(View.GONE);
        findViewById(R.id.buttonContainer).setVisibility(View.GONE);
        findViewById(R.id.instructionsContainer).setVisibility(View.VISIBLE);
        findViewById(R.id.gameOverContainer).setVisibility(View.GONE);
        instructionText.setText("Use dedo índice de la mano derecha");

        instructionSprite.setSide(BananaGameSurface.RIGHT);
        instructionSprite.start();
    }

    @Override
    public void onGameInstructionsLeft() {
        findViewById(R.id.gameContainer).setVisibility(View.GONE);
        findViewById(R.id.resultsContainer).setVisibility(View.GONE);
        findViewById(R.id.buttonContainer).setVisibility(View.GONE);
        findViewById(R.id.instructionsContainer).setVisibility(View.VISIBLE);
        findViewById(R.id.gameOverContainer).setVisibility(View.GONE);
        instructionText.setText("Use dedo índice de la mano izquierda");

        instructionSprite.setSide(BananaGameSurface.LEFT);
        instructionSprite.start();
    }

    @Override
    protected void onPause() {
        instructionSprite.pause();
        super.onPause();
    }

    @Override
    public void onGameFinish() {
        runOnUiThread(() -> {
            findViewById(R.id.gameContainer).setVisibility(View.GONE);
            findViewById(R.id.resultsContainer).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonContainer).setVisibility(View.GONE);
            findViewById(R.id.instructionsContainer).setVisibility(View.GONE);
            findViewById(R.id.gameOverContainer).setVisibility(View.VISIBLE);

            ((Button) findViewById(R.id.tryagain)).setText("Intentarlo de nuevo");
            ((TextView) findViewById(R.id.rightTouchesText)).setText("" + status.getRightTouches());
            ((TextView) findViewById(R.id.leftTouchesText)).setText("" + status.getLeftTouches());

            ((TextView) findViewById(R.id.rightTimeText)).setText("" + status.getRightDuration());
            ((TextView) findViewById(R.id.leftTimeText)).setText("" + status.getLeftDuration());
        });
    }

}
