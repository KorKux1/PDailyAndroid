package co.edu.icesi.pdailyandroid.gamecontrols;

import java.util.ArrayList;

public class WormGameStatus {

    static final int INIT_GAME = 0;
    static final int INGAME = 1;
    static final int GAME_OVER = 2;
    static final int GAME_INSTRUCTIONS_RIGHT = 3;
    static final int GAME_INSTRUCTIONS_LEFT = 4;

    private int gameState = INIT_GAME;

    private OnGameStatusObserver observer;

    private int gameTime = 10;
    private int points = 0;
    private int errors = 0;

    private ArrayList<Long> timestamps;

    public WormGameStatus(){
        timestamps = new ArrayList<>();
    }

    public void setObserver(OnGameStatusObserver observer){
        this.observer = observer;
    }

    public void notifyGameStart() {
        gameState = INGAME;
        observer.onGameStart();
    }


    public void notifyGameInit() {
        resetGameTime();
        resetPointsAndErrors();
        resetTimestamps();
        gameState = INIT_GAME;
        observer.onGameInit();
    }

    private void resetPointsAndErrors() {
        points = 0;
        errors = 0;
    }

    public void addTimeStamp() {
        timestamps.add(System.currentTimeMillis());
    }

    public long calculateMean() {
        int size = timestamps.size();

        if(size <= 1){
            return 0;
        }

        long acu = 0;
        for(int i=1 ; i<size ; i=i+2){
            long time = timestamps.get(i) - timestamps.get(i-1);
            acu += time;
        }
        return acu/(size/2);
    }

    public void notifyGameInstructionsRight() {
        gameState = GAME_INSTRUCTIONS_RIGHT;
        observer.onGameInstructionsRight();
    }

    public void notifyGameInstructionsLeft() {
        gameState = GAME_INSTRUCTIONS_LEFT;
        observer.onGameInstructionsLeft();
    }


    public interface OnGameStatusObserver{
        void onGameInit();
        void onGameInstructionsRight();
        void onGameInstructionsLeft();
        void onGameStart();
        void onGameOver();
    }

    public int getGameTime(){
        return gameTime;
    }

    public void decreaseGameTime(){
        gameTime--;
        if(gameTime <= -1) {
            gameState = GAME_OVER;
            observer.onGameOver();
        }
    }

    public void resetGameTime(){
        gameTime = 10;
    }

    public int getGameState(){
        return gameState;
    }

    public int getPoints() {
        return points;
    }

    public int getErrors() {
        return errors;
    }

    public void increasePoints(){
        if(gameState == INGAME) {
            addTimeStamp();
            points++;
        }
    }
    public void increaseErrors(){
        if(gameState == INGAME){
            errors++;
        }
    }

    public void resetTimestamps(){
        timestamps.clear();
    }
}
