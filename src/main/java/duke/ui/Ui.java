package duke.ui;

import duke.data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String DOTTED_LINE = "____________________________________________________________";
    private final Scanner in = new Scanner(System.in);

    private void printWithIndent(String string) {
        System.out.println("\t " + string);
    }

    public void showLine() {
        System.out.println("\t" + DOTTED_LINE);
    }

    public void showError(String message) {
        printWithIndent(message);
    }

    public void showWelcome() {
        showLine();
        printWithIndent("Hello! I'm Duke");
        printWithIndent("What can I do for you?");
        showLine();
    }

    public void showList(ArrayList<Task> tasks) {
        printWithIndent("Here are the tasks in your list:");
        for (Task task : tasks) {
            printWithIndent(task.getTaskNumber() + "." + task);
        }
    }

    public void showDone(Task task) {
        printWithIndent("Nice! I've marked this task as done:");
        printWithIndent("  " + task);
    }

    public void showExit() {
        printWithIndent("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        showError("Error loading file.");
    }

    public String readCommand() {
        System.out.println();
        return in.nextLine();
    }

    public void showAdd(Task task, int size) {
        printWithIndent("Got it. I've added this task:");
        printWithIndent("  " + task);
        printWithIndent("Now you have " + size + " tasks in the list.");
    }

    public void showDelete(Task task, int size) {
        printWithIndent("Noted. I've removed this task:");
        printWithIndent("  " + task);
        printWithIndent("Now you have " + size + " tasks in the list.");
    }
}
