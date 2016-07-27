/**
 * /**
 * Class: ICS 372-01
 * @author Ian, Kyle, Chelsea, Kevin, 
 * based upon code by Brahma Dathan and Sarnath Ramnath
 * Last modified: 11/27/2015
 * Project Name: Project #3
 * Class Name: UIContext
 * Description: Represents a Polygon
 *
 */


import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;

public class Polygon extends Item {
  private ArrayList<Point> points = new ArrayList<Point>(); 
  private ArrayList<Line> sides = new ArrayList<Line>(); 
  private Line line;
  private Point point1;
  private Point point2;
  private Boolean hit;
  
  /**
   * Creates a polygon with no specific endpoints
   */
  public Polygon() {
  }
  /**
   * Checks whether the given point falls within the polygon endpoints
   * @return true iff the given point is close to one of the endpoints
   */
  public boolean includes(Point point) {
      for (Point pt : points){
          if(distance(point, pt) < 10.0){
              return true;
          }
      }
      return false;
  }

  /**
   * Displays the polygon
   */
  public void render() {
    uiContext.draw(this);
  }
  /**
   * Adds the point to the pointList array
   * @param point adds the point
   */
  public void setPoint(Point point) {
    points.add(point);
  }
  /**
   * Returns the ArrayList pointList
   * @return an ArrayList of points, pointList
   */
  public ArrayList getPoints() {
    return points;
  }
  /**
   * Sets one of the lines
   * @param line a line
   */
  public void setLine(Line line1) {
    line = line1;
  }
  /**
   * Adds a line to the polygonList
   * @param line a line
   */
  public void addLine(Line line) {
    sides.add(line);
  }
  /**
   * Returns the polygonList
   * @return an ArrayList of lines
   */
  public ArrayList getPolygonList() {
    return sides;
  }
  
  /**
   * Moves the polygon to the new point.
   */
  @Override
  public void move(Point startPoint, Point endPoint) {

      //How much to move the points of the oval.
      int xOffset = (int) endPoint.getX() - (int) startPoint.getX();
      int yOffset = (int) endPoint.getY() - (int) startPoint.getY();    

      //Move the polygon.
      for (Line currentSide : sides) {
          currentSide.move(startPoint, endPoint);
      }
      
      for (Point currentPoint : points) {
          currentPoint.setLocation(currentPoint.getX() + xOffset,
                  currentPoint.getY() + yOffset);
      }
      
  }
  
  
  /**
   * Returns a string representation of the polygon
   * @return a string representation
   */
  public String toString() {
    return "Polygon with lines " + sides;
  }
}