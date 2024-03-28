package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindStarsLessThanCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StarsLessThanPredicate;

/**
 * Parser for the FindStarsLessThanCommand.
 */
public class FindStarsLessThanCommandParser implements Parser<FindStarsLessThanCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindStarsLessThanCommand
     * and returns an FindStarsLessThan object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStarsLessThanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            int intValue = Integer.parseInt(args.trim());
            if (intValue < 1) {
                throw new ParseException(FindStarsLessThanCommand.MESSAGE_VALID_UPPER_BOUND);
            }
            StarsLessThanPredicate predicate = new StarsLessThanPredicate(intValue);
            return new FindStarsLessThanCommand(predicate);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindStarsLessThanCommand.MESSAGE_USAGE), e);
        }
    }

}
