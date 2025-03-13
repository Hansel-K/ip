package capy.command;

import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;

/**
 * Represents a command to list all tasks in the task list
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks in the task list
     *
     * @param tasks   the task list containing all the tasks
     * @param ui      the user interface to display the tasks
     * @param storage the storage system (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
    }
}
