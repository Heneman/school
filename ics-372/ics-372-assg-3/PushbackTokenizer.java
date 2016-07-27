import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Breaks a String into tokens delimited by a whitespace (" ")
 * and prints each token to the screen while pseudorandomly
 * pushing tokens back into the printing Stack to be printed
 * again
 *
 * @author Kyle Heneman
 * @since 09/25/2015
 */
public class PushbackTokenizer implements PushbackableTokenizer {
    // Two Stacks to hold tokens to be printed
    private Stack<String> toPrint, pushedBack;

    /**
     * Instantiates `toPrint` and `pushedBack`,
     * tokenizes the String parameter, and populates
     * the `toPrint` Stack into printing order
     * by bouncing the tokens through `pushedBack`
     *
     * @param data A String which contains groups of characters
     *             delimited by a whitespace (" ")
     */
    public PushbackTokenizer(String data){
        // Instantiate the token Stacks
        toPrint = new Stack<String>();
        pushedBack = new Stack<String>();

        // Tokenize the String parameter using " " as the delmiter
        StringTokenizer tokenizer = new StringTokenizer(data, " ", false);

        // While the tokenizer contains more tokens
        while(tokenizer.hasMoreTokens()) {
            /*
             * Push each token into `pushedBack` to reverse
             * the order of tokens
             */
            pushedBack.push(tokenizer.nextToken());
        }

        // While `pushedBack` still contains the reversed tokens
        while(!pushedBack.isEmpty()) {
            /*
             * Pop them into the `toPrint` Stack, reestablishing
             * original order
             */
            toPrint.push(pushedBack.pop());
        }
    }

    /**
     * Returns the next token from the `toPrint` Stack
     *
     * @return String at top of the `toPrint` Stack
     */
    public String nextToken() {
        // Use placeholder to use value in multiple places
        String temp = toPrint.pop();
        // Push the String into `pushedBack` so it can be pushed back
        pushedBack.push(temp);

        // Return the String to be printed
        return temp;
    }

    /**
     * Tests if the `toPrint` Stack contains more Strings
     *
     * @return true if `toPrint` is not empty, false otherwise
     */
    public boolean hasMoreTokens() {
        /*
         * Switch the value of `toPrint.isEmpty()` to get
         * boolean representing whether or not`toPrint`
         * is NOT empty
         */
        return !toPrint.isEmpty();
    }

    /**
     * Pops the last token printed off of `pushedBack`
     * and back onto the `toPrint` Stack
     */
    public void pushback() {
        // Push the top String from `pushedBack` to `toPrint`
        toPrint.push(pushedBack.pop());
    }
}
