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
import co.edu.icesi.pdailyandroid.services.SessionManager;


public class LevoFragment extends Fragment {

    private ListView levoTable;
    private LevoAdapter adapter;
    private ArrayList<MedicineLevodopaScheduleDTO> list;
    private SessionManager sessionManager;

    public LevoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        View v = inflater.inflate(R.layout.fragment_levo, container, false);
        levoTable = v.findViewById(R.id.levoTable);
        list = new ArrayList<>();
        adapter = new LevoAdapter(list);
        levoTable.setAdapter(adapter);

        updateList();

        return v;
    }

    private void updateList() {
        SchedulesCollectionDTO schedules = sessionManager.loadSchedulesData();
        ArrayList<MedicineLevodopaScheduleDTO> levoSchedules = schedules.getMedicineLevodopaSchedules();
        list.removeAll(list);
        list.addAll(levoSchedules);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        ((DashBoard) getActivity()).getUpdateUserDataThread((updated) -> {
            if (updated) {
                getActivity().runOnUiThread(() -> {
                    updateList();
                });
            }
        }).start();

        super.onResume();
    }
}
