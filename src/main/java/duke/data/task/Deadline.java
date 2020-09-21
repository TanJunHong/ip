package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public static final String LOGO = "D";
    public static final String DELIMITER = "/by";

    public Deadline(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone, date, time);
    }

    private String getByDateString() {
        return getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String getByTimeString() {
        return getTime() == null ? "" : " " + getTime().toString();
    }

    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + " | " + getDate() + getByTimeString() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString() + " (by: " + getByDateString() + getByTimeString() + ")";
    }
}
