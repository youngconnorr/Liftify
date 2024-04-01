package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


//Represents a log of alarm system events. We use the Singleton design Pattern to ensure that there is only one EventLog
// in the system and that the system has global access to the single instance of the EventLog
public class EventLog implements Iterable<Event> {


    //EFFECTS: the only EventLog in the system (Single Design Pattern)
    private static EventLog theLog;
    private Collection<Event> events;


    //EFFECTS: prevent external construction (singleton design pattern)
    private EventLog() {
        events = new ArrayList<Event>();
    }


    //EFFECTS: gets instance of EventLog - creates it if it doesn't already exist (single design pattern),
    //         return instance
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    //EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    //EFFECTS: clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    //EFFECTS: returns an Iterator of Event
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
