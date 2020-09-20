package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    private final LocalDate date;
    private final LocalTime time;

    public ListCommand(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks(date, time));
    }
}
