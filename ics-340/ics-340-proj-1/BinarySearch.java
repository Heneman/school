// Javadocs giving a description, the author, and the date created of this Java Class
/**
 * Makes use of the Linear Search class for the ordered int[] given by John Bussjaeger
 *
 * @author Kyle Heneman
 * @since 01/24/2015
 */

// Class header
public class BinarySearch
{
  // Finds `target` int in `array` using binary search algorithm
  public static int find( int target, int[] array )
  {
    // `first` marks the beginning of the sub-Array
    int first = 0;

    // `last` marks the end of the sub-array
    int last = (array.length - 1);

    // `middle` marks the middle of the sub-array
    int middle = (first + last)/2;

    // While the value of the `first` index is less than
    // the value of the `last` index
    while( first <= last )
    {
      // Prints message to terminal giving current index and value
      System.out.println( "* ints[" + middle + "] contains: " + array[ middle ] );

      // If the int in the middle of the sub-Array is less than the target integer...
      if ( array[middle] < target )
      {
        // ...set `first` equal to the beginning of the second sub-Array
        first = middle + 1;
      }
      // Else if the in in the middle is equal to the target integer...
      else if ( array[middle] == target )
      {
        // ...return it
        return middle;
      }
      // Else
      else
      {
        // Set the `last` marker equal to the end of the first sub-Array
        last = middle - 1;
      }

      // Set `middle` equal to new position based on new values of `first` and `last
      middle = (first + last)/2;
    }

    // Default return value is -1 if the taget integer wasn't found
    return -1;
  }
}