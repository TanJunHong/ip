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

    private final int index;

    /**
     * Initialize the index of task to delete.
     *
     * @param index index of task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task, shows the task and saves remaining list of tasks into file.
     *
     * @param tasks   TaskList class.
     * @param ui      Ui class.
     * @param storage Storage class.
     * @throws DukeException  If there is issue deleting task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.showDelete(task, tasks.getSize());
        storage.save(tasks);
    }
}
