import java.util.Scanner; // Import Scanner class to enable reading of inputs

public class Capy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Capy");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner inputObj = new Scanner(System.in); // Create Scanner object
        String userInput; // Declare string to hold user input

        String[] strArray = new String[100]; // Declare string array to store user inputs
        int counter = 0; // Declare int to track how many strings have been added

        // Read input until "bye" is received
        while (true) {

            userInput = inputObj.nextLine(); // Read user input

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Break loop when "bye" is received
            } else if (userInput.equals("list")) {
                // Print stored strings as a list
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    // Iterate through string array to print stored strings
                    System.out.println(i + 1 +". " + strArray[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                // Add user input to string array
                strArray[counter] = userInput;
                counter++; // Update counter

                // Echo that user input has been added
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
    }
}