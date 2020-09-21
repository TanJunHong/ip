package duke.data.task;

/**
 * Todo task.
 */
public class ToDo extends Task {

    public static final String LOGO = "T";

    /**
     * Initializes todo.
     *
     * @param description Description of todo.
     * @param isDone      Completion status of todo.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone, null, null);
    }

    /**
     * Returns saving task format.
     *
     * @return String of task format for saving.
     */
    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + System.lineSeparator();
    }

    /**
     * Returns string version of task.
     *
     * @return Task string
     */
    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString();
    }
}
