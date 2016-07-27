/**
 * Simple Object representing a Book
 *
 * @author Kyle Heneman <a href="http://www.kyleheneman.com"> www.kyleheneman.com </a> <a
 *         href="mailto:holla@kyleheneman.com"> holla@kyleheneman.com </a>
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
   * Sets `title` of Book
   *
   * @param newTitle Changes `title` of Book
   */

  public void setTitle( String newTitle )
  {
    this.title = newTitle;
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
   * Sets `author` of Book
   *
   * @param newAuthor Changes `author` of Book
   */

  public void setAuthor( String newAuthor )
  {
    this.author = newAuthor;
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
   * Sets `genre` of Book
   *
   * @param newGenre Changes `genre` of Book
   */

  public void setGenre( String newGenre )
  {
    this.genre = newGenre;
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
   * Sets `price` of Book
   *
   * @param newPrice Changes `price` of Book
   */

  public void setPrice( double newPrice )
  {
    this.price = newPrice;
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
   * Sets `review` of Book
   *
   * @param newReview Changes `review` of Book
   */

  public void setReview( double newReview )
  {
    this.review = newReview;
  }


  /**
   * Compares `titles` of two Books
   *
   * @param book Book to compare this Book with
   *
   * @return -1 If compared Book `title` is "less" than this Book's `title`, 0 if `title`s are "equal", 1 if compared
   * Book `title` is "greater" than this Book's `title`
   */

  @Override
  public int compareTo( Book book )
  {
    return this.getTitle().compareToIgnoreCase( book.getTitle() );
  }
}

