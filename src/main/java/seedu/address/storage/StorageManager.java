package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyClassMonitor;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ClassMonitor data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClassMonitorStorage classMonitorStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClassMonitorStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClassMonitorStorage classMonitorStorage, UserPrefsStorage userPrefsStorage) {
        this.classMonitorStorage = classMonitorStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ClassMonitor methods ==============================

    @Override
    public Path getClassMonitorFilePath() {
        return classMonitorStorage.getClassMonitorFilePath();
    }

    @Override
    public Optional<ReadOnlyClassMonitor> readClassMonitor() throws DataLoadingException {
        return readClassMonitor(classMonitorStorage.getClassMonitorFilePath());
    }

    @Override
    public Optional<ReadOnlyClassMonitor> readClassMonitor(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return classMonitorStorage.readClassMonitor(filePath);
    }

    @Override
    public void saveClassMonitor(ReadOnlyClassMonitor classMonitor) throws IOException {
        saveClassMonitor(classMonitor, classMonitorStorage.getClassMonitorFilePath());
    }

    @Override
    public void saveClassMonitor(ReadOnlyClassMonitor classMonitor, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        classMonitorStorage.saveClassMonitor(classMonitor, filePath);
    }

}
