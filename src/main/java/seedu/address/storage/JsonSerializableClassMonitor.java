package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ClassMonitor;
import seedu.address.model.ReadOnlyClassMonitor;
import seedu.address.model.student.Student;

/**
 * An Immutable ClassMonitor that is serializable to JSON format.
 */
@JsonRootName(value = "classmonitor ")
class JsonSerializableClassMonitor {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableClassMonitor} with the given students.
     */
    @JsonCreator
    public JsonSerializableClassMonitor(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyClassMonitor} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableClassMonitor}.
     */
    public JsonSerializableClassMonitor(ReadOnlyClassMonitor source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ClassMonitor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ClassMonitor toModelType() throws IllegalValueException {
        ClassMonitor classMonitor = new ClassMonitor();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (classMonitor.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            classMonitor.addStudent(student);
        }
        return classMonitor;
    }

}
