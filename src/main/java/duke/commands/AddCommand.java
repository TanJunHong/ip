package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddCommand extends Command {

    public static final String TODO_COMMAND_WORD = "todo";
    public static final String DEADLINE_COMMAND_WORD = "deadline";
    public static final String EVENT_COMMAND_WORD = "event";

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        ui.showAdd(task, tasks.getSize());
        storage.save(tasks);
    }
}
