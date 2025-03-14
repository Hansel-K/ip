package capy.storage;

import capy.*;
import capy.task.Deadline;
import capy.task.Event;
import capy.task.Task;
import capy.task.ToDo;
import capy.ui.Ui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Manages the loading and saving of tasks to a persistent file storage
 * Handles the creation of directories and files as needed, and ensures tasks are
 * properly saved and loaded in a specified format
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object for managing the given file path
     *
     * @param filePath the path to the file where tasks will be saved and loaded from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the file path
     *
     * @return an ArrayList of tasks loaded from the file
     * @throws CapyException if the file contains corrupted data or cannot be accessed
     */
    public ArrayList<Task> loadTasks() throws CapyException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // Ensure the directory exists
            Files.createDirectories(Paths.get("./data"));
            File file = new File(filePath);

            // Read tasks if the file exists
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");

                    // Check for corrupted file format
                    if (parts.length < 3) {
                        throw new CapyException(Ui.CORRUPTED_FILE);
                    }

                    String type = parts[0].trim();
                    boolean isDone = parts[1].trim().equals("1");

                    // Parse tasks based on their type
                    switch (type) {
                        case "T":
                            tasks.add(new ToDo(parts[2].trim(), isDone));
                            break;
                        case "D":
                            tasks.add(new Deadline(parts[2].trim(), parts[3].trim(), isDone));
                            break;
                        case "E":
                            tasks.add(new Event(parts[2].trim(), parts[3].trim(), parts[4].trim(), isDone));
                            break;
                        default:
                            throw new CapyException(Ui.CORRUPTED_FILE);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            throw new CapyException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file specified in the file path
     *
     * @param tasks the list of tasks to be saved to the file
     * @throws CapyException if an I/O error occurs while saving the tasks
     */
    public void saveTasks(ArrayList<Task> tasks) throws CapyException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new CapyException("Error saving tasks: " + e.getMessage());
        }
    }
}
