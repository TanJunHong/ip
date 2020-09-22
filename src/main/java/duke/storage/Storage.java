package duke.storage;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.parser.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Stores tasks into file and loads tasks from file.
 */
public class Storage {

    public static final String LOAD_ERROR = "Error loading file.";
    private static final String READ_ERROR = "Error reading file.";
    private static final String DIRECTORY_ERROR = "Error creating directory.";
    private static final String WRITE_ERROR = "Error writing file.";

    private final String folder;
    private final String file;

    /**
     * Initializes path of folder and file.
     *
     * @param filePath Full file path.
     */
    public Storage(String filePath) {
        int index = filePath.lastIndexOf("/");
        folder = filePath.substring(0, index);
        file = filePath.substring(index + 1);
    }

    /**
     * Loads tasks, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of tasks.
     * @throws DukeException If there is problem reading file.
     */
    public ArrayList<Task> load() throws DukeException {
        Path path = Paths.get(folder, file);
        ArrayList<Task> tasks = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    Task task = Parser.readTask(line);
                    tasks.add(task);
                }
            } catch (IOException e) {
                throw new DukeException(READ_ERROR);
            }
        }

        return tasks;
    }

    /**
     * Saves tasks into file.
     *
     * @param tasks ArrayList of tasks to save.
     * @throws DukeException If there is problem writing or saving file.
     */
    public void save(TaskList tasks) throws DukeException {
        StringBuilder fileContent = new StringBuilder();

        for (int i = 0; i < tasks.getSize(); i++) {
            fileContent.append(tasks.getTaskUsingIndex(i).getFormattedTask());
        }

        Path folderPath = Paths.get(folder);
        if (!Files.exists(folderPath) && !new File(folder).mkdir()) {
            throw new DukeException(DIRECTORY_ERROR);
        }

        Path filePath = Paths.get(folder, file);
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath);
            bufferedWriter.write(fileContent.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new DukeException(WRITE_ERROR);
        }
    }
}
