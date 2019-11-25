package co.edu.icesi.pdailyandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

import co.edu.icesi.pdailyandroid.broadcastreceivers.ActionReceiver;
import co.edu.icesi.pdailyandroid.communication.MQTTClientST;
import co.edu.icesi.pdailyandroid.database.DataHandler;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class MQTTService extends Service implements MqttCallback {

    public static String clientId = null;
    public static final String FOOD_TOPIC = "pdaily/food/";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        Log.e(">>>","onCreate");
        startForeground(1, NotificationUtils.createSimpleNotification(this, 1, "PDaily", "No tiene novedades"));
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(">>>","onStartCommand");
        clientId = this.getSharedPreferences("user", MODE_PRIVATE).getString("clienteid", null);
        Log.e(">>>", "" + clientId);
        if (clientId == null) {
            return START_NOT_STICKY;
        }

        if (MQTTClientST.getInstance().exists()) {
            new Thread(
                    () -> {
                        MQTTClientST.getInstance().reconectIfDisconnected();
                        MQTTClientST.getInstance().suscribeToTopic(FOOD_TOPIC + clientId);
                    }
            ).start();
            return START_STICKY;
        }

        new Thread(
                () -> {
                    MQTTClientST.getInstance().connectToBrokerWithID("1143848901", this);
                    MQTTClientST.getInstance().suscribeToTopic(FOOD_TOPIC + clientId);
                    Log.e(">>>", "Sucribed to " + FOOD_TOPIC + clientId);
                }
        ).start();

        return START_STICKY;
    }



    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage msg) {
        Log.e(">>>", "Receiver from topic: " + topic);
        try {
            if (topic.equals(FOOD_TOPIC + clientId)) {
                String json = new String(msg.getPayload(), "UTF-8").trim();
                Log.e(">>>", "Json recibido: "+json);
                if (json.contains("confirmado")) {
                    Log.e(">>>", "Recibi un confirmado!");
                    return;
                }
                NotificationFoodFollowUp obj = new Gson().fromJson(json, NotificationFoodFollowUp.class);
                Log.e(">>>", "Objeto: id:"+obj.getId()+" name:"+obj.getName());
                if(!DataHandler.getInstance(this).notificationExists(obj)) {
                    Log.e(">>>", "La notificacion no existe, entonces se agrega");
                    DataHandler.getInstance(this).insertFoodNotification(obj);
                    Intent intentAction = new Intent(this, ActionReceiver.class);
                    intentAction.putExtra("model", obj);
                    int messagesCount = DataHandler.getInstance(this).getNotificationCount();
                    String textBlock = DataHandler.getInstance(this).getBlockOfNotifications();
                    NotificationUtils.createBigNotification(this, 1, "Atención", "Tiene "+messagesCount+" mensajes", "Tiene "+messagesCount+" mensajes:\n"+textBlock,intentAction);
                }

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(">>>", e.getLocalizedMessage());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }



    @Override
    public void onDestroy() {
        Log.e(">>>","onDestroy");
        super.onDestroy();
    }
}
