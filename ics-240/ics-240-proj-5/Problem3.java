import java.applet.Applet;
import java.awt.*;

/**
 * @author Kyle Heneman
 * @since 11/8/14
 *
 * Draws fractal resembling tick marks on a ruler
 */
public class Problem3 extends Applet
{
  private Image display;

  public void init()
  {
    int height = getSize().height;
    int width = getSize().width;
    display = createImage( width, height );
    Graphics drawingArea = display.getGraphics();

    // Start with initial values
    drawRuler( 0, height / 2, width, height / 4, drawingArea );
  }

  public void paint( Graphics g )
  {
    g.drawImage( display, 0, 0, null );
  }

  public static void drawRuler( double leftX,
                                double bottomY,
                                double rightX,
                                double topY,
                                Graphics drawingArea )
  {
    final int STOP = 30;

    // Draws baseline if ticks are separated by near 30 pixels
    if(( rightX - leftX ) <= STOP )
    {
      drawingArea.drawLine( (int) leftX, (int) bottomY, (int) rightX, (int) bottomY );
    }
    else
    {
      // Finds midpoint of  x-coordinates
      int midX = (int) ( leftX + rightX ) / 2;

      // Draws line from midpoint on x-axis to half the height of current iteration
      drawingArea.drawLine( midX, (int) bottomY, midX, (int) topY / 2);

      // Calls itself twice to create a left and right 'box' that's
      // half the current size
      drawRuler( leftX, bottomY, midX, (topY + (topY / 2)), drawingArea );
      drawRuler( midX, bottomY, rightX, (topY + (topY / 2)), drawingArea );
    }
  }
}
