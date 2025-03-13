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

// Manages loading and saving tasks to the file
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws CapyException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get("./data"));
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length < 3) {
                        throw new CapyException(Ui.CORRUPTED_FILE);
                    }
                    String type = parts[0].trim();
                    boolean isDone = parts[1].trim().equals("1");
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
