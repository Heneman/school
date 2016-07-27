package com.kyleheneman;

/**
 * @author Kyle Heneman
 * @since 09/03/2015
 *
 * Manipulates Lists of WeatherInformation objects.  Methods were made
 * static because no information of the WeatherInformation objects
 * is stored in here
 */

import java.util.List;

abstract class WeatherLogManipulator{
    // Limit to latitudinal coordinate values
    private static final int LATITUDE_MAX = 90;
    // Limit to longitudinal coordinate values
    private static final int LONGITUDE_MAX = 180;
    // Limit to coordinate minute values
    private static final int MINUTE_MAX = 60;
    // Limit to List length
    private static final int LIST_LENGTH = 10;
    // Just a constant 0 value
    private static final int ZERO = 0;

    // Limit for minimum temperature values
    private static final double TEMP_MIN = -130;
    // Limit for maximum temperature values
    private static final double TEMP_MAX = 130;

    // Counter wrapper object
    private static final DataCounter TALLIER = new DataCounter();

    /**
     * Populates a WeatherInformation objects with data and adds
     * said objects to a List
     *
     * @param logs Empty List for WeatherInformation objects
     * @return List containing WeatherInformation objects
     */
    public static List<WeatherInformation> populate(List<WeatherInformation>
                                                      logs){
        // Start at 0
        int counter = ZERO;

        // While the List contains fewer than LIST_LENGTH objects...
        while(counter < LIST_LENGTH){
            // Get a valid Latitude object
            Latitude latitude = getValidLatitude();
            // Get a valid Longitude object
            Longitude longitude = getValidLongitude();
            // Get a valid maximum temperature value
            double maxTemperature = getValidMaxTemperature();
            // Get a valid mimimum temperature value
            double minTemperature = getValidMinTemperature(maxTemperature);

            // Create new WeatherInformation object using valid values
            WeatherInformation log = new WeatherInformation(latitude,
                                                            longitude,
                                                            minTemperature,
                                                            maxTemperature);
            // Add the new WeatherInformation object to the List
            logs.add(log);
            // Increment the counter
            counter++;
        }

        // Return the List of WeatherInformation objects once it's full
        return logs;
    }

    /**
     * Pseudo-randomly gets an index to change the minimum and maximum
     * temperatures of a WeatherInformation object from the List
     *
     * @param logs List containing WeatherInformation objects
     */
    public static void changeTemperatures(List<WeatherInformation> logs) {
        // Gets pseudo-random int between 0 and 10
        int index = NumberGenerator.getRandomInt(ZERO, LIST_LENGTH);
        // Gets WeatherInformation object at index `index`
        WeatherInformation log = logs.get(index);

        // Notifications
        System.out.println("Changing temperatures of:");
        System.out.println(log.toString());

        // Gets new valid minimum and valid maximum temperature values
        double maxTemperature = getValidMaxTemperature();
        double minTemperature = getValidMinTemperature(maxTemperature);

        // Sets new temperature values of WeatherInformation object
        log.setMaxTemp(maxTemperature);
        log.setMinTemp(minTemperature);

        // Notifications
        System.out.println("New temperatures:");
        System.out.println(log.toString());
    }

    /**
     * Gets a valid minimum temperature value not greater than its
     * corresponding maximum value, and a non-negative value if half
     * of the List contains negative valued minimum temperatures
     *
     * @param maxTemperature Value that `minTemperature` must be less than
     * @return double representing new `minTemperature` value
     */
    private static double getValidMinTemperature(double maxTemperature) {
        // Termination flag for `while` loop
        boolean invalid = true;
        // New value for `minTemperature`
        double minTemperature = getTemperature();

        /*
         * While `minTmperature` is negative and 5 or more negative
         * values already exist in the List, or while `minTemperature`
         * is greater than `maxTemperature`
         */
        while(invalid) {
            if((minTemperature < 0 &&
                TALLIER.getNegativeTemperatureCount() >= 5) ||
                (minTemperature > maxTemperature)) {
                // Get new value for `minTemperature`
                minTemperature = getTemperature();
            }
            // Otherwise new `minTmperature` value is valid
            else {
                invalid = false;
            }
        }

        /*
         * If `minTemperature` value is negative, increment
         * the negative temperature counter
         */
        if(minTemperature < 0) {
            TALLIER.incrementNegativeTemperatureCount();
        }

        // Return new `minTemperature` value
        return minTemperature;
    }

    /**
     * Returns a valid maximum temperature
     *
     * @return double representing a valid maximum temperature
     */
    private static double getValidMaxTemperature() {
        // Termination flag for `while` loop
        boolean invalid = true;
        // New value for `maxTemperature`
        double maxTemperature = getTemperature();

        /*
         * While `maxTemperature` is less than 0,
         * get a new value for `maxTemperature`.
         *
         * Keeping this value above 0 greatly eased
         * the burden of keeping `minTemperature`s to
         * a ratio of 1/2 that are below 0, plus there
         * were no specifications agains defining the
         * domain of `maxTemperatures` in this way
         */

        while(invalid) {
            if(maxTemperature < ZERO) {
                maxTemperature = getTemperature();
            }
            else {
                invalid = false;
            }
        }
        // Return new valid value for `maxTemperature`
        return maxTemperature;
    }

