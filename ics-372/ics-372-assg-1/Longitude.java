package com.kyleheneman;

/**
 * @author Kyle Heneman
 * @since 09/03/2015
 *
 * A simple object representing a longitudinal Position
 */

class Longitude extends Position {
    private String direction;

    public Longitude(int degree, int minute, String direction) {
        this.degree = degree;
        this.minute = minute;
        this.direction = direction;
    }

    /**
     * Retrieves the `direction` this Longitude object is
     * "oriented" to
     *
     * @return String representing the `direction` this
     *                Longitude object is "oriented" to
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Builds a String representation of this Longitude object
     *
     * @return String representing this Longitude object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Longitude: [")
        .append("degree=")
        .append(this.getDegree())
        .append(", ")
        .append("minute=")
        .append(this.getMinute())
        .append(", ")
        .append("direction=")
        .append(this.getDirection())
        .append("]");

        return builder.toString();
    }
}