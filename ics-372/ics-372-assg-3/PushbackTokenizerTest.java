import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Tests each PushbackTokenizer method
 *
 * @author Kyle Heneman
 * @since 09/27/2015
 */
public class PushbackTokenizerTest{
    // One String to test them all...
    private final String DATA = "I am Vengeance.  I am the Night.  I am " +
                                "Batman.";

    /**
     * Tests PushbackTokenizer's method by ensuring the order in which
     * Strings are returned from the `toPrint` Stack
     *
     * @throws Exception if PushbackTokenizer contains no more
     *                   Strings, yet is still trying to be accessed
     */
    @org.junit.Test
    public void testNextToken() throws Exception{
        // Value placeholders
        String test, current = "";
        // StringTokenizer acting as control since we know it works
        StringTokenizer control = new StringTokenizer(DATA, " ", false);
        // PushbackTokenizer to test
        PushbackTokenizer tokenizer = new PushbackTokenizer(DATA);

        /*
         * Assert that the String given to StringTokenizer was valid under
         * our assumptions
         */
        assert control.hasMoreTokens();

        // While the control still has tokens
        while(control.hasMoreTokens()) {
            // Placeholder for the token to be validated
            test = tokenizer.nextToken();
            // Placeholder for the control token
            current = control.nextToken();

            // Ensure test token is not null
            assert test != null;
            // Ensure that it is not an empty String
            assert !test.isEmpty();
            // Ensure that it is the same value as the control token
            assert current.equals(test);
        }
    }

    /**
     * Tests PushbackTokenizer's `hasMoreTokens()` method by ensuring
     * that `true` is always returned when a PushbackTokenizer has more
     * tokens
     *
     * @throws Exception if the PushbackTokenizer doesn't contain anymore
     *                   tokens, yet tokens are still attempting to be
     *                   retrieved
     */
    @org.junit.Test
    public void testHasMoreTokens() throws Exception{
        // StringTokenizer for control
        StringTokenizer control = new StringTokenizer(DATA, " ", false);
        // PushbackTokenizer for testing
        PushbackTokenizer tokenizer = new PushbackTokenizer(DATA);

        // Ensure that there will be tokens to test
        assert control.hasMoreTokens();

        // While the control has more tokens
        while(control.hasMoreTokens()) {
            // Assert that the PushbackTokenizer does as well
            assert tokenizer.hasMoreTokens();

            // Pop the next tokens from both the control and the test
            control.nextToken();
            tokenizer.nextToken();
        }

        // Assert that both control and test are empty
        // after popping off all of the tokens from the control
        assert !control.hasMoreTokens();
        assert !tokenizer.hasMoreTokens();
    }

    /**
     * Tests PushbackTokenizer's `pushback()` method by mimicking its
     * behavior with local variables as the control and then testing the
     * control values against what's done with `pushback()`
     *
     * @throws Exception if empty element is accessed
     */
    @org.junit.Test
    public void testPushback() throws Exception{
        // Placeholders
        String test, current;
        // StringTokenizer as control since we know it works
        StringTokenizer control = new StringTokenizer(DATA, " ", false);
        // Stack of Strings for the local printing queue
        Stack<String> controlPrint = new Stack<String>();
        // Stack of Strings to be pushed back to the printing queue
        Stack<String> controlPushed = new Stack<String>();
        // Tokenizer to test against the control
        PushbackTokenizer tokenizer = new PushbackTokenizer(DATA);

        // Ensure the String to be tested contained valid tokens
        assert control.hasMoreTokens();

        // While the control has more tokens
        while(control.hasMoreTokens()) {
            // Push them into the control's pushed Stack
            controlPushed.push(control.nextToken());
        }
        // While the controls' pushed Stack has tokens
        while(!controlPushed.isEmpty()) {
            // Push them onto the control's print Stack
            controlPrint.push(controlPushed.pop());
        }

        // Ensure that the control's print Stack has tokens
        assert !controlPrint.isEmpty();
        // While the control's print Stack has tokens
        while(!controlPrint.isEmpty()) {
            // Ensure that the PushbackTokenizer has tokens
            assert tokenizer.hasMoreTokens();

            // Place the control's current token into placeholder
            current = controlPrint.pop();
            // Place the test's current token into placeholder
            test = tokenizer.nextToken();

            // Ensure that the two current tokens are equal
            assert current.equals(test);

            // If the random boolean is true
            if(RandomBoolean.getRandomBoolean()) {
                // Push the last printed token back onto
                // the control's print Stack
                controlPrint.push(current);
                // Push the last printed token back onto
                // the test's print Stack
                tokenizer.pushback();
            }
        }

        // Afterwards, ensure that both print Stacks are empty
        assert controlPrint.isEmpty();
        assert !tokenizer.hasMoreTokens();
    }
}