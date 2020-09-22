package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Adds a task to Duke.
 */
public class AddCommand extends Command {

    public static final String TODO_COMMAND_WORD = "todo";
    public static final String DEADLINE_COMMAND_WORD = "deadline";
    public static final String EVENT_COMMAND_WORD = "event";
    public static final String MESSAGE = "Got it. I've added this task:";

    private final Task task;

    /**
     * Initializes task to add.
     *
     * @param task Task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task, shows the task and saves list of tasks into file.
     *
     * @param tasks   TaskList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws DukeException If there is issue adding task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        ui.showAdd(task, tasks.getSize());
        storage.save(tasks);
    }
}
