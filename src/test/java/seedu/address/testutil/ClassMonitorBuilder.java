package seedu.address.testutil;

import seedu.address.model.ClassMonitor;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ClassMonitor ab = new ClassMonitorBuilder().withStudent("John", "Doe").build();}
 */
public class ClassMonitorBuilder {

    private ClassMonitor classMonitor;

    public ClassMonitorBuilder() {
        classMonitor = new ClassMonitor();
    }

    public ClassMonitorBuilder(ClassMonitor classMonitor) {
        this.classMonitor = classMonitor;
    }

    /**
     * Adds a new {@code Student} to the {@code ClassMonitor} that we are building.
     */
    public ClassMonitorBuilder withStudent(Student student) {
        classMonitor.addStudent(student);
        return this;
    }

    public ClassMonitor build() {
        return classMonitor;
    }
}
