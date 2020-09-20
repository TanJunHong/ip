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

    private final int index;

    /**
     * Initializes the index of task to mark as done.
     *
     * @param index index of task to mark as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as done, shows the task and saves list of tasks into file.
     *
     * @param tasks   TaskList class.
     * @param ui      Ui class.
     * @param storage Storage class.
     * @throws DukeException If there is issue marking task as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsDone(index);
        ui.showDone(tasks.getTask(index));
        storage.save(tasks);
    }
}
