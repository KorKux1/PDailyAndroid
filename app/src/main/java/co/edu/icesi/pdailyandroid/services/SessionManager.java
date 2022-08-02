package co.edu.icesi.pdailyandroid.services;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Context;

import com.google.gson.Gson;

import co.edu.icesi.pdailyandroid.model.dto.SchedulesCollectionDTO;
import co.edu.icesi.pdailyandroid.model.session.SessionData;

public class SessionManager {
    private static final String PREF_NAME = "LoginInformation";
    private static final String PREF_KEY_USER_ID = "UserId";
    private static final String PREF_KEY_PATIENT_ID = "PatientId";
    private static final String PREF_KEY_USER_NAME = "UserName";
    private static final String PREF_KEY_TOKEN = "Token";

    private SharedPreferences _preferences;
    private Editor _editor;

    private static final String PREF_SCHEDULES_NAME = "SchedulesInformation";
    private static final String PREF_KEY_SCHEDULES = "Schedules";

    private SharedPreferences schedulesPreferences;
    private Editor schedulesEditor;

    public SessionManager(Context context)
    {
        _preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        _editor = _preferences.edit();

        schedulesPreferences = context.getSharedPreferences(PREF_SCHEDULES_NAME, Context.MODE_PRIVATE);
        schedulesEditor = schedulesPreferences.edit();
    }

    public void createLoginSession(String userId, String patientId, String userName, String token)
    {
        deleteLoginSession();
        _editor.putString(PREF_KEY_USER_ID, userId);
        _editor.putString(PREF_KEY_PATIENT_ID, patientId);
        _editor.putString(PREF_KEY_USER_NAME, userName);
        _editor.putString(PREF_KEY_TOKEN, token);
        _editor.commit();
    }

    public void deleteLoginSession()
    {
        _editor.clear();
        _editor.commit();
    }

    public SessionData getSessionData()
    {
        String userId = _preferences.getString(PREF_KEY_USER_ID, null);
        String patientId = _preferences.getString(PREF_KEY_PATIENT_ID, null);
        String userName = _preferences.getString(PREF_KEY_USER_NAME, null);
        String token = _preferences.getString(PREF_KEY_TOKEN, null);
        return new SessionData(userId, patientId, userName, token);
    }

    public boolean isLoggedIn()
    {
        SessionData sessionData = getSessionData();
        return sessionData.IsValid();
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

    public SchedulesCollectionDTO getSchedules() {
        Gson gson = new Gson();
        String storedSchedules = getSchedulesInfo();
        SchedulesCollectionDTO schedulesCollection = gson.fromJson(
                storedSchedules, SchedulesCollectionDTO.class);
        return schedulesCollection;
    }
}
