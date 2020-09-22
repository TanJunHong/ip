package duke.ui;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.data.task.Task;
import duke.storage.Storage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interacts with user.
 */
public class Ui {

    private static final String DOTTED_LINE = "____________________________________________________________";

    private final Scanner in = new Scanner(System.in);

    /**
     * Prints string with indent.
     *
     * @param string String to print.
     */
    private void printWithIndent(String string) {
        System.out.println("\t " + string);
    }

    /**
     * Prints dotted line.
     */
    public void showLine() {
        System.out.println("\t" + DOTTED_LINE);
    }

    /**
     * Prints error message.
     *
     * @param message Error message to print.
     */
    public void showError(String message) {
        printWithIndent(message);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showLine();
        printWithIndent(Command.FIRST_MESSAGE);
        printWithIndent(Command.SECOND_MESSAGE);
        showLine();
    }

    /**
     * Prints list of tasks.
     *
     * @param tasks Tasks to print.
     */
    public void showList(ArrayList<Task> tasks) {
        printWithIndent(ListCommand.MESSAGE);
        for (Task task : tasks) {
            printWithIndent(task.getTaskNumber() + "." + task);
        }
    }

    /**
     * Prints task with done as completion status.
     *
     * @param task Task marked with done as completion status.
     */
    public void showDone(Task task) {
        printWithIndent(DoneCommand.MESSAGE);
        printWithIndent("  " + task);
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        printWithIndent(ExitCommand.MESSAGE);
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        showError(Storage.LOAD_ERROR);
    }

    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        System.out.println();
        return in.nextLine();
    }

    /**
     * Prints task added.
     *
     * @param task Task added.
     * @param size Size of tasks after added task.
     */
    public void showAdd(Task task, int size) {
        printWithIndent(AddCommand.MESSAGE);
        printWithIndent("  " + task);
        printWithIndent("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints task deleted.
     *
     * @param task Task deleted.
     * @param size Size of tasks after deleted task.
     */
    public void showDelete(Task task, int size) {
        printWithIndent(DeleteCommand.MESSAGE);
        printWithIndent("  " + task);
        printWithIndent("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints results of finding keyword.
     *
     * @param tasks Tasks containing keyword.
     */
    public void showFind(ArrayList<Task> tasks) {
        printWithIndent(FindCommand.MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            printWithIndent((i + 1) + "." + tasks.get(i));
        }
    }
}
