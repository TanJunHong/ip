package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsDone(index);
        ui.showDone(tasks.getTask(index));
        storage.save(tasks);
    }
}
