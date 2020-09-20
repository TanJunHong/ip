package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Exits Duke.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Says goodbye to user and exits Duke.
     *
     * @param tasks   TaskList class.
     * @param ui      Ui class.
     * @param storage Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        ui.showExit();
    }
}
