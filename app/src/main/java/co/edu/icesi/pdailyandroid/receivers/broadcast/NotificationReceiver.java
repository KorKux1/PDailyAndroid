package co.edu.icesi.pdailyandroid.receivers.broadcast;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.edu.icesi.pdailyandroid.MainActivity;
import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.WakeUpQuestionsActivity;

import co.edu.icesi.pdailyandroid.viewcontrollers.WakeUpQuestionsFragment;

public class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            String type = intent.getStringExtra("type").toUpperCase();
            String title = intent.getStringExtra("title");
            String contextText = intent.getStringExtra("contextText");
            String channelId = "PDAILY_"+ type;

            int flag = 0;

            // Crea un canal de notificación si no existe
          if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
                flag = PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT;
            }
          else {
                flag = PendingIntent.FLAG_UPDATE_CURRENT;
          }

          Intent activityIntent;
          PendingIntent pendingIntent;

          switch (type) {
              case "FOOD":
                  activityIntent = new Intent(context, WakeUpQuestionsActivity.class);
                  activityIntent.putExtra("openFragment", "fragment_food");
                  pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, flag);
                  break;

              default:
                  activityIntent = new Intent(context, WakeUpQuestionsActivity.class);
                  activityIntent.putExtra("openFragment", "fragment_wakeup_questions");
                  pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, flag);
                  break;
          }

            // Construye la notificación
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.logopdaily)
                    .setContentTitle(title)
                    .setContentText(contextText)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    //.setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            ContextCompat.startForegroundService(context, activityIntent);

            // Envía la notificación
            notificationManager.notify(9999, builder.build());

        }
}
