package duke.task;

public class Deadline extends Task {

    private final String LOGO = "[D]";

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return LOGO + super.toString() + " (by: " + by + ")";
    }
}
