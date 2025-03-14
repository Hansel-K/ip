package capy.task;

/**
 * Represents an Event task with a description, start time, and end time
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an Event task with the given description, start time, end time, and completion status
     *
     * @param description the description of the event
     * @param start       the start time of the event
     * @param end         the end time of the event
     * @param isDone      whether the event is completed
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description);
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the Event task
     *
     * @return a string in the format "[E][status] description (from: start to: end)"
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " " +
                                                    "to: " + end + ")";
    }

    /**
     * Converts the Event task to a format suitable for saving to a file
     *
     * @return a formatted string representing the Event task's data
     */
    @Override
    public String toFileFormat() { // Method to save to file
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
