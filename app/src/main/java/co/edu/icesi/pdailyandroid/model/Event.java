package co.edu.icesi.pdailyandroid.model;

import androidx.annotation.Nullable;

public class Event {
    private String name;
    private String from;
    private String to;

    public Event() {
    }

    public Event(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
