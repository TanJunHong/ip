package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Personal assistant chat bot.
 */
public class Duke {

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initializes chat bot.
     *
     * @param filePath Path to store and load tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts Duke.
     *
     * @param args Command line argument.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
