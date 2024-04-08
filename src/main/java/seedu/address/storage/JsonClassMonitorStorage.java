package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyClassMonitor;

/**
 * A class to access ClassMonitor data stored as a json file on the hard disk.
 */
public class JsonClassMonitorStorage implements ClassMonitorStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClassMonitorStorage.class);

    private Path filePath;

    public JsonClassMonitorStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getClassMonitorFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClassMonitor> readClassMonitor() throws DataLoadingException {
        return readClassMonitor(filePath);
    }

    /**
     * Similar to {@link #readClassMonitor()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyClassMonitor> readClassMonitor(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableClassMonitor> jsonClassMonitor = JsonUtil.readJsonFile(
                filePath, JsonSerializableClassMonitor.class);
        if (!jsonClassMonitor.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonClassMonitor.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveClassMonitor(ReadOnlyClassMonitor classMonitor) throws IOException {
        saveClassMonitor(classMonitor, filePath);
    }

    /**
     * Similar to {@link #saveClassMonitor(ReadOnlyClassMonitor)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveClassMonitor(ReadOnlyClassMonitor classMonitor, Path filePath) throws IOException {
        requireNonNull(classMonitor);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableClassMonitor(classMonitor), filePath);
    }

}
