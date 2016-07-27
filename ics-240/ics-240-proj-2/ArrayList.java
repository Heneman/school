/**
 * Manages Array of Books
 *
 * @author Kyle Heneman <a href="http://www.kyleheneman.com"> www.kyleheneman.com </a> <a
 *         href="mailto:holla@kyleheneman.com"> holla@kyleheneman.com </a>
 */


import javax.swing.*;

class ArrayList< E > implements Cloneable
{
   //// Invariants ////
  ////////////////////

  private Object[] bookArray;
  private int bookCount = 0;


  /**
   * Create an Array of Books with size equal to parameter
   */

  ArrayList( int bound )
  {
    bookArray = new Object[ bound ];
  }


  /**
   * Add Book to `bookArray`
   *
   * @param book A new Book to add to `bookArray`
   *
   * @throws RuntimeException
   */

  public void add( E book )
  {
    if ( !( bookArray.length - bookCount > 0 ) )
    {
      JOptionPane.showMessageDialog( null, "No more room for another book!" );
    }
    else
    {
      bookArray[ bookCount ] = book;

      this.incrementBookCount();
    }
  }


  /**
   * Searches through `bookArray` for a Book with `title` matching query
   *
   * @param query Search query
   *
   * @return index Index of Book with `title` matching query
   */

  public int find( String query )
  {
    int index = -1;

    for ( int i = 0; i < bookCount; i++ )
    {
      if ( ( bookArray[ i ] instanceof Book ) &&
           ( ( Book ) bookArray[ i ] ).getTitle().equalsIgnoreCase( query ) )
      {
        index = i;
      }
    }

    return index;
  }


  /**
   * Edits selected Book
   *
   * @param title  New `title` for Book
   * @param author New `author` for Book
   * @param genre  New `genre` for Book
   * @param price  New `price` for Book
   * @param review New `review` for Book
   */

  public void edit( String query,
                    String title,
                    String author,
                    String genre,
                    double price,
                    double review )
  {
    int index = this.find( query );

    if ( index >= 0 )
    {
      try
      {
        Book book = ( Book ) this.getBook( index );

        book.setTitle( title );
        book.setAuthor( author );
        book.setGenre( genre );
        book.setPrice( price );
        book.setReview( review );

        this.setBook( book, index );
      }
      catch ( CloneNotSupportedException e )
      {
        JOptionPane.showMessageDialog( null, "This class does not implement Cloneable" );
      }
    }
    else
    {
      JOptionPane.showMessageDialog( null, "No Book found with that title" );
    }
  }


  /**
   * Sorts Books in `bookArray` by `title` in descending alphabetical order
   */

  public void sort()
  {
    SelectionSort.sort( bookArray, bookCount );
  }


  /**
   * Sets element at given index to given Book
   *
   * @param book  Book to be set
   * @param index Index to set Book at
   */

  public void setBook( Book book, int index )
  {
    bookArray[ index ] = book;
  }

  /**
   * Returns Book at given index
   *
   * @param i Index to get Book from
   *
   * @return E Book at given index
   */

  public E getBook( int i ) throws CloneNotSupportedException
  {
    Book book = ( ( Book ) bookArray[ i ] ).clone();

    @SuppressWarnings( "unchecked" )
    E eBook = ( E ) book;

    return eBook;
  }


  /**
   * Deletes selected Book
   *
   * @param title Title of Book to be deleted
   */
  public void delete( String title )
  {
    int index = this.find( title );

    if ( index == -1 )
    {
      JOptionPane.showMessageDialog( null, "No Book found at that index" );
    }
    else
    {
      this.decrementBookCount();
      bookArray[ index ] = bookArray[ bookCount ];
    }
  }


  /**
   * Gets count of Books in `bookArray`
   *
   * @return bookCount Number of Books currently stored in `bookArray`
   */

  public int getBookCount()
  {
    return this.bookCount;
  }


  /**
   * Increments `bookCount` by 1
   */

  void incrementBookCount()
  {
    this.bookCount++;
  }


  /**
   * Decrements `bookCount` by 1
   */

  void decrementBookCount()
  {
    this.bookCount--;
  }

  /**
   * Returns `bookArray.length`
   *
   * @return length Returns `bookArray.length`
   */

  public int getLength()
  {
    return bookArray.length;
  }


  /**
   * Return Clone of ArrayList
   *
   * @return arrayList Clone of this ArrayList
   */

  @Override
  @SuppressWarnings( "unchecked" )
  public ArrayList< E > clone() throws CloneNotSupportedException
  {
    ArrayList< E > arrayList;

    try
    {
      arrayList = ( ArrayList< E > ) super.clone();

      arrayList.bookArray = this.bookArray.clone();

      for ( int i = 0; i < bookCount; i++ )
      {
        arrayList.bookArray[ i ] = ( ( Book ) this.bookArray[ i ] ).clone();
      }
    }
    catch ( CloneNotSupportedException e )
    {
      throw new RuntimeException( "Clone not supported" );
    }

    return arrayList;
  }
}

