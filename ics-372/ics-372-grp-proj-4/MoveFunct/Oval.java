/**
 * Class: ICS 372-01
 * @author Ian, Kyle, Chelsea, Kevin, 
 * based upon code by Brahma Dathan and Sarnath Ramnath
 * Last modified: 11/27/2015
 * Project Name: Project #3
 * Class Name: NewSwingUI
 * Description: Represents an Oval
 */
import java.awt.*;

/**
 * Represents an oval.
 */
public class Oval extends Item {
    private Point point1;
    private Point point2;
    private int width;
    private int height;
    private int xCoord;
    private int yCoord;

    /**
     * Creates an Oval with the given endpoints
     * 
     * @param point1
     *            one endpoint
     * @param point2
     *            another endpoint
     */
    public Oval(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        width = (int) (point2.getX() - point1.getX());
        height = (int) (point2.getY() - point1.getY());
        xCoord = (int) point1.getX();
        yCoord = (int) point1.getY();
    }

    /**
     * Creates an Oval with no specific endpoints
     */
    public Oval() {
    }

    /**
     * Checks whether the given point falls within the oval's bounding box *
     * 
     * @return true iff the given point is close to bounding box
     */
    public boolean includes(Point point) {

        boolean xCheck = (point.getX() < (point1.getX() + width + 10))
                && (point.getX() > (point1.getX() - 10));
        boolean yCheck = (point.getY() < (point1.getY() + height + 10))
                && (point.getY() > (point1.getY() - 10));
        return (xCheck && yCheck);
    }

    /**
     * Displays the Oval
     */
    public void render() {
        uiContext.draw(this);
    }

    /**
     * Sets one of the endpoints
     * 
     * @param point
     *            an endpoint
     */
    public void setPoint1(Point point) {
        point1 = point;
        xCoord = (int) point1.getX();
        yCoord = (int) point1.getY();
    }

    /**
     * Sets one of the endpoints
     * 
     * @param point
     *            an endpoint
     */
    public void setPoint2(Point point) {
        point2 = point;
        width = (int) (point2.getX() - point1.getX());
        height = (int) (point2.getY() - point1.getY());
    }

    /**
     * Returns first of the endpoints
     * 
     * @return an endpoint
     */
    public Point getPoint1() {
        return point1;
    }

    /**
     * Returns second of the endpoints
     * 
     * @return an endpoint
     */
    public Point getPoint2() {
        return point2;
    }

    /**
     * Returns the width of the oval.
     * @return the wdith of the oval.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the oval.
     * @param height the new width of the oval.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of the oval.
     * @return the height of the oval.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the oval.
     * @param height the new height of the oval.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the xCoordinate of the first point.
     * @return the xCoordinate of the first point.
     */
    public int getXCoord() {
        return xCoord;
    }

    /**
     * Gets the yCoordinate of the first point.
     * @return the yCoordinate of the first point.
     */
    public int getYCoord() {
        return yCoord;
    }

    /**
     * Returns a string representation of the Oval
     * 
     * @return a string representation
     */
    public String toString() {
        return "Oval with center at " + (int) (point1.getX() + width / 2) + "," +
                (int) (point1.getY() + height / 2) + ", width of " + width +
                ", and height of " + height + ".";
    }
    
    /**
     * Moves the oval to the new point.
     */
    @Override
    public void move(Point startPoint, Point endPoint) {
        //How much to move the points of the oval.
        int xOffset = (int) endPoint.getX() - (int) startPoint.getX();
        int yOffset = (int) endPoint.getY() - (int) startPoint.getY();
        
        //Move the oval. 
        Point newPoint1 = new Point((int) point1.getX() + xOffset ,
                (int) point1.getY() + yOffset);
        Point newPoint2 = new Point((int) point2.getX() + xOffset ,
                (int) point2.getY() + yOffset);    
        setPoint1(newPoint1);
        setPoint2(newPoint2);
    }
}