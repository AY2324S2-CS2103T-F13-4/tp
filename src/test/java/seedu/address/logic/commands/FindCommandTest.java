package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.GEORGE;
import static seedu.address.testutil.TypicalStudents.getTypicalClassMonitor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.predicates.BoltWithinBoundsPredicate;
import seedu.address.model.student.predicates.MajorContainsSubStringPredicate;
import seedu.address.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.student.predicates.StarWithinBoundsPredicate;
import seedu.address.model.student.predicates.TagContainsSubStringPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalClassMonitor(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalClassMonitor(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);

        // blank keyword for find name
        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(
                preparePredicateInput(" "));
        FindCommand firstFindCommand = new FindCommand(firstPredicate);
        expectedModel.updateFilteredStudentList(firstPredicate);
        assertCommandSuccess(firstFindCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());

        // blank keyword for find major
        MajorContainsSubStringPredicate secondPredicate = new MajorContainsSubStringPredicate(" ");
        FindCommand secondFindCommand = new FindCommand(secondPredicate);
        expectedModel.updateFilteredStudentList(secondPredicate);
        assertCommandSuccess(secondFindCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());

        // blank keyword for find tag
        TagContainsSubStringPredicate thirdPredicate = new TagContainsSubStringPredicate(" ");
        FindCommand thirdFindCommand = new FindCommand(thirdPredicate);
        expectedModel.updateFilteredStudentList(thirdPredicate);
        assertCommandSuccess(thirdFindCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        // multiple keywords for find name
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                preparePredicateInput("Kurz Elle Kunz"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_findMajorComputerScience_oneStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        MajorContainsSubStringPredicate predicate = new MajorContainsSubStringPredicate("Computer Science");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredStudentList());
    }

    @Test
    public void execute_findStarOrBolt_success() {
        // valid operator for find star
        String starExpectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
        StarWithinBoundsPredicate starPredicate = new StarWithinBoundsPredicate(">=", 5);
        FindCommand starCommand = new FindCommand(starPredicate);
        expectedModel.updateFilteredStudentList(starPredicate);
        assertCommandSuccess(starCommand, model, starExpectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE), model.getFilteredStudentList());

        // valid operator for find bolt
        String boltExpectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        BoltWithinBoundsPredicate boltPredicate = new BoltWithinBoundsPredicate("=", 5);
        FindCommand boltCommand = new FindCommand(boltPredicate);
        expectedModel.updateFilteredStudentList(boltPredicate);
        assertCommandSuccess(boltCommand, model, boltExpectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredStudentList());
    }

    @Test
    public void execute_findTagFriends_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        TagContainsSubStringPredicate predicate = new TagContainsSubStringPredicate("friends");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredStudentList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private List<String> preparePredicateInput(String userInput) {
        return Arrays.asList(userInput.split("\\s+"));
    }
}
