package co.edu.icesi.pdailyandroid.viewmodel;

import java.io.Serializable;

import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;

public class SimpleNotification implements Serializable {

    private String title;
    private String notification;
    private String date;

    public SimpleNotification(){}

    public SimpleNotification(String title, String notificationDescription, String notificationDate) {
        this.title = title;
        this.notification = notificationDescription;
        this.date = notificationDate;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public static SimpleNotification bind(NotificationFoodFollowUp notification) {
        return new SimpleNotification("Alimentación", notification.getName(), notification.getDate());
    }

}
