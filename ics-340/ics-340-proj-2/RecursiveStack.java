import java.util.Stack;

/**
 * Simple exercises using Stacks and recursion
 *
 ****
 *
 * Answers to questions:
 *
 * 1.) A Stack utilizes the FIFO principle of loading a number of things into a queue and working on the last
 *     one first; it's almost identical to the recursion principle of breaking one problem or task into smaller ones
 *     that are similar in behavior until a base-case is reached and then using the answers from those smaller
 *     problems to answer the initial larger problem.
 *
 *     Since we only care about working with the base-case first when using with recursion, it makes sense to store
 *     our progress with the larger problems for later, and by using the FIFO principle we are able to initially work
 *     with the base-case, and then the next bigger problem that uses input from the base-case's output, then to the
 *     next problem that uses the previous bigger problems output as input, and so on and so forth.
 *
 *     The computer even uses a stack when performing recursive operations.  If that doesn't convince one of the
 *     similarities between the two, then I don't know what would.
 *
 *
 * 2.) Let's use the Factorial function as an example:
 *
 *        recursive_factorial( x )
 *        {
 *          if( x equals 0 )
 *          {
 *            Then use the number 1 as output
 *          }
 *          else( x is > 0 )
 *          {
 *            Then use the output from calling this function again with (x - 1) and multiply it by x
 *            i.e. return factorial( x - 1 ) * x
 *          }
 *        }
 *
 *
 *        stack_factorial( x )
 *        {
 *          if( x equals 0 )
 *          {
 *            Then use the number 1 as output
 *          }
 *          else( x is > 0 )
 *          {
 *            While( x is > 0 )
 *            {
 *              Put x into the Stack
 *
 *              Decrement x by 1
 *            }
 *
 *            While( the Stack is not empty )
 *            {
 *              The answer starts at 1 and multiples
 *              itself with the next number popped off
 *              of the Stack
 *
 *              i.e. answer *= Stack.pop()
 *            }
 *
 *            Return answer
 *          }
 *        }
 *
 *
 *    The recursive argument acts as the incrementing or decrementing iterator variable, while using a Stack
 *    requires the iterator to be manually incremented or decremented and used as counter in a loop.
 *
 *    Both implementations use the base-case of starting with the value 1 once the iterator/recursive argument
 *    reach 0.  In MY implementation they both use a StringBuilder to build output so the return values are very
 *    similar.  One big difference is that using the Stack uses the accumulator to build an answer and returns once
 *    it's calculated, while recursion returns what's returned from the method call of itself with an incremented or
 *    decremented value, which returns what's returned from the method call of itself with an incremented or
 *    decremented value, etc etc.
 *
 *
 * 3.) An accumulator variable is the sum of the results of multiple operations, compared to an iterator which is the
 *     count of how many time the operation was performed.
 *
 *
 ********
 *
 * @author Kyle Heneman
 * @since 04/16/2015
 */
public class Recursion_Is_Stack
{
  /*
   * Global delimiter value to reset and reuse throughout exercises.
   * Exists mostly for recursive approach to calculating range,
   * but used in iterative approach because it was there and I'm lazy
   */
  private static String delim = "";


  /**
   * Iteratively produces a range of integers using a while-loop and
   * pushes each into a Stack object to imitate recursion
   *
   * @param from  Integer for inclusive start of range
   * @param to  Integer for incluseive end of range
   *
   * @return Comma-delimited String representing the range from `from` value to `to` value
   */
  public static String count_iterative( int from, int to )
  {
    if( from >= to )
    {
      throw new IllegalArgumentException( "Starting integer cannot be greater than the ending integer" );
    }
    else
    {
      // Stack for holding values in range of `from` until `to`
      Stack st = new Stack();

      // Using StringBuilders simplifies building output Strings
      StringBuilder sb = new StringBuilder();

      // Resets global delimiter
      resetDelimiter();

      // Until the end value of the range is reached
      while( from <= to )
      {
        // Print out current value
        System.out.println( "pre: " + from + "\n" );

        // Push current value into the Stack
        st.push( String.valueOf( from ) );

        // Append this entry into `sb` and increment current value
        sb.append( delim ).append( from++ );

        // Set value of `delim` to a comma for building Strings listing multiple values
        delim = ", ";
      }

      // Until the Stack is empty
      while( !st.isEmpty() )
      {
        // Pop off the next value in Stack and print it to console
        System.out.println( "post: " + st.pop() + "\n" );
      }

      // Return the built String representation of the range
      return sb.toString();
    }
  }


  /**
   * Public delegate method which instantiates a StringBuilder
   * and resets `delim` to start recursive calculation of range
   * beginning at `from` and going until `to`
   *
   * @param from  Integer used as inclusive start of range
   * @param to  Integer used as inclusive end of ranges
   *
   * @return Comma-delimited String representing the range from `from` value to `to` value
   */
  public static String count_recursive( int from, int to )
  {
    if( from > to )
    {
      throw new IllegalArgumentException( "Starting integer cannot be greather than the ending integer" );
    }
    else
    {
      // Using StringBuilder again makes building output easier
      StringBuilder sb = new StringBuilder();

      // Resets `delim` for use with recursive range calculation
      resetDelimiter();

      // Returns String built from private `countRecursive()`
      return count_recursive( from, to, sb );
    }
  }


  /**
   * Prints pre- and post-values of `from` while recursively building
   * values in inclusive range from `from` until `to` for output
   *
   * @param from  Beginning of range
   * @param to    End of range
   * @param sb    StringBuilder for to build output Strings
   *
   * @return  Comma-delimited String representing the range from `from` value to `to` value
   */
  private static String count_recursive( int from, int to, StringBuilder sb )
  {
    // Print pre-value to console for tracking changes of value
    System.out.println( "pre: " + from + "\n" );

    // Traverse through beginning of range until the end of the range
    if( from <= to )
    {
      // Record delimter and current value
      sb.append( delim ).append( from );

      // Ensure `delim` is ready for use listing multiple values
      delim = ", ";

      // Get the next value in the range
      count_recursive( (from + 1), to, sb );

      // Print post-value to console for tracking changes
      System.out.println( "post: " + from + "\n" );
    }

    // Return the built output String
    return sb.toString();
  }


  /**
   * Resets `delim` to blank String for leading value in list
   */
  private static void resetDelimiter()
  {
    delim = "";
  }


  /**
   * Tests methods
   *
   * @param args  Parameters from CLI when launching application
   */
  public static void main( String[] args )
  {
    // Test the iterative approach
    System.out.println( count_iterative( -3, 5 ) + "\n");

    // Test the recursive approach
    System.out.println( count_recursive( -3, 5 ) );
  }
}
