// Javadocs giving a description, the author, and the date created of this Java Class
/**
 * Makes use of the Linear Search class for the ordered int[] given by John Bussjaeger
 *
 * @author Kyle Heneman
 * @since 01/24/2015
 */

// Class header
public class LinearSearch
{
  // Finds `target` int in `array` using a linear algorithm
  public static int find(int target, int[] array)
  {
    // Method returns -1 when `target` is not found
    // so this instantiates the return variable as default
    int index = -1;

    // "Iterates" through `array` from 0 to `array.length`
    // incrementing `i` as it goes
    for ( int i = 0; i < array.length; i++ )
    {
      // Prints message to terminal giving current index and value
      System.out.println( "* ints[" + i + "] contains: " + array[ i ] );

      // If the int at `array[ i ]` is equal to the int from user input...
      if ( array[ i ] == target )
      {
        // ...set the return variabe to index `target` was located at
        index = i;

        // Break out of the for-loop
        break;
      }
    }

    // Return the index `target` was located at or return -1 indicating
    // that `target` was not found
    return index;
  }
}