package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalClassMonitor;

import org.junit.jupiter.api.Test;

import seedu.address.model.ClassMonitor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyClassMonitor_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyClassMonitor_success() {
        Model model = new ModelManager(getTypicalClassMonitor(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalClassMonitor(), new UserPrefs());
        expectedModel.setClassMonitor(new ClassMonitor());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
