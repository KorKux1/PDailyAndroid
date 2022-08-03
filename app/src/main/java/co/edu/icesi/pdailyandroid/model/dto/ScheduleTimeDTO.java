package co.edu.icesi.pdailyandroid.model.dto;

public class ScheduleTimeDTO {
    private int hour;
    private int minute;

    public ScheduleTimeDTO() {
    }

    public ScheduleTimeDTO(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String get12HString() {
        int h = this.hour > 13 ? this.hour - 12 : this.hour;
        String t = this.hour >= 12 && this.minute >= 0 ? "pm" : "am";
        String m = String.format("%02d", this.minute);
        return String.format("%s:%s%s", h, m, t);
    }
}
