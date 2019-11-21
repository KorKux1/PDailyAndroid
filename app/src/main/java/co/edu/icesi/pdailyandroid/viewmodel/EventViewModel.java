package co.edu.icesi.pdailyandroid.viewmodel;

import java.io.Serializable;

public class EventViewModel implements Serializable {

    private String name;
    private boolean isEvaluated;

    public EventViewModel() {
    }

    public EventViewModel(String name, boolean isEvaluated) {
        this.name = name;
        this.isEvaluated = isEvaluated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public void setEvaluated(boolean evaluated) {
        isEvaluated = evaluated;
    }
}
