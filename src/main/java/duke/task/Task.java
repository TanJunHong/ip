package duke.task;

public class Task {

    private final String TICK = "[✓]";
    private final String CROSS = "[✗]";
    private final int COMPLETE = 1;
    private final int INCOMPLETE = 0;

    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        isDone = false;
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
