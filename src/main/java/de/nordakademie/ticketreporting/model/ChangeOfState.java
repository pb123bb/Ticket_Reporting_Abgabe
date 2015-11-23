package de.nordakademie.ticketreporting.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class for the params for a change of state for tickets
 * @author Philipp Brämer
 * @author Jan-Nicolas Kugler
 * @since 29.10.2015
 */
public class ChangeOfState implements Serializable {

    /**
     * Enum with the state, initialized as OPENED
     */
    private State name = State.OPENED;
    /**
     * Username of the specialist how made this change
     */
    private String specialist;
    /**
     * Annotation to change
     */
    private String annotation;
    /**
     * Date of change
     */
    private Date changed;

    public State getName() {
        return name;
    }

    public void setName(State name) {
        this.name = name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

}
