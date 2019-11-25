package co.edu.icesi.pdailyandroid.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.util.NotificationUtils;
import co.edu.icesi.pdailyandroid.viewmodel.SimpleNotification;

public class ActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);
        //Serializable obj = intent.getExtras().getSerializable("model");

        //if(obj instanceof NotificationFoodFollowUp){
        //    NotificationFoodFollowUp notifood = (NotificationFoodFollowUp) obj;
        //NotificationUtils.createSimpleNotification(context, 1, "PDaily", "No tiene novedades");
            Intent i = new Intent(context, DashBoard.class);
            i.putExtra("fragment","binnacle");
        //    i.putExtra("notification",notifood);
            context.startActivity(i);
        //}



    }
}
