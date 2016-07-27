package com.kyleheneman;

/**
 * @author Kyle Heneman
 * @since 09/03/2015
 *
 * A simple `main` function that obtains a List of WeatherInformation
 * and prints the contents to the console
 */

import java.util.ArrayList;
import java.util.List;

class WeatherDriver {
    public static void main(String[] args) {

        // Get an empty List of WeatherInformation objects
        List<WeatherInformation> logs = new ArrayList<WeatherInformation>();

        // Populate the WeatherInformations in the List with data
        logs = WeatherLogManipulator.populate(logs);

        // Print the contents of each WeatherInformation in the List
        for(WeatherInformation log : logs) {
            System.out.print(log.toString());
        }

        // Line break
        System.out.println();

        // Notification
        System.out.println("Changing temperatures of arbitrary log...");

        // Change the temperatures of one WeatherInformation object in the List
        WeatherLogManipulator.changeTemperatures(logs);
    }
}