package capy.parser;

import capy.CapyException;
import capy.command.*;
import capy.ui.Ui;

/**
 * Parses user input and identifies the command entered
 */
public class Parser {
    public static Command parse(String fullCommand) throws CapyException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            return new MarkCommand(fullCommand);
        } else if (fullCommand.startsWith("unmark")) {
            return new UnmarkCommand(fullCommand);
        } else if (fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") || fullCommand.startsWith("event")) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else {
            throw new CapyException(Ui.INVALID_COMMAND);
        }
    }
}
