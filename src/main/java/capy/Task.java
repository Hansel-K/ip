package capy;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // Set default as not done
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toFileFormat(); // Abstract method for saving tasks
}
