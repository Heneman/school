/**
 * Iterator for ArrayList
 *
 * @author Kyle Heneman <a href="http://www.kyleheneman.com"> www.kyleheneman.com </a> <a
 *         href="mailto:holla@kyleheneman.com"> holla@kyleheneman.com </a>
 */

import javax.swing.*;
import java.util.Iterator;

public class Lister< E > implements Iterator< E >
{
  private ArrayList< Object > bl;
  private int i;

  public Lister( ArrayList< Object > bookList )
  {
    this.bl = bookList;
    i = 0;
  }


  /**
   * Tests if next element in iterator exists
   *
   * @return true If next element in iterator exists
   */

  @Override
  public boolean hasNext()
  {
    return !( bl.getBookCount() == i );
  }


  /**
   * Gets next element in iterator
   *
   * @return eBook Next element in iterator
   */

  @Override
  @SuppressWarnings( "unchecked" )
  public E next()
  {
    E eBook = null;

    try
    {
      eBook = ( E ) bl.getBook( i );
      i++;
    }
    catch ( CloneNotSupportedException e )
    {
      JOptionPane.showMessageDialog( null, "This class does not implement Cloneable" );
    }

    return eBook;
  }


  /**
   * Currently does nothing
   */

  @Override
  public void remove()
  {
    JOptionPane.showMessageDialog( null, "There is no remove method :(" );
  }
}
