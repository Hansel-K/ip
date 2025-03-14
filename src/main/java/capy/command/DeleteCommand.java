package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

/**
 * Represents a command to delete a task from the task list
 */
public class DeleteCommand extends Command {
    private final String fullCommand;

    /**
     * Constructs a DeleteCommand with the given user input
     *
     * @param fullCommand the user's input, which includes the "delete" command and the task number
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the delete command by removing the specified task from the task list,
     * displaying a message, and saving the updated task list
     *
     * @param tasks   the task list from which the task will be deleted
     * @param ui      the user interface to display messages
     * @param storage the storage system for saving tasks
     * @throws CapyException if the task number is invalid or out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        Task removedTask = tasks.deleteTask(fullCommand);
        ui.showDeleteTaskMessage(removedTask, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
