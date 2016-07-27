/**
 * `main()` method to run GUI
 *
 * @author Kyle Heneman <a href="http://www.kyleheneman.com"> www.kyleheneman.com </a> <a
 *         href="mailto:holla@kyleheneman.com"> holla@kyleheneman.com </a>
 */


import javax.swing.*;

class BookManager
{
  public static void main( String[] s )
  {
    JFrame frame = new BookManagerFrame();
    frame.setVisible( true );
  }
}

class BookManagerFrame extends JFrame
{
  public BookManagerFrame()
  {
    setTitle( "Book Inventory Manager" );
    setResizable( false );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setSize( 600, 800 );
    setLocationRelativeTo( null );

    JPanel panel = new GUI();

    this.add( panel );
    this.pack();
  }
}