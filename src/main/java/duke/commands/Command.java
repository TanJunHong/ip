package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Parent Command class.
 */
public abstract class Command {

    boolean isExit;

    /**
     * Initializes command to execute and set exit status to false.
     */
    public Command() {
        isExit = false;
    }

    /**
     * Executes command.
     *
     * @param tasks   TaskList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws DukeException If there is issue executing command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns Duke's exit status.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return isExit;
    }
}
