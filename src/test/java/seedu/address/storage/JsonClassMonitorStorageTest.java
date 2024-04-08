package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalStudents.getTypicalClassMonitor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ClassMonitor;
import seedu.address.model.ReadOnlyClassMonitor;

public class JsonClassMonitorStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonClassMonitorStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readClassMonitor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readClassMonitor(null));
    }

    private java.util.Optional<ReadOnlyClassMonitor> readClassMonitor(String filePath) throws Exception {
        return new JsonClassMonitorStorage(Paths.get(filePath)).readClassMonitor(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readClassMonitor("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readClassMonitor("notJsonFormatClassMonitor.json"));
    }

    @Test
    public void readClassMonitor_invalidStudentClassMonitor_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readClassMonitor("invalidStudentClassMonitor.json"));
    }

    @Test
    public void readClassMonitor_invalidAndValidStudentClassMonitor_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readClassMonitor("invalidAndValidStudentClassMonitor.json"));
    }

    @Test
    public void readAndSaveClassMonitor_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClassMonitor.json");
        ClassMonitor original = getTypicalClassMonitor();
        JsonClassMonitorStorage jsonClassMonitorStorage = new JsonClassMonitorStorage(filePath);

        // Save in new file and read back
        jsonClassMonitorStorage.saveClassMonitor(original, filePath);
        ReadOnlyClassMonitor readBack = jsonClassMonitorStorage.readClassMonitor(filePath).get();
        assertEquals(original, new ClassMonitor(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonClassMonitorStorage.saveClassMonitor(original, filePath);
        readBack = jsonClassMonitorStorage.readClassMonitor(filePath).get();
        assertEquals(original, new ClassMonitor(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonClassMonitorStorage.saveClassMonitor(original); // file path not specified
        readBack = jsonClassMonitorStorage.readClassMonitor().get(); // file path not specified
        assertEquals(original, new ClassMonitor(readBack));

    }

    @Test
    public void saveClassMonitor_nullClassMonitor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClassMonitor(null, "SomeFile.json"));
    }

    /**
     * Saves {@code classMonitor} at the specified {@code filePath}.
     */
    private void saveClassMonitor(ReadOnlyClassMonitor classMonitor, String filePath) {
        try {
            new JsonClassMonitorStorage(Paths.get(filePath))
                    .saveClassMonitor(classMonitor, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClassMonitor_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClassMonitor(new ClassMonitor(), null));
    }
}
