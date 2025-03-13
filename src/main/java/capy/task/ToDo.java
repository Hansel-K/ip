package capy.task;

/**
 * Represents a To-Do task with a description
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo task with the given description and completion status
     *
     * @param description The description of the task.
     * @param isDone      Whether the task is completed.
     */
    public ToDo(String description, boolean isDone) { // Constructor to load from file
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return a string in the format "[T][status] description"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the ToDo task to a format suitable for saving to a file
     *
     * @return a formatted string representing the ToDo task's data
     */
    @Override
    public String toFileFormat() { // Method to save to file
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}


