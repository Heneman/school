/**
 * Class: ICS 372-01
 * @author Ian, Kyle, Chelsea, Kevin, 
 * based upon code by Brahma Dathan and Sarnath Ramnath
 * Last modified: 11/27/2015
 * Project Name: Project #3
 * Class Name: NewSwingUI
 * Description: The button to create Polygons. Processes the mouse movements and clicks calling
 * the appropriate methods of controller.
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PolygonButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  
  /**
   * Creates the button for the Polygon
   * @param jFrame the frame where the label is put
   * @param jPanel the panel within the frame
   */
  public PolygonButton(View jFrame, JPanel jPanel) {
    super("Polygon");
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
  }
  /**
   * Handle click for creating a new polygon
   */
  public void actionPerformed(ActionEvent event) {
    drawingPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    mouseHandler = new MouseHandler();
    // Change cursor when button is clicked
    Controller.instance().makePolygon();
    drawingPanel.addMouseListener(mouseHandler);
  // Start listening for mouseclicks on the drawing panel
  }
  /**
   * Handles mouse click so that the points can now be captured.
   * 
   */
  private class MouseHandler extends MouseAdapter {
    private int pointCount;
    private Point firstPoint;
    private Point lastPoint;
    
    public void mouseClicked(MouseEvent event) {
      Controller.instance().setPolygonSide(View.mapPoint(event.getPoint()));
      //If it is the first click, set the first point
      if (++pointCount == 1){
          firstPoint = event.getPoint();
      }
      else{
          //Else set/update the last point
          lastPoint = event.getPoint();
          //Calculate the distance between the first point and the last point
          double xDifference = firstPoint.getX() - lastPoint.getX();
          double yDifference = firstPoint.getY() - lastPoint.getY();
          double distance = (Math.sqrt(xDifference * xDifference + yDifference * yDifference));
          //If the distance is within 10 pixels, end mouse listener and change the cursor
          if((distance < 10.0)){
              drawingPanel.removeMouseListener(this);
              drawingPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
              pointCount = 0;
          }
      }
    }
  }
}