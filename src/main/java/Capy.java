import javax.swing.*;
import java.util.Scanner; // Import Scanner class to enable reading of inputs

public class Capy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Capy");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner inputObj = new Scanner(System.in); // Create Scanner object
        String userInput; // Declare string to hold user input

        Task[] tasks = new Task[100]; // Declare array of Task objects
        int counter = 0; // Declare int to track how many strings have been added

        // Read input until "bye" is received
        while (true) {

            userInput = inputObj.nextLine(); // Read user input

            // user inputs to handle:
            // "bye", "list", "mark", "unmark"
            // "todo", "deadline", "event"

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Break loop when "bye" is received
            } else if (userInput.equals("list")) {
                // Print stored tasks as a list
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    // Iterate through task array to print stored tasks
                    System.out.println(i + 1 +". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                // Mark task as done
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskNumber >= 0 && taskNumber < counter) {
                    tasks[taskNumber].mark();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tasks[taskNumber]);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (userInput.startsWith("unmark")) {
                // Unmark task as not done
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskNumber >= 0 && taskNumber < counter) {
                    tasks[taskNumber].unmark();
                    System.out.println("____________________________________________________________");
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    System.out.println(tasks[taskNumber]);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(5); // beginindex determined by length of command
                    tasks[counter] = new ToDo(description);
                    counter++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[counter - 1]);
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split(" /by ");
                String description = parts[0].substring(9); // beginindex determined by length of command
                String dueDate = parts[1];
                tasks[counter] = new Deadline(description, dueDate);
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + tasks[counter - 1]);
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.split(" /from | /to ");
                String description = parts[0].substring(6); // beginindex determined by length of command
                String start = parts[1];
                String end = parts[2];
                tasks[counter] = new Event(description, start, end);
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + tasks[counter - 1]);
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                // Add user input to string array
                tasks[counter] = new Task(userInput);
                counter++; // Update counter

                // Echo that user input has been added
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }

        }
    }
}