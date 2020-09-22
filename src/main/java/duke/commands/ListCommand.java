package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Lists tasks in Duke.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE = "Here are the tasks in your list:";

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Initializes date & time to filter.
     *
     * @param date Date to filter.
     * @param time Time to filter.
     */
    public ListCommand(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Shows the list of tasks in Duke using date & time to filter.
     *
     * @param tasks   TaskList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks(date, time));
    }
}
