package capy;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description);
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " " +
                                                    "to: " + end + ")";
    }

    @Override
    public String toFileFormat() { // Method to save to file
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
