package duke.data;

import duke.data.exception.DukeException;
import duke.data.task.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number cannot be out of range.");
        }
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number cannot be out of range.");
        }
        tasks.get(index).markAsDone();
    }
}
