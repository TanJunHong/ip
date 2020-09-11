package duke.task;

public class Event extends Task {

    private final String LOGO = "[E]";

    private final String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return LOGO + super.toString() + " (at: " + at + ")";
    }
}
