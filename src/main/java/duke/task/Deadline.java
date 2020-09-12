package duke.task;

public class Deadline extends Task {

    private final String LOGO = "D";

    private String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + " | " + by + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString() + " (by: " + by + ")";
    }
}
