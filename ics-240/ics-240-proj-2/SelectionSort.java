/**
 * Sorts ArrayList items in descending alphabetical order
 * <p/>
 * Taken from reading
 *
 * @author Kyle Heneman <a href="http://www.kyleheneman.com"> www.kyleheneman.com </a> <a
 *         href="mailto:holla@kyleheneman.com"> holla@kyleheneman.com </a>
 */

public class SelectionSort
{
  public static < E > void sort( E[] bookArray, int n )
  {
    int i, j;
    int bigger;
    E temp;

    for ( i = ( n - 1 ); i > 0; i-- )
    {
      bigger = 0;

      for ( j = 1; j <= i; j++ )
      {
        if ( ( ( Book ) bookArray[ bigger ] ).compareTo( ( Book ) bookArray[ j ] ) > 0 )
        {
          bigger = j;
        }
      }

      temp = bookArray[ i ];
      bookArray[ i ] = bookArray[ bigger ];
      bookArray[ bigger ] = temp;
    }
  }
}