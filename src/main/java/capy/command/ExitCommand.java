package capy.command;

import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;

/**
 * Terminates while loop to exit program
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true; // Indicates the program should terminate
    }
}