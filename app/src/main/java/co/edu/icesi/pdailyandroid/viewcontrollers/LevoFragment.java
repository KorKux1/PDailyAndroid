package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.adapters.LevoAdapter;
import co.edu.icesi.pdailyandroid.app.App;
import co.edu.icesi.pdailyandroid.model.dto.MedicineScheduleDTO;
import co.edu.icesi.pdailyandroid.model.dto.ScheduleTimeDTO;
import co.edu.icesi.pdailyandroid.model.dto.SchedulesCollectionDTO;
import co.edu.icesi.pdailyandroid.receivers.broadcast.AlarmReceiver;


public class LevoFragment extends Fragment {
    private DashBoard parentActivity;
    private LevoAdapter adapter;
    private ArrayList<MedicineScheduleDTO> list;

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

        alarmMgr = (AlarmManager) App.getAppContext().getSystemService(Context.ALARM_SERVICE);
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
        ArrayList<MedicineScheduleDTO> levoSchedules = schedules.getMedicineSchedules();
        list.removeAll(list);
        list.addAll(levoSchedules);
        adapter.notifyDataSetChanged();
    }

    private void setupLevoAlarms(ArrayList<MedicineScheduleDTO> schedules) {
        // Alarms with id from 100 to 200 are for levo. Try to cancel all.
        for (int i = 100; i < 200; i++) {
            PendingIntent levoPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), i, levoIntent, 0);
            alarmMgr.cancel(levoPendingIntent);
        }

        if (schedules == null || schedules.isEmpty()) return;

        for (MedicineScheduleDTO schedule : schedules) {
            ArrayList<ScheduleTimeDTO> times = schedule.getPlan().getTimes();
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
        ArrayList<MedicineScheduleDTO> currentSchedules = parentActivity.getSessionManager().loadSchedulesData().getMedicineSchedules();

        parentActivity.getUpdateUserDataThread((updated) -> {
            if (updated) {
                parentActivity.runOnUiThread(this::updateList);
                ArrayList<MedicineScheduleDTO> newSchedules = parentActivity.getSessionManager().loadSchedulesData().getMedicineSchedules();
                ArrayList<MedicineScheduleDTO> toUpdate = new ArrayList<>(newSchedules);
                // TODO: Bug. current schedules are never null... They get loaded previously
                toUpdate.removeIf(s -> currentSchedules.contains(s));
                if (!toUpdate.isEmpty()) {
                    parentActivity.runOnUiThread(() -> setupLevoAlarms(newSchedules));
                }
            }
        }).start();

        super.onResume();
    }
}
