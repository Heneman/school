/**
 * Simple Object representing a Book
 *
 * NO MUTATOR METHODS WERE IMPLEMENTED SINCE EDITING WAS NOT PART OF THE REQUIREMENTS
 *
 * @author Kyle Heneman
 * @since 11/19/2014
 */


public class Book implements Cloneable, Comparable< Book >
{
  private String title;
  private String author;
  private String genre;
  private double price;
  private double review;


  /**
   * Initializes attributes of a new Book
   *
   * @param title  Title of new Book
   * @param author Name of new Book's Author
   * @param genre  Genre of new Book
   * @param price  Price of new Book
   * @param review My rating of new Book on scale of 1-5
   */

  public Book( String title,
               String author,
               String genre,
               double price,
               double review )
  {
    this.title = title;
    this.author = author;
    this.genre = genre;
    this.price = price;
    this.review = review;
  }


  /**
   * Clones Book
   *
   * @return clone Clone of Book
   */

  @Override
  public Book clone() throws CloneNotSupportedException
  {
    try
    {
      return ( Book ) super.clone();
    }
    catch ( CloneNotSupportedException e )
    {
      throw new RuntimeException( "Clone not supported" );
    }
  }


  /**
   * Gets `title` of Book
   *
   * @return title Returns String of `title`
   */

  public String getTitle()
  {
    return this.title;
  }


  /**
   * Gets `author` of Book
   *
   * @return author Returns String of `author`
   */

  public String getAuthor()
  {
    return this.author;
  }


  /**
   * Gets `genre` of Book
   *
   * @return genre Returns String of `genre`
   */

  public String getGenre()
  {
    return this.genre;
  }


  /**
   * Gets `price` of Book
   *
   * @return price Returns double of `price` formatted to USD
   */

  public double getPrice()
  {
    return this.price;
  }


  /**
   * Gets `review` of Book
   *
   * @return review Returns double of `review`
   */

  public double getReview()
  {
    return this.review;
  }


  /**
   * Compares `titles` of two Books
   *
   * @param book Book to compare this instance of Book with
   *
   * @return Negative int if compared Book `title` is "less than" this Book's `title`, 0 if `title`s are "equal",
   *         positive int if compared Book `title` is "greater than" this Book's `title`
   */

  @Override
  public int compareTo( Book book )
  {
    return getTitle().compareToIgnoreCase( book.getTitle() );
  }


  /**
   * Compares `title` of `this` Book to `query`
   *
   * @param query String to compare to `this` Book's `title`
   *
   * @return Negative int if `query` is "less than" this Book's `title`, 0 if `query` "equals" this Book's `title`,
   *         positive int if `query` is "greater than" this Book's `title`
   */

  public int compareTitle( String query )
  {
    return getTitle().compareToIgnoreCase( query );
  }


  /**
   * Compares titles of `this` Book to `title` passed in
   *
   * @param query  Title to compare to `this` Book's `title`
   * @return True if queried `title` is the same as `this` Book's `title,
   *         False if otherwise
   */

  public boolean titleEquals( String query )
  {
    return getTitle().compareToIgnoreCase( query ) == 0;
  }


  /**
   * Builds String containing attribute values of `this` Book
   *
   * @return Formatted String containing attribute values of `this` Book
   */

  public String toString()
  {
    return getTitle();
  }
}