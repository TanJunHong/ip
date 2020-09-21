package duke.data;

import duke.data.exception.DukeException;
import duke.data.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Stores tasks in ArrayList.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Initializes tasks ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes tasks ArrayList using parameter.
     *
     * @param tasks Tasks list to use.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns size of tasks.
     *
     * @return Length of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns task of corresponding task number.
     *
     * @param taskNumber Task number of task.
     * @return Task of the corresponding task number.
     * @throws DukeException If task number does not exist.
     */
    public Task getTask(int taskNumber) throws DukeException {
        int index = findTaskIndex(taskNumber);
        return getTaskUsingIndex(index);
    }

    /**
     * Returns task using index.
     *
     * @param index Index of task.
     * @return Task at that index.
     */
    public Task getTaskUsingIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Returns ArrayList of tasks that are of corresponding date & time.
     *
     * @param date Date of task.
     * @param time Time of task.
     * @return Tasks with corresponding date & time.
     */
    public ArrayList<Task> getTasks(LocalDate date, LocalTime time) {
        return (ArrayList<Task>) tasks.stream()
                .filter(task -> date == null || date.equals(task.getDate()))
                .filter(task -> time == null || time.equals(task.getTime()))
                .collect(Collectors.toList());
    }

    /**
     * Returns ArrayList of tasks that contains keyword.
     *
     * @param keyword Keyword to search.
     * @return Tasks that contains keyword.
     */
    public ArrayList<Task> getTasks(String keyword) {
        return (ArrayList<Task>) tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Adds task to tasks ArrayList.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from tasks ArrayList.
     *
     * @param taskNumber Task number of task to remove.
     * @throws DukeException If task number is invalid.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        int index = findTaskIndex(taskNumber);
        tasks.remove(index);
    }

    /**
     * Marks task as done.
     *
     * @param taskNumber Task number of task to mark as done.
     * @throws DukeException If task number is invalid.
     */
    public void markTaskAsDone(int taskNumber) throws DukeException {
        int index = findTaskIndex(taskNumber);
        tasks.get(index).markAsDone();
    }

    /**
     * Returns index of task using task number.
     *
     * @param taskNumber Task number of task to find index.
     * @return Index of task.
     * @throws DukeException If task number is invalid.
     */
    private int findTaskIndex(int taskNumber) throws DukeException {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskNumber() == taskNumber) {
                return i;
            }
        }
        throw new DukeException("Invalid task number.");
    }
}
