package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.student.predicates.BoltWithinBoundsPredicate;
import seedu.address.model.student.predicates.MajorContainsSubStringPredicate;
import seedu.address.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.student.predicates.StarWithinBoundsPredicate;
import seedu.address.model.student.predicates.TagContainsSubStringPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // find
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // find name
        assertParseFailure(parser, "name   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("name")));

        // find major
        assertParseFailure(parser, "major   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("major")));

        // find star
        assertParseFailure(parser, "star    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("star")));

        // find bolt
        assertParseFailure(parser, "bolt   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("bolt")));

        // find tag
        assertParseFailure(parser, "tag   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("tag")));
    }

    @Test
    public void parse_invalidField_throwsParseException() {
        assertParseFailure(parser, "school",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseFindStar_invalidArgs_throwsParseException() {
        // invalid operator
        assertParseFailure(parser, "star a 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("star")));

        // invalid operand
        assertParseFailure(parser, "star < a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("star")));
        assertParseFailure(parser, "star = -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("star")));

        // missing operator
        assertParseFailure(parser, "star 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("star")));

        // missing operand
        assertParseFailure(parser, "star a ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("star")));
    }

    @Test
    public void parseFindBolt_invalidArgs_throwsParseException() {
        // invalid operator
        assertParseFailure(parser, "bolt a 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("bolt")));

        //invalid operand
        assertParseFailure(parser, "bolt < a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("bolt")));
        assertParseFailure(parser, "bolt = -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("bolt")));

        // missing operator
        assertParseFailure(parser, "bolt 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("bolt")));

        // missing operand
        assertParseFailure(parser, "bolt >",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.getSpecificMessageUsage("bolt")));
    }

    @Test
    public void parseFindName_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for find name
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "name Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords for find name
        assertParseSuccess(parser, "name  \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parseFindMajor_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for find major
        FindCommand expectedFindCommand =
                new FindCommand(new MajorContainsSubStringPredicate("Computer Science"));
        assertParseSuccess(parser, "major Computer Science", expectedFindCommand);

        // multiple whitespaces before keyword for find major
        assertParseSuccess(parser, "major  \n Computer Science", expectedFindCommand);
    }

    @Test
    public void parseFindStar_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for find star
        FindCommand expectedFindCommand =
                new FindCommand(new StarWithinBoundsPredicate("<", 1));
        assertParseSuccess(parser, "star < 1", expectedFindCommand);

        // multiple whitespaces between operator and operand for find star
        assertParseSuccess(parser, "star  <  \n  1", expectedFindCommand);
    }

    @Test
    public void parseFindBolt_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for find bolt
        FindCommand expectedFindCommand =
                new FindCommand(new BoltWithinBoundsPredicate("<", 1));
        assertParseSuccess(parser, "bolt < 1", expectedFindCommand);

        // multiple whitespaces between operator and operand for find bolt
        assertParseSuccess(parser, "bolt  <  \n  1", expectedFindCommand);
    }

    @Test
    public void parseFindTag_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for find tag
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsSubStringPredicate("friends"));
        assertParseSuccess(parser, "tag friends", expectedFindCommand);

        // multiple whitespaces before keyword for find tag
        assertParseSuccess(parser, "tag  \n friends", expectedFindCommand);
    }

    @Test
    public void isInvalidOperator_validOperator_false() {
        assertFalse(parser.isInvalidOperator("<"));
    }

    @Test
    public void isInvalidOperator_invalidOperator_true() {
        assertTrue(parser.isInvalidOperator("<>"));
    }

}
