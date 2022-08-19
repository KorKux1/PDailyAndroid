package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.adapters.LevoAdapter;
import co.edu.icesi.pdailyandroid.app.App;
import co.edu.icesi.pdailyandroid.model.dto.FoodScheduleDTO;
import co.edu.icesi.pdailyandroid.model.dto.MedicineLevodopaScheduleDTO;
import co.edu.icesi.pdailyandroid.model.dto.ScheduleTimeDTO;
import co.edu.icesi.pdailyandroid.model.dto.SchedulesCollectionDTO;
import co.edu.icesi.pdailyandroid.receivers.broadcast.AlarmReceiver;


public class LevoFragment extends Fragment {
    private DashBoard parentActivity;
    private LevoAdapter adapter;
    private ArrayList<MedicineLevodopaScheduleDTO> list;

    private AlarmManager alarmMgr;
    private Intent levoIntent;

    public LevoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentActivity = (DashBoard) getActivity();
        View v = inflater.inflate(R.layout.fragment_levo, container, false);
        ListView levoTable = v.findViewById(R.id.levoTable);

        levoIntent = new Intent(App.getAppContext(), AlarmReceiver.class);
        levoIntent.putExtra("type", "LEVO");

        list = new ArrayList<>();
        adapter = new LevoAdapter(list);
        levoTable.setAdapter(adapter);
        updateList();
        return v;
    }

    private void updateList() {
        SchedulesCollectionDTO schedules = parentActivity.getSessionManager().loadSchedulesData();
        ArrayList<MedicineLevodopaScheduleDTO> levoSchedules = schedules.getMedicineLevodopaSchedules();
        list.removeAll(list);
        list.addAll(levoSchedules);
        adapter.notifyDataSetChanged();
    }

    private void setupLevoAlarms(ArrayList<MedicineLevodopaScheduleDTO> schedules) {
        // Alarms with id from 100 to 200 are for levo. Try to cancel all.
        for (int i = 100; i < 200; i++) {
            PendingIntent levoPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), i, levoIntent, 0);
            alarmMgr.cancel(levoPendingIntent);
        }

        if (schedules == null || schedules.isEmpty()) return;

        for (MedicineLevodopaScheduleDTO schedule : schedules) {
            ArrayList<ScheduleTimeDTO> times = schedule.getMetadata().getTimes();
            for (int i = 0; i < times.size(); i++) {
                int alarmId = 100 + i;
                ScheduleTimeDTO time = times.get(i);
                PendingIntent levoPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), alarmId, levoIntent, 0);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time.getCalendarRepresentation().getTimeInMillis(), AlarmManager.INTERVAL_DAY, levoPendingIntent);
            }
        }
    }

    @Override
    public void onResume() {
        ArrayList<MedicineLevodopaScheduleDTO> currentSchedules = parentActivity.getSessionManager().loadSchedulesData().getMedicineLevodopaSchedules();

        parentActivity.getUpdateUserDataThread((updated) -> {
            if (updated) {
                parentActivity.runOnUiThread(this::updateList);
                ArrayList<MedicineLevodopaScheduleDTO> newSchedules = parentActivity.getSessionManager().loadSchedulesData().getMedicineLevodopaSchedules();
                parentActivity.runOnUiThread(() -> setupLevoAlarms(newSchedules));
            }
        }).start();

        super.onResume();
    }
}
