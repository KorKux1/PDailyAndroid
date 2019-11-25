package co.edu.icesi.pdailyandroid.communication;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTClientST {

    private static MqttClient instance = null;
    private static final String BROKER = "tcp://mqtt.eclipse.org:1883";

    public synchronized static void connectToBrokerWithID(String clientid){
        if(instance == null){
            try {
                instance = new MqttClient(BROKER, clientid, null);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setAutomaticReconnect(true);
                connOpts.setCleanSession(true);
                instance.connect(connOpts);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    private MQTTClientST(){}

    public static void suscribeToTopic(String topic, IMqttMessageListener listener) {
        try {
            instance.subscribe(topic, listener);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    public static void publish(String topic, String text) {
        Log.e(">>>","Pub...");
        MqttMessage msg = new MqttMessage();
        msg.setPayload(text.getBytes());
        try {
            instance.publish(topic,msg);
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e(">>>","Error: "+e.getMessage());
        }
    }

    public static void reconectIfDisconnected() {
        if(!instance.isConnected()){
            try {
                instance.reconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean isConnected(){
        return instance.isConnected();
    }

    public void disconnect(){
        try {
            instance.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
