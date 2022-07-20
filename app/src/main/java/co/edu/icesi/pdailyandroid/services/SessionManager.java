package co.edu.icesi.pdailyandroid.services;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Context;

import co.edu.icesi.pdailyandroid.model.session.SessionData;

public class SessionManager {
    private static final String PREF_NAME = "LoginInformation";
    private static final String PREF_KEY_USER_ID = "UserId";
    private static final String PREF_KEY_PATIENT_ID = "PatientId";
    private static final String PREF_KEY_USER_NAME = "UserName";
    private static final String PREF_KEY_TOKEN = "Token";

    private SharedPreferences _preferences;
    private Editor _editor;

    public SessionManager(Context context)
    {
        _preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        _editor = _preferences.edit();
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
}
