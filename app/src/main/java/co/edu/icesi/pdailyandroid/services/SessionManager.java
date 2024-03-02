package co.edu.icesi.pdailyandroid.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import co.edu.icesi.pdailyandroid.model.dto.SchedulesCollectionDTO;
import co.edu.icesi.pdailyandroid.model.session.SessionData;

public class SessionManager {
    // Login
    private static final String PREF_LOGIN_NAME = "LoginInformation";
    private static final String PREF_KEY_USER_ID = "UserId";
    private static final String PREF_KEY_PATIENT_ID = "PatientId";
    private static final String PREF_KEY_USER_NAME = "UserName";
    private static final String PREF_KEY_TOKEN = "Token";
    // Schedules
    private static final String PREF_SCHEDULES_NAME = "SchedulesInformation";
    private static final String PREF_KEY_SCHEDULES = "Schedules";
    // Usage
    private static final String PREF_USAGE_NAME = "UsageInformation";
    private static final String PREF_KEY_MOOD_INSTRUCTIONS = "MoodInstructionsShown";
    // Editors
    private final SharedPreferences loginPreferences;
    private final Editor loginEditor;
    private final SharedPreferences schedulesPreferences;
    private final Editor schedulesEditor;
    private final SharedPreferences usagePreferences;
    private final Editor usageEditor;

    public SessionManager(Context context) {
        loginPreferences = context.getSharedPreferences(PREF_LOGIN_NAME, Context.MODE_PRIVATE);
        loginEditor = loginPreferences.edit();
        schedulesPreferences = context.getSharedPreferences(PREF_SCHEDULES_NAME, Context.MODE_PRIVATE);
        schedulesEditor = schedulesPreferences.edit();
        usagePreferences = context.getSharedPreferences(PREF_USAGE_NAME, Context.MODE_PRIVATE);
        usageEditor = usagePreferences.edit();
    }

    public void saveLoginData(String userId, String patientId, String userName, String token) {
        deleteLoginData();
        loginEditor.putString(PREF_KEY_USER_ID, userId);
        loginEditor.putString(PREF_KEY_PATIENT_ID, patientId);
        loginEditor.putString(PREF_KEY_USER_NAME, userName);
        loginEditor.putString(PREF_KEY_TOKEN, token);
        loginEditor.commit();
    }

    public void deleteLoginData() {
        loginEditor.clear();
        loginEditor.commit();
    }

    public SessionData loadLoginData() {
        String userId = loginPreferences.getString(PREF_KEY_USER_ID, null);
        String patientId = loginPreferences.getString(PREF_KEY_PATIENT_ID, null);
        String userName = loginPreferences.getString(PREF_KEY_USER_NAME, null);
        String token = loginPreferences.getString(PREF_KEY_TOKEN, null);
        return new SessionData(userId, patientId, userName, token);
    }

    public boolean isLoggedIn() {
        SessionData sessionData = loadLoginData();
        return sessionData.IsValid();
    }

    public void saveSchedulesData(String schedules) {
        deleteSchedulesData();
        schedulesEditor.putString(PREF_KEY_SCHEDULES, schedules);
        schedulesEditor.commit();
    }

    public void deleteSchedulesData() {
        schedulesEditor.clear();
        schedulesEditor.commit();
    }

    public SchedulesCollectionDTO loadSchedulesData() {
        Gson gson = new Gson();
        String schedules = schedulesPreferences.getString(PREF_KEY_SCHEDULES, null);
        SchedulesCollectionDTO schedulesCollection = gson.fromJson(
                schedules, SchedulesCollectionDTO.class);
        return schedulesCollection;
    }

    public void setMoodInstructionsShown() {
        usageEditor.putBoolean(PREF_KEY_MOOD_INSTRUCTIONS, true);
        usageEditor.commit();
    }

    public boolean getMoodInstructionsShown() {
        return usagePreferences.getBoolean(PREF_KEY_MOOD_INSTRUCTIONS, false);
    }
}
