package capy.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) { // Constructor to load from file
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() { // Method to save to file
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}


