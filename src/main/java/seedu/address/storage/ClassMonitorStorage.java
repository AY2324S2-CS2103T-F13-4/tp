package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyClassMonitor;

/**
 * Represents a storage for {@link seedu.address.model.ClassMonitor}.
 */
public interface ClassMonitorStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClassMonitorFilePath();

    /**
     * Returns ClassMonitor data as a {@link ReadOnlyClassMonitor}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClassMonitor> readClassMonitor() throws DataLoadingException;

    /**
     * @see #getClassMonitorFilePath()
     */
    Optional<ReadOnlyClassMonitor> readClassMonitor(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyClassMonitor} to the storage.
     * @param classMonitor cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClassMonitor(ReadOnlyClassMonitor classMonitor) throws IOException;

    /**
     * @see #saveClassMonitor(ReadOnlyClassMonitor)
     */
    void saveClassMonitor(ReadOnlyClassMonitor classMonitor, Path filePath) throws IOException;

}
