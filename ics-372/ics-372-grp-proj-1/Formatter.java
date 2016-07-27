import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Formats Strings to different outputs
 *
 * @author Kyle Heneman
 * @since 10/12/2015
 */
abstract class Formatter {
    // Format for Currency
    private static final DecimalFormat formatCurrency =
        new DecimalFormat("$0.00");

    // Format for Dates
    private static final DateFormat formatDate =
        new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Formats a String for output as Currency
     *
     * @param amount String containing numbers for currency output
     *
     * @return String Currency formatted String
     */
    public static String formatCurrency(double amount) {
        return formatCurrency.format(amount);
    }

    /**
     * Formats a String for output as Date
     *
     * @param date String containing Date for formatting
     * @return String Date formatted String
     */
    public static String formatDate(Date date) {
        return formatDate.format(date);
    }

    /**
     * Formats a String for output as a phone number
     *
     * @param phoneNumber String containing numbers for phone number output
     *
     * @return String Phone number formatted String
     */
    public static String formatPhone(String phoneNumber) {
        return String.format("(%s) %s-%s",
                             phoneNumber.substring(0,3),
                             phoneNumber.substring(3,6),
                             phoneNumber.substring(6,10));
    }
}