package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

public class DeleteCommand extends Command {
    private final String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        Task removedTask = tasks.deleteTask(fullCommand);
        ui.showDeleteTaskMessage(removedTask, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
