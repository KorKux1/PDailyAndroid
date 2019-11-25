package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.adapters.EventsAdapter;
import co.edu.icesi.pdailyandroid.customview.IntensityView;
import co.edu.icesi.pdailyandroid.modals.RangeHourModal;
import co.edu.icesi.pdailyandroid.viewmodel.EventViewModel;


public class EventFragment extends Fragment {

    private ListView eventsTable;
    private ArrayList<EventViewModel> events;
    private EventsAdapter adapter;
    private Fragment intensityView;

    public EventFragment() {
        events = createArray();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event, container, false);
        eventsTable = v.findViewById(R.id.eventsTable);
        adapter = new EventsAdapter(events);
        eventsTable.setAdapter(adapter);

        intensityView = new IntensityView();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.intensityViewFrame, intensityView);
        ft.commit();

        eventsTable.setOnItemClickListener(
                (parent, view, position, id) -> {
                    EventViewModel event = adapter.select(position);
                    view.setAlpha(0);
                    view.animate().alpha(1);
                    Intent i = new Intent(getActivity(), RangeHourModal.class);
                    i.putExtra("event",event);
                    startActivity(i);
                }
        );

        return v;
    }

    private ArrayList<EventViewModel> createArray() {
        ArrayList<EventViewModel> out = new ArrayList<>();
        EventViewModel s1 = new EventViewModel("Congelamiento", true);
        EventViewModel s2 = new EventViewModel("Lentificación", false);
        EventViewModel s3 = new EventViewModel("Discinesias", true);
        EventViewModel s4 = new EventViewModel("Temblor", false);
        EventViewModel s5 = new EventViewModel("Tropezones", false);
        EventViewModel s6 = new EventViewModel("Caídas", false);
        out.add(s1);
        out.add(s2);
        out.add(s3);
        out.add(s4);
        out.add(s5);
        out.add(s6);
        return out;
    }


}
