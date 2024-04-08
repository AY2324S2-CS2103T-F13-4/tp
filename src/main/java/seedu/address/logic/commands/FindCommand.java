package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Finds and lists all persons in address book who matches the given predicate.
 * Text matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds students based on specified criteria.\n"
            + "Parameters: FIELD CRITERIA \n"
            + "Example: " + COMMAND_WORD + " name Alex\n"
            + "Example: " + COMMAND_WORD + " star < 1\n"
            + "Available fields: name, major, star, bolt, tag\n"
            + "Note:\n"
            + "- For name field, multiple keywords can be used to perform a keyword search.\n"
            + "- For star/bolt field, CRITERIA consists of comparison operators (<, <=, >, >=, =)\n"
            + "followed by a whitespace and a single number.";

    private final Predicate<Student> predicate;

    public FindCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    public static String getSpecificMessageUsage(String field) {
        String specificMessageUsage = COMMAND_WORD + " " + field + ": ";
        switch (field) {
        case "name":
            specificMessageUsage += "Finds students with names that contain any of the keywords specified.\n"
                    + "Parameters: KEYWORD [MORE KEYWORDS]\n"
                    + "Example: " + COMMAND_WORD + " name Alex Bernice";
            break;
        case "major":
            specificMessageUsage += "Finds students with majors that contain the keyword specified.\n"
                    + "Parameters: KEYWORD\n"
                    + "Example: " + COMMAND_WORD + " major Computer Science";
            break;
        case "star":
            specificMessageUsage += "Finds students with stars that fall within the specified range.\n"
                    + "Parameters: OPERATOR NUMBER\n"
                    + "Example: " + COMMAND_WORD + " star < 1\n"
                    + "Note: Valid operators are <, <=, >, >=, =. Only non-negative integers.";
            break;
        case "bolt":
            specificMessageUsage += "Finds students with bolts that fall within the specified range.\n"
                    + "Parameters: OPERATOR NUMBER\n"
                    + "Example: " + COMMAND_WORD + " bolt > 1\n"
                    + "Note: Valid operators are <, <=, >, >=, =. Only non-negative integers.";
            break;
        case "tag":
            specificMessageUsage += "Finds students with tags that contain the keyword specified.\n"
                    + "Parameters: KEYWORD\n"
                    + "Example: " + COMMAND_WORD + " tag CS2103T";
            break;
        default:
        }
        return specificMessageUsage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
