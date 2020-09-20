package duke.data.task;

/**
 * Deadline task.
 */
public class Deadline extends Task {

    public static final String LOGO = "D";
    public static final String DELIMITER = "/by";

    private final String by;

    /**
     * Initializes deadline.
     *
     * @param description Description of deadline.
     * @param isDone Completion status of deadline.
     * @param by Date/Time to complete deadline.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns saving task format.
     *
     * @return String of task format for saving.
     */
    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + " | " + by + System.lineSeparator();
    }

    /**
     * Returns string version of task.
     *
     * @return Task string
     */
    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString() + " (by: " + by + ")";
    }
}
