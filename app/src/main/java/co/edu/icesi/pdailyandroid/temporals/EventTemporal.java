package co.edu.icesi.pdailyandroid.temporals;

import java.util.ArrayList;
import java.util.HashMap;

import co.edu.icesi.pdailyandroid.model.Event;
import co.edu.icesi.pdailyandroid.viewmodel.EventViewModel;

public class EventTemporal {

    public static HashMap<String, Event> events;

    public static void createTemp(){
        if(events == null) {
            events = new HashMap<>();
        }
    }

    public static void deleteTemp(){
        events = null;
    }

    public static void deleteEvent(String name) {
        events.remove(name);
    }
}
