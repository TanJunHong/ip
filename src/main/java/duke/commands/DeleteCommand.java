package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.showDelete(task, tasks.getSize());
        storage.save(tasks);
    }
}
