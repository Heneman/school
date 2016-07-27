import java.util.Date;

/**
 * Ensures different inputs conform to specs
 *
 * @author Kyle Heneman
 * @since 10/12/2015
 */
abstract class Validator {

    /**
     * Validates that a String contains only numbers and is 10 digits in length
     *
     * @param phoneNumber String containing possible phone number to test
     *
     * @return boolean True if String is a valid phone number, false otherwise
     */
    public static boolean validPhone(String phoneNumber) {
        boolean isValid = true;

        // If the phone number is not 10 digits or contains anything but numbers
        if(phoneNumber.length() != 10 || !phoneNumber.matches("[0-9]+")) {
            System.out.println("Phone number must be exactly 10 digits");
            System.out.println("Please re-enter a phone number:");

            // Mark as invalid
            isValid = false;
        }

        return isValid;
    }

    /**
     * Validates that a String contains only numbers and is 16 digits in length
     *
     * @param cardNumber String containing possible Card number to test
     *
     * @return boolean True if String can be used as a Card number, false
     *                 otherwise
     */
    public static boolean validCardNumber(String cardNumber) {
        boolean isValid = true;

        // If the Card number is not 16 digits or contains anything but numbers
        if(cardNumber.length() != 16 || !cardNumber.matches("[0-9]+")) {
            System.out.println("Credit Card number must be exactly 16 digits");
            System.out.println("Please re-enter a credit card number:");

            // Mark as invalid
            isValid = false;
        }

        return isValid;
    }

    /**
     * Validates that two Dates are compatible for Show times
     *
     * @param start Date that a Show will start
     * @param end Date that a Show will end
     *
     * @return boolean True if Dates are compatible, false otherwise
     */
    public static boolean validShowDates(Date start, Date end) {
        Date now = new Date();
        boolean isValid = true;

        // If Dates aren't physically possible as beginning and ending dates
        if(start.compareTo(end) > 0 || end.compareTo(start) < 0 ||
           start.compareTo(now) < 0 || end.compareTo(now) < 0) {
            System.out.println("Invalid Show times.  Beginning date must be " +
                               "in the future and, at an earlier than, or " +
                               "equal to, time than the ending date");
            // Mark as invalid
            isValid = false;
        }

        return isValid;
    }
}
