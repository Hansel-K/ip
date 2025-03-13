package capy.task;

/**
 * Represents a task with a description and its completion status
 * This is an abstract class to be extended by specific types of tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // Set default as not done
    }

    /**
     * Returns the status icon of the task
     *
     * @return "X" if the task is done, else return a space character " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task
     *
     * @return a string in the format "[status] description"
     */
  
    public String getDescription() {
        return this.description; // Return the description of the task
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to a format suitable for saving to a file
     *
     * @return a formatted string representing the task's data
     */
    public abstract String toFileFormat(); // Abstract method for saving tasks
}
