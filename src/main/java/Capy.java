import java.util.Scanner; // Import Scanner class to enable reading of inputs

public class Capy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Capy");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner inputObj = new Scanner(System.in); // Create Scanner object
        String userInput; // Declare string to hold user input

        // Read input until "bye" is received
        while (true) {

            userInput = inputObj.nextLine(); // Read user input

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Break loop when "bye" is received
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(userInput); // Echo user input
                System.out.println("____________________________________________________________");
            }
        }
    }
}