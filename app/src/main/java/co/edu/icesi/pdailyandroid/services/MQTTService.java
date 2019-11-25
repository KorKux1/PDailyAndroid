package co.edu.icesi.pdailyandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

import co.edu.icesi.pdailyandroid.broadcastreceivers.ActionReceiver;
import co.edu.icesi.pdailyandroid.communication.MQTTClientST;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class MQTTService extends Service implements IMqttMessageListener {

    public static String clientId = null;
    public static final String FOOD_TOPIC = "pdaily/food/";
    private MqttClient client = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        clientId = this.getSharedPreferences("user",MODE_PRIVATE).getString("clienteid",null);
        Log.e(">>>",""+clientId);
        if(clientId == null) return START_NOT_STICKY;


        if (client != null) {
            new Thread(
                    ()-> {
                        MQTTClientST.reconectIfDisconnected();
                        MQTTClientST.suscribeToTopic(FOOD_TOPIC+clientId, this);
                    }
            ).start();
            return START_STICKY;
        }

        new Thread(()->{
            MQTTClientST.connectToBrokerWithID("1143848901");
            MQTTClientST.suscribeToTopic(FOOD_TOPIC+clientId, this);
            Log.e(">>>","Sucribed to " + FOOD_TOPIC+clientId);
        }).start();

        return START_STICKY;
    }

    @Override
    public void messageArrived(String topic, MqttMessage msg) {
        Log.e(">>>","Receiver from topic: " + topic);
        try {
            if (topic.equals(FOOD_TOPIC + clientId)) {
                String json = new String(msg.getPayload(), "UTF-8").trim();
                NotificationFoodFollowUp obj = new Gson().fromJson(json, NotificationFoodFollowUp.class);
                Intent intentAction = new Intent(this, ActionReceiver.class);
                intentAction.putExtra("model", obj);
                NotificationUtils.createNotification(this, 1, "Alimentación", obj.getName(), intentAction);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
