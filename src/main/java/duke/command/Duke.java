package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

class Duke {

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String BYE = "bye";
    private static final String BY = "/by";
    private static final String AT = "/at";

    private final String LOGO = " ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String DOTTED_LINE = "____________________________________________________________";
    private final int MAX_TASKS = 100;

    private Task[] tasks;
    private int taskCount;

    Duke() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    private void printWithIndent(String string) {
        String formattedString = string.replaceAll("\n", "\n\t");
        System.out.println("\t" + formattedString);
    }

    private void greetUser() {
        printWithIndent(DOTTED_LINE);
        printWithIndent(LOGO);
        printWithIndent(" Hello! I'm Duke");
        printWithIndent(" What can I do for you?");
        printWithIndent(DOTTED_LINE);
    }

    /*private void addTask(String description) {
        tasks[taskCount] = new Task(description);

        printWithIndent(DOTTED_LINE);
        printWithIndent(" added: " + tasks[taskCount].getDescription());
        printWithIndent(DOTTED_LINE);

        taskCount++;
    }*/

    private void addTask(Task task) {
        tasks[taskCount] = task;

        printWithIndent(DOTTED_LINE);
        printWithIndent(" Got it. I've added this task:");
        printWithIndent("   " + tasks[taskCount].toString());

        taskCount++;
        printWithIndent(" Now you have " + taskCount + " tasks in the list.");
        printWithIndent(DOTTED_LINE);
    }

    private void addToDo(String description) {
        addTask(new ToDo(description));
    }

    private void addDeadline(String line) {
        String[] instructions = line.split(BY);
        String description = instructions[0];
        String by = instructions[1];

        addTask(new Deadline(description, by));
    }

    private void addEvent(String line) {
        String[] instructions = line.split(AT);
        String description = instructions[0];
        String at = instructions[1];

        addTask(new Event(description, at));
    }

    private void echoUser(String line) {
        printWithIndent(DOTTED_LINE);
        printWithIndent(line);
        printWithIndent(DOTTED_LINE);
    }

    private void listTasks() {
        printWithIndent(DOTTED_LINE);
        printWithIndent(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            printWithIndent(" " + (i + 1) + "." + tasks[i]);
        }
        printWithIndent(DOTTED_LINE);
    }

    private void markTaskAsDone(int taskNumber) {
        tasks[taskNumber - 1].markAsDone();

        printWithIndent(DOTTED_LINE);
        printWithIndent(" Nice! I've marked this task as done:");
        printWithIndent("   " + tasks[taskNumber - 1].toString());
        printWithIndent(DOTTED_LINE);
    }

    private void exit() {
        printWithIndent(DOTTED_LINE);
        printWithIndent(" Bye. Hope to see you again soon!");
        printWithIndent(DOTTED_LINE);
        System.exit(0);
    }

    private void processInput() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();
            String[] instructions = line.split(" ", 2);
            try {

                switch (instructions[0]) {
                case LIST:
                    listTasks();
                    continue;
                case BYE:
                    exit();
                    break;
                case TODO:
                    addToDo(instructions[1]);
                    break;
                case DEADLINE:
                    addDeadline(instructions[1]);
                    break;
                case EVENT:
                    addEvent(instructions[1]);
                    break;
                case DONE:
                    int taskNumber = Integer.parseInt(instructions[1]);
                    markTaskAsDone(taskNumber);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                try {
                    throw new DukeException("☹ OOPS!!! The description of a " + instructions[0] + " cannot be empty.");
                } catch (DukeException dukeException) {

                }
            } catch (DukeException e) {

            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greetUser();
        duke.processInput();
    }
}
