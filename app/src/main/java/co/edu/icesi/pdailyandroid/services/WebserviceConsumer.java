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
                            Log.e(">>>",json);
                            HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
                            String response = util.POSTrequest(URL_EVENTS, json);

                            //Thread.sleep(5000);


                            //String response = "{\"bodyDetails\":[{\"bodyPartId\":\"30c0bca3-0189-4962-be52-e02de69a2664\",\"bodyPartName\":\"Rostro\",\"id\":\"cd93e8ee-0838-4662-8c06-7b48ae4311ca\"}],\"finalDate\":1581367179,\"id\":\"a0eb9e0e-2f1a-448f-b806-3c1c9c0aa898\",\"initialDate\":1581367169,\"injuryTypeId\":\"e58d8c80-b421-4ce2-8582-ab2f89330bb7\",\"injuryTypeName\":\"Temblor\",\"intensity\":5}";
                            listener.onResponse(response);
                        }
                    } catch (Exception e) {
                        listener.onResponse("ERROR");
                    }
                }
        ).start();
    }

    public interface OnResponseListener{
        void onResponse(String response);
    }


}
