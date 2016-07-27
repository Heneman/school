/**
 * Binary Search Tree ADT for BookNodes
 *
 * Meant to store a collection of data in Nodes
 * that are able to be traversed, and have data
 * retrieved, in an efficient manner
 *
 * @author Kyle Heneman
 * @since 11/19/2014
 */

public class BookBST
{
  /**
   * Invariant of Binary Search Tree:
   *
   * 1.) Elements of Nodes must be comparable with
   *     a total order semantics
   *
   * 2.) Every element in Node n's left subtree is
   *     less than or equal to the element in Node n
   *
   * 3.) Every element in Node n's right subtree is
   *     greater than the element in Node n
   */

  BookNode root;


  /**
   * Instantiates `root` BookNode to `null`
   *
   * Precondition: The constructor of BookBST
   *               has been called.
   *
   * Postcondition: A new BookBST has been created
   */

  public BookBST()
  {
    root = null;
  }


  /**
   * Delegate for private `addBook()` method
   *
   * @param book New Book to add to BookBST
   *
   * Precondition: `addBook()`has been called and
   *               passed in a new Book to be added
   *               to BookBST
   *
   * Postcondition: `book` has been added to a new BookNode
   *                and assigned as `root` if BookBST was empty,
   *                or has been added to a subtree if `root`
   *                already existed
   */

  public void addBook( Book book )
  {
    if( root == null )
    {
      root = new BookNode( book, null, null );
    }
    else
    {
      addBook( book, root );
    }
  }


  /**
   * Creates new BookNode with `book` argument and
   * adds it to BookBST in alphabetical
   * order according to the BookBST invariant.
   *
   * @param book New Book to be added to the BookBST
   * @param cur  Pointer to the current BookNode
   */

  private void addBook( Book book, BookNode cur )
  {
    if( book.compareTo( cur.getData() ) <= 0 )
    {
      if( cur.getLeft() == null )
      {
        cur.setLeft( new BookNode( book, null, null ) );
      }
      else
      {
        addBook( book, cur.getLeft() );
      }
    }
    else
    {
      if( cur.getRight() == null )
      {
        cur.setRight( new BookNode( book, null, null ) );
      }
      else
      {
        addBook( book, cur.getRight() );
      }
    }
  }


  /**
   * Delegate for private `getPreOrder()` method
   *
   * @return String of comma delimited values of `data`
   *         from BookNodes
   *
   * Precondition: The "Display Pre-Order" button was pressed
   *
   * Postcondition: A new StringBuilder was created and passed
   *                to the private overloaded `getPreOrder()`
   *                method along with `root`, and the String
   *                returned from the private overloaded `getPreOrder()`
   *                was returned to location that called this.
   */

  public String getPreOrder()
  {
    StringBuilder sb = new StringBuilder();
    return getPreOrder( root, sb );
  }


  /**
   * Traverses BookBST and returns a String
   * containing BookNode `data` values in Pre-Order order
   *
   * @param cur Pointer to the current BookNode
   * @param sb  StringBuilder used to append values
   *            of `data` from BookNodes
   *
   * @return String of values of `data` from BookNodes
   *
   * Precondition: Method was called from either the public
   *               `getPreOrder` method or itself, and passed
   *               a BookNode pointer referencing the BookBST
   *               `root` or a BookNode referencing a subtree
   *               of `root`
   *
   * Postcondition: A String containing the `title`s of the Book
   *                in each BookNode was built and returned in
   *                Pre-Order order and BookBST was unaltered.
   */

  private String getPreOrder( BookNode cur, StringBuilder sb )
  {
    if( cur != null )
    {
      sb.append( "* " ).append( cur.getData().getTitle() ).append( "\n" );

      if ( cur.getLeft() != null )
      {
        getPreOrder( cur.getLeft(), sb );
      }

      if ( cur.getRight() != null )
      {
        getPreOrder( cur.getRight(), sb );
      }
    }

    return sb.toString();
  }


  /**
   * Delegate for private `inOrder()` method
   *
   * @return String of comma delimited values of `data`
   *         from BookNodes
   *
   * Precondition: The "Display In-Order" button was pressed
   *
   * Postcondition: A new StringBuilder was created and passed
   *                to the private overloaded `getInOrder()`
   *                method along with `root`, and the String
   *                returned from the private overloaded `getInOrder()`
   *                was returned to location that called this.
   */

  public String getInOrder()
  {
    StringBuilder sb = new StringBuilder();
    return getInOrder( root, sb );
  }


  /**
   * Traverses BookBST and returns a String
   * containing BookNode `data` values in In-Order order
   *
   * @param cur Pointer to the current BookNode
   * @param sb  StringBuilder used to append values
   *            of `data` from BookNodes
   *
   * @return String of comma delimited values of `data`
   *         from BookNodes
   *
   * Precondition: Method was called from either the public
   *               `getInOrder` method or itself, and passed
   *               a BookNode pointer referencing the BookBST
   *               `root` or a BookNode referencing a subtree
   *               of `root`
   *
   * Postcondition: A String containing the `title`s of the Book
   *                in each BookNode was built and returned in
   *                In-Order order and BookBST was unaltered.
   */

