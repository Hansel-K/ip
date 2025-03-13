package capy.command;

import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;

/**
 * Represents a command to exit the application
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message
     *
     * @param tasks   the current task list (not used in this command)
     * @param ui      the user interface to display the goodbye message
     * @param storage the storage system (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether the command is an exit command
     *
     * @return true, so that the command terminates the application
     */
    @Override
    public boolean isExit() {
        return true; // Indicates the program should terminate
    }
}