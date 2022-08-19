package co.edu.icesi.pdailyandroid.receivers.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import co.edu.icesi.pdailyandroid.app.App;
import co.edu.icesi.pdailyandroid.localdatabase.DataHandler;
import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {

    public static int notificationNumber = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = sdf.format(Calendar.getInstance().getTime());
        String type = intent.getExtras().getString("type", "NONE");
        Intent i = new Intent(context, ActionReceiver.class);

        switch (type) {
            case "FOOD01":
                notificationNumber = 1;
                NotificationFoodFollowUp breakfast = new NotificationFoodFollowUp(UUID.randomUUID().toString(), "¿Ya desayunaste?", date);
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(breakfast);
                NotificationUtils.createSimpleNotification(context, notificationNumber, "PDaily", "¿Ya desayunaste?", i);
                break;
            case "FOOD02":
                notificationNumber = 2;
                NotificationFoodFollowUp lunch = new NotificationFoodFollowUp(UUID.randomUUID().toString(), "¿Ya almorzaste?", date);
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(lunch);
                NotificationUtils.createSimpleNotification(context, notificationNumber, "PDaily", "¿Ya almorzaste?", i);
                break;
            case "FOOD03":
                notificationNumber = 3;
                NotificationFoodFollowUp dinner = new NotificationFoodFollowUp(UUID.randomUUID().toString(), "¿Ya cenaste?", date);
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(dinner);
                NotificationUtils.createSimpleNotification(context, notificationNumber, "PDaily", "¿Ya cenaste?", i);
                break;
        }
    }
}
