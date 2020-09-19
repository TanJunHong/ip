package duke.data.task;

public class Task {

    private static final String TICK = "[✓]";
    private static final String CROSS = "[✗]";
    private static final int COMPLETE = 1;
    private static final int INCOMPLETE = 0;

    private final String description;
    private boolean isDone;

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    private String getDescription() {
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
