package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's bolts in the address book.
 */
public class Bolt implements Comparable<Bolt> {

    public static final String MESSAGE_CONSTRAINTS =
            "Total bolts should be between and inclusive of 0 and 50000.";

    public static final Bolt NO_BOLT = new Bolt(0);

    public final Integer numOfBolts; // number of bolts given to a student

    /**
     * Constructs a {@code Bolt}.
     *
     * @param numOfBolts A valid number.
     */
    public Bolt(Integer numOfBolts) {
        requireNonNull(numOfBolts);
        checkArgument(isValidBolt(numOfBolts), MESSAGE_CONSTRAINTS);
        this.numOfBolts = numOfBolts;
    }

    /**
     * Returns true if a given string is a valid number. This should be within the range of 0 and 50000 inclusive.
     */
    public static boolean isValidBolt(Integer numOfBolts) {
        return (numOfBolts >= 0 && numOfBolts <= 50000);
    }

    @Override
    public String toString() {
        return this.numOfBolts.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Bolt)) {
            return false;
        }

        Bolt otherPhone = (Bolt) other;
        return numOfBolts.equals(otherPhone.numOfBolts);
    }

    @Override
    public int compareTo(Bolt other) {
        int otherStars = other.numOfBolts;
        return this.numOfBolts.compareTo(otherStars);
    }

    @Override
    public int hashCode() {
        return numOfBolts.hashCode();
    }
}
