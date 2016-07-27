// Imports java.util.Scanner
import java.util.Scanner;

// Javadocs giving a description, the author, and the date created of this Java Class
/**
 * Makes use of the Linear Search class for the ordered int[] given by John Bussjaeger
 *
 * @author Kyle Heneman
 * @since 01/24/2015
 */

// Class header
public class UsesLinearSearch
{
  // Instantiates an ordered Array of ints, copy and pasted from http://www.domfu.com
  private static final int[] ints = { 0, 1, 2, 2, 2, 3, 3, 4, 4, 5, 6, 8, 8, 9, 9, 9, 10, 11, 12, 13,
                                        15, 16, 20, 21, 22, 22, 23, 24, 24, 26, 27, 27, 28, 30, 30, 30, 35, 39, 39, 41,
                                        42, 42, 42, 44, 44, 44, 45, 45, 50, 51, 52, 54, 54, 56, 57, 57, 58, 59, 60, 62,
                                        63, 63, 63, 63, 64, 64, 65, 66, 66, 66, 67, 67, 68, 68, 69, 69, 71, 72, 74, 74,
                                        75, 76, 77, 78, 81, 81, 81, 82, 82, 82, 83, 85, 86, 86, 87, 87, 88, 93, 96, 98 };

  // Instantiates a Scanner object to collect input from user
  private static final Scanner reader = new Scanner( System.in );

  // The main function
  public static void main( String[] args )
  {
    // Print a message to the terminal prompting for integer input
    System.out.print( "Enter a number to search the int[] `ints` for: " );

    // Ensures next token from `reader` is an int
    if ( reader.hasNextInt() )
    {
      // Assigns input integer to variable
      int query = reader.nextInt();

      // Calls `find()` in LinearSearch with user input and the int[] from http://www.domfu.com
      int index = LinearSearch.find( query, ints );

      // If the returned index exists
      if( index != -1 )
      {
        // Print out dashes
        System.out.println( "----------------------------" );

        // Print out the input and the index the input is located at
        System.out.println( "\n" + query + " was found at index: " + index + "\n\n" );
      }
      // Else
      else
      {
        // Print out dashes
        System.out.println( "----------------------------" );

        // Print message letting user know their query wasn't found
        System.out.println( "\n" + query + " was not found in `ints`\n\n" );
      }
    }
  }
}
