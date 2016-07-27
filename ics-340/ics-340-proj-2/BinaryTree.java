/**
 * A Binary Tree data structure to hold,
 * search for, and describe BinaryNodes.
 *
 * Repurposed old assignment for this one.  As such only the methods that are used exist
 * and all others have been deleted.
 *
 * @author Kyle Heneman
 * @since 04/24/2015
 */

// Need these things
import java.util.*;


public class BinaryTree
{
  // Allocates memory for a BinaryNode object named `root`
  BinaryNode< String > root;

  // Constructs a new BinaryNode object with `root` equal to null
  public BinaryTree()
  {
    root = null;
  }

  // `main` method
  public static void main( String[] args ) {
    // Instantiate and populate a new BinaryTree object
    BinaryTree t = new BinaryTree();
    t.add( "george" );
    t.add( "jetson" );
    t.add( "barney" );
    t.add( "bambam" );
    t.add( "wilma" );
    t.add( "betty" );

    // View the DoT Structure
    t.toDot();

    // Blank line
    System.out.println();

    // Expected results from next test
    String[] expectedOutput = {"barney", "betty", "george", "jetson"};

    // Get contents of range of arguments
    ArrayList<String> r = t.search( "jetson", "barney" );

    // While `r` is not empty
    for( int i = 0; i < expectedOutput.length; ++i )
    {
      // Assert that each value is equal to its corresponding expected value
      org.junit.Assert.assertEquals( expectedOutput[ i ], r.size() >= i ? r.get( i ) : null );
    }


    // New set of expected results from next test
    expectedOutput = new String[]{"bambam", "barney", "betty", "george"};

    // Get contents of range of arguments
    r = t.search( "abba", "hal" );

    // While `r` is not empty
    for( int i = 0; i < expectedOutput.length; ++i )
    {
      // Assert that each value is equal to its corresponding expected value
      org.junit.Assert.assertEquals( expectedOutput[ i ], r.size() >= i ? r.get( i ) : null );
    }


    // Instantiate and populate a new BinaryTree object
    BinaryTree u = new BinaryTree();
    u.add( "flintstone" );
    u.add( "george" );
    u.add( "jetson" );
    u.add( "barney" );
    u.add( "bambam" );
    u.add( "wilma" );
    u.add( "betty" );
    u.add( "dino" );
    u.add( "gazoo" );

    // View the DoT structure of `u`
    u.toDot();

    // Set of expected values to be returned from next test
    expectedOutput = new String[]{"dino", "flintstone", "gazoo"};

    // Get contents of range of arguments
    r = u.search( "dino", "gazz" );

    // while `u` is not empty
    for( int i = 0; i < expectedOutput.length; ++i )
    {
      // Assert that each value is equal to its corresponding expected value
      org.junit.Assert.assertEquals( expectedOutput[ i ], r.size() >= i ? r.get( i ) : null );
    }

    // Print contets of results
    for( String s : r )
    {
      System.out.println( s );
    }
  }


  /**
   * Delegate for private `add()` method
   *
   * @param value  Data to store in the BinaryNode
   */
  public void add( String value )
  {
    // If `root` is empty
    if ( root == null )
    {
      // Give it a new BinaryNode containing `value`
      root = new BinaryNode< String >( value );
    }
    // Else
    else
    {
      // Call the private `add()` method with the
      // `value` to be added to the BinaryTree, and `root`
      add( value, root );
    }
  }


  /**
   * Creates a new BinaryNode and assigns `value` to the
   * new node's `data` attribute, then assigns the new BinaryNode
   * to either the `left` or `right` sub-tree, depending on if the
   * `value` of the new node is less than or equal to, or greater than
   * the `value` of the current node, respectively
   *
   * @param value  The new value to be added to the BinaryTree
   * @param cur    The BinaryNode currently being inspected
   */
  private void add( String value, BinaryNode< String > cur )
  {
    // If the new `value` is less than or equal to the `data`
    // stored in the current BinaryNode
    if ( value.compareTo( cur.getData() ) <= 0 )
    {
      // ...and its `left` child is empty
      if ( cur.getLeft() == null )
      {
        // Give the `left` child the new BinaryNode containing `value`
        cur.setLeft( new BinaryNode< String >( value ) );
      }
      // Otherwise
      else
      {
        // Repeat the steps above with the `left` sub-tree
        add( value, cur.getLeft() );
      }
    }
    // Else
    else
    {
      // If the `right` child is empty
      if ( cur.getRight() == null )
      {
        // Give the `right` child the new BinaryNode containing `value`
        cur.setRight( new BinaryNode< String >( value ) );
      }
      // Otherwise
      else
      {
        // Repeat the steps above with the `right` sub-tree
        add( value, cur.getRight() );
      }
    }
  }


