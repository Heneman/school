package com.kyleheneman;

/**
 * @author Kyle Heneman
 * @since 09/03/2015
 *
 * A wrapper object containing Latitude and Longitude objects representing
 * global coordinates, as well as two doubles representing minimum and
 * maximum temperatures for respective coordinates
 */

class WeatherInformation implements WeatherRecord {
    private Latitude latitude;
    private Longitude longitude;
    private double minTemp;
    private double maxTemp;

    public WeatherInformation(Latitude latitude,
                              Longitude longitude,
                              double minTemperature,
                              double maxTemperature) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.minTemp = minTemperature;
        this.maxTemp = maxTemperature;
    }

    /**
     * Overrides the WeatherRecord interface's specs
     *
     * @param minTemperature the new minimum temperature
     */
    @Override
    public void setMinTemp(double minTemperature){
        this.minTemp = minTemperature;
    }

    /**
     * Overrides the WeatherRecord interface's specs
     *
     * @param maxTemperature the new maximum temperature
     */
    @Override
    public void setMaxTemp(double maxTemperature){
        this.maxTemp = maxTemperature;
    }

    /**
     * Retrieves the value of this WeatherInformation's minTemp
     *
     * @return int representing the minimum temperature recorded
     *             at this WeatherInformation object's coordinates
     */
    public double getMinTemp() {
        return this.minTemp;
    }

    /**
     * Retrieves the value of this WeatherInformation's maxTemp
     *
     * @return int representing the maximum temperature recorded
     *             at this WeatherInformation object's coordinates
     */
    public double getMaxTemp() {
        return this.maxTemp;
    }

    /**
     * Builds a String representation of this WeatherInformation object
     *
     * @return String representing this WeatherInformation object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("WeatherInformation: [")
               .append("latitude=")
               .append(latitude.toString())
               .append(", ")
               .append("longitude=")
               .append(longitude.toString())
               .append(", ")
               .append("maxTemp=")
               .append(this.getMaxTemp())
               .append(", ")
               .append("minTemp=")
               .append(this.getMinTemp())
               .append("]\n");

        return builder.toString();
    }
}