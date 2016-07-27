import java.awt.*;

/**
 * A simple extension of Figure to hold
 * data for a Circle and the logic
 * to draw the shape
 *
 * @author Kyle Heneman
 * @since 09/09/2015
 */
class Circle extends Figure {
    /*
     * Fields to hold Circle's origin's
     * coordinates, as well as dimensions
     * for current Circle
     */
    private int x, y, radius;

    /**
     * Tells the Graphics context of FiguresPanel
     * the color the Circle will be filled with,
     * as well as where to draw the Circle and
     * the bounds to fill it with a color
     *
     * @param graphic The Graphics context from FigurePanel
     */
    @Override
    public void draw(Graphics graphic) {
        // Give Graphics context current Circle's `color`
        graphic.setColor(color);
        /*
         * As well as the origin and dimensions.
         *
         * To get the mouse-click position to be
         * used as the middle of the circle, we need
         * to off-set the origin's coordinates by the
         * radius size, and give the bounds of the
         * circle as the dimension (2r)
         */
        graphic.drawOval(x - radius, y - radius, radius * 2, radius * 2);
        /*
         * And the bounds to fill using the same
         * rules for the bounds as above
         */
        graphic.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    /**
     * Sets the Circle's origin's x-coordinate
     *
     * @param x Horizontal position of Circle's origin
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets the Circle's origin's y-coordinate
     *
     * @param y Vertical position of Circle's origin
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Sets the length of Circle's radius
     *
     * @param radius Length of Circle's radius
     */
    public void setRadius(int radius){
        this.radius = radius;
    }

    /**
     * Sets the Color that Circle will be
     * filled with
     *
     * @param color Color that Circle will be filled with
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Builds a String representation of Circle
     *
     * @return String representing Circle
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.getClass().getName());
        builder.append(" [x=").append(this.x);
        builder.append(", y=").append(this.y);
        builder.append(", radius=").append(this.radius);
        builder.append(", color=").append(this.color);
        builder.append("]");

        return builder.toString();
    }
}