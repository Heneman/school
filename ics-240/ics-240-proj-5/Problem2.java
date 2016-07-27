/**
 * `main()` method to run GUI
 *
 * @author Kyle Heneman <a href="http://www.kyleheneman.com"> www.kyleheneman.com </a> <a
 *         href="mailto:holla@kyleheneman.com"> holla@kyleheneman.com </a>
 */


import javax.swing.*;

class Problem2
{
  public static void main( String[] s )
  {
    JFrame frame = new Problem2Frame();
    frame.setVisible( true );
  }
}

class Problem2Frame extends JFrame
{
  public Problem2Frame()
  {
    setTitle( "Step Combinations" );
    setResizable( true );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setLocationRelativeTo( null );

    JPanel panel = new Problem2GUI();

    this.add( panel );
    this.pack();
  }
}