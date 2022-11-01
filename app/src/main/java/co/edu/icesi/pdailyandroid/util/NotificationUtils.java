package co.edu.icesi.pdailyandroid.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import co.edu.icesi.pdailyandroid.R;


public class NotificationUtils {

    public static final String CHANNEL_ID = "PDaily";
    public static final String CHANNEL_NAME = "PDailyCloud";
    public static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;

    public static void createNotification(Context context, int id, String title, String msg, Intent intentAction) {
        NotificationManager manager;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE);
            manager.createNotificationChannel(canal);
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intentAction, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        manager.notify(id, builder.build());
    }

    public static void createBigNotification(Context context, int id, String title, String msg, String textBlock, Intent intentAction) {
        NotificationManager manager;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE);
            manager.createNotificationChannel(canal);
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intentAction, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(textBlock))
                .setAutoCancel(true);

        manager.notify(id, builder.build());
    }

    public static Notification createSimpleNotification(Context context, int id, String title, String msg, Intent intentAction) {
        NotificationManager manager;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE);
            manager.createNotificationChannel(canal);
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intentAction, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.FLAG_FOREGROUND_SERVICE)
                .setVibrate(new long[]{0L})
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        manager.notify(id, builder.build());
        return builder.build();
    }
}
