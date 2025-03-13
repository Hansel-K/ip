package capy.ui;

import capy.task.Task;

import java.util.Scanner;

/**
 * Handles user interaction, including input and output
 * Provides methods for displaying messages, reading user commands,
 * and showing feedback or errors to the user
 */
public class Ui {
    private static final String LINE_SPACING = "____________________________________________________________";

    // Constants for common error messages
    public static final String INVALID_COMMAND = "Oops! Command not Recognised! Please try a valid command!";
    public static final String INVALID_TASK_NUMBER = "Oops! Invalid task number!";
    public static final String MISSING_TASK_NUMBER = "Oops! Seems like the command is missing a task number!";
    public static final String NOT_AN_INTEGER = "Oops! Please enter a valid integer as a task number!";
    public static final String MISSING_DESCRIPTION = "Oops! Seems like the command is missing a description!";
    public static final String MISSING_DETAILS = "Oops! Seems like the command is missing some details!";
    public static final String WRONG_FORMAT = "Oops! Seems like the command is in the wrong format!";
    public static final String CORRUPTED_FILE = "Oh no! Data file is corrupted!";

    private Scanner scanner;

    /**
     * Constructs a new Ui object that handles user interaction
     * Initializes the scanner for reading user input
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to greet the user and start the application
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Capy");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Reads and returns a command entered by the user
     *
     * @return The user command as a trimmed string
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a generic error message
     *
     * @param errorMessage the error message to be displayed
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays an error message when tasks fail to load
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }

    /**
     * Displays a line separator for formatting the output
     */
    public void showLine() {
        System.out.println(LINE_SPACING);
    }

    /**
     * Displays a message when a task is successfully added
     *
     * @param task the task that was added
     * @param size the total number of tasks in the task list after adding the task
     */
    public void showAddTaskMessage(Task task, int size) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message when a task is successfully deleted
     *
     * @param task the task that was deleted
     * @param size the total number of tasks in the task list after deleting the task
     */
    public void showDeleteTaskMessage(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message when a task is successfully marked as done
     *
     * @param task the task that was marked as done
     */
    public void showMarkTaskMessage(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        showLine();
    }

    /**
     * Displays a message when a task is successfully marked as not done
     *
     * @param task the task that was marked as not done
     */
    public void showUnmarkTaskMessage(Task task) {
        showLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" " + task);
        showLine();
    }
}