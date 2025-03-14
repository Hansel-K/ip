package capy.tasklist;

import capy.*;
import capy.task.Deadline;
import capy.task.Event;
import capy.task.Task;
import capy.task.ToDo;
import capy.ui.Ui;

import java.util.ArrayList;

/**
 * Manages the list of tasks and provides operations to modify and retrieve tasks
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor, constructs an empty task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the given tasks
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the entire list of tasks
     *
     * @return an ArrayList of Task objects
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task at a specific index.
     *
     * @param index the index of the task to retrieve (0-based)
     * @return the Task object at the specified index.
     * @throws CapyException if the index is out of bounds
     */
    public Task getTask(int index) throws CapyException {
        if (index < 0 || index >= tasks.size()) {
            throw new CapyException(Ui.INVALID_TASK_NUMBER);
        }
        return tasks.get(index);
    }

    /**
     * Displays all tasks in the task list using the UI
     *
     * @param ui the user interface for displaying tasks
     */
    public void listTasks(Ui ui) {
        ui.showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        ui.showLine();
    }

    /**
     * Adds a ToDo task to the task list
     *
     * @param userInput the user input containing the task description
     * @throws CapyException if the task description is missing
     */
    public void addTodoTask(String userInput) throws CapyException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new CapyException(Ui.MISSING_DESCRIPTION);
        }
        tasks.add(new ToDo(description));
    }

    /**
     * Adds a Deadline task to the task list
     *
     * @param userInput the user input containing the task details
     * @throws CapyException if the task details are incomplete or missing
     */
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

    /**
     * Adds an Event task to the task list
     *
     * @param userInput the user input containing the task details
     * @throws CapyException if the task details are incomplete or missing
     */
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

    /**
     * Marks a task as done based on the task number provided in the user input
     *
     * @param userInput the user input containing the "mark" command and the task number
     * @throws CapyException if the task number is missing, in the wrong format,
     *                        not a valid integer, or out of bounds.
     */
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

    /**
     * Marks a task as not done based on the task number provided in the user input
     *
     * @param userInput the user input containing the "unmark" command and the task number
     * @throws CapyException if the task number is missing, in the wrong format,
     *                        not a valid integer, or out of bounds
     */
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

    /**
     * Deletes a task from the task list based on the task number provided in the user input
     *
     * @param userInput the user input containing the "delete" command and the task number
     * @return the deleted Task object.
     * @throws CapyException if the task number is missing, in the wrong format,
     *                        not a valid integer, or out of bounds
     */
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
