package co.edu.icesi.pdailyandroid.temporals;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.model.Event;
import co.edu.icesi.pdailyandroid.viewmodel.EventViewModel;

public class EventTemporal {

    public static ArrayList<Event> events;

    public static void createTemp(){
        if(events == null) {
            events = new ArrayList<>();
        }
    }

    public static void deleteTemp(){
        events = null;
    }

}
