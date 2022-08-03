package co.edu.icesi.pdailyandroid.model.dto;


import java.util.ArrayList;

public class SchedulePlanDTO {
    private ScheduleDateDTO startDate;
    private ScheduleDateDTO endDate;
    private ArrayList<ScheduleTimeDTO> times;
    private int repeatFrequency;
    private ScheduleRecurrenceEnum repeatRecurrence;
    // 0: sunday; 6: saturday
    private int[] weeklyRecurrenceDays;

    public SchedulePlanDTO() {
    }

    public SchedulePlanDTO(
            ScheduleDateDTO startDate, ScheduleDateDTO endDate, ArrayList<ScheduleTimeDTO> times,
            int repeatFrequency, ScheduleRecurrenceEnum repeatRecurrence,
            int[] weeklyRecurrenceDays) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.times = times;
        this.repeatFrequency = repeatFrequency;
        this.repeatRecurrence = repeatRecurrence;
        this.weeklyRecurrenceDays = weeklyRecurrenceDays;
    }

    public ScheduleDateDTO getStartDate() {
        return startDate;
    }

    public void setStartDate(ScheduleDateDTO startDate) {
        this.startDate = startDate;
    }

    public ScheduleDateDTO getEndDate() {
        return endDate;
    }

    public void setEndDate(ScheduleDateDTO endDate) {
        this.endDate = endDate;
    }

    public ArrayList<ScheduleTimeDTO> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<ScheduleTimeDTO> times) {
        this.times = times;
    }

    public int getRepeatFrequency() {
        return repeatFrequency;
    }

    public void setRepeatFrequency(int repeatFrequency) {
        this.repeatFrequency = repeatFrequency;
    }

    public ScheduleRecurrenceEnum getRepeatRecurrence() {
        return repeatRecurrence;
    }

    public void setRepeatRecurrence(ScheduleRecurrenceEnum repeatRecurrence) {
        this.repeatRecurrence = repeatRecurrence;
    }

    public int[] getWeeklyRecurrenceDays() {
        return weeklyRecurrenceDays;
    }

    public void setWeeklyRecurrenceDays(int[] weeklyRecurrenceDays) {
        this.weeklyRecurrenceDays = weeklyRecurrenceDays;
    }

    public String getStartDateString() {
        return startDate == null ? null : String.format("%s-%s-%s", startDate.getYear(),
                String.format("%02d", startDate.getMonth()),
                String.format("%02d", startDate.getDay()));
    }

    public String getEndDateString() {
        return endDate == null ? null : String.format("%s-%s-%s", endDate.getYear(),
                String.format("%02d", endDate.getMonth()),
                String.format("%02d", endDate.getDay()));
    }

    public String getTimesString() {
        String hours = "";
        for (ScheduleTimeDTO t : times) {
            hours = hours + " " + t.get12HString();
        }
        return hours.trim();
    }

    public String getDaysString() {
        String[] all = new String[]{"Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"};
        String days = "";
        for (int d : weeklyRecurrenceDays) {
            days = days + " " + all[d];
        }
        return days.trim();
    }
}
