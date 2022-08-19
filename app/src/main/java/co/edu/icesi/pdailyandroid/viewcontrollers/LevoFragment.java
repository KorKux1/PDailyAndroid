package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.adapters.LevoAdapter;
import co.edu.icesi.pdailyandroid.model.dto.MedicineLevodopaScheduleDTO;
import co.edu.icesi.pdailyandroid.model.dto.SchedulesCollectionDTO;


public class LevoFragment extends Fragment {

    private DashBoard parentActivity;
    private LevoAdapter adapter;
    private ArrayList<MedicineLevodopaScheduleDTO> list;

    public LevoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentActivity = (DashBoard) getActivity();
        View v = inflater.inflate(R.layout.fragment_levo, container, false);
        ListView levoTable = v.findViewById(R.id.levoTable);

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

    @Override
    public void onResume() {
        parentActivity.getUpdateUserDataThread((updated) -> {
            if (updated) {
                parentActivity.runOnUiThread(this::updateList);
            }
        }).start();

        super.onResume();
    }
}
