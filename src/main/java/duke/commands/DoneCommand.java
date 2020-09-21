package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    private final int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsDone(taskNumber);
        ui.showDone(tasks.getTask(taskNumber));
        storage.save(tasks);
    }
}
