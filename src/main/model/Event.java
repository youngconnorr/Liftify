package model;

import java.util.Calendar;
import java.util.Date;


//Represents an alarm system event.
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;


    //EFFECTS: Creates an event with the given description
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    //EFFECTS: gets the date of this event
    public Date getDate() {
        return dateLogged;
    }

    @Override
    //EFFECTS: overrides in order to check equality of two different logs
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged) && this.description.equals(otherEvent.description));
    }

    @Override
    //EFFECTS: overrides hashcode
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    //EFFECTS: overrides to string with log formatting
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
