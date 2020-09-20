package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public static final String LOGO = "E";
    public static final String DELIMITER = "/at";

    public Event(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone, date, time);
    }

    private String getAtDateString() {
        return getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String getAtTimeString() {
        return getTime() == null ? "" : " " + getTime().toString();
    }

    @Override
    public String getFormattedTask() {
        return LOGO + super.getFormattedTask() + " | " + getDate() + getAtTimeString() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + LOGO + "]" + super.toString() + " (at: " + getAtDateString() + getAtTimeString() + ")";
    }
}
