package co.edu.icesi.pdailyandroid.services;

import com.google.gson.Gson;

import co.edu.icesi.pdailyandroid.model.dto.SchedulesCollectionDTO;
import co.edu.icesi.pdailyandroid.model.session.SessionData;
import co.edu.icesi.pdailyandroid.util.Constants;

public class UserInfoService {
    private SessionManager sessionManager;

    public UserInfoService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public boolean updateSchedulesCollectionFromServer() {
        Gson gson = new Gson();
        SessionData sessionData = sessionManager.getSessionData();
        String url = Constants.SERVER_BASE_URL + "/api/patients/" +
                sessionData.getPatientId() + "/schedules";
        String response = PDailyHttpClient.doGetRequest(url, sessionData.getToken());
        SchedulesCollectionDTO schedules = gson.fromJson(response, SchedulesCollectionDTO.class);
        if (schedules != null && schedules.isValid()) {
            this.sessionManager.createSchedulesInfo(response);
            return true;
        }

        return false;
    }
}
