package co.edu.icesi.pdailyandroid.model.dto;

public class ScheduleDateDTO {
    private int year;
    private int month;
    private int day;

    public ScheduleDateDTO() {
    }

    public ScheduleDateDTO(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
