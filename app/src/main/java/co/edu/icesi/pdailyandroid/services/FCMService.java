package co.edu.icesi.pdailyandroid.services;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONObject;

import co.edu.icesi.pdailyandroid.localdatabase.DataHandler;
import co.edu.icesi.pdailyandroid.model.dto.FoodDTO;
import co.edu.icesi.pdailyandroid.model.dto.GenericDTO;
import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationFollowUp;
import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationType;
import co.edu.icesi.pdailyandroid.receivers.broadcast.ActionReceiver;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class FCMService extends FirebaseMessagingService {

    public static int countId = 0;

    public FCMService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e(">>> FROM", remoteMessage.getFrom());

        JSONObject object = new JSONObject(remoteMessage.getData());
        String json = object.toString();
        Log.e(">>> DATA", json);
        Intent intentAction = new Intent(this, ActionReceiver.class);
        NotificationUtils.createSimpleNotification(this, countId, "Alfa", remoteMessage.getData() + "", intentAction);
        countId++;

        Gson gson = new Gson();
        GenericDTO generic = gson.fromJson(json, GenericDTO.class);
        if ("food".equals(generic.getType())) {
            FoodDTO foodDTO = gson.fromJson(json, FoodDTO.class);
            NotificationFollowUp foodFollowUp = new NotificationFollowUp(
                    foodDTO.getId(),
                    "Ya comió?",
                    "Julio",
                    NotificationType.FOOD
            );
            DataHandler.getInstance(this).insertFoodNotification(foodFollowUp);
        }

        /*
        if (remoteMessage.getNotification() != null) {
            Log.d(">>>", "Message Notification Body: " + remoteMessage.getNotification().getBody());


            //String json = remoteMessage.getNotification().getBody();
            //NotificationFoodFollowUp obj = new Gson().fromJson(json, NotificationFoodFollowUp.class);
            //DataHandler.getInstance(this).insertFoodNotification(obj);


            //intentAction.putExtra("model", obj);
            //int messagesCount = DataHandler.getInstance(this).getNotificationCount();
            //String textBlock = DataHandler.getInstance(this).getBlockOfNotifications();


            Intent local = new Intent();
            local.setAction("com.hello.action");
            this.sendBroadcast(local);
        }
        */

    }

    @Override
    public void onNewToken(String token) {
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

}
