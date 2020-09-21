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

    public Task getTask(int taskNumber) throws DukeException {
        int index = findTaskIndex(taskNumber);
        return getTaskUsingIndex(index);
    }

    public Task getTaskUsingIndex(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks(LocalDate date, LocalTime time) {
        return (ArrayList<Task>) tasks.stream()
                .filter(task -> date == null || date.equals(task.getDate()))
                .filter(task -> time == null || time.equals(task.getTime()))
                .collect(Collectors.toList());
    }

    public ArrayList<Task> getTasks(String keyword) {
        return (ArrayList<Task>) tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) throws DukeException {
        int index = findTaskIndex(taskNumber);
        tasks.remove(index);
    }

    public void markTaskAsDone(int taskNumber) throws DukeException {
        int index = findTaskIndex(taskNumber);
        tasks.get(index).markAsDone();
    }

    private int findTaskIndex(int taskNumber) throws DukeException {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskNumber() == taskNumber) {
                return i;
            }
        }
        throw new DukeException("Task number cannot be out of range.");
    }
}
