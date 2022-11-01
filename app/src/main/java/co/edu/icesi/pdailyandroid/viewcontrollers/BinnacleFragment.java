package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.adapters.NotificationsAdapter;
import co.edu.icesi.pdailyandroid.localdatabase.DataHandler;
import co.edu.icesi.pdailyandroid.model.datatype.INotification;
import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationFollowUp;
import co.edu.icesi.pdailyandroid.model.viewmodel.NotificationGame;

public class BinnacleFragment extends Fragment {

    private NotificationsAdapter adapter;
    private ArrayList<INotification> notifications;

    public BinnacleFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_binnacle, container, false);
        ListView notificationTable = v.findViewById(R.id.notificationTable);
        notifications = new ArrayList<>();
        adapter = new NotificationsAdapter(notifications);
        notificationTable.setAdapter(adapter);
        loadNotificationsList();
        return v;
    }

    private void loadNotificationsList() {
        notifications.clear();

        DataHandler db = DataHandler.getInstance(getContext());
        ArrayList<NotificationFollowUp> foodFollowUps = db.getAllFoodNotifications();
        ArrayList<NotificationFollowUp> levoFollowUps = db.getAllLevoNotifications();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = sdf.format(Calendar.getInstance().getTime());
        NotificationGame bananaGame = new NotificationGame(
                NotificationGame.BANANA_GAME_ID, "Vamos a jugar con la banana", date);
        NotificationGame wormGame = new NotificationGame(
                NotificationGame.WORM_GAME_ID, "Vamos a jugar con los gusanos", date);

        ArrayList<INotification> info = new ArrayList<>(foodFollowUps);
        info.addAll(levoFollowUps);
        info.add(bananaGame);
        info.add(wormGame);

        notifications.addAll(info);
        adapter.notifyDataSetChanged();
    }
}
