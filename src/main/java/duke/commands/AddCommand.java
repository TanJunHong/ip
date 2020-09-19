package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.task.Task;
import duke.ui.Ui;

public class AddCommand extends Command {

    private final Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(toAdd);
        ui.showAdd(toAdd, tasks.getSize());
        storage.save(tasks);
    }
}
