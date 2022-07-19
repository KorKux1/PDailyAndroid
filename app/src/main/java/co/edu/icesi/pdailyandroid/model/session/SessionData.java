package co.edu.icesi.pdailyandroid.model.session;

public class SessionData {
    private String userName;
    private String token;

    public SessionData(String userName, String token)
    {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