  /**
   * Delegate method for finding the range between two String values
   *
   * @param from  Lower-bound of range being collected
   * @param to    Upper-bound of range being collected
   *
   * @return A collection containing data from every node with a value between `from` and `to`, inclusively
   */
  public ArrayList<String> search( String from, String to )
  {
    // New ArrayList<String> to store found values in
    ArrayList<String> list = new ArrayList<String>();

    // If a BinaryTree exists
    if( root != null )
    {
      // If the upper-bound is of lesser value than the lower-bound, switch their values
      if( from.compareTo( to ) > 0 )
      {
        String temp = to;
        to = from;
        from = temp;
      }

      // Give `list` the collection of values in range between `from` and `to`, inclusive
      list = search( from, to, root, list );

      // Return that collection
      return list;
    }
    // Otherwise a BinaryTree doesn't exist to search through
    else
    {
      System.out.println( "Tree is empty; Nothing to search" );
      return null;
    }
  }

  /**
   * Returns a collection of "all node values... which were found in the tree
   * between the 1st string argument and the 2nd string argument", inclusively
   *
   * Since we have to inspect every Node up until the Node that contains the upper-bounds value,
   * this is essentially a traversal of the whole BinaryTree, and as such we can use a modified In-Order
   * traversal (so the output is inherently going to be ordered from least to greatest) and test whether
   * the current value is within the bounds of the desired range before adding it to the ArrayList
   *
   * The Big O time complexity of this is O(n), since every Node up to the upper-bounds has to be inspected
   *
   * @param from  Lower-bound of range being collected
   * @param to    Upper-bound of range being collected
   * @param cur   Current BinaryNode for inspection
   * @param list  Collection to add values in range to
   *
   * @return ArrayList containing all values in range of lower- and upper-bounds, inclusively
   */
  public ArrayList<String> search( String from, String to, BinaryNode<String> cur, ArrayList<String> list )
  {
    // If the current BinaryNode being inspected exists
    if( cur != null )
    {
      // If it has a left-child
      if( cur.getLeft() != null )
      {
        // Recurse to its left-child
        search( from, to, cur.getLeft(), list );
      }

      // If the current Node is greater than or equal to the lower-bound and lower than the upper-boudn
      if( cur.getData().compareTo(from) >= 0 && cur.getData().compareTo(to) < 0 )
      {
        // Add the value of the current Node to the range collection
        list.add( cur.getData() );
      }
      // Or if it is equal to the upper-bound
      else if( cur.getData().compareTo( to ) == 0 )
      {
        // Add the value of the current Node to the range collection
        list.add( cur.getData() );

        // And return the collection
        return list;
      }

      // If the current Node has a right-child
      if( cur.getRight() != null )
      {
        // Recurse down its right child
        search( from, to, cur.getRight(), list );
      }
    }

    // Return the list once all Nodes have been inspected and all values
    // contained within the range bounds have been added
    return list;
  }


  /**
   * Builds a String representation of the BinaryTree in DOT format
   */
  public void toDot()
  {
    // Instantiate a new generic BinaryNode
    BinaryNode< ? > n;

    // Initial String to append to
    String dot = "graph BinaryTree {\n";

    // Instantiate a Stack of generic BinaryNodes
    Stack< BinaryNode< ? > > stack = new Stack< BinaryNode< ? > >();

    // Assign `root` to `n`
    n = root;

    // Push `n` onto `stack`
    stack.push( n );

    // Do
    do
    {
      // Append the `toDot()` value of the current BinaryNode to `dot`
      dot += n.toDot();

      // If the current BinaryNode has a "left" child
      if ( n.left != null )
      {
        // Push the "left" child onto `stack`
        stack.push( n.left );
      }

      // If the current BinaryNode has a "right" child
      if ( n.right != null )
      {
        // Push the "right" child onto `stack`
        stack.push( n.right );
      }

      // Set `n` to the next BinaryNode in `stack`
      n = stack.pop();
    }
    // While `stack` is not empty
    while ( !stack.empty() );

    // Print the DOT format representation of the BinaryTree to console
    System.out.print( dot + "\n}" );
  }
}
