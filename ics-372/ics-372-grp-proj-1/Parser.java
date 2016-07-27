import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Attempts to parse input Strings into Dates
 *
 * @author Kyle Heneman
 * @since 10/16/2015
 */
abstract class Parser {
    public static Date parseDate(String dateString) {
        // Set Locale to en-US
        Locale locale = Locale.getDefault();
        // Set the format for input Dates
        SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy", locale);
        // Make Date parsing strict
        parser.setLenient(false);
        // Placeholder Date
        Date date = null;

        try {
            // Parse Date from input String
            date = parser.parse(dateString);
        }
        // Catch any exceptions from incorrect input
        catch(ParseException e) {
            System.out.println("Could not parse date.  Please enter in " +
                               "mm/dd/yyy format...");
        }

        return date;
    }
}
