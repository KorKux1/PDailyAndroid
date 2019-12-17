package co.edu.icesi.pdailyandroid.communication;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import co.edu.icesi.pdailyandroid.services.MQTTService;

public class MQTTClientST {

    private static MQTTClientST instance;

    public static MQTTClientST getInstance() {
        if (instance == null) {
            instance = new MQTTClientST();
        }
        return instance;
    }

    private MQTTClientST() {
    }


    private static MqttAndroidClient client = null;
    private static final String BROKER = "tcp://mqtt.eclipse.org:1883";

    public void connectToBrokerWithID(MQTTService context, String clientid, MqttCallback listener) {
        try {
            client = new MqttAndroidClient(context, BROKER, clientid);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            //connOpts.setAutomaticReconnect(true);
            connOpts.setCleanSession(true);
            client.setCallback(listener);
            client.connect(connOpts).setActionCallback(context);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void suscribeToTopic(String topic) {
        try {
            client.subscribe(topic, 0);
            Log.e(">>>", "Suscrito!");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String text) {
        Log.e(">>>", "Pub...");
        MqttMessage msg = new MqttMessage();
        msg.setPayload(text.getBytes());
        try {
            client.publish(topic, msg);
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e(">>>", "Error: " + e.getMessage());
        }
    }

    public boolean exists() {
        return client != null;
    }


    public boolean isConnected() {
        return client.isConnected();
    }

}
