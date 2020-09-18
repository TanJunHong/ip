package duke;

import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class Duke {

    private static final String FOLDER_PATH = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String BYE = "bye";
    private static final String BY = "/by";
    private static final String AT = "/at";
    private static final String DELETE = "delete";
    private static final String DOTTED_LINE = "____________________________________________________________";
    private static final String LOGO = (" ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n")
            .replaceAll("\n", System.lineSeparator() + "\t");

    private static final int INSTRUCTION_LENGTH = 2;

    private final ArrayList<Task> tasks;
    private int taskCount;

    private boolean isLoaded;

    Duke() {
        tasks = new ArrayList<>();
        taskCount = 0;
        isLoaded = false;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.loadTasks();
        duke.greetUser();
        duke.processInput();
    }

    private void printWithIndent(String string) {
        System.out.println("\t" + string);
    }

    private void loadTasks() {
        Path filePath = Paths.get(FOLDER_PATH, FILE_NAME);

        try {
            try {
                if (Files.exists(filePath)) {
                    BufferedReader bufferedReader = Files.newBufferedReader(filePath);

                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        String[] instructions = line.split("\\|", 3);
                        boolean isDone = instructions[1].trim().equals("1");
                        switch (instructions[0].trim()) {
                        case "D":
                            addDeadline(instructions[2], isDone);
                            break;
                        case "E":
                            addEvent(instructions[2], isDone);
                            break;
                        case "T":
                            addToDo(instructions[2], isDone);
                            break;
                        default:
                            throw new DukeException("Error reading file!");
                        }
                    }
                }
            } catch (IOException e) {
                throw new DukeException("Error reading file!");
            }
        } catch (DukeException ignored) {

        }

        isLoaded = true;

    }

    private void greetUser() {
        printWithIndent(DOTTED_LINE);
        printWithIndent(LOGO);
        printWithIndent(" Hello! I'm Duke");
        printWithIndent(" What can I do for you?");
        printWithIndent(DOTTED_LINE);
    }

    private void deleteTask(String instruction) throws DukeException {

        try {
            int taskNumber = Integer.parseInt(instruction);
            if (taskNumber < 1 || taskNumber > taskCount) {
                throw new DukeException("Invalid task number!");
            }
            Task removedTask = tasks.remove(taskNumber - 1);
            taskCount--;

            printWithIndent(DOTTED_LINE);
            printWithIndent(" Noted. I've removed this task:");
            printWithIndent("   " + removedTask.toString());
            printWithIndent(" Now you have " + taskCount + " tasks in the list.");
            printWithIndent(DOTTED_LINE);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        taskCount++;

        if (isLoaded) {
            printWithIndent(DOTTED_LINE);
            printWithIndent(" Got it. I've added this task:");
            printWithIndent("   " + tasks.get(taskCount - 1).toString());
            printWithIndent(" Now you have " + taskCount + " tasks in the list.");
            printWithIndent(DOTTED_LINE);
        }
    }

    private void addToDo(String line, boolean isDone) {
        String description = line.trim();
        addTask(new ToDo(description, isDone));
    }

    private String[] splitDescriptionAndDateTime(String line, String delimiter) throws DukeException {
        String[] instructions = line.split(delimiter);
        if (instructions.length == 1) {
            instructions = line.split("\\|");
        }

        if (instructions.length < INSTRUCTION_LENGTH) {
            throw new DukeException("\u2639 OOPS!!! Cannot decipher description or date/time.");
        }

        instructions[0] = instructions[0].trim();
        instructions[1] = instructions[1].trim();

        if (instructions[0].isBlank() || instructions[1].isBlank()) {
            throw new DukeException("\u2639 OOPS!!! Cannot decipher description or date/time.");
        }

        return instructions;
    }

    private void addDeadline(String line, boolean isDone) throws DukeException {
        String[] instructions = splitDescriptionAndDateTime(line, BY);

        String description = instructions[0];
        String by = instructions[1];

        addTask(new Deadline(description, isDone, by));

    }

    private void addEvent(String line, boolean isDone) throws DukeException {
        String[] instructions = splitDescriptionAndDateTime(line, AT);

        String description = instructions[0];
        String at = instructions[1];

        addTask(new Event(description, isDone, at));
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
    }

    private void verifyInstructionLength(String[] instructions) throws DukeException {
        if (instructions.length < INSTRUCTION_LENGTH) {
            throw new DukeException("\u2639 OOPS!!! The description of a " + instructions[0] + " cannot be empty.");
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
                    System.exit(0);
                case TODO:
                    verifyInstructionLength(instructions);
                    addToDo(instructions[1], false);
                    break;
                case DEADLINE:
                    verifyInstructionLength(instructions);
                    addDeadline(instructions[1], false);
                    break;
                case EVENT:
                    verifyInstructionLength(instructions);
                    addEvent(instructions[1], false);
                    break;
                case DONE:
                    verifyInstructionLength(instructions);
                    markTaskAsDone(instructions[1]);
                    break;
                case DELETE:
                    verifyInstructionLength(instructions);
                    deleteTask(instructions[1]);
                    break;
                default:
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                try {
                    saveToFile();
                } catch (IOException e) {
                    throw new DukeException("Error Writing File!");
                }
            } catch (DukeException ignored) {

            }
        }
    }

    private void saveToFile() throws IOException {
        StringBuilder fileContent = new StringBuilder();

        for (int i = 0; i < taskCount; i++) {
            fileContent.append(tasks.get(i).getFormattedTask());
        }

        Path folderPath = Paths.get(FOLDER_PATH);
        if (!Files.exists(folderPath) && !new File(FOLDER_PATH).mkdir()) {
            System.out.println("Error creating directory!");
        }

        Path filePath = Paths.get(FOLDER_PATH, FILE_NAME);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath);
        bufferedWriter.write(fileContent.toString());
        bufferedWriter.close();
    }
}
