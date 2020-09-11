package duke.task;

public class ToDo extends Task {

    private final String LOGO = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString();
    }
}
