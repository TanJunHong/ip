package duke.parser;

import duke.commands.*;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {

    private final static int MAX_INSTRUCTION_LENGTH = 3;

    public static Command parse(String fullCommand) throws DukeException {
        String[] instructions = fullCommand.split(" ", MAX_INSTRUCTION_LENGTH - 1);

        String commandWord = instructions[0].trim().toLowerCase();
        String[] furtherInstructions;
        String description;
        int taskNumber;

        Command command;
        Task task;

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            LocalDate date = null;
            LocalTime time = null;
            if (instructions.length > 1) {
                String dateTimeFilter = instructions[1].trim();
                Object[] dateTime = parseDateTime(dateTimeFilter);
                date = (LocalDate) dateTime[0];
                time = (LocalTime) dateTime[1];
            }
            command = new ListCommand(date, time);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case AddCommand.TODO_COMMAND_WORD:
            verifyInstruction(instructions);
            description = instructions[1].trim();
            task = new ToDo(description, false);
            command = new AddCommand(task);
            break;
        case AddCommand.DEADLINE_COMMAND_WORD:
            furtherInstructions = splitDescriptionAndDateTime(instructions[1], Deadline.DELIMITER);
            description = furtherInstructions[0].trim();
            String by = furtherInstructions[1].trim();
            Object[] byDateTime = parseDateTime(by);
            LocalDate byDate = (LocalDate) byDateTime[0];
            LocalTime byTime = (LocalTime) byDateTime[1];
            task = new Deadline(description, false, byDate, byTime);
            command = new AddCommand(task);
            break;
        case AddCommand.EVENT_COMMAND_WORD:
            furtherInstructions = splitDescriptionAndDateTime(instructions[1], Event.DELIMITER);
            description = furtherInstructions[0].trim();
            String at = furtherInstructions[1].trim();
            Object[] atDateTime = parseDateTime(at);
            LocalDate atDate = (LocalDate) atDateTime[0];
            LocalTime atTime = (LocalTime) atDateTime[1];
            task = new Event(description, false, atDate, atTime);
            command = new AddCommand(task);
            break;
        case DoneCommand.COMMAND_WORD:
            verifyInstruction(instructions);
            taskNumber = convertToNumber(instructions[1]);
            command = new DoneCommand(taskNumber - 1);
            break;
        case DeleteCommand.COMMAND_WORD:
            verifyInstruction(instructions);
            taskNumber = convertToNumber(instructions[1]);
            command = new DeleteCommand(taskNumber - 1);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }

    private static int convertToNumber(String instruction) throws DukeException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(instruction);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number cannot be non-numeric.");
        }

        return taskNumber;
    }

    private static Object[] parseDateTime(String line) throws DukeException {
        try {
            String[] dateTimeString = line.split(" ", 2);
            LocalDate date = LocalDate.parse(dateTimeString[0].trim());
            LocalTime time = null;
            if (dateTimeString.length == 2) {
                time = LocalTime.parse(dateTimeString[1].trim());
            }
            return new Object[]{date, time};
        } catch (DateTimeParseException e) {
            throw new DukeException("Error parsing date/time");
        }
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

    public Task readTask(String line) throws DukeException {
        String[] instructions = line.split("\\|", MAX_INSTRUCTION_LENGTH + 1);
        verifyInstructionLength(instructions, MAX_INSTRUCTION_LENGTH);

        String taskType = instructions[0].trim();
        boolean isDone;
        String description = instructions[2].trim();
        Task task;

        switch (instructions[1].trim()) {
        case Task.COMPLETE:
            isDone = true;
            break;
        case Task.INCOMPLETE:
            isDone = false;
            break;
        default:
            throw new DukeException("Unable to read task completion status.");
        }

        switch (taskType) {
        case Deadline.LOGO:
            verifyInstructionLength(instructions, MAX_INSTRUCTION_LENGTH + 1);
            String by = instructions[3].trim();
            Object[] byDateTime = parseDateTime(by);
            LocalDate byDate = (LocalDate) byDateTime[0];
            LocalTime byTime = (LocalTime) byDateTime[1];
            task = new Deadline(description, isDone, byDate, byTime);
            break;
        case Event.LOGO:
            verifyInstructionLength(instructions, MAX_INSTRUCTION_LENGTH + 1);
            String at = instructions[3].trim();
            Object[] atDateTime = parseDateTime(at);
            LocalDate atDate = (LocalDate) atDateTime[0];
            LocalTime atTime = (LocalTime) atDateTime[1];
            task = new Event(description, isDone, atDate, atTime);
            break;
        case ToDo.LOGO:
            task = new ToDo(description, isDone);
            break;
        default:
            throw new DukeException("Unable to read task type.");
        }
        return task;
    }

    private void verifyInstructionLength(String[] instructions, int instructionLength) throws DukeException {
        if (instructions.length < instructionLength) {
            throw new DukeException("Missing parameter(s) for task.");
        }
    }
}
