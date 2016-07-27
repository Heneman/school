package com.kyleheneman;

/**
 * @author Kyle Heneman
 * @since 09/03/2015
 *
 * Generates pseudo-random ints and doubles within specified ranges
 */

import java.util.Random;

abstract class NumberGenerator {
    private static final Random RANDOM = new Random();

    /**
     * Returns a pseudo-random int within specified range
     *
     * @param min Minimum value for returned int
     * @param max Maximum value for returned int (exclusive)
     * @return int containing a number within the range of `min`-`max`
     */
    public static int getRandomInt(int min, int max) {
        /*
         * Giving `nextInt` the max value of the difference
         * of `max` and `min` will only return ints up to
         * the value of the difference, but then adding the
         * value of `min` to the returned value will bump it
         * back to within specified ranges.  I'm not sure why
         * this is considered the best practice for Java but
         * everything I found on the internet agreed that it is
         */
        int randomInt = RANDOM.nextInt(max - min) + min;

        // Return the new pseudo-random int
        return  randomInt;
    }

    /**
     * Returns a pseudo-random double within specified range
     *
     * @param min Minimum value for returned double
     * @param max Maximum value for returned double (exclusive)
     * @return
     */
    public static double getRandomDouble(double min, double max) {
        /*
         * Shave .01 from the values of `min` and `max` to
         * make sure the specified range endpoints are excluded
         */
        min = min + .01;
        max = max - .01;

        /*
         * `nextDouble` returns a value between 0.0 and 1.0.  Multiplying
         * the returned value by the difference of `max` and `min`, and then
         * adding the value of `min` will keep the value within the specified
         * ranges.  Again, the internet agreed this is the best practice for
         * pseudo-random doubles
         */
        double randomDouble = min + (max - min) * RANDOM.nextDouble();
        return randomDouble;
    }
}