class Task {

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
        return isDone ? "✓" : "✗";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
