package duke.data;

import duke.data.exception.DukeException;
import duke.data.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) throws DukeException {
        verifyTaskNumber(index);
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks(LocalDate date, LocalTime time) {
        return (ArrayList<Task>) tasks.stream()
                .filter(task -> date == null || date.equals(task.getDate()))
                .filter(task -> time == null || time.equals(task.getTime()))
                .collect(Collectors.toList());
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTaskAsDone(int index) throws DukeException {
        verifyTaskNumber(index);
        tasks.get(index).markAsDone();
    }

    private void verifyTaskNumber(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number cannot be out of range.");
        }
    }
}
