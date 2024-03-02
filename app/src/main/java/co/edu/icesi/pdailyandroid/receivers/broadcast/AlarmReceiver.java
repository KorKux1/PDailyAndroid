package co.edu.icesi.pdailyandroid.receivers.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import co.edu.icesi.pdailyandroid.WakeUpQuestionsActivity;
import co.edu.icesi.pdailyandroid.app.App;
import co.edu.icesi.pdailyandroid.localdatabase.DataHandler;
import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationFollowUp;
import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationType;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {

    public static int notificationNumber = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = sdf.format(Calendar.getInstance().getTime());
        String type = intent.getExtras().getString("type", "NONE");
        Intent i = new Intent(context, WakeUpQuestionsActivity.class);
        i.putExtra("openFragment", "fragment_food");
        ContextCompat.startForegroundService(context, i);

        switch (type) {
            case "FOOD01":
                notificationNumber = 1;
                NotificationFollowUp breakfast = new NotificationFollowUp(UUID.randomUUID().toString(), "¿Ya desayunaste?", date, NotificationType.FOOD);
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(breakfast);
                NotificationUtils.createSimpleNotification(context, notificationNumber, "PDaily", "¿Ya desayunaste?", i);
                break;
            case "FOOD02":
                notificationNumber = 2;
                NotificationFollowUp lunch = new NotificationFollowUp(UUID.randomUUID().toString(), "¿Ya almorzaste?", date, NotificationType.FOOD);
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(lunch);
                NotificationUtils.createSimpleNotification(context, notificationNumber, "PDaily", "¿Ya almorzaste?", i);
                break;
            case "FOOD03":
                notificationNumber = 3;
                NotificationFollowUp dinner = new NotificationFollowUp(UUID.randomUUID().toString(), "¿Ya cenaste?", date, NotificationType.FOOD);
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(dinner);
                NotificationUtils.createSimpleNotification(context, notificationNumber, "PDaily", "¿Ya cenaste?", i);
                break;
            case "LEVO":
                notificationNumber = 4;
                NotificationFollowUp levo = new NotificationFollowUp(UUID.randomUUID().toString(), "¿Ya tomaste tu medicina?", date, NotificationType.LEVO);
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateLevoNotification(levo);
                NotificationUtils.createSimpleNotification(context, notificationNumber, "PDaily", "¿Ya tomaste tu medicina?", i);
                break;
        }
    }
}
