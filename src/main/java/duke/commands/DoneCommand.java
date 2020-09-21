package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    private final int taskNumber;

    /**
     * Initializes the task number of task to mark as done.
     *
     * @param taskNumber Task number of task to mark as done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks task as done, shows the task and saves list of tasks into file.
     *
     * @param tasks   TaskList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws DukeException If there is issue marking task as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsDone(taskNumber);
        ui.showDone(tasks.getTask(taskNumber));
        storage.save(tasks);
    }
}
