package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ClassMonitor;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableClassMonitorTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableClassMonitorTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentClassMonitor.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentClassMonitor.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentClassMonitor.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableClassMonitor dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableClassMonitor.class).get();
        ClassMonitor classMonitorFromFile = dataFromFile.toModelType(); // represent the stars as String
        ClassMonitor typicalStudentsClassMonitor = TypicalStudents.getTypicalClassMonitor();
        assertEquals(classMonitorFromFile, typicalStudentsClassMonitor);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableClassMonitor dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableClassMonitor.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableClassMonitor dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableClassMonitor.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableClassMonitor.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
