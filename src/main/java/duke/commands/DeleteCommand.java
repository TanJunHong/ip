package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Deletes a task from Duke.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE = "Noted. I've removed this task:";

    private final int taskNumber;

    /**
     * Initializes the task number of task to delete.
     *
     * @param taskNumber Task number of task to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task, shows the task and saves remaining list of tasks into file.
     *
     * @param tasks   TaskList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws DukeException If there is issue deleting task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        ui.showDelete(task, tasks.getSize());
        storage.save(tasks);
    }
}
