package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

public class UnmarkCommand extends Command {
    private final String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        tasks.unmarkTask(fullCommand);
        Task unmarkedTask = tasks.getTask(Integer.parseInt(fullCommand.split(" ")[1]) - 1);
        ui.showUnmarkTaskMessage(unmarkedTask);
        storage.saveTasks(tasks.getTasks());
    }
}
