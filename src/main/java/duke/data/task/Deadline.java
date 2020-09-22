package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline task.
 */
public class Deadline extends Task {

    public static final String LOGO = "D";
    public static final String DELIMITER = "/by";

    /**
     * Initializes deadline task.
     *
     * @param description Description of deadline.
     * @param isDone      Completion status of deadline.
     * @param date        Date of deadline.
     * @param time        Time of deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone, date, time);
    }

    /**
     * Returns saving task format.
     *
     * @return String of task format for saving.
     */
    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + Task.INSTRUCTION_DELIMITER + getDate() + getTimeString()
                + System.lineSeparator();
    }

    /**
     * Returns string version of task.
     *
     * @return Task string
     */
    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString() + " (by: " + getDateString() + getTimeString() + ")";
    }
}
