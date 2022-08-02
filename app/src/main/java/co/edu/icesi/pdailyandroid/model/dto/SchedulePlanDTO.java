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
}
