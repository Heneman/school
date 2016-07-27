/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.awt.*;

/**
 * Implements a line; stores the end points.
 *
 */
public class Label extends Item {
    private Point startingPoint;
    private String text = "";

    /**
     * Creates a label object with the starting point determined.
     * 
     * @param point
     *            the start of the label
     */
    public Label(Point point) {
        startingPoint = point;
    }

    /**
     * Adds one more character to the label
     * 
     * @param character
     *            a new character in the label
     */
    public void addCharacter(char character) {
        text += character;
    }

    /**
     * removes the rightmost character in the label
     */
    public void removeCharacter() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    /**
     * Checks if the given point is in the label
     */
    public boolean includes(Point point) {
        return distance(point, startingPoint) < 10.0;
    }

    /**
     * Displays the label
     */
    public void render() {
        uiContext.draw(this);
    }

    /**
     * Returns the actual text in the label
     * 
     * @return the text in the label
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the starting point
     * 
     * @return starting point
     */
    public Point getStartingPoint() {
        return startingPoint;
    }
    
    /**
     * Sets the starting point to a new point.
     * @param point The new point of the label.
     */
    public void setStartingPoint(Point point) {
        startingPoint = point;
    }

    

    /**
     * Moves the label to the new point.
     */
    @Override
    public void move(Point startPoint, Point endPoint) {
        //How much to move the label.
        int xOffset = (int) endPoint.getX() - (int) startPoint.getX();
        int yOffset = (int) endPoint.getY() - (int) startPoint.getY();
        
        //Move the label. 
        Point newPoint = new Point((int) startingPoint.getX() + xOffset ,
                (int) startingPoint.getY() + yOffset);    
        setStartingPoint(newPoint);
    }


}