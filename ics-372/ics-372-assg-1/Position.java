package com.kyleheneman;

/**
 * @author Kyle Heneman
 * @since 09/03/2015
 *
 * Simple object representing a global coordinate
 * of degrees and minutes
 */

abstract class Position{
    protected int degree;
    protected int minute;

    /**
     * Returns an int representing the minute scale of Position
     * @return int representing Position minutes
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Returns an int representing the degree scale of Position
     * @return in representing Position degree
     */
    public int getDegree() {
        return degree;
    }
}