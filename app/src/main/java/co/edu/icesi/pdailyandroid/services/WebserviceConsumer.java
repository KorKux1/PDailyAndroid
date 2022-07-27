package co.edu.icesi.pdailyandroid.services;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.model.dto.AnimicEventDTO;
import co.edu.icesi.pdailyandroid.model.dto.EventDTO;
import co.edu.icesi.pdailyandroid.model.dto.FoodEventDTO;
import co.edu.icesi.pdailyandroid.util.Constants;

public class WebserviceConsumer {

    public static final String URL_EVENTS = Helper.getConfigValue("url_events");
    public static final String URL_FOOD = Helper.getConfigValue("url_food");
    public static final String URL_ANIMIC = Helper.getConfigValue("url_animic");

    private OnResponseListener listener;
    private Thread process;

    public WebserviceConsumer withEndAction(OnResponseListener listener) {
        this.listener = listener;
        return this;
    }

    public WebserviceConsumer postEvents(ArrayList<EventDTO> events, String authToken) {
        process = new Thread(() -> {
            try {
                boolean success = true;
                for (EventDTO event : events) {
                    Gson gson = new Gson();
                    String json = gson.toJson(event);
                    Log.e(">>>", json);
                    String url = Constants.SERVER_BASE_URL + "/api/patients/" +
                            event.getPatientId() + "/events/physical";
                    String response = PDailyHttpClient.doPostRequest(url, json, authToken);
                    if (response == null) {
                        success = false;
                    }
                }
                listener.onResponse(success ? "SUCCESS" : "ERROR");
            } catch (Exception e) {
                listener.onResponse("ERROR");
            }
        });
        return this;
    }

    public WebserviceConsumer postAnimic(AnimicEventDTO animicEventDTO, String authToken) {
        process = new Thread(() -> {
            try {
                Gson gson = new Gson();
                String json = gson.toJson(animicEventDTO);
                Log.e(">>>", json);
                String url = Constants.SERVER_BASE_URL + "/api/patients/" +
                        animicEventDTO.getPatientId() + "/events/mood";
                String response = PDailyHttpClient.doPostRequest(url, json, authToken);
                listener.onResponse(response == null ? "ERROR" : "SUCCESS");
            } catch (Exception e) {
                listener.onResponse("ERROR");
            }
        });
        return this;
    }

    public WebserviceConsumer postFood(FoodEventDTO foodEventDTO, String authToken) {
        process = new Thread(() -> {
            try {
                Gson gson = new Gson();
                String json = gson.toJson(foodEventDTO);
                Log.e(">>>", json);
                String url = Constants.SERVER_BASE_URL + "/api/patients/" +
                        foodEventDTO.getPatientId() + "/events/food";
                String response = PDailyHttpClient.doPostRequest(url, json, authToken);
                listener.onResponse(response == null ? "ERROR" : "SUCCESS");
            } catch (Exception e) {
                listener.onResponse("ERROR");
            }
        });
        return this;
    }

    public void execute() {
        process.start();
    }

    public interface OnResponseListener {
        void onResponse(String response);
    }

    public interface IPromise {
        void withEndAction(IAction response);
    }

    public interface IAction {
        void withEndAction(String response);
    }
}
