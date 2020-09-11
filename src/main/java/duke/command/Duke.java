package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

class Duke {

    private final String TODO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    private final String LIST = "list";
    private final String DONE = "done";
    private final String BYE = "bye";
    private final String BY = "/by";
    private final String AT = "/at";
    private final String DOTTED_LINE = "____________________________________________________________";
    private final String LOGO = (" ____        _        \n"
                            + "|  _ \\ _   _| | _____ \n"
                            + "| | | | | | | |/ / _ \\\n"
                            + "| |_| | |_| |   <  __/\n"
                            + "|____/ \\__,_|_|\\_\\___|\n")
                            .replaceAll("\n", "\n\t");

    private final int INSTRUCTION_LENGTH = 2;

    private ArrayList<Task> tasks;
    private int taskCount;

    Duke() {
        tasks = new ArrayList<>();
        taskCount = 0;
    }

    private void printWithIndent(String string) {
        System.out.println("\t" + string);
    }

    private void greetUser() {
        printWithIndent(DOTTED_LINE);
        printWithIndent(LOGO);
        printWithIndent(" Hello! I'm Duke");
        printWithIndent(" What can I do for you?");
        printWithIndent(DOTTED_LINE);
    }

    private void addTask(Task task) {
        tasks.add(task);

        printWithIndent(DOTTED_LINE);
        printWithIndent(" Got it. I've added this task:");
        printWithIndent("   " + tasks.get(taskCount).toString());

        taskCount++;
        printWithIndent(" Now you have " + taskCount + " tasks in the list.");
        printWithIndent(DOTTED_LINE);
    }

    private void addToDo(String description) {
        addTask(new ToDo(description));
    }

    private void addDeadline(String line) throws DukeException {
        String[] instructions = line.split(BY);

        if (instructions.length < INSTRUCTION_LENGTH) {
            throw new DukeException("☹ OOPS!!! Cannot decipher description or date/time.");
        }

        String description = instructions[0];
        String by = instructions[1];

        if (description.isBlank() || by.isBlank()) {
            throw new DukeException("☹ OOPS!!! Cannot decipher description or date/time.");
        }

        addTask(new Deadline(description, by));

    }

    private void addEvent(String line) throws DukeException {
        String[] instructions = line.split(AT);

        if (instructions.length < INSTRUCTION_LENGTH) {
            throw new DukeException("☹ OOPS!!! Cannot decipher description or date/time.");
        }

        String description = instructions[0];
        String at = instructions[1];

        if (description.isBlank() || at.isBlank()) {
            throw new DukeException("☹ OOPS!!! Cannot decipher description or date/time.");
        }

        addTask(new Event(description, at));
    }

    private void listTasks() {
        printWithIndent(DOTTED_LINE);
        printWithIndent(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            printWithIndent(" " + (i + 1) + "." + tasks.get(i));
        }
        printWithIndent(DOTTED_LINE);
    }

    private void markTaskAsDone(String instruction) throws DukeException {

        try {
            int taskNumber = Integer.parseInt(instruction);
            if (taskNumber < 1 || taskNumber > taskCount) {
                throw new DukeException("Invalid task number!");
            }
            tasks.get(taskNumber - 1).markAsDone();

            printWithIndent(DOTTED_LINE);
            printWithIndent(" Nice! I've marked this task as done:");
            printWithIndent("   " + tasks.get(taskNumber - 1).toString());
            printWithIndent(DOTTED_LINE);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    private void exit() {
        printWithIndent(DOTTED_LINE);
        printWithIndent(" Bye. Hope to see you again soon!");
        printWithIndent(DOTTED_LINE);
        System.exit(0);
    }

    private void verifyInstructionLength(String[] instructions) throws DukeException {
        if (instructions.length < INSTRUCTION_LENGTH) {
            throw new DukeException("☹ OOPS!!! The description of a " + instructions[0] + " cannot be empty.");
        }
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
                    verifyInstructionLength(instructions);
                    addToDo(instructions[1]);
                    break;
                case DEADLINE:
                    verifyInstructionLength(instructions);
                    addDeadline(instructions[1]);
                    break;
                case EVENT:
                    verifyInstructionLength(instructions);
                    addEvent(instructions[1]);
                    break;
                case DONE:
                    verifyInstructionLength(instructions);
                    markTaskAsDone(instructions[1]);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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
