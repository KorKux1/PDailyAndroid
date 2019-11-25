package co.edu.icesi.pdailyandroid.viewmodel;

import java.io.Serializable;

public class NotificationViewModel implements Serializable {

    private String notificationTitle;
    private String notificationDescription;
    private String notificationDate;

    public NotificationViewModel(){}

    public NotificationViewModel(String notificationTitle, String notificationDescription, String notificationDate) {
        this.notificationTitle = notificationTitle;
        this.notificationDescription = notificationDescription;
        this.notificationDate = notificationDate;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }
}
