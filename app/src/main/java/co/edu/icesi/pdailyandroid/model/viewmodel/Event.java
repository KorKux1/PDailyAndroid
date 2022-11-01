package co.edu.icesi.pdailyandroid.model.viewmodel;

import java.util.ArrayList;

public class Event {
    private String name;
    private long from;
    private long to;
    private int intensity;
    private ArrayList<String> bodyParts;

    public Event() {
        intensity = 1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public ArrayList<String> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(ArrayList<String> bodyParts) {
        this.bodyParts = bodyParts;
    }
}
