package net.coderodde.research.missionaries;

/**
 * This class provides some utility methods shared by the project.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.61
 */
class Utilities {

        /**
         * Checks that {@code integer} is no less than {@code minimum}, and if it
         * is, throws an exception with message {@code errorMessage}.
         * 
         * @param integer      the integer to check.
         * @param minimum      the minimum allowed value of {@code integer}.
         * @param errorMessage the error message.
         * @throws IllegalArgumentException if {@code integer < minimum}.
         */
        static void checkIntNotLess(int integer, 
                                    int minimum, 
                                    String errorMessage) {
            if (integer < minimum) {
                throw new IllegalArgumentException(errorMessage);
            }
        }

        /**
         * Checks that {@code integer} is not negative.
         * 
         * @param integer      the integer to check.
         * @param errorMessage the error message for the exception upon failure.
         */
        static void checkNotNegative(int integer, String errorMessage) {
            checkIntNotLess(integer, 0, errorMessage);
        }
}