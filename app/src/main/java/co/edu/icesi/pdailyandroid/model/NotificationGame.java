package co.edu.icesi.pdailyandroid.model;

import java.io.Serializable;

import co.edu.icesi.pdailyandroid.interfaces.INotification;

public class NotificationGame implements Serializable, INotification {


    public static final int BANANA_GAME_ID=0;
    public static final int WORM_GAME_ID=1;
    private int id;
    private String name;
    private String date;

    public NotificationGame() {
    }

    public NotificationGame(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
