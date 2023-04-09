package model;

import java.util.Calendar;
import java.util.Date;


/**
 * Represents an alarm system event.
 */
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    /**
     * Creates an event with the given description
     * and the current date/time stamp.
     *
     * @param description a description of the event
     */
    // MODIFIES: this
    // EFFECTS: Creates an event with the given description and the current date/time stamp.
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**
     * Gets the date of this event (includes time).
     *
     * @return the date of the event
     */
    // EFFECTS: getter for dateLogged
    public Date getDate() {
        return dateLogged;
    }

    /**
     * Gets the description of this event.
     *
     * @return the description of the event
     */
    // EFFECTS: getter for event description
    public String getDescription() {
        return description;
    }

    @Override
    // EFFECTS: Compare this event to another object. Return true if dateLogged and description of this and the object
    // is equal
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    @Override
    // EFFECTS: return hashcode of this according to dateLogged and description
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    // EFFECTS: return the date the event is logged in the first line, then in the second line, the event description
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
