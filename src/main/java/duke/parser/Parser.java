package duke.parser;

import duke.commands.*;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

public class Parser {

    private final static int MAX_INSTRUCTION_LENGTH = 3;

    public static Command parse(String fullCommand) throws DukeException {

        String[] instructions = fullCommand.split(" ", 2);

        String commandWord = instructions[0].trim().toLowerCase();
        String[] furtherInstructions;
        String description;
        int taskNumber;

        Command command;
        Task task;

        switch (commandWord) {
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "todo":
            verifyInstruction(instructions);
            description = instructions[1].trim();
            task = new ToDo(description, false);
            command = new AddCommand(task);
            break;
        case "deadline":
            furtherInstructions = splitDescriptionAndDateTime(instructions[1], "/by");
            description = furtherInstructions[0].trim();
            String by = furtherInstructions[1].trim();
            task = new Deadline(description, false, by);
            command = new AddCommand(task);
            break;
        case "event":
            furtherInstructions = splitDescriptionAndDateTime(instructions[1], "/at");
            description = furtherInstructions[0].trim();
            String at = furtherInstructions[1].trim();
            task = new Event(description, false, at);
            command = new AddCommand(task);
            break;
        case "done":
            verifyInstruction(instructions);
            try {
                taskNumber = Integer.parseInt(instructions[1]);
                command = new DoneCommand(taskNumber - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("Task number cannot be non-numeric.");
            }
            break;
        case "delete":
            verifyInstruction(instructions);
            try {
                taskNumber = Integer.parseInt(instructions[1]);
                command = new DeleteCommand(taskNumber - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("Task number cannot be non-numeric.");
            }
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    private static String[] splitDescriptionAndDateTime(String line, String delimiter) throws DukeException {
        String[] instructions = line.split(delimiter, 2);

        verifyInstruction(instructions);

        return new String[]{instructions[0].trim(), instructions[1].trim()};
    }

    private static void verifyInstruction(String[] instructions) throws DukeException {
        if (instructions.length != MAX_INSTRUCTION_LENGTH - 1) {
            throw new DukeException("The description of a " + instructions[0] + " cannot be empty.");
        } else if (instructions[0].isBlank() || instructions[1].isBlank()) {
            throw new DukeException("Cannot decipher description or date/time.");
        }
    }

    public Task readTask(String line) {
        String[] instructions = line.split("\\|", MAX_INSTRUCTION_LENGTH + 1);

        String taskType = instructions[0].trim();
        boolean isDone = instructions[1].trim().equals("1");
        String description = instructions[2].trim();
        Task task;

        switch (taskType) {
        case "D":
            String by = instructions[3].trim();
            task = new Deadline(description, isDone, by);
            break;
        case "E":
            String at = instructions[3].trim();
            task = new Event(description, isDone, at);
            break;
        case "T":
            task = new ToDo(description, isDone);
            break;
        default:
            task = null;
            break;
        }
        return task;
    }
}
