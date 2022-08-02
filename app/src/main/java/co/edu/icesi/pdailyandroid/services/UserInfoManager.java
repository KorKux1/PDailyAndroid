package co.edu.icesi.pdailyandroid.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserInfoManager {
    private static final String PREF_SCHEDULES_NAME = "SchedulesInformation";
    private static final String PREF_KEY_SCHEDULES = "Schedules";

    private SharedPreferences schedulesPreferences;
    private Editor schedulesEditor;

    public UserInfoManager(Context context) {
        schedulesPreferences = context.getSharedPreferences(PREF_SCHEDULES_NAME, Context.MODE_PRIVATE);
        schedulesEditor = schedulesPreferences.edit();
    }

    public void createSchedulesInfo(String schedules) {
        deleteSchedulesInfo();
        schedulesEditor.putString(PREF_KEY_SCHEDULES, schedules);
        schedulesEditor.commit();
    }

    public void deleteSchedulesInfo() {
        schedulesEditor.clear();
        schedulesEditor.commit();
    }

    public String getSchedulesInfo() {
        String schedulesInfo = schedulesPreferences.getString(PREF_KEY_SCHEDULES, null);
        return schedulesInfo;
    }
}
