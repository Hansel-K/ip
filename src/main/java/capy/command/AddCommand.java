package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

public class AddCommand extends Command {
    private final String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        if (fullCommand.startsWith("todo")) {
            tasks.addTodoTask(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            tasks.addDeadlineTask(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            tasks.addEventTask(fullCommand);
        } else {
            throw new CapyException("Invalid task type for AddCommand.");
        }
        Task addedTask = tasks.getTasks().get(tasks.size() - 1);
        ui.showAddTaskMessage(addedTask, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}