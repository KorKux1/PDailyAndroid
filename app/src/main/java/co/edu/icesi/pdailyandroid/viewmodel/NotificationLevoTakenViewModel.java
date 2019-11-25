package co.edu.icesi.pdailyandroid.viewmodel;

import java.io.Serializable;

public class NotificationLevoTakenViewModel extends NotificationViewModel implements Serializable {

    private String notificationButtonText;

    public NotificationLevoTakenViewModel() {}

    public NotificationLevoTakenViewModel(String notificationTitle, String notificationDescription, String notificationDate, String notificationButtonText) {
        super(notificationTitle, notificationDescription, notificationDate);
        this.notificationButtonText = notificationButtonText;
    }

    public String getNotificationButtonText() {
        return notificationButtonText;
    }

    public void setNotificationButtonText(String notificationButtonText) {
        this.notificationButtonText = notificationButtonText;
    }
}
