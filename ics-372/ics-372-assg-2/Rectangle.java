import java.awt.*;

/**
 * A simple extension of Figure to hold
 * data for a Rectangle and the logic
 * to draw the shape
 *
 * @author Kyle Heneman
 * @since 09/09/2015
 */
class Rectangle extends Figure {
    /*
     * Fields to hold Rectangle's origin's
     * coordinates, as well as dimensions
     * for current Rectangle
     */
    private int x, y, width, height;

    /**
     * Tells the Graphics context of FiguresPanel
     * the color of current Rectangle, as well as
     * where to draw the outlines and where to
     * fill the Figure
     *
     * @param graphic The Graphics context from FigurePanel
     */
    @Override
    public void draw(Graphics graphic) {
        // Give Graphics context current Rectangle's `color`
        graphic.setColor(color);
        // As well as the origin and dimensions
        graphic.drawRect(x, y, width, height);
        // and the bounds to fill with `color`
        graphic.fillRect(x, y, width, height);
    }

    /**
     * Sets the Rectangle's origin's x-coordinate
     *
     * @param x Horizontal position of Rectangle's origin
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Rectangle's origin's y-coordinate
     *
     * @param y Vertical position of Rectangle's origin
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the width of the Rectangle's bounds from
     * the origin
     *
     * @param width The width Rectangle will be
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     * Sets the height of the Rectangles bounds from
     * the origin
     *
     * @param height The height Rectangle will be
     */
    public void setHeight(int height){
        this.height = height;
    }

    /**
     * Sets the Color that Rectangle will be
     * filled with
     *
     * @param color Color that Rectangle will be filled with
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Builds a String representation of Rectangle
     *
     * @return String representing Rectangle
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.getClass().getName());
        builder.append(" [x=").append(this.x);
        builder.append(", y=").append(this.y);
        builder.append(", width=").append(this.width);
        builder.append(", height=").append(this.height);
        builder.append(", color=").append(this.color);
        builder.append("]");

        return builder.toString();
    }
}