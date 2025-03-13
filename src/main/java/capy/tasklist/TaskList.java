package capy.tasklist;

import capy.*;
import capy.task.Deadline;
import capy.task.Event;
import capy.task.Task;
import capy.task.ToDo;
import capy.ui.Ui;

import java.util.ArrayList;

// Manages the task  list and task-related operations
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) throws CapyException {
        if (index < 0 || index >= tasks.size()) {
            throw new CapyException(Ui.INVALID_TASK_NUMBER);
        }
        return tasks.get(index);
    }

    public void listTasks(Ui ui) {
        ui.showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        ui.showLine();
    }

    public void addTodoTask(String userInput) throws CapyException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new CapyException(Ui.MISSING_DESCRIPTION);
        }
        tasks.add(new ToDo(description));
    }

    public void addDeadlineTask(String userInput) throws CapyException {
        String[] parts = userInput.split("/by");
        if (parts.length < 2) {
            throw new CapyException(Ui.MISSING_DETAILS);
        }

        String description = parts[0].substring(8).trim();
        String dueDate = parts[1].trim();

        if (description.isEmpty()) {
            throw new CapyException(Ui.MISSING_DESCRIPTION);
        }
        tasks.add(new Deadline(description, dueDate, false));
    }

    public void addEventTask(String userInput) throws CapyException {
        String[] parts = userInput.split("/from|/to");
        if (parts.length < 3) {
            throw new CapyException(Ui.MISSING_DETAILS);
        }
        String description = parts[0].substring(5).trim();
        String start = parts[1].trim();
        String end = parts[2].trim();

        if (description.isEmpty()) {
            throw new CapyException(Ui.MISSING_DESCRIPTION);
        }

        Event newEvent = new Event(description, start, end, false);
        tasks.add(newEvent);
    }

    public void markTask(String userInput) throws CapyException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new CapyException(Ui.MISSING_TASK_NUMBER);
        } else if (parts.length > 2) {
            throw new CapyException(Ui.WRONG_FORMAT);
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new CapyException(Ui.INVALID_TASK_NUMBER);
            }
            tasks.get(taskNumber).markDone();
        } catch (NumberFormatException e) {
            throw new CapyException(Ui.NOT_AN_INTEGER);
        }
    }

    public void unmarkTask(String userInput) throws CapyException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new CapyException(Ui.MISSING_TASK_NUMBER);
        } else if (parts.length > 2) {
            throw new CapyException(Ui.WRONG_FORMAT);
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new CapyException(Ui.INVALID_TASK_NUMBER);
            }
            tasks.get(taskNumber).unmarkDone();
        } catch (NumberFormatException e) {
            throw new CapyException(Ui.NOT_AN_INTEGER);
        }
    }

    public Task deleteTask(String userInput) throws CapyException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new CapyException(Ui.MISSING_TASK_NUMBER);
        } else if (parts.length > 2) {
            throw new CapyException(Ui.WRONG_FORMAT);
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new CapyException(Ui.INVALID_TASK_NUMBER);
            }
            return tasks.remove(taskNumber);
        } catch (NumberFormatException e) {
            throw new CapyException(Ui.NOT_AN_INTEGER);
        }
    }

}
