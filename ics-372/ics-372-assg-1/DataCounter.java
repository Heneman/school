package com.kyleheneman;

/**
 * @author Kyle Heneman
 * @since 09/03/2015
 *
 * A simple wrapper object that holds the quantities of various values
 * used in WeatherLogManipulator
 */

class DataCounter{
    // Counters for the amount of "N", "S", "E", "W", and negative temperatures
    private int nCount, sCount, eCount, wCount, negativeTemperatureCount;

    // Initialize all counters to 0
    public DataCounter() {
        nCount = sCount = eCount = wCount = negativeTemperatureCount = 0;
    }

    /**
     * Retruns the quanity of WeatherInformation objects containing
     * a `direction` value of "N"
     *
     * @return int representing quantity of WeatherInformation objects
     *             containing a `direction` value of "N"
     */
    public int getNCount() {
        return nCount;
    }

    /**
     * Increments the counter for WeatherInformation objects containing
     * a `direction` of value "N"
     */
    public void incrementNCount(){
        nCount++;
    }

    /**
     * Retruns the quanity of WeatherInformation objects containing
     * a `direction` value of "S"
     *
     * @return int representing quantity of WeatherInformation objects
     *             containing a `direction` value of "S"
     */
    public int getSCount(){
        return sCount;
    }

    /**
     * Increments the counter for WeatherInformation objects containing
     * a `direction` of value "S"
     */
    public void incrementSCount(){
        sCount++;
    }

    /**
     * Retruns the quanity of WeatherInformation objects containing
     * a `direction` value of "E"
     *
     * @return int representing quantity of WeatherInformation objects
     *             containing a `direction` value of "E"
     */
    public int getECount(){
        return eCount;
    }

    /**
     * Increments the counter for WeatherInformation objects containing
     * a `direction` of value "E"
     */
    public void incrementECount(){
        eCount++;
    }

    /**
     * Retruns the quanity of WeatherInformation objects containing
     * a `direction` value of "W"
     *
     * @return int representing quantity of WeatherInformation objects
     *             containing a `direction` value of "W"
     */
    public int getWCount(){
        return wCount;
    }

    /**
     * Increments the counter for WeatherInformation objects containing
     * a `direction` of value "W"
     */
    public void incrementWCount(){
        wCount++;
    }

    /**
     * Increments the counter for WeatherInformation objects containing
     * a negative `minTemperature` value
     */
    public void incrementNegativeTemperatureCount() {
        negativeTemperatureCount++;
    }

    /**
     * Retrieves the quantity of WeatherInformation objects containing a
     * negative `minTemperature` value
     */
    public int getNegativeTemperatureCount() {
        return negativeTemperatureCount;
    }
}