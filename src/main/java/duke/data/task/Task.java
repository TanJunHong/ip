package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Task {

    public static final String COMPLETE = "1";
    public static final String INCOMPLETE = "0";
    private static final String TICK = "[✓]";
    private static final String CROSS = "[✗]";

    private static int numberOfTasks = 0;
    private final int taskNumber;
    private final String description;
    private final LocalDate date;
    private final LocalTime time;
    private boolean isDone;

    Task(String description, boolean isDone, LocalDate date, LocalTime time) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
        taskNumber = ++numberOfTasks;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    private String getStatusIcon() {
        return isDone ? TICK : CROSS;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getFormattedTask() {
        return " | " + (isDone ? COMPLETE : INCOMPLETE) + " | " + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
