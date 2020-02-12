package co.edu.icesi.pdailyandroid.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.dto.EventDTO;

public class WebserviceConsumer {


    public static final String URL_EVENTS = Helper.getConfigValue("url_events");

    private OnResponseListener listener;

    public void setListener(OnResponseListener listener){
        this.listener = listener;
    }


    public void postEvents(ArrayList<EventDTO> events) {
        new Thread(
                ()->{
                    try {
                        for(int i=0 ; i<events.size() ; i++) {
                            Gson gson = new Gson();
                            String json = gson.toJson(events.get(i));
                            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
                            String response = util.POSTrequest(URL_EVENTS, json);
                            listener.onResponse(response);
                        }
                    } catch (IOException e) {
                        listener.onResponse("ERROR");
                    }
                }
        ).start();
    }

    public interface OnResponseListener{
        void onResponse(String response);
    }


}
