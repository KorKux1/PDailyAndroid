package co.edu.icesi.pdailyandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.communication.MQTTClientST;
import co.edu.icesi.pdailyandroid.database.DataHandler;
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
                    (v)-> {
                        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                        noti.setDate(date);
                        noti.setName("confirmado");
                        MQTTClientST.getInstance().publish(MQTTService.FOOD_TOPIC+MQTTService.clientId, new Gson().toJson(noti));
                        DataHandler.getInstance(parent.getContext()).deleteFoodNotification(noti);
                        notifications.remove(noti);
                        notifyDataSetChanged();
                    }
            );
        }
        return row;
    }

}
