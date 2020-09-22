package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Parent Task class.
 */
public abstract class Task {

    public static final String INSTRUCTION_DELIMITER = " | ";
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

    /**
     * Initializes task.
     *
     * @param description Description of task.
     * @param isDone      Completion status of task.
     * @param date        Date of task.
     * @param time        Time of task.
     */
    Task(String description, boolean isDone, LocalDate date, LocalTime time) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
        taskNumber = ++numberOfTasks;
    }

    /**
     * Marks completion status as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns date.
     *
     * @return Date of task.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns time.
     *
     * @return Time of task.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns date string.
     *
     * @return Date string of task.
     */
    public String getDateString() {
        return getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns time string.
     *
     * @return Time string of task.
     */
    public String getTimeString() {
        return getTime() == null ? "" : " " + getTime().toString();
    }

    /**
     * Returns description.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns status icon of task.
     *
     * @return String status icon of task.
     */
    private String getStatusIcon() {
        return isDone ? TICK : CROSS;
    }

    /**
     * Returns task number.
     *
     * @return Task number of task.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Returns saving task format.
     *
     * @return String of task format for saving.
     */
    public String getFormattedTask() {
        return INSTRUCTION_DELIMITER + (isDone ? COMPLETE : INCOMPLETE) + INSTRUCTION_DELIMITER + description;
    }

    /**
     * Returns string version of task.
     *
     * @return Task string
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
