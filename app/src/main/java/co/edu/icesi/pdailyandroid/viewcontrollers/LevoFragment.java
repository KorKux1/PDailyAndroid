package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.model.dto.MedicineLevodopaScheduleDTO;
import co.edu.icesi.pdailyandroid.model.dto.SchedulesCollectionDTO;
import co.edu.icesi.pdailyandroid.services.SessionManager;


public class LevoFragment extends Fragment {

    public LevoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SessionManager sessionManager = new SessionManager(
                getActivity().getApplicationContext());
        SchedulesCollectionDTO schedules = sessionManager.loadSchedulesData();
        ArrayList<MedicineLevodopaScheduleDTO> levoSchedules = schedules.getMedicineLevodopaSchedules();

        for (MedicineLevodopaScheduleDTO s : levoSchedules) {
            Log.w("log", s.getMedicineLevodopaTypeLabel());
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_levo, container, false);
    }
}
