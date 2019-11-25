package co.edu.icesi.pdailyandroid.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.viewmodel.NotificationLevoTakenViewModel;
import co.edu.icesi.pdailyandroid.viewmodel.NotificationViewModel;

public class ActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);

        NotificationViewModel model = (NotificationViewModel) intent.getExtras().getSerializable("model");
        Intent i = new Intent(context, DashBoard.class);
        i.putExtra("fragment","binnacle");
        i.putExtra("notification",model);
        context.startActivity(i);

    }
}
