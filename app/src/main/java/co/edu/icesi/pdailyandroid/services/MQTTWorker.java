package co.edu.icesi.pdailyandroid.services;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class MQTTWorker {

    public static String CLIENT_ID = null;
    private static MqttAndroidClient client = null;
    private static final String BROKER = "tcp://mqtt.eclipse.org:1883";
    public static final String FOOD_TOPIC = "pdaily/food/";

    public static void connectToBroker(Context context) {
        if (client == null) {
            try {
                CLIENT_ID = context.getSharedPreferences("user", Context.MODE_PRIVATE).getString("clienteid", null);
                client = new MqttAndroidClient(context, BROKER, CLIENT_ID);
                MqttConnectOptions options = new MqttConnectOptions();
                options.setAutomaticReconnect(true);
                options.setCleanSession(true);

                client.connect(options).setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.e(">>>", "Conected!");
                        try {
                            client.subscribe(FOOD_TOPIC + CLIENT_ID, 0,
                                    (topic, message) -> {
                                        String msg = new String(message.getPayload());
                                        Log.e(">>>",msg);
                                        NotificationUtils.createSimpleNotification(context, 1,"Alfa",msg);
                                    }
                            );
                        } catch (MqttException ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            } catch (MqttException ex) {
                ex.printStackTrace();
            }
        }
    }


}
