package co.edu.icesi.pdailyandroid.util;

import java.util.Calendar;

public class DateUtils {

    public static String getHourInString(Calendar calendarFrom) {
        int hour = calendarFrom.get(Calendar.HOUR_OF_DAY);
        int minute = calendarFrom.get(Calendar.MINUTE);
        int AM_PM = calendarFrom.get(Calendar.AM_PM);

        if (AM_PM == Calendar.PM && hour >= 13) hour -= 12;
        String hh = String.format("%02d", hour);
        String mm = String.format("%02d", minute);
        String tt = AM_PM == Calendar.PM ? "PM" : "AM";

        return hh + ":" + mm + " " + tt;
    }
}
