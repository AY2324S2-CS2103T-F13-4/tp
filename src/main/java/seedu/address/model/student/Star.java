package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's stars in the address book.
 */
public class Star implements Comparable<Star> {

    public static final String MESSAGE_CONSTRAINTS =
            "Total stars should be between and inclusive of 0 and 50000.";

    public static final Star NO_STAR = new Star(0);

    public final Integer numOfStars; // number of stars given to a student

    /**
     * Constructs a {@code Star}.
     *
     * @param numOfStars A valid number.
     */
    public Star(Integer numOfStars) {
        requireNonNull(numOfStars);
        checkArgument(isValidStar(numOfStars), MESSAGE_CONSTRAINTS);
        this.numOfStars = numOfStars;
    }

    /**
     * Returns true if a given string is a valid number. This should be within the range of 0 and 50000 inclusive.
     */
    public static boolean isValidStar(Integer numOfStars) {
        return (numOfStars >= 0 && numOfStars <= 50000);
    }

    @Override
    public String toString() {
        return this.numOfStars.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Star)) {
            return false;
        }

        Star otherPhone = (Star) other;
        return numOfStars.equals(otherPhone.numOfStars);
    }

    @Override
    public int compareTo(Star other) {
        int otherStars = other.numOfStars;
        return this.numOfStars.compareTo(otherStars);
    }

    @Override
    public int hashCode() {
        return numOfStars.hashCode();
    }
}
