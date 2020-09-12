package duke.task;

public class ToDo extends Task {

    private final String LOGO = "T";

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
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
