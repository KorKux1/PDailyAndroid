package co.edu.icesi.pdailyandroid.model.viewmodel;

import java.io.Serializable;

import co.edu.icesi.pdailyandroid.model.datatype.INotification;

public class NotificationFollowUp implements Serializable, INotification {

    private String id;
    private String name;
    private String date;

    public NotificationFollowUp() {
    }

    public NotificationFollowUp(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
