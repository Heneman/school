// Importing things I think this will need
import junit.framework.Assert;
import junit.framework.TestCase;

// Class header
public class BinarySearchTest extends TestCase
{
  // Tests `find()` in BinarySearch.  I think.  I hope.
  public void testFind() throws Exception
  {

    // Ordered array
    int[] ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    // Asserting that `find()` will return the correct index
    // given an int with a know index in the Array
    Assert.assertTrue( BinarySearch.find( 9, ints ) == 9 );
  }
}