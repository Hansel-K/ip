package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

/**
 * Represents a command to mark a task as not completed in the task list
 */
public class UnmarkCommand extends Command {
    private final String fullCommand;

    /**
     * Constructs an UnmarkCommand with the given user input
     *
     * @param fullCommand the user's input, which includes the "unmark" command and the task number
     */
    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the unmark command by marking the specified task as not completed,
     * displaying a message, and saving the updated task list
     *
     * @param tasks   the task list containing the task to be unmarked
     * @param ui      the user interface to display messages
     * @param storage the storage system for saving tasks
     * @throws CapyException if the task number is invalid or out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        tasks.unmarkTask(fullCommand);
        Task unmarkedTask = tasks.getTask(Integer.parseInt(fullCommand.split(" ")[1]) - 1);
        ui.showUnmarkTaskMessage(unmarkedTask);
        storage.saveTasks(tasks.getTasks());
    }
}
