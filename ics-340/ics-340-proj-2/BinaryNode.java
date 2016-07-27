/**
 * A Node object (presumably) written by John Bussjaeger
 *
 * Copy/pasted from http://www.domfu.com, reformatted and
 * with accessors/mutators added by Kyle Heneman
 */

// BinaryNode contains a generic type KEY, which itself
// implements the Comparable interface, and which can be compared
// to other objects of type KEY.  Apparently there is a convention
// when using bounded type parameters to use extends instead of implements

public class BinaryNode< KEY extends Comparable< KEY > >
{
  // The information to be stored in the BinaryNode
  public KEY data;

  // The height value of each node
  public int height;

  // The "left" child of the BinaryNode
  public BinaryNode< KEY > left;

  // The "right" child of the BinaryNode
  public BinaryNode< KEY > right;

  // New BinaryNodes are constructed as a "leaf" containing
  // the information they need to store
  public BinaryNode( KEY theData )
  {
    data = theData;
    height = 0;
    left = null;
    right = null;
  }



  /**
   * Describes the BinaryNode with information contained in `data`,
   * as well as the `data` stored in the children BinaryNodes and
   * their "positions"
   *
   * @return String representation of the BinaryNode
   */
  public String toString()
  {
    // Returns `data`, and the `data` from both child BinaryNodes
    if ( left != null && right != null )
    {
      return data + ": " + left.data + " , " + right.data;
    }
    // Returns only `data` and the `data` of the `left` child,
    // plus a "_" to denote a null value in `right`
    else if ( left != null )
    {
      return data + ": " + left.data + " , _";
    }
    // Returns only `data` and the `data` of the `right` child,
    // plus a "_" to denote a null value in `left`
    else if ( right != null )
    {
      return data + ": _ , " + right.data;
    }

    // Returns `data` and two "_" to denote null values in both the
    // `left` and `right` child BinaryNodes
    return data + ": _ , _";
  }

  /**
   * Describes the BinaryNode in a DOT formatted String
   *
   * @return A String that describes the BinaryNode in DOT format
   */
  public String toDot()
  {
    // Replaces all whitespace in `data` with an underscore and
    // assigns value to `name`
    String name = ( data.toString() ).replaceAll( "\\W", "_" );

    // `dot` is an empty String
    String dot = "";

    // If the "left" child is not null, build a String describing
    // that the BinaryNode has an undirected edge with its "left" child
    if ( left != null )
    {
      dot += name + " -- " + ( left.data.toString() ).replaceAll( "\\W", "_" ) + ";\n";
    }

    // If the "right" child is not null, build a String describing that
    // the BinaryNode has an undirected edge with its "right" child
    if ( right != null )
    {
      dot += name + " -- " + ( right.data.toString() ).replaceAll( "\\W", "_" ) + ";\n";
    }

    // If the BinaryNode does not have any children,
    // describe it as a "leaf" node
    if ( right == null && left == null )
    {
      return name + ";\n";
    }

    // Return the String describing the BinaryNode in DOT format
    return dot;
  }


  /**
   * Retrieves `height` value
   *
   * @reutrn Value stored in `height`
   */
  public int getHeight()
  {
    return this.height;
  }


  /**
   * Sets new value of `height`
   *
   * @param h New value for `height`
   */
  public void setHeight( int h )
  {
    this.height = h;
  }


  /**
   * Retrieves `data`
   *
   * @return Information stored in `data`
   */
  public KEY getData()
  {
    return data;
  }


  /**
   * Sets `left` to the BinaryNode passed to it
   *
   * @param node BinaryNode to be assigned to `left`
   */
  public void setLeft( BinaryNode node )
  {
    this.left = node;
  }


  /**
   * Retrieves `left`
   *
   * @return BinaryNode assigned to `left`
   */
  public BinaryNode< KEY > getLeft()
  {
    return left;
  }


  /**
   * Sets `right` to the BinaryNode passed to it
   *
   * @param node BinaryNode to be assigned to `right`
   */
  public void setRight( BinaryNode node )
  {
    this.right = node;
  }


  /**
   * Retrieves `right`
   *
   * @return BinaryNode assigned to `right`
   */
  public BinaryNode< KEY > getRight()
  {
    return right;
  }

}