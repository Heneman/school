/**
 * Binary Tree Node for storing Books
 *
 * NO MUTATORS BECAUSE UPDATING WAS NOT PART OF THE REQUIREMENTS
 *
 * @author Kyle Heneman
 * @since 11/19/2014
 */
public class BookNode
{
  private Book data;
  private BookNode left;
  private BookNode right;


  public BookNode( Book book, BookNode lNode, BookNode rNode )
  {
    data = book;
    left = lNode;
    right = rNode;
  }


  /**
   * Gets `data` from BookNode
   *
   * @return book Book referenced in `data`
   */

  public Book getData()
  {
    return this.data;
  }


  /**
   * Gets `left` child BookNode from current BookNode
   *
   * @return node BookNode referenced in `left`
   */

  public BookNode getLeft()
  {
    return this.left;
  }


  /**
   * Sets `left` child BookNode in current BookNode
   *
   * @param node BookNode to set `left` to
   */

  public void setLeft( BookNode node )
  {
    this.left = node;
  }


  /**
   * Gets `right` child BookNode in current BookNode
   *
   * @return node BookNode referenced in `right`
   */

  public BookNode getRight()
  {
    return this.right;
  }


  /**
   * Sets `right` child BookNode in current BookNode
   *
   * @param node BookNode to reference in `right`
   */

  public void setRight( BookNode node )
  {
    this.right = node;
  }


  /**
   * Tests if current BookNode is a 'leaf'
   *
   * @return True if BookNode has no children,
   *         False if otherwise
   */

  public boolean isLeaf()
  {
    return ( this.left == null ) && ( this.right == null );
  }
}
