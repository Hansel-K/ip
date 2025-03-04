package capy;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner; // Import Scanner class to enable reading of inputs

public class Capy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Capy");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner inputObj = new Scanner(System.in); // Create Scanner object
        String userInput; // Declare string to hold user input

        ArrayList<Task> tasks = new ArrayList<>(); // Use ArrayList instead of Array to store objects

        // Read input until "bye" is received
        while (true) {
            userInput = inputObj.nextLine().trim(); // Read user input
            // User inputs to handle:
            // "bye", "list", "mark", "unmark", "delete"
            // "todo", "deadline", "event"
            try {
                if (userInput.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break; // Break loop when "bye" is received
                } else if (userInput.equals("list")) {
                    // Print stored tasks as a list
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        // Iterate through task array to print stored tasks
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark")) {
                    // Input command "mark taskNumber"
                    try {
                        String[] parts = userInput.split(" ");
                        if (parts.length < 2) {
                            throw new CapyException("Oops! Seems like the command is missing a task number!");
                        } else if (parts.length > 2) {
                            throw new CapyException("Oops! Seems like the command has included extrat details!");
                        }
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            tasks.get(taskNumber).markDone();
                            System.out.println("____________________________________________________________");
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println(tasks.get(taskNumber));
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println("Oops! Invalid task number!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Oops! Please enter a valid integer as a task number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! Index is out of bounds. Please enter an index within bounds!");
                    }
                } else if (userInput.startsWith("unmark")) {
                    // Input command "unmark taskNumber"
                    try {
                        String[] parts = userInput.split(" ");
                        if (parts.length < 2) {
                            throw new CapyException("Oops! Seems like the command is missing a task number");
                        } else if (parts.length > 2) {
                            throw new CapyException("Oops! Seems like the command has included extra details!");
                        }
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            tasks.get(taskNumber).unmarkDone();
                            System.out.println("____________________________________________________________");
                            System.out.println("Ok, I've marked this task as not done yet: ");
                            System.out.println(tasks.get(taskNumber));
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Oops! Please enter a valid integer as a task number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! Index is out of bounds. Please enter an index within bounds!");
                    }
                } else if (userInput.startsWith("delete")) {
                    // Input command "delete taskNumber"
                    try {
                        String[] parts = userInput.split(" ");
                        if (parts.length < 2) {
                            throw new CapyException("Oops! Seems like the command is missing a task number!");
                        } else if (parts.length > 2) {
                            throw new CapyException("Oops! Seems like the command has included extra details!");
                        }
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            Task removedTask = tasks.remove(taskNumber); // ArrayList helps shift taskNumber
                            System.out.println("____________________________________________________________");
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(" " + removedTask);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println("Oops! Invalid task number!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Oops! Please enter a valid integer as a task number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! Index is out of bounds. Please enter an index within bounds!");
                    }
                } else if (userInput.startsWith("todo")) {
                    // Input command "todo description"
                    String description = userInput.substring(4).trim(); // Filter out "todo"
                    if (description.trim().isEmpty()) {
                        throw new CapyException("Oops! Seems like the command is missing a description!");
                    }
                    tasks.add(new ToDo(description));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("deadline")) {
                    // Input command "deadline description /by dueDate"
                    String[] parts = userInput.split("/by");
                    if (parts.length < 2) {
                        throw new CapyException("Oops! Seems like the command is missing some details!");
                    }
                    String description = parts[0].substring(8).trim(); // Filter out "deadline"
                    String dueDate = parts[1].trim();
                    if (description.isEmpty()) {
                        throw new CapyException("Oops! Seems like the command is missing a description!");
                    }
                    tasks.add(new Deadline(description, dueDate));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("event")) {
                    // Input command "event description /from start /to end"
                    String[] parts = userInput.split("/from|/to");
                    if (parts.length < 3) {
                        throw new capy.CapyException("Oops! Seems like the command is missing some details!");
                    }
                    String description = parts[0].substring(5).trim(); // Filter out "event"
                    String start = parts[1].trim();
                    String end = parts[2].trim();
                    if (description.isEmpty()) {
                        throw new capy.CapyException("Oops! Seems like the command is missing a description!");
                    } else if (start.isEmpty()) {
                        throw new CapyException("Oops! Seems like the command is missing a start date/time!");
                    }
                    tasks.add(new Event(description, start, end));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new CapyException("Oops! Command not recognised! Please try a valid command!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}