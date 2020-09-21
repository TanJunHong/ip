package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Finds tasks containing keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    /**
     * Initializes keyword to find.
     *
     * @param keyword Keyword to search.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks containing keyword and display them.
     *
     * @param tasks   TaskList object.
     * @param ui      Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFind(tasks.getTasks(keyword));
    }
}