    /**
     * Retrieves a pseudo-random value for new temperatures
     *
     * @return double representing a temperature value
     */
    private static double getTemperature() {
        return NumberGenerator.getRandomDouble(TEMP_MIN, TEMP_MAX);
    }

    /**
     * Returns a valid Latitude object
     *
     * @return Latitude object that's been validated
     */
    private static Latitude getValidLatitude() {
        // New `degree` value for this Latitude object
        int latitude_degree = NumberGenerator.getRandomInt(ZERO,
                                                           LATITUDE_MAX);
        // New `minute` value for this Latitude object
        int latitude_minute = NumberGenerator.getRandomInt(ZERO,
                                                           MINUTE_MAX);
        // New `direction` value for this Latitude object
        String latitude_direction = getLatitudeDirection();

        // If the `direction` value is "N"...
        if(latitude_direction.equals("N")) {
            /*
             * And if the number of WeatherInformation objects
             * containing a `direction` of "N" is greater than
             * half of the List...
             */
            if(TALLIER.getNCount() >= (LIST_LENGTH/2)) {
                // Switch the value to "S"
                latitude_direction = "S";
                /*
                 * Increment count of WeatherInformaion objects
                 * containing a `direction` with value of "S"
                 */
                TALLIER.incrementSCount();
            }
            /*
             * Otherwise increment the count of WeatherInformation
             * objects containing a `direction` of "N"
             */
            else {
                TALLIER.incrementNCount();
            }
        }
        // Otherwise if `direction` is "S"...
        else {
            /*
             * And the number of WeatherInformation objects in
             * the List containing a `direction of "S" is greater
             * than half the List...
             */
            if(TALLIER.getSCount() >= (LIST_LENGTH/2)) {
                // Switch the value to "N"
                latitude_direction = "N";
                /*
                 * Increment count of WeatherInformation objects
                 * containing a `direction` with value of "N"
                 */
                TALLIER.incrementNCount();
            }
            else {
                /*
                 * Otherwise increment the count of WeatherInformation
                 * objects containing a `direction` of "S"
                 */
                TALLIER.incrementSCount();
            }
        }

        // Return a new Latitude object containing above values
        return new Latitude(latitude_degree,
                            latitude_minute,
                            latitude_direction);
    }

    /**
     * Returns a valid Longitude object
     *
     * @return Longitude object that's been validated
     */
    private static Longitude getValidLongitude() {
        // New `degree` value for this Longitude object
        int longitude_degree = NumberGenerator.getRandomInt(ZERO,
                                                            LONGITUDE_MAX);
        // New `minute` value for this Longitude object
        int longitude_minute = NumberGenerator.getRandomInt(ZERO,
                                                            MINUTE_MAX);
        // New `direction` value for this Longitude object
        String longitude_direction = getLongitudeDirection();

        // If the `direction` value is "E"...
        if(longitude_direction.equals("E")) {
            /*
             * And if the number of WeatherInformation objects
             * containing a `direction` of "E" is greater than
             * half of the List...
             */
            if(TALLIER.getECount() >= (LIST_LENGTH/2)) {
                // Switch the value to "W"
                longitude_direction = "W";
                /*
                 * Increment count of WeatherInformation objects
                 * containing a `direction` with value of "W"
                 */
                TALLIER.incrementWCount();
            }
            /*
             * Otherwise incrememnt the count of WeatherInformation
             * objects containing a `direction` of "E"
             */
            else {
                TALLIER.incrementECount();
            }
        }
        // Otherwise if `direction` is "W"...
        else {
            /*
             * And the number of WeatherInformation objects in
             * the List containing a `direction` of "W" is greater
             * than half of the list...
             */
            if(TALLIER.getWCount() >= (LIST_LENGTH/2)) {
                // Switch the value to "E"
                longitude_direction = "E";
                /*
                 * Increment count of WeatherInformation objects
                 * containing a `direction` with value "E"
                 */
                TALLIER.incrementECount();
            }
            else {
                /*
                 * Otherwise increment the count of WeatherInformation
                 * objects containing a `direction` of "W"
                 */
                TALLIER.incrementWCount();
            }
        }

        // Return a new Longitude object containing above values
        return new Longitude(longitude_degree,
                             longitude_minute,
                             longitude_direction);
    }

    /**
     * Gets value for `direction` of Latitude objects
     *
     * @return String containing value of "N" or "S"
     */
    private static String getLatitudeDirection() {
        // Get pseudo-random int value between 0 and 90
        int randomInt = NumberGenerator.getRandomInt(ZERO, LATITUDE_MAX);
        // If the int is even...
        if(randomInt % 2 == 0) {
            return "N";
        }
        return "S";
    }

    /**
     * Gets value for `direction` of Longitude objects
     *
     * @return String containing value of "E" or "W"
     */
    private static String getLongitudeDirection() {
        // Get pseudo-random int value between 0 and 180
        int randomInt = NumberGenerator.getRandomInt(ZERO, LONGITUDE_MAX);
        // If the int is even...
        if(randomInt % 2 == 0) {
            return "E";
        }
        return "W";
    }
}