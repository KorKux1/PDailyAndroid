package co.edu.icesi.pdailyandroid.communication;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTClientST {

    private static MQTTClientST instance;
    public static MQTTClientST getInstance(){
        if(instance == null){
            instance = new MQTTClientST();
        }
        return instance;
    }
    private MQTTClientST(){}


    private static MqttClient client = null;
    private static final String BROKER = "tcp://mqtt.eclipse.org:1883";

    public void connectToBrokerWithID(String clientid, MqttCallback listener){
        if(client == null){
            try {
                client = new MqttClient(BROKER, clientid, null);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setAutomaticReconnect(true);
                connOpts.setCleanSession(true);
                client.setCallback(listener);
                client.connect(connOpts);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }


    public void suscribeToTopic(String topic) {
        try {
            Log.e(">>>","Suscrito!");
            client.subscribe(topic);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    public void publish(String topic, String text) {
        Log.e(">>>","Pub...");
        MqttMessage msg = new MqttMessage();
        msg.setPayload(text.getBytes());
        try {
            client.publish(topic,msg);
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e(">>>","Error: "+e.getMessage());
        }
    }

    public void reconectIfDisconnected() {
        Log.e(">>>","Reconectado si desconecto");
        if(!client.isConnected()){
            Log.e(">>>","No se encontraba conectado");
            try {
                client.reconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean exists() {
        return client != null;
    }


    public boolean isConnected(){
        return client.isConnected();
    }

    public void disconnect(){
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }



}
