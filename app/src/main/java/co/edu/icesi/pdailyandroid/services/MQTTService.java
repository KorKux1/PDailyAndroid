package co.edu.icesi.pdailyandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import co.edu.icesi.pdailyandroid.broadcastreceivers.ActionReceiver;
import co.edu.icesi.pdailyandroid.communication.MQTTClientST;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;
import co.edu.icesi.pdailyandroid.viewmodel.NotificationLevoTakenViewModel;

public class MQTTService extends Service implements IMqttMessageListener {

    public static final String BROKER = "tcp://mqtt.eclipse.org:1883";
    public static final String clientId = "1143848900";
    private MqttClient client;

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

        if (client != null) {
            return START_STICKY;
        }

        new Thread(()->{
            MQTTClientST.connectToBrokerWithID("1143848901");
            MQTTClientST.suscribeToTopic("alfa", this);
        }).start();

        return START_STICKY;
    }

    @Override
    public void messageArrived(String topic, MqttMessage msg) {
        if (topic.equals("alfa")) {
            Intent intentAction = new Intent(this, ActionReceiver.class);
            NotificationLevoTakenViewModel model = new NotificationLevoTakenViewModel("Titulo", new String(msg.getPayload()), "03/12/2019 14:12:00", "SI");
            intentAction.putExtra("model", model);
            NotificationUtils.createNotification(this, 1, model.getNotificationTitle(), model.getNotificationDescription(), intentAction);
        }
    }
}