  private String getInOrder( BookNode cur, StringBuilder sb )
  {
    if( cur != null )
    {
      if( cur.getLeft() != null )
      {
        getInOrder( cur.getLeft(), sb );
      }

      sb.append( "* " ).append( cur.getData().getTitle() ).append( "\n" );

      if( cur.getRight() != null )
      {
        getInOrder( cur.getRight(), sb );
      }
    }

    return sb.toString();
  }


  /**
   * Delegate for private `postOrder()` method
   *
   * @return String of comma delimited values of `data`
   *         from BookNodes
   *
   * Precondition: The "Display Post-Order" button was pressed
   *
   * Postcondition: A new StringBuilder was created and passed
   *                to the private overloaded `getPostOrder()`
   *                method along with `root`, and the String
   *                returned from the private overloaded `getPostOrder()`
   *                was returned to location that called this.
   */

  public String getPostOrder()
  {
    StringBuilder sb = new StringBuilder();
    return getPostOrder( root, sb );
  }


  /**
   * Traverses BookBST and returns a String
   * containing BookNode `data` values in Post-Order order
   *
   * @param cur Pointer to current BookNode
   * @param sb  StringBuilder used to append values
   *            of `data` from BookNodes
   *
   * @return String of comma delimited values of `data`
   *         from BookNodes
   *
   * Precondition: Method was called from either the public
   *               `getPostOrder` method or itself, and passed
   *               a BookNode pointer referencing the BookBST
   *               `root` or a BookNode referencing a subtree
   *               of `root`
   *
   * Postcondition: A String containing the `title`s of the Book
   *                in each BookNode was built and returned in
   *                In-Order order and BookBST was unaltered.
   */

  private String getPostOrder( BookNode cur, StringBuilder sb )
  {
    if( cur != null )
    {
      if( cur.getLeft() != null )
      {
        getPostOrder( cur.getLeft(), sb );
      }

      if( cur.getRight() != null )
      {
        getPostOrder( cur.getRight(), sb );
      }

      sb.append( "* " ).append( cur.getData().getTitle() ).append( "\n" );
    }

    return sb.toString();
  }


  /**
   * Delegate for private `getDepth()` method
   *
   * @return Current depth of BookBST
   *
   * Precondition: The label displaying the BookBST depth
   *               in the GUI is updating and needs a new
   *               count of BookBST's depth
   *
   * Postcondtion: The private overloaded `getDepth()` method
   *               was called and the int returned from it was
   *               returned by this method
   */

  public int getDepth()
  {
    return getDepth( root );
  }


  /**
   * Calculates the depth of the BookBST
   *
   * @param cur Pointer to current BookNode
   *
   * @return Current depth of BookBST
   *
   * Precondtion: This was called from the public `getDepth()`
   *              method or itself and passed a BookNode pointer
   *              referencing the BookBST `root` or a BookNode
   *              referencing a subtree of `root`
   *
   * Postcondition: BookBST was traversed, an int accurately
   *                representing the depth of BookBST was returned,
   *                and BookBST was unaltered
   */

  private int getDepth( BookNode cur )
  {
    if( cur == null )
    {
      return -1;
    }
    else if( cur.isLeaf() )
    {
      return 0;
    }
    else
    {
      return 1 + Math.max( getDepth(cur.getLeft()), getDepth(cur.getRight()) );
    }
  }


  /**
   * Delegate for private `search()` method
   *
   * @param query String to match against existing Book `title`s
   *
   * @return Formatted String containing all attributes of Book
   *         with `title` matching `query`, `null` if no Book
   *         `title` matches `query`
   *
   * Precondition: The Search button was pressed and passed a String
   *               as an argument to attempt to match to a `title` of a
   *               Book contained in a BookNode within BookBST
   *
   * Postcondition: The private overloaded `search()` method was called
   *                and passed `query`
   */

  public Book search( String query ) throws CloneNotSupportedException
  {
    return search( query, root );
  }


  /**
   * Traverses BookBST attempting to find a BookNode
   * containing a Book with `title` matching `query`
   *
   * @param query String to match against existing Book `title`s
   * @param cur Pointer to the current BookNode
   *
   * @return Formatted String containing all attributes of Book
   *         with `title` matching `query`, `null` if no Book
   *         `title` matches `query`
   *
   * Precondition: This was called from the public `search()` method
   *               or itself and passed the `query` as well as a BookNode
   *               pointer referencing the BookBST `root` or a BookNode
   *               referencing a subtree of `root`
   *
   * Postcondition: BookBST was traversed and a Book with `title` matching
   *                `query` was found and a clone of that Book returned, or
   *                null was returned if no match was found.  BookBST was
   *                unaltered.
   */

  public Book search( String query, BookNode cur ) throws CloneNotSupportedException
  {
    if( cur != null )
    {
      if( cur.getData().titleEquals( query ) )
      {
        return cur.getData().clone();
      }
      else if( cur.getData().compareTitle( query ) > 0 )
      {
        return search( query, cur.getLeft() );
      }
      else
      {
        return search( query, cur.getRight() );
      }
    }
    else
    {
      return null;
    }
  }
}