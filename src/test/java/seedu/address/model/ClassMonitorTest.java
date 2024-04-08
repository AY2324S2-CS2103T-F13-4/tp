package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalClassMonitor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class ClassMonitorTest {

    private final ClassMonitor classMonitor = new ClassMonitor();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), classMonitor.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classMonitor.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClassMonitor_replacesData() {
        ClassMonitor newData = getTypicalClassMonitor();
        classMonitor.resetData(newData);
        assertEquals(newData, classMonitor);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        ClassMonitorStub newData = new ClassMonitorStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> classMonitor.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> classMonitor.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInClassMonitor_returnsFalse() {
        assertFalse(classMonitor.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInClassMonitor_returnsTrue() {
        classMonitor.addStudent(ALICE);
        assertTrue(classMonitor.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInClassMonitor_returnsTrue() {
        classMonitor.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(classMonitor.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> classMonitor.getStudentList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ClassMonitor.class.getCanonicalName() + "{students=" + classMonitor.getStudentList() + "}";
        assertEquals(expected, classMonitor.toString());
    }

    /**
     * A stub ReadOnlyClassMonitor whose students list can violate interface constraints.
     */
    private static class ClassMonitorStub implements ReadOnlyClassMonitor {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        ClassMonitorStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
