package co.edu.icesi.pdailyandroid.services;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import co.edu.icesi.pdailyandroid.model.dto.AuthDTO;
import co.edu.icesi.pdailyandroid.model.dto.AuthResponseDTO;
import co.edu.icesi.pdailyandroid.util.Constants;

public class AuthService {

    private SessionManager sessionManager;

    public AuthService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public boolean authenticate(String userName, String password) {
        AuthDTO authDTO = new AuthDTO(userName, password);
        String authJson = new Gson().toJson(authDTO);
        String response = PDailyHttpClient.doPostRequest(
                Constants.SERVER_BASE_URL + "/api/auth", authJson);
        AuthResponseDTO authResponse = new Gson().fromJson(response, AuthResponseDTO.class);
        if (authResponse != null && authResponse.isValid()) {
            this.sessionManager.createLoginSession(
                    authResponse.getUserName(),
                    authResponse.getToken());
            return true;
        }

        return false;
    }
}
