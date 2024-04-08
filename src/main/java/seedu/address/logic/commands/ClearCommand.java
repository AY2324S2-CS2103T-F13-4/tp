package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ClassMonitor;
import seedu.address.model.Model;

/**
 * Clears ClassMonitor.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ClassMonitor has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClassMonitor(new ClassMonitor());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
