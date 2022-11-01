package co.edu.icesi.pdailyandroid.viewmodel;

import java.io.Serializable;

import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationFollowUp;
import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationType;

public class SimpleNotification implements Serializable {

    private String title;
    private String notification;
    private String date;
    private NotificationType type;

    public SimpleNotification() {
    }

    public SimpleNotification(String title, String notificationDescription, String notificationDate, NotificationType notificationType) {
        this.title = title;
        this.notification = notificationDescription;
        this.date = notificationDate;
        this.type = notificationType;
    }

    public static SimpleNotification bind(NotificationFollowUp notification) {

        String title = notification.getType() == NotificationType.FOOD ? "Alimentación" : "Medicamento";
        return new SimpleNotification(title, notification.getName(), notification.getDate(), notification.getType());
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

    public NotificationType getType() {
        return type;
    }
}
