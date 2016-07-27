/**
 * A State for the Refrigerator that handles the door being opened for fridge
 * and freezer
 *
 * @author Kyle Heneman
 * @since 11/12/2015
 */
public class DoorOpenedState extends RefrigeratorState {

    // Timers for the fridge and freezer to warm by 1 degree
	private int timerFridgeOpenWarm;
	private int timerFreezerOpenWarm;

    // Singleton instance of the DoorOpenedState
	private static DoorOpenedState instance;

    /**
     * Private Constructor to follow the Singleton pattern.  Initializes
     * timers to 0
     */
	private DoorOpenedState() {
		timerFridgeOpenWarm = 0;
        timerFreezerOpenWarm = 0;
	}

	/**
	 * Returns Singleton instance of this DoorOpenedState
	 * 
	 * @return Singleton instance of this DoorOpenedState
	 */
	public static DoorOpenedState instance() {
        // If there isn't an instance of DoorOpenedState already
		if(instance == null) {
            // Create one
            instance = new DoorOpenedState();
        }

        // Return the instance of DoorOpenedState
        return instance;
	}

	/**
	 * Sets the fridge state to closed
	 */
	public void processDoorCloseFridge() {
        // Sets current fridge state to closed
		context.changeCurrentStateFridge(DoorClosedState.instance());		
	}

	/**
	 * Sets the freezer state to closed
	 */
	public void processDoorCloseFreezer() {
        // Sets current freezer state to closed
		context.changeCurrentStateFreezer(DoorClosedState.instance());		
	}

	/**
	 * Changes the display texts for fridge
	 */
	@Override
	public void runFridge() {
        // Change fridge light status to on
		display.turnLightOn("fridge");

        // Change fridge compartment status to idle
		display.compartmentIdle("fridge");

        // Change fridge door status to open
		display.doorOpened("fridge");
	}

	/**
	 * Changes the display texts for freezer
	 */
	@Override
	public void runFreezer() {
        // Change freezer light status to on
		display.turnLightOn("freezer");

        // Change freezer compartment status to idle
		display.compartmentIdle("freezer");

        // Change freezer door status to open
		display.doorOpened("freezer");
	}

	/**
	 * Handles Clock and state change events for fridge
	 *
     * @param event Event indicating passage of time or a change of state for
     *              fridge
	 */
	@Override
	public void handleFridge(Object event) {
        // If the event is a change of state
		if (event.equals(RefrigeratorContext.FridgeStates.FRIDGE_DOOR_CLOSED_EVENT)) {
            // Process the door closing
			processDoorCloseFridge();
		}
        // Or if the event is from Clock
        else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)){
            // Process the passage of time
			processClockTickFridge();
		}
	}

	/**
	 * Handles Clock and state change events for freezer
     *
     * @param event Event indicating passage of time or change of state for
     *              freezer
	 */
	@Override
	public void handleFreezer(Object event) {
        // If the event is a change of state
		if (event.equals(RefrigeratorContext.FreezerStates.FREEZER_DOOR_CLOSED_EVENT)) {
            // Process the door closing
			processDoorCloseFreezer();
		}
        // Or if the event is from Clock
        else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
            // Process the passage of time
			processClockTickFreezer();
		}
	}

	/**
	 * When the CLOCK_TICK_EVENT is observed, raise the fridge temperature.
     * When the desired temperature is reached, change to an idle state
	 */
	public void processClockTickFridge() {
        // If the current fridge temperature is less than the room temperature
		if (context.getTempFridge() < context.getRoomTemp()) {

            // Increment the time for fridge to warm by 1 degree
			timerFridgeOpenWarm++;

            // If the timer has reached the amount of time for fridge to
            // warm, warm the fridge and restart the timer
			if (timerFridgeOpenWarm == context.getTimeToWarmOpenFridge()) {
                // Set the new temperature
				context.setTempFridge(context.getTempFridge() + 1);

                // Change the fridge temperature display
				display.tempDisplay(context.getTempFridge(), "fridge");

                // Restart timer
				timerFridgeOpenWarm = 0;
			}
		}
	}

	/**
	 * When the CLOCK_TICK_EVENT is observed, raise the fridge temperature.
     * When the desired temperature is reached, change to an idle state
	 */
	public void processClockTickFreezer() {
        // If the current freezer temperature is lower than the room temperature
		if (context.getTempFreezer() < context.getRoomTemp()) {

            // Increment the time for freezer to warm by 1 degree
			timerFreezerOpenWarm++;

            // If the timer has reached the amount of time for freezer to
            // warm, warm the freezer and restart the timer
			if (timerFreezerOpenWarm == context.getTimeToWarmOpenFreezer()) {
                // Set the new temperature
				context.setTempFreezer(context.getTempFreezer() + 1);

                // Change the fridge temperature display
				display.tempDisplay(context.getTempFreezer(), "freezer");

                // Restart timer
				timerFreezerOpenWarm = 0;
			}
		}
	}
}


