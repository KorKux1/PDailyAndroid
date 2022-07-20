package co.edu.icesi.pdailyandroid.model.session;

public class SessionData {
    private String userId;
    private String patientId;
    private String userName;
    private String token;

    public SessionData(String userId, String patientId, String userName, String token) {
        this.userId = userId;
        this.patientId = patientId;
        this.userName = userName;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public boolean IsValid() {
        return this.userId != null && this.patientId != null &&
            this.userName != null && this.token != null;
    }
}
