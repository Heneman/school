/**
 * @author Kyle Heneman
 * @since 11/8/14
 *
 * Recursively converts base-10 ints to base-2 binary strings
 */
class BinaryConverter
{
  static String convert( int num, StringBuilder sb )
  {
    if( num > 0)
    {
      // Keep calling until `num` can no longer
      // be reduced by a factor of 2
      convert( num/2, sb );

      // Build binary string with results
      sb.append( num % 2 );
    }

    return sb.toString();
  }
}
