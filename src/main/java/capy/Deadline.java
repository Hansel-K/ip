package capy;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate, boolean isDone) {
        super(description);
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    @Override
    public String toFileFormat() { // Method to save to file
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
    }
}
