package co.edu.icesi.pdailyandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.communication.MQTTClientST;
import co.edu.icesi.pdailyandroid.interfaces.INotification;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.services.MQTTService;
import co.edu.icesi.pdailyandroid.viewmodel.SimpleNotification;

public class NotificationsAdapter extends BaseAdapter {

    private ArrayList<INotification> notifications;

    public NotificationsAdapter(ArrayList<INotification> simpleNotifications) {
        this.notifications = simpleNotifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = null;

        if(notifications.get(position) instanceof NotificationFoodFollowUp){

            NotificationFoodFollowUp noti = (NotificationFoodFollowUp) notifications.get(position);

            row = inflater.inflate(R.layout.notificationfoodcell, null, false);
            TextView notificationtitle = row.findViewById(R.id.notificationtitle);
            TextView notificationmessage = row.findViewById(R.id.notificationmessage);
            TextView notificationDate = row.findViewById(R.id.notificationdate);
            Button notificationButton = row.findViewById(R.id.notificationButton);

            notificationtitle.setText("Alimentación");
            notificationmessage.setText(noti.getName());
            notificationDate.setText(noti.getDate().split(" ")[0]+"\n"+noti.getDate().split(" ")[1]);
            notificationButton.setText("SI");
            notificationButton.setOnClickListener(
                    (v)-> MQTTClientST.publish(MQTTService.FOOD_TOPIC+MQTTService.clientId,
                            "{\"id\":\"8dd588bb-c8de-4f0f-900a-6a38af2865b5\",\"name\":\"confirmado\",\"date\":\"20/11/2019 14:29:01\"}")
            );
        }



        return row;
    }

}
