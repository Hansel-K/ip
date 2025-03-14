package capy.task;

/**
 * Represents a Deadline task with a description and a due date
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructs a Deadline task with the given description, due date, and completion status
     *
     * @param description the description of the deadline
     * @param dueDate     the due date of the task
     * @param isDone      whether the task is completed
     */
    public Deadline(String description, String dueDate, boolean isDone) {
        super(description);
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the Deadline task
     *
     * @return a string in the format "[D][status] description (by: dueDate)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Converts the Deadline task to a format suitable for saving to a file
     *
     * @return a formatted string representing the Deadline task's data
     */
    @Override
    public String toFileFormat() { // Method to save to file
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
    }
}
