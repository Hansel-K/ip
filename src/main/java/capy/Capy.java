package capy;

import capy.command.Command;
import capy.parser.Parser;
import capy.storage.Storage;
import capy.tasklist.TaskList;
import capy.ui.Ui;

/**
 * The main class of the application that initializes the components,
 * processes user input, and executes commands to manage a task list
 */
public class Capy {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs the Capy application by initializing the UI, storage, and task list
     *
     * @param filePath the file path where tasks will be loaded from and saved to
     */
    public Capy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (CapyException e) {
            ui.showLoadingError();
            tasks = new TaskList(); // Initialize with an empty task list if loading fails
        }
    }

    /**
     * Runs the main application loop, handling user commands until the exit command is issued
     * The loop reads user input, parses it into commands, executes the commands, and
     * displays appropriate output or error messages
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); // Read user input
                ui.showLine(); // Print a separator line
                Command command = Parser.parse(fullCommand); // Parse input into a Command
                command.execute(tasks, ui, storage); // Execute the parsed command
                isExit = command.isExit(); // Check if this is the exit command
            } catch (CapyException e) {
                ui.showError(e.getMessage()); // Display error messages for invalid commands
            } finally {
                ui.showLine(); // Always print a separator line at the end
            }
        }
    }

    /**
     * The main entry point of the application
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new Capy("./data/capy.txt").run(); // Run the application with the specified data file path
    }
}
