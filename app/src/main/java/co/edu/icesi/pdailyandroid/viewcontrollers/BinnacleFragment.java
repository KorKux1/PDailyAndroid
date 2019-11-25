package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.adapters.NotificationsAdapter;
import co.edu.icesi.pdailyandroid.interfaces.INotification;
import co.edu.icesi.pdailyandroid.model.NotificationFoodFollowUp;
import co.edu.icesi.pdailyandroid.viewmodel.SimpleNotification;


public class BinnacleFragment extends Fragment {

    private ListView notificationTable;
    private NotificationsAdapter adapter;
    private ArrayList<INotification> notifications;

    public BinnacleFragment() {
        notifications = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_binnacle, container, false);
        notificationTable = v.findViewById(R.id.notificationTable);
        adapter = new NotificationsAdapter(notifications);
        notificationTable.setAdapter(adapter);
        return v;
    }


    public void addNotification(INotification notification){
        notifications.add( notification );
    }

}
