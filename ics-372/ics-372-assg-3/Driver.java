import java.util.ArrayList;
import java.util.List;

/**
 * Calls each method of PushbackTokenizers for
 * a collection of Strings
 *
 * @author Kyle Heneman
 * @since 09/27/2015
 */
public class Driver{

    /**
     * Builds a List of Strings, and for each String creates a new
     * PushbackTokenizer and calls each method, with the `pushback()`
     * method being called dependent on a randomly generated boolean
     *
     * @param args System arguments
     */
    public static void main(String[] args) {
        // A List of Strings to hold the Star Wars quotes to be tested
        List<String> strings = new ArrayList<String>();

        /*
         *     0000000000  000    00000
         *    00     00   00 00   00  00
         *     0000  00  00   00  00000
         *        00 00 000000000 00   00
         * 00000000  00 00     00 00    000000
         *
         * 00  00  00   000    00000    000000
         * 00  00  00  00 00   00  00  00
         * 00  00  00 00   00  00000    0000
         *  888  888 000000000 00   00     00
         *   88  88  88     88 88    000000
         *
         */
        strings.add("It's an older code, sir, but it checks out");
        strings.add("Don't underestimate the Force");
        strings.add("Laugh it up, fuzz ball");
        strings.add("Red Five, standing by");
        strings.add("Several transmissions were beamed to this ship by " +
                    "Rebel spies");
        strings.add("In my experience, there is no such thing as luck");


        // For each Star Wars quote above
        for(String string : strings){
            // Make a new PushbackTokenizer for it
            PushbackTokenizer tokenizer = new PushbackTokenizer(string);

            // While there are still tokens from the quote
            while(tokenizer.hasMoreTokens()){
                // Print the next token
                System.out.println(tokenizer.nextToken());

                // If a true is returned
                if(RandomBoolean.getRandomBoolean()){
                    // Push last token back onto the
                    // `toPrint` Stack
                    tokenizer.pushback();
                }
            }
        }
    }
}
