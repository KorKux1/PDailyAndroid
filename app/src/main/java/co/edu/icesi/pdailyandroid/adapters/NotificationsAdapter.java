package co.edu.icesi.pdailyandroid.adapters;

import android.content.Context;
import android.content.Intent;
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
import co.edu.icesi.pdailyandroid.gamecontrols.BananaGame;
import co.edu.icesi.pdailyandroid.gamecontrols.WormGame;
import co.edu.icesi.pdailyandroid.interfaces.INotification;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.model.NotificationGame;
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
                        notifications.remove(noti);
                        notifyDataSetChanged();
                    }
            );
        }
        else if(notifications.get(position) instanceof NotificationGame){

            NotificationGame noti = (NotificationGame) notifications.get(position);

            row = inflater.inflate(R.layout.notificationgamecell, null, false);
            TextView notificationtitle = row.findViewById(R.id.notificationtitle);
            TextView notificationmessage = row.findViewById(R.id.notificationmessage);
            TextView notificationDate = row.findViewById(R.id.notificationdate);
            Button notificationButton = row.findViewById(R.id.notificationButton);

            notificationtitle.setText("Actividad");
            notificationmessage.setText(noti.getName());
            notificationDate.setText(noti.getDate().split(" ")[0]+"\n"+noti.getDate().split(" ")[1]);
            notificationButton.setText("JUGAR");
            notificationButton.setOnClickListener(
                    (v)-> {
                        switch (noti.getId()){
                            case NotificationGame.BANANA_GAME_ID:
                                Intent i = new Intent(parent.getContext(), BananaGame.class);
                                parent.getContext().startActivity(i);
                                break;
                            case NotificationGame.WORM_GAME_ID:
                                Intent j = new Intent(parent.getContext(), WormGame.class);
                                parent.getContext().startActivity(j);
                                break;
                        }
                    }
            );
        }
        return row;
    }

}
