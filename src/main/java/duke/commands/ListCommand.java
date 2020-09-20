package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Lists tasks in Duke.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Shows the list of tasks in Duke.
     *
     * @param tasks   TaskList class.
     * @param ui      Ui class.
     * @param storage Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks());
    }
}
