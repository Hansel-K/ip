package capy.parser;

import capy.CapyException;
import capy.command.*;
import capy.ui.Ui;

/**
 * Parses user input and identifies the command entered
 */
public class Parser {

    /**
     * Parses the user input string and returns the appropriate command object
     *
     * @param fullCommand The full input string entered by the user.
     * @return A Command object corresponding to the user input.
     * @throws CapyException If the command is unrecognized or invalid.
     */
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
        } else if (fullCommand.startsWith("find")) {
            return new FindCommand(fullCommand);
        } else {
            throw new CapyException(Ui.INVALID_COMMAND);
        }
    }
}
