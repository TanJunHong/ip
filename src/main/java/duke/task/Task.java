package duke.task;

public class Task {

    private final String TICK = "✓";
    private final String CROSS = "✗";

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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
