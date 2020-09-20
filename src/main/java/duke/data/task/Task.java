package duke.data.task;

/**
 * Parent Task class.
 */
public abstract class Task {

    public static final String COMPLETE = "1";
    public static final String INCOMPLETE = "0";
    private static final String TICK = "[✓]";
    private static final String CROSS = "[✗]";
    private final String description;
    private boolean isDone;

    /**
     * Initializes task.
     *
     * @param description Description of task.
     * @param isDone Completion status of task.
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks completion status as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns description of task.
     *
     * @return String description of task.
     */
    private String getDescription() {
        return description;
    }

    /**
     * Returns status icon of task.
     *
     * @return String status icon of task.
     */
    private String getStatusIcon() {
        return isDone ? TICK : CROSS;
    }

    /**
     * Returns saving task format.
     *
     * @return String of task format for saving.
     */
    public String getFormattedTask() {
        return " | " + (isDone ? COMPLETE : INCOMPLETE) + " | " + description;
    }

    /**
     * Returns string version of task.
     *
     * @return Task string
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
