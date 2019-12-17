package co.edu.icesi.pdailyandroid.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.broadcastreceivers.ActionReceiver;
import co.edu.icesi.pdailyandroid.communication.MQTTClientST;
import co.edu.icesi.pdailyandroid.database.DataHandler;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;

import static android.os.PowerManager.PARTIAL_WAKE_LOCK;

public class MQTTService extends Service implements MqttCallback, IMqttActionListener {

    public static String clientId = null;
    public static final String FOOD_TOPIC = "pdaily/food/";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(">>>", "onCreate");


        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PARTIAL_WAKE_LOCK, "EndlessService::lock");
        wakeLock.acquire();


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //new Thread(
        //        ()->{
        clientId = this.getSharedPreferences("user", MODE_PRIVATE).getString("clienteid", null);
        MQTTClientST.getInstance().connectToBrokerWithID(this, clientId, this);
        //        }
        //).start();
        Log.e(">>>", "onStartCommand");
        startForeground(1, NotificationUtils.createSimpleNotification(this, 1, "PDaily", "Loading...", new Intent(MQTTService.this, ActionReceiver.class)));
        return START_STICKY;
    }


    @Override
    public void connectionLost(Throwable cause) {
        NotificationUtils.createSimpleNotification(this, 1, "PDaily", "Coneccion perdida: " + cause.getMessage(), new Intent(MQTTService.this, ActionReceiver.class));
        //new Thread(
        //        () -> {
        clientId = this.getSharedPreferences("user", MODE_PRIVATE).getString("clienteid", null);
        MQTTClientST.getInstance().connectToBrokerWithID(this, clientId, this);
        //        }
        //).start();

    }

    @Override
    public void messageArrived(String topic, MqttMessage msg) {
        Log.e(">>>", "Receiver from topic: " + topic);
        //new Thread(
        //        ()->{
        try {
            if (topic.equals(FOOD_TOPIC + clientId)) {
                String json = new String(msg.getPayload(), "UTF-8").trim();
                Log.e(">>>", "Json recibido: " + json);
                if (json.contains("confirmado")) {
                    Log.e(">>>", "Recibi un confirmado!");
                    return;
                }
                NotificationFoodFollowUp obj = new Gson().fromJson(json, NotificationFoodFollowUp.class);
                Log.e(">>>", "Objeto: id:" + obj.getId() + " name:" + obj.getName());
                if (!DataHandler.getInstance(this).notificationExists(obj)) {
                    Log.e(">>>", "La notificacion no existe, entonces se agrega");
                    DataHandler.getInstance(this).insertFoodNotification(obj);
                    Intent intentAction = new Intent(this, ActionReceiver.class);
                    intentAction.putExtra("model", obj);
                    int messagesCount = DataHandler.getInstance(this).getNotificationCount();
                    String textBlock = DataHandler.getInstance(this).getBlockOfNotifications();
                    NotificationUtils.createBigNotification(this, 1, "Atención", "Tiene " + messagesCount + " mensajes", "Tiene " + messagesCount + " mensajes:\n" + textBlock, intentAction);

                    Intent local = new Intent();
                    local.setAction("com.hello.action");
                    this.sendBroadcast(local);
                }

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(">>>", e.getLocalizedMessage());
        }
        //        }
        //).start();

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }


    @Override
    public void onDestroy() {
        Log.e(">>>", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        NotificationUtils.createSimpleNotification(this, 1, "PDaily", "Conectado a PDaily", new Intent(MQTTService.this, ActionReceiver.class));
        //new Thread(
        //        ()->{
        NotificationUtils.createSimpleNotification(this, 1, "PDaily", "Suscrito", new Intent(MQTTService.this, ActionReceiver.class));
        MQTTClientST.getInstance().suscribeToTopic(FOOD_TOPIC + clientId);
        //        }
        //).start();
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

    }
}
