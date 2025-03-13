package capy.command;

import capy.task.Task;
import capy.tasklist.TaskList;
import capy.ui.Ui;
import capy.storage.Storage;
import capy.CapyException;

/**
 * Finds and lists tasks that contain a specified keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String fullCommand) throws CapyException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CapyException(Ui.MISSING_DESCRIPTION); // Ensure keyword is provided
        }
        this.keyword = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CapyException {
        ui.showLine();
        System.out.println("Here are the matching tasks in your list:");

        int matchCounter = 1; // Counter for matching tasks
        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i); // Use TaskList's getTask method
            if (task.getDescription().contains(keyword)) {
                System.out.println(matchCounter + ". " + task); // Number matches correctly
                matchCounter++;
                found = true;
            }
        }

        if (!found) {
            System.out.println("No tasks found matching the keyword: " + keyword);
        }
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false; // This command does not terminate the application
    }
}
