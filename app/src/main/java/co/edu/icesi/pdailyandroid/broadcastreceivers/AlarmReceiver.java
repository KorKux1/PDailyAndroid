package co.edu.icesi.pdailyandroid.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import co.edu.icesi.pdailyandroid.util.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {

    public static int numNot = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, ActionReceiver.class);
        numNot++;
        NotificationUtils.createSimpleNotification(context, numNot , "PDaily", "Advertencia "+intent.getExtras().getString("type","NONE"), i);

    }
}
