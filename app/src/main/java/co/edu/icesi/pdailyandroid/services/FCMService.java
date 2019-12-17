package co.edu.icesi.pdailyandroid.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import androidx.core.app.NotificationCompat;
import co.edu.icesi.pdailyandroid.MainActivity;
import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.broadcastreceivers.ActionReceiver;
import co.edu.icesi.pdailyandroid.database.DataHandler;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class FCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            Log.d(">>>", "Message Notification Body: " + remoteMessage.getNotification().getBody());


            String json = remoteMessage.getNotification().getBody();
            NotificationFoodFollowUp obj = new Gson().fromJson(json, NotificationFoodFollowUp.class);
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


    @Override
    public void onNewToken(String token) {
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

}
