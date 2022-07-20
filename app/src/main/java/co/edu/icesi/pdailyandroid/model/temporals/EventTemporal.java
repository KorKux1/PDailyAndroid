package co.edu.icesi.pdailyandroid.model.temporals;

import java.util.ArrayList;
import java.util.HashMap;

import co.edu.icesi.pdailyandroid.model.viewmodel.Event;

public class EventTemporal {

    public static HashMap<String, Event> events;

    public static void createTemp() {
        if (events == null) {
            events = new HashMap<>();
        }
    }

    public static ArrayList<Event> getAllEvents() {
        ArrayList<Event> out = new ArrayList<>();
        if (events == null) {
            return out;
        }
        for (String key : events.keySet()) {
            Event event = events.get(key);
            out.add(event);
        }
        return out;
    }

    public static void deleteTemp() {
        events = null;
    }

    public static void deleteEvent(String name) {
        events.remove(name);
    }
}
