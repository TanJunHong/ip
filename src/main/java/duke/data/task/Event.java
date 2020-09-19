package duke.data.task;

public class Event extends Task {

    public static final String LOGO = "E";
    public static final String DELIMITER = "/at";

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
