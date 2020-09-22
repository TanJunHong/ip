package duke.data.task;


import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event task.
 */
public class Event extends Task {

    public static final String LOGO = "E";
    public static final String DELIMITER = "/at";

    /**
     * Initializes event.
     *
     * @param description Description of event.
     * @param isDone      Completion status of event.
     * @param date        Date of event.
     * @param time        Time of event.
     */
    public Event(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone, date, time);
    }

    /**
     * Returns saving task format.
     *
     * @return String of task format for saving.
     */
    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + INSTRUCTION_DELIMITER + getDate() + getTimeString()
                + System.lineSeparator();
    }

    /**
     * Returns string version of task.
     *
     * @return Task string
     */
    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString() + " (at: " + getDateString() + getTimeString() + ")";
    }
}
