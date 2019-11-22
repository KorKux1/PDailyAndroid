package co.edu.icesi.pdailyandroid.viewmodel;

import java.io.Serializable;

public class OtherViewModel implements Serializable {

    private String othersTitle;
    private String othersType;
    private String othersDose;
    private String othersDate;
    private String othersEvery;
    private String othersHour;

    public OtherViewModel() {
    }

    public OtherViewModel(String othersTitle, String othersType, String othersDose, String othersDate, String othersEvery, String othersHour) {
        this.othersTitle = othersTitle;
        this.othersType = othersType;
        this.othersDose = othersDose;
        this.othersDate = othersDate;
        this.othersEvery = othersEvery;
        this.othersHour = othersEvery;
    }

    public String getOthersTitle() {
        return othersTitle;
    }

    public void setOthersTitle(String othersTitle) {
        this.othersTitle = othersTitle;
    }

    public String getOthersType() {
        return othersType;
    }

    public void setOthersType(String othersType) {
        this.othersType = othersType;
    }

    public String getOthersDose() {
        return othersDose;
    }

    public void setOthersDose(String othersDose) {
        this.othersDose = othersDose;
    }

    public String getOthersDate() {
        return othersDate;
    }

    public void setOthersDate(String othersDate) {
        this.othersDate = othersDate;
    }

    public String getOthersEvery() {
        return othersEvery;
    }

    public void setOthersEvery(String othersEvery) {
        this.othersEvery = othersEvery;
    }

    public String getOthersHour() {
        return othersHour;
    }

    public void setOthersHour(String othersHour) {
        this.othersHour = othersHour;
    }
}
