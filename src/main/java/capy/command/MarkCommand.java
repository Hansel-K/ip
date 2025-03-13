package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

public class MarkCommand extends Command {
    private final String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        tasks.markTask(fullCommand);
        Task markedTask = tasks.getTask(Integer.parseInt(fullCommand.split(" ")[1]) - 1);
        ui.showMarkTaskMessage(markedTask);
        storage.saveTasks(tasks.getTasks());
    }
}
