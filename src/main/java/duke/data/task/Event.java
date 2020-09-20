package duke.data.task;

/**
 * Event task.
 */
public class Event extends Task {

    public static final String LOGO = "E";
    public static final String DELIMITER = "/at";

    private final String at;

    /**
     * Initializes event.
     *
     * @param description Description of event.
     * @param isDone Completion status of event.
     * @param at Date/Time of event.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns saving task format.
     *
     * @return String of task format for saving.
     */
    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + " | " + at + System.lineSeparator();
    }

    /**
     * Returns string version of task.
     *
     * @return Task string
     */
    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString() + " (at: " + at + ")";
    }
}
