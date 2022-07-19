package co.edu.icesi.pdailyandroid.services;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Context;

import co.edu.icesi.pdailyandroid.model.session.SessionData;

public class SessionManager {
    private static final String PREF_NAME = "LoginInformation";
    private static final String PREF_KEY_USER = "User";
    private static final String PREF_KEY_TOKEN = "Token";

    private SharedPreferences _preferences;
    private Editor _editor;

    public SessionManager(Context context)
    {
        _preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        _editor = _preferences.edit();
    }

    public void createLoginSession(String userName, String token)
    {
        _editor.putString(PREF_KEY_USER, userName);
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
        String userName = _preferences.getString(PREF_KEY_USER, null);
        String token = _preferences.getString(PREF_KEY_TOKEN, null);
        return new SessionData(userName, token);
    }

    public boolean isLoggedIn()
    {
        String token = _preferences.getString(PREF_KEY_TOKEN, null);
        return token != null;
    }
}
