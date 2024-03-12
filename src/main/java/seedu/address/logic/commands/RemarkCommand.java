package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a remark to a person.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
//            + "Parameters: "
//            + PREFIX_NAME + "NAME "
//            + PREFIX_PHONE + "PHONE "
//            + PREFIX_EMAIL + "EMAIL "
//            + PREFIX_ADDRESS + "ADDRESS "
//            + "[" + PREFIX_TAG + "TAG]...\n"
//            + "Example: " + COMMAND_WORD + " "
//            + PREFIX_NAME + "John Doe "
//            + PREFIX_PHONE + "98765432 "
//            + PREFIX_EMAIL + "johnd@example.com "
//            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
//            + PREFIX_TAG + "friends "
//            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Index index;
    private final Remark toAdd;

    public RemarkCommand(Index index, Remark remark) {
        requireNonNull(remark);
        this.index = index;
        this.toAdd = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        if (index.getZeroBased() >= model.getFilteredPersonList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        model.getFilteredPersonList().get(index.getZeroBased());

        Person personToEdit = model.getFilteredPersonList().get(index.getZeroBased());

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), toAdd, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult("remarked");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        RemarkCommand otherAddCommand = (RemarkCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .add("index", index)
                .toString();
    }
}
