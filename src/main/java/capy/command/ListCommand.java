package capy.command;

import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
    }
}
