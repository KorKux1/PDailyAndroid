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
import co.edu.icesi.pdailyandroid.services.MQTTService;
import co.edu.icesi.pdailyandroid.viewmodel.NotificationLevoTakenViewModel;
import co.edu.icesi.pdailyandroid.viewmodel.NotificationViewModel;

public class NotificationsAdapter extends BaseAdapter {

    private ArrayList<NotificationViewModel> notifications;

    public NotificationsAdapter(ArrayList<NotificationViewModel> notifications) {
        this.notifications = notifications;
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

        if(notifications.get(position) instanceof NotificationLevoTakenViewModel){
            row = inflater.inflate(R.layout.notificationlevotakencell, null, false);
            TextView notificationtitle = row.findViewById(R.id.notificationtitle);
            TextView notificationmessage = row.findViewById(R.id.notificationmessage);
            TextView notificationDate = row.findViewById(R.id.notificationDate);
            Button notificationButton = row.findViewById(R.id.notificationButton);

            notificationtitle.setText(notifications.get(position).getNotificationTitle());
            notificationmessage.setText(notifications.get(position).getNotificationDescription());
            notificationDate.setText(notifications.get(position).getNotificationDate());
            notificationButton.setText(((NotificationLevoTakenViewModel) notifications.get(position)).getNotificationButtonText());
            notificationButton.setOnClickListener((v)->{
                MQTTClientST.publish("Confirm");
            });
        }


        return row;
    }

}
