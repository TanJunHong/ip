package duke.task;

public class Event extends Task {

    private static final String LOGO = "E";

    private final String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + " | " + at + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString() + " (at: " + at + ")";
    }
}
