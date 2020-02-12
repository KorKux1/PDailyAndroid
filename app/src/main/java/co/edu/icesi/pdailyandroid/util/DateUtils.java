package co.edu.icesi.pdailyandroid.util;

import java.util.Calendar;

public class DateUtils {

    public static String getHourInString(Calendar calendarFrom) {
        int hour = calendarFrom.get(Calendar.HOUR);
        int minutes = calendarFrom.get(Calendar.MINUTE);
        int AM_PM = calendarFrom.get(Calendar.AM_PM);
        String strHour = (""+hour).length()==1?"0"+hour:""+hour;
        String strMinute = (""+minutes).length()==1?"0"+minutes:""+minutes;
        if(AM_PM == Calendar.AM){
            if(hour == 0) strHour = "12";
            return strHour+":"+strMinute+" AM";
        }else{
            return strHour+":"+strMinute+" PM";
        }
    }

}
