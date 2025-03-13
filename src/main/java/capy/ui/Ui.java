package capy.ui;

import capy.task.Task;

import java.util.Scanner;

// Handles user interaction (input and output)
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

    // Handles message printing
    public Ui() {
        scanner = new Scanner(System.in);
    }

    // Displays a welcome message
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Capy");
        System.out.println("What can I do for you?");
        showLine();
    }

    // Reads and returns a user command
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    // Displays a generic error message
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    // Displays an error when tasks fail to load
    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }

    // Displays a line separator
    public void showLine() {
        System.out.println(LINE_SPACING);
    }

    // Displays the message when a task is added
    public void showAddTaskMessage(Task task, int size) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    // Displays the message when a task is deleted
    public void showDeleteTaskMessage(Task task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    // Displays the message when a task is marked as done
    public void showMarkTaskMessage(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
        showLine();
    }

    // Displays the message when a task is marked as not done
    public void showUnmarkTaskMessage(Task task) {
        showLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" " + task);
        showLine();
    }
}