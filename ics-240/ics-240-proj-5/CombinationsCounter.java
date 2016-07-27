/**
 * @author Kyle Heneman
 * @since 11/8/14
 *
 * Counts combinations of traversing a staircase
 * with 1- or 2-step increments
 *
 * RECURRENCE RELATION:  n = (n - 1) + (n - 2)
 */

public class CombinationsCounter
{
  public static int count( int n )
  {
    if( n == 1 || n == 2 )
    {
      return n;
    }

    // Keep calling until n is either 1 or 2
    return count( n - 1 ) + count( n - 2 );
  }
}
