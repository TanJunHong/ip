package duke.data.task;

public abstract class Task {

    public static final String COMPLETE = "1";
    public static final String INCOMPLETE = "0";
    private static final String TICK = "[✓]";
    private static final String CROSS = "[✗]";
    private final String description;
    private boolean isDone;

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    private String getStatusIcon() {
        return isDone ? TICK : CROSS;
    }

    public String getFormattedTask() {
        return " | " + (isDone ? COMPLETE : INCOMPLETE) + " | " + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
