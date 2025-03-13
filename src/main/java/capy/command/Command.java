package capy.command;

import capy.CapyException;
import capy.storage.Storage;
import capy.tasklist.TaskList;
import capy.ui.Ui;

/**
 * Executes certain functionalities based on user commands
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException;

    public boolean isExit() {
        return false; // Default behavior: command does not exit the application
    }
}