class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    String getName() {
        return name;
    }

    String getStatusIcon() {
        return isDone ? "✓" : "✗";
    }

}
