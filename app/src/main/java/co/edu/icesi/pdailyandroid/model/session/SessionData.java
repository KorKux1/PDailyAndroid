package co.edu.icesi.pdailyandroid.model.session;

public class SessionData {
    private String _userName;
    private String _token;

    public SessionData(String userName, String token)
    {
        _userName = userName;
        _token = token;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        this._userName = userName;
    }

    public String getToken() {
        return _token;
    }

    public void setToken(String token) {
        this._token = token;
    }
}
