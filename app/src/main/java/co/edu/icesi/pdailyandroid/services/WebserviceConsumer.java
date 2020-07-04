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


    public WebserviceConsumer postEvents(ArrayList<EventDTO> events) {
        process = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < events.size(); i++) {
                            Gson gson = new Gson();
                            String json = gson.toJson(events.get(i));
                            Log.e(">>>", json);
                            HTTPWebUtilDomi util = new HTTPWebUtilDomi();
                            util.setHeader("Content-Type", "application/json");
                            util.setHeader("pdaily-tenant", Constants.PDAILY_PASSWORD);
                            util.setBasicAuth("admin","admin");
                            String response = util.syncPOSTRequest(URL_EVENTS, json);

                            //Thread.sleep(1000);
                            //String response = "{\"bodyDetails\":[{\"bodyPartId\":\"30c0bca3-0189-4962-be52-e02de69a2664\",\"bodyPartName\":\"Rostro\",\"id\":\"cd93e8ee-0838-4662-8c06-7b48ae4311ca\"}],\"finalDate\":1581367179,\"id\":\"a0eb9e0e-2f1a-448f-b806-3c1c9c0aa898\",\"initialDate\":1581367169,\"injuryTypeId\":\"e58d8c80-b421-4ce2-8582-ab2f89330bb7\",\"injuryTypeName\":\"Temblor\",\"intensity\":5}";

                            listener.onResponse(response);
                        }
                    } catch (Exception e) {
                        listener.onResponse("ERROR");
                    }
                }
        );
        return this;
    }

    public WebserviceConsumer postAnimic(AnimicEventDTO animicEventDTO) {
        process = new Thread(
                () -> {
                    try {
                        Gson gson = new Gson();
                        String json = gson.toJson(animicEventDTO);
                        Log.e(">>>", json);
                        HTTPWebUtilDomi util = new HTTPWebUtilDomi();
                        util.setHeader("Content-Type", "application/json");
                        util.setHeader("pdaily-tenant", Constants.PDAILY_PASSWORD);
                        util.setBasicAuth("admin","admin");
                        String response = util.syncPOSTRequest(URL_ANIMIC, json);
                        //Thread.sleep(1000);
                        //listener.onResponse("{\"date\":1583263094126,\"id\":\"aa6b16a6-7ddb-4216-88b1-52cd88b7d8fc\",\"patientId\":\"df20d5bd-f16a-48b0-9922-0d5e537dcb24\"}");
                        listener.onResponse(response);
                    } catch (Exception e) {
                        listener.onResponse("ERROR");
                    }
                }
        );
        return this;
    }

    public WebserviceConsumer postFood(FoodEventDTO foodEventDTO) {
        process = new Thread(
                () -> {
                    try {

                        Gson gson = new Gson();
                        String json = gson.toJson(foodEventDTO);
                        Log.e(">>>", json);
                        HTTPWebUtilDomi util = new HTTPWebUtilDomi();
                        util.setHeader("Content-Type", "application/json");
                        util.setHeader("pdaily-tenant", Constants.PDAILY_PASSWORD);
                        util.setBasicAuth("admin","admin");
                        String response = util.syncPOSTRequest(URL_FOOD, json);

                        //Thread.sleep(1000);
                        //listener.onResponse("{\"date\":1583263094126,\"id\":\"aa6b16a6-7ddb-4216-88b1-52cd88b7d8fc\",\"patientId\":\"df20d5bd-f16a-48b0-9922-0d5e537dcb24\"}");

                        listener.onResponse(response);

                    } catch (Exception e) {
                        listener.onResponse("ERROR");
                    }
                }
        );
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
