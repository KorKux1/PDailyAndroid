package co.edu.icesi.pdailyandroid.model;

import androidx.annotation.Nullable;

public class Event {
    private String name;
    private String from;
    private String to;
    private int intensity;

    public Event() {
        intensity = 1;
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

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
