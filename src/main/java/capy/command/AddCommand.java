package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

/**
 * Represents a command to add a new task to the task list
 */
public class AddCommand extends Command {
    private final String fullCommand;

    /**
     * Constructs an AddCommand with the given user input
     *
     * @param fullCommand the user's input, which includes the task type and details
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the add command by identifying the task type (ToDo, Deadline, or Event)
     * and adding the corresponding task to the task list. Displays a message and saves
     * the updated task list
     *
     * @param tasks   the task list to which the task will be added
     * @param ui      the user interface to display messages
     * @param storage the storage system for saving tasks
     * @throws CapyException if the task type is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        if (fullCommand.startsWith("todo")) {
            tasks.addTodoTask(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            tasks.addDeadlineTask(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            tasks.addEventTask(fullCommand);
        } else {
            throw new CapyException("Invalid task type for AddCommand.");
        }
        Task addedTask = tasks.getTasks().get(tasks.size() - 1);
        ui.showAddTaskMessage(addedTask, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}