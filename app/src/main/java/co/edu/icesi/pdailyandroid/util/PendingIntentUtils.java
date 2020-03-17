package co.edu.icesi.pdailyandroid.util;

import android.app.PendingIntent;
import android.content.Intent;

import co.edu.icesi.pdailyandroid.app.App;

public class PendingIntentUtils {

    public static boolean isPendingIntentRegistered(int requestCode, Intent intent){
        return PendingIntent.getBroadcast(App.getAppContext(), requestCode, intent, PendingIntent.FLAG_NO_CREATE) != null;
    }

}
