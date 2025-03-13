package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

/**
 * Represents a command to mark a task as completed in the task list
 */
public class MarkCommand extends Command {
    private final String fullCommand;

    /**
     * Constructs a MarkCommand with the given user input
     *
     * @param fullCommand the user's input, which includes the "mark" command and the task number
     */
    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the mark command by marking the specified task as completed,
     * displaying a message, and saving the updated task list
     *
     * @param tasks   the task list containing the task to be marked
     * @param ui      the user interface to display messages
     * @param storage the storage system for saving tasks
     * @throws CapyException if the task number is invalid or out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        tasks.markTask(fullCommand);
        Task markedTask = tasks.getTask(Integer.parseInt(fullCommand.split(" ")[1]) - 1);
        ui.showMarkTaskMessage(markedTask);
        storage.saveTasks(tasks.getTasks());
    }
}
