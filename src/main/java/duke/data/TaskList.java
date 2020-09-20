package duke.data;

import duke.data.exception.DukeException;
import duke.data.task.Task;

import java.util.ArrayList;

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
     * Returns task using index.
     *
     * @param index Index of task.
     * @return Task at that index.
     * @throws DukeException If index is invalid.
     */
    public Task getTask(int index) throws DukeException {
        verifyTaskNumber(index);
        return tasks.get(index);
    }

    /**
     * Returns ArrayList of tasks.
     *
     * @return Tasks as ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
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
     * Removes task using index.
     *
     * @param index Index of task to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks task as done using index.
     *
     * @param index Index of task to mark as done.
     * @throws DukeException If index is invalid.
     */
    public void markTaskAsDone(int index) throws DukeException {
        verifyTaskNumber(index);
        tasks.get(index).markAsDone();
    }

    /**
     * Checks if index of task is valid.
     *
     * @param index Index of task to check.
     * @throws DukeException If index is invalid.
     */
    private void verifyTaskNumber(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number cannot be out of range.");
        }
    }
}
