package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.adapters.EventsAdapter;
import co.edu.icesi.pdailyandroid.customview.IntensityView;
import co.edu.icesi.pdailyandroid.modals.BodyModal;
import co.edu.icesi.pdailyandroid.dto.EventDTO;
import co.edu.icesi.pdailyandroid.modals.RangeHourModal;
import co.edu.icesi.pdailyandroid.model.Event;
import co.edu.icesi.pdailyandroid.services.WebserviceConsumer;
import co.edu.icesi.pdailyandroid.temporals.EventTemporal;
import co.edu.icesi.pdailyandroid.viewmodel.EventViewModel;


public class EventFragment extends Fragment implements IntensityView.onValueListener, WebserviceConsumer.OnResponseListener {

    private static final int HOUR_MODAL_CALLBACK = 100;
    private static final int BODY_MODAL_CALLBACK = 101;


    private ListView eventsTable;
    private ArrayList<EventViewModel> events;
    private ArrayList<EventViewModel> out;
    private EventsAdapter adapter;
    private Fragment intensityView;
    private Button saveBtn;


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
        ((IntensityView) intensityView).setListener(this);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.intensityViewFrame, intensityView);
        ft.commit();

        saveBtn = v.findViewById(R.id.saveBtn);

        eventsTable.setOnItemClickListener(
                (parent, view, position, id) -> {
                    EventViewModel event = adapter.select(position);
                    view.setAlpha(0);
                    view.animate().alpha(1);
                    adapter.mark(position);
                    if( event.isEvaluated() ) {
                        ( (IntensityView) intensityView ).select();
                        String key = adapter.getNameOfItemMarked();
                        Event ev = EventTemporal.events.get(key);
                        ( (IntensityView) intensityView ).setValue(ev.getIntensity());
                    }else{
                        ( (IntensityView) intensityView ).deselect();
                        ( (IntensityView) intensityView ).setValue(1);
                    }
                }
        );

        eventsTable.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    EventViewModel event = adapter.select(position);
                    view.setAlpha(0);
                    view.animate().alpha(1);
                    ( (IntensityView) intensityView ).deselect();
                    ( (IntensityView) intensityView ).setValue(1);
                    Intent i = new Intent(getContext(), RangeHourModal.class);
                    i.putExtra("event", event);
                    startActivityForResult(i, HOUR_MODAL_CALLBACK);
                    return true;
                }
        );

        saveBtn.setOnClickListener(
                (view) -> {
                    if(saveBtn.getVisibility() == View.VISIBLE){
                        ArrayList<Event> events = EventTemporal.getAllEvents();
                        ArrayList<EventDTO> eventDTOS = new ArrayList<>();
                        for(int i=0 ; i<events.size() ; i++){
                            EventDTO dto = EventDTO.transformToDTO(events.get(i));
                            eventDTOS.add(dto);
                        }
                        WebserviceConsumer consumer = new WebserviceConsumer();
                        consumer.setListener(this);
                        consumer.postEvents(eventDTOS);
                    }
                }
        );

        return v;
    }

    private ArrayList<EventViewModel> createArray() {
        out = new ArrayList<>();
        EventViewModel s1 = new EventViewModel("Congelamiento", false);
        EventViewModel s2 = new EventViewModel("Lentificación", false);
        EventViewModel s3 = new EventViewModel("Discinesias", false);
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

    public void refreshList() {
        if (EventTemporal.events == null) return;



        for(String key : EventTemporal.events.keySet()){
            Event event = EventTemporal.events.get(key);
            switch (event.getName()){
                case "Congelamiento":
                    out.get(0).setEvaluated(true);
                    adapter.mark(0);
                    break;
                case "Lentificación":
                    out.get(1).setEvaluated(true);
                    adapter.mark(1);
                    break;
                case "Discinesias":
                    out.get(2).setEvaluated(true);
                    adapter.mark(2);
                    break;
                case "Temblor":
                    out.get(3).setEvaluated(true);
                    adapter.mark(3);
                    break;
                case "Tropezones":
                    out.get(4).setEvaluated(true);
                    adapter.mark(4);
                    break;
                case "Caídas":
                    out.get(5).setEvaluated(true);
                    adapter.mark(5);
                    break;
            }
        }
        ( (IntensityView) intensityView ).select();
    }


    //CONTROL DE FLUJO DE QUESTIONARIO
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == HOUR_MODAL_CALLBACK){
            if(resultCode == Activity.RESULT_OK){

                EventTemporal.createTemp();

                Event ev = new Event();
                ev.setName(data.getExtras().getString("title"));
                ev.setFrom(data.getExtras().getLong("from"));
                ev.setTo(data.getExtras().getLong("to"));
                EventTemporal.events.put( ev.getName(), ev );

                Intent i = new Intent(getActivity(), BodyModal.class);
                i.putExtra("name", data.getExtras().get("title").toString());
                startActivityForResult(i, BODY_MODAL_CALLBACK);
            }else{

            }
        } else if(requestCode == BODY_MODAL_CALLBACK){
            if(resultCode == Activity.RESULT_OK){
                String name = data.getExtras().getString("name");
                String bodyList = data.getExtras().getString("bodyList");
                ArrayList<String> bodyArray = new Gson().fromJson(bodyList, new TypeToken<ArrayList<String>>(){}.getType());
                EventTemporal.events.get( name ).setBodyParts(bodyArray);
                saveBtn.setVisibility(View.VISIBLE);
                refreshList();
                selectOnList(name);
            }else{
                String name = data.getExtras().getString("name");
                EventTemporal.deleteEvent(name);
            }
        }
    }

    private void selectOnList(String name) {
        ( (IntensityView) intensityView ).select();
        adapter.mark(adapter.getPositionOf(name));
        String key = adapter.getNameOfItemMarked();
        Event ev = EventTemporal.events.get(key);
        ( (IntensityView) intensityView ).setValue(ev.getIntensity());
    }

    @Override
    public void onValue(int value) {
        if(adapter.isAnyItemSelected()){
            String name = adapter.getNameOfItemMarked();
            EventTemporal.events.get(name).setIntensity(value);
        }
    }

    @Override
    public void onResponse(String response) {
        Log.e("<<<",response);
    }
}
