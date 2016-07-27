/**
 * Uses java.Math.random() to produce a random boolean
 *
 * @author Kyle Heneman
 * @since 09/27/2015
 */
public abstract class RandomBoolean{
    /**
     * If the double returned from Math.random(),
     * which value ranges from 0.0 - 1.0 (exclusive),
     * is less than 0.5 return true
     *
     * @return true if Math.random() returns a value
     *         less than 0.5
     */
    static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }
}
