package capy;

import java.util.Scanner; // Import Scanner class to enable reading of inputs
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Capy {

    // Error messages to avoid code duplication
    private static final String LINE_SPACING = "____________________________________________________________";
    private static final String INVALID_COMMAND = "Oops! Command not Recognised! PLease try a valid command!";
    private static final String INVALID_TASK_NUMBER = "Oops! Invalid task number!";
    private static final String MISSING_TASK_NUMBER = "Oops! Seems like the command is missing a task number!";
    private static final String NOT_AN_INTEGER = "Oops! Please enter a valid integer as a task number!";
    private static final String MISSING_DESCRIPTION = "Oops! Seems like the command is missing a description!";
    private static final String MISSING_DETAILS = "Oops! Seems like the command is missing some details!";
    private static final String CORRUPTED_FILE = "Oh no! Data file is corrupted!";

    private static final String FILE_PATH = "./data/capy.txt"; // Set file path as constant
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner inputObj = new Scanner(System.in);

    public static void main(String[] args) {
        loadTasks();
        System.out.println(LINE_SPACING);
        System.out.println("Hello! I'm Capy");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SPACING);

        while (true) {
            String userInput = inputObj.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                exit();
                break; // Terminate program
            }
            try {
                handleUserInput(userInput);
                saveTasks();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleUserInput(String userInput) {
        try {
            if (userInput.equals("bye")) {
                exit();
                return;
            } else if (userInput.equals("list")) {
                listTasks();
            } else if (userInput.startsWith("mark")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput);
            } else if (userInput.startsWith("todo")) {
                addTodoTask(userInput);
            } else if (userInput.startsWith("deadline")) {
                addDeadlineTask(userInput);
            } else if (userInput.startsWith("event")) {
                addEventTask(userInput);
            } else {
                throw new CapyException(INVALID_COMMAND);
            }
        } catch (CapyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadTasks() {
        try {
            Files.createDirectories(Paths.get("./data"));
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String taskData = fileScanner.nextLine();
                    String[] parts = taskData.split("\\|");
                    if (parts.length < 3) {
                        throw new CapyException(CORRUPTED_FILE);
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
                            throw new CapyException(CORRUPTED_FILE);
                    }
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void exit() {
        System.out.println(LINE_SPACING);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SPACING);
    }

    private static void listTasks() {
        System.out.println(LINE_SPACING);
        System.out.println("Here are the tasks in your list:");
        for (int taskPointer = 0; taskPointer < tasks.size(); taskPointer++) {
            System.out.println((taskPointer + 1) + ". " + tasks.get(taskPointer));
        }
        System.out.println(LINE_SPACING);
    }

    private static void markTask(String userInput) throws CapyException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new CapyException(MISSING_TASK_NUMBER);
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new CapyException(INVALID_TASK_NUMBER);
            }
            tasks.get(taskNumber).markDone();
            System.out.println(LINE_SPACING);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNumber));
            System.out.println(LINE_SPACING);
        } catch (NumberFormatException e) {
            throw new CapyException(NOT_AN_INTEGER);
        }
    }

    private static void unmarkTask(String userInput) throws CapyException {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            throw new CapyException(MISSING_TASK_NUMBER);
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new CapyException(INVALID_TASK_NUMBER);
            }
            tasks.get(taskNumber).unmarkDone();
            System.out.println(LINE_SPACING);
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskNumber));
            System.out.println(LINE_SPACING);
        } catch (NumberFormatException e) {
            throw new CapyException(NOT_AN_INTEGER);
        }
    }

    private static void addTodoTask(String userInput) throws CapyException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new CapyException(MISSING_DESCRIPTION);
        }
        tasks.add(new ToDo(description));
        System.out.println(LINE_SPACING);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE_SPACING);
    }

    private static void addDeadlineTask(String userInput) throws CapyException {
        String[] parts = userInput.split("/by");
        if (parts.length < 2) {
            throw new CapyException(MISSING_DETAILS);
        }
        String description = parts[0].substring(8).trim();
        String dueDate = parts[1].trim();
        if (description.isEmpty()) {
            throw new CapyException(MISSING_DESCRIPTION);
        }
        tasks.add(new Deadline(description, dueDate, false));
        System.out.println(LINE_SPACING);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE_SPACING);
    }

    private static void addEventTask(String userInput) throws CapyException {
        String[] parts = userInput.split("/from|/to");
        if (parts.length < 3) {
            throw new CapyException(MISSING_DETAILS);
        }
        String description = parts[0].substring(5).trim();
        String start = parts[1].trim();
        String end = parts[2].trim();
        if (description.isEmpty()) {
            throw new CapyException(MISSING_DESCRIPTION);
        }
        tasks.add(new Event(description, start, end, false));
        System.out.println(LINE_SPACING);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE_SPACING);
    }
}
