package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

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
        TextView noNotifications = v.findViewById(R.id.noNotificationsTextView);

        notifications = new ArrayList<>();
        adapter = new NotificationsAdapter(notifications);
        notificationTable.setAdapter(adapter);
        loadNotificationsList();

        if (notifications.isEmpty()) {
            noNotifications.setVisibility(View.VISIBLE);
            notificationTable.setVisibility(View.GONE);
        } else {
            noNotifications.setVisibility(View.GONE);
            notificationTable.setVisibility(View.VISIBLE);
        }

        return v;
    }

    private void loadNotificationsList() {
        notifications.clear();

        DataHandler db = DataHandler.getInstance(getContext());
        ArrayList<NotificationFollowUp> foodFollowUps = db.getAllFoodNotifications();
        ArrayList<NotificationFollowUp> levoFollowUps = db.getAllLevoNotifications();

        ArrayList<INotification> info = new ArrayList<>(foodFollowUps);
        info.addAll(levoFollowUps);

        notifications.addAll(info);
        adapter.notifyDataSetChanged();

    }
}
