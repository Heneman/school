/**
 * @author Kyle Heneman
 * @since 11/8/14
 *
 * `main()` method to run GUI
 */


import javax.swing.*;

class Problem1
{
  public static void main( String[] s )
  {
    JFrame frame = new Problem1Frame();
    frame.setVisible( true );
  }
}

class Problem1Frame extends JFrame
{
  public Problem1Frame()
  {
    setTitle( "Binary Converter" );
    setResizable( true );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setLocationRelativeTo( null );

    JPanel panel = new Problem1GUI();

    this.add( panel );
    this.pack();
  }
}