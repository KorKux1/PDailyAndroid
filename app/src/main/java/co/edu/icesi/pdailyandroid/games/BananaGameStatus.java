package co.edu.icesi.pdailyandroid.games;

public class BananaGameStatus {


    static final int GAME_WON_RIGHT = 100;
    static final int GAME_WON_LEFT = 101;
    static final int GAME_INIT = 102;
    static final int INGAMERIGHT = 103;
    static final int INGAMELEFT = 104;
    static final int INSTRUCTIONS_RIGHT = 105;
    static final int INSTRUCTIONS_LEFT = 106;
    static final int GAME_FINISHED = 107;

    private int gameState = GAME_INIT;

    private OnGameStatusObserver observer;


    private int rightTouches = 0;
    private int leftTouches = 0;

    private long startRightTime;
    private long startLeftTime;

    private long rightTime = 0;
    private long leftTime = 0;

    public void setObserver(OnGameStatusObserver observer) {
        this.observer = observer;
    }

    public void notifyInit() {
        gameState = GAME_INIT;
        observer.onGameInit();
    }

    public void notifyGameStartRight() {
        gameState = INGAMERIGHT;
        observer.onGameStartRight();
    }

    public void notifyGameInstructionsRight() {
        gameState = INSTRUCTIONS_RIGHT;
        observer.onGameInstructionsRight();
    }

    public boolean isInInstructionsLeft() {
        return gameState == INSTRUCTIONS_LEFT;
    }

    public boolean isInInstructionsRight() {
        return gameState == INSTRUCTIONS_RIGHT;
    }

    public void notifyGameInstructionsLeft() {
        gameState = INSTRUCTIONS_LEFT;
        observer.onGameInstructionsLeft();
    }

    public int getStatus() {
        return gameState;
    }

    public void notifyVictoryRight() {
        gameState = GAME_WON_RIGHT;
        rightTime = System.currentTimeMillis() - startRightTime;
        observer.onGameWonRight();
    }

    public void notifyVictoryLeft() {
        gameState = GAME_WON_LEFT;
        leftTime = System.currentTimeMillis() - startLeftTime;
        observer.onGameWonLeft();
    }

    public void notifyGameStartLeft() {
        gameState = INGAMELEFT;
        observer.onGameStartLeft();
    }

    public void notifyFinish() {
        gameState = GAME_FINISHED;
        observer.onGameFinish();
    }

    public void increaseRightPoints() {
        if (gameState == INGAMERIGHT) {
            rightTouches++;
            if (rightTouches == 1) startRightTime = System.currentTimeMillis();
        }
    }

    public void restoreRightPoints() {
        rightTouches = 0;
    }

    public int getRightTouches() {
        return rightTouches;
    }

    public int getLeftTouches() {
        return leftTouches;
    }

    public void increaseLeftPoints() {
        if (gameState == INGAMELEFT) {
            leftTouches++;
            if (leftTouches == 1) startLeftTime = System.currentTimeMillis();
        }
    }

    public void restoreLeftPoints() {
        leftTouches = 0;
    }

    public long getRightDuration() {
        return rightTime;
    }

    public long getLeftDuration() {
        return leftTime;
    }


    public interface OnGameStatusObserver {
        void onGameWonRight();

        void onGameWonLeft();

        void onGameInit();

        void onGameStartRight();

        void onGameStartLeft();

        void onGameInstructionsRight();

        void onGameInstructionsLeft();

        void onGameFinish();
    }
}
