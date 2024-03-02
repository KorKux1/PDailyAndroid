package co.edu.icesi.pdailyandroid.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import co.edu.icesi.pdailyandroid.BuildConfig;

public class App extends Application {

    private static Context context;

    public static Context getAppContext() {
        return App.context;
    }

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();

     /*   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("PDAILY", "PDAILY", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }*/
    }
}
