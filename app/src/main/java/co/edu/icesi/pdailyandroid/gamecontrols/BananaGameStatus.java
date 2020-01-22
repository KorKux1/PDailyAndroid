package co.edu.icesi.pdailyandroid.gamecontrols;

public class BananaGameStatus {


    static final int GAME_WON_RIGHT = 100;
    static final int GAME_WON_LEFT = 101;
    static final int GAME_INIT = 102;
    static final int INGAMERIGHT = 103;
    static final int INGAMELEFT = 104;
    static final int INSTRUCTIONS_RIGHT = 105;
    static final int INSTRUCTIONS_LEFT = 106;

    private int gameState = GAME_INIT;

    private OnGameStatusObserver observer;

    public void setObserver(OnGameStatusObserver observer){
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
        observer.onGameWonRight();
    }

    public void notifyVictoryLeft() {
        gameState = GAME_WON_LEFT;
        observer.onGameWonLeft();
    }

    public void notifyGameStartLeft() {
        gameState = INGAMELEFT;
        observer.onGameStartLeft();
    }

    public interface OnGameStatusObserver{
        void onGameWonRight();
        void onGameWonLeft();
        void onGameInit();
        void onGameStartRight();
        void onGameStartLeft();
        void onGameInstructionsRight();
        void onGameInstructionsLeft();
    }
}
