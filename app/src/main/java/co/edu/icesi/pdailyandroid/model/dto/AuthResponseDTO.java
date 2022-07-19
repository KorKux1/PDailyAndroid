package co.edu.icesi.pdailyandroid.model.dto;

public class AuthResponseDTO {
    private String userName;
    private String token;

    public AuthResponseDTO(String userName, String token) {
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

    public boolean isValid()
    {
        return this.userName != null && this.token != null;
    }
}
