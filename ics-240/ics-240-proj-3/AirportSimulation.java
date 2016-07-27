import javax.swing.*;

/**
 * Main process
 *
 * @author Kyle Heneman
 * @since 10/11/2014
 *
 * <a href="http://www.kyleheneman.com">kyleheneman.com</a>
 */

public class AirportSimulation
{
  public static void main( String[] s )
  {
    JFrame frame = new AirportSimulationFrame();
    frame.setVisible( true );
  }
}

class AirportSimulationFrame extends JFrame
{
  public AirportSimulationFrame()
  {
    setTitle( "Airport Simulation" );
    setResizable( false );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setLocationRelativeTo( null );

    JPanel panel = new GUI();

    this.add( panel );
    this.pack();
  }
}
