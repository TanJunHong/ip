class Task {

    private final String TICK = "✓";
    private final String CROSS = "✗";

    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    String getDescription() {
        return description;
    }

    String getStatusIcon() {
        return isDone ? TICK : CROSS;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
