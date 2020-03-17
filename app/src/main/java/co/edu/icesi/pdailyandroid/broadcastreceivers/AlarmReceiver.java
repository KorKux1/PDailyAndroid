package co.edu.icesi.pdailyandroid.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.app.App;
import co.edu.icesi.pdailyandroid.database.DataHandler;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {

    public static int numNot = 0;

    @Override
    public void onReceive(Context context, Intent intent) {


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String type = intent.getExtras().getString("type","NONE");
        Intent i = new Intent(context, ActionReceiver.class);

        switch (type){
            case "FOOD01":
                numNot = 1;
                NotificationFoodFollowUp desayuno = new NotificationFoodFollowUp(UUID.randomUUID().toString(), "¿Ya desayunaste?", sdf.format(Calendar.getInstance().getTime()));
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(desayuno);
                NotificationUtils.createSimpleNotification(context, numNot , "PDaily", "¿Ya desayunaste?", i);
                break;
            case "FOOD02":
                numNot = 2;
                NotificationFoodFollowUp almuerzo = new NotificationFoodFollowUp(UUID.randomUUID().toString(), "¿Ya almorzaste?", sdf.format(Calendar.getInstance().getTime()));
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(almuerzo);
                NotificationUtils.createSimpleNotification(context, numNot , "PDaily", "¿Ya almorzaste?", i);
                break;
            case "FOOD03":
                numNot = 3;
                NotificationFoodFollowUp cena = new NotificationFoodFollowUp(UUID.randomUUID().toString(), "¿Ya cenaste?", sdf.format(Calendar.getInstance().getTime()));
                DataHandler.getInstance(App.getAppContext()).insertOrUpdateFoodNotification(cena);
                NotificationUtils.createSimpleNotification(context, numNot , "PDaily", "¿Ya cenaste?", i);
                break;
        }





    }
}
