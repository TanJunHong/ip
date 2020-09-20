package duke.ui;

import duke.data.task.Task;

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
        printWithIndent("Hello! I'm Duke");
        printWithIndent("What can I do for you?");
        showLine();
    }

    /**
     * Prints list of tasks.
     *
     * @param tasks Tasks to print.
     */
    public void showList(ArrayList<Task> tasks) {
        printWithIndent("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printWithIndent((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Prints task marked as done.
     *
     * @param task Task marked as done.
     */
    public void showDone(Task task) {
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent("  " + task);
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        printWithIndent("Bye. Hope to see you again soon!");
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        showError("Error loading file.");
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
        printWithIndent("Got it. I've added this task:");
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
        printWithIndent("Noted. I've removed this task:");
        printWithIndent("  " + task);
        printWithIndent("Now you have " + size + " tasks in the list.");
    }
}
