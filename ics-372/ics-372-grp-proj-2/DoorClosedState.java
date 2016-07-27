/**
 * A State for the Refrigerator that handles the door being closed for fridge
 * and freezer
 *
 * @author Kyle Heneman
 * @since 11/12/2015
 */
public class DoorClosedState extends RefrigeratorState {

    // Timers for the fridge and freezer to warm by 1 degree
	private int timerFridgeClosedWarm;
	private int timerFreezerClosedWarm;

    // Singleton instance of the DoorClosedState
	private static DoorClosedState instance;

    /**
     * Private Constructor to follow the Singleton pattern.  Initializes
     * timers to 0
     */
	private DoorClosedState() {
		timerFridgeClosedWarm = 0;
        timerFreezerClosedWarm = 0;
	}

	/**
	 * Returns Singleton instance of this DoorClosedState
	 * 
	 * @return Singleton instance of this DoorClosedState
	 */
	public static DoorClosedState instance() {
        // If there isn't an instance of DoorClosedState already
		if(instance == null) {
            // Create one
            instance = new DoorClosedState();
        }

        // Return the instance of DoorClosedState
        return instance;
	}

	/**
	 * Sets fridge state to open
	 */
	public void processDoorOpenFridge() {
        // Sets current fridge state to open
		context.changeCurrentStateFridge(DoorOpenedState.instance());
	}

	/**
	 * Sets freezer state to open
	 */
	public void processDoorOpenFreezer() {
        // Sets current freezer state to open
		context.changeCurrentStateFreezer(DoorOpenedState.instance());
	}

	/**
	 * Changes the display texts for fridge
	 */
	@Override
	public void runFridge() {
        // Change fridge door status to closed
		display.doorClosed("fridge");

        // Change fridge light status to off
		display.turnLightOff("fridge");

        // Change fridge compartment status to idle
		display.compartmentIdle("fridge");
	}

	/**
	 * Changes the display texts for freezer
	 */
	@Override
	public void runFreezer() {
        // Change freezer door status to closed
		display.doorClosed("freezer");

        // Change freezer light status to off
		display.turnLightOff("freezer");

        // Change freezer compartment status to idle
		display.compartmentIdle("freezer");

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
		if (event.equals(RefrigeratorContext.FridgeStates.FRIDGE_DOOR_OPENED_EVENT)) {
            // Process the door opening
			processDoorOpenFridge();
		}
        // Or if the event is from Clock
        else if(event.equals(Clock.Events.CLOCK_TICKED_EVENT)){
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
		if (event.equals(RefrigeratorContext.FreezerStates.FREEZER_DOOR_OPENED_EVENT)) {
            // Process the door opening
            processDoorOpenFreezer();
        }
        // Of it the event is from Clock
        else if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)){
            // Process the passage of time
			processClockTickFreezer();
		}
	}

	/**
	 * When the CLOCK_TICK_EVENT is observed, raise the fridge temperature.
     * When the desired temperature is reached, change to an idle state
	 */
	private void processClockTickFridge() {
        // If the current fridge temperature is lower than the allowed
        // variance of the desired temperature
		if (context.getTempFridge() <= context.getDesiredFridgeTemp() + context.getFridgeRange()) {

            // Increment the time for fridge to warm by 1 degree
			timerFridgeClosedWarm++;

            // If the timer has reached the amount of time for fridge to
            // warm, warm the fridge and restart timer
			if (timerFridgeClosedWarm == context.getTimeToWarmClosedFridge()) {
                // Set the new temperature
				context.setTempFridge(context.getTempFridge() + 1);

                // Change the fridge temperature display
				display.tempDisplay(context.getTempFridge(), "fridge");

                // Restart timer
				timerFridgeClosedWarm = 0;
			}
		}
        // Otherwise change the state to cooling
		else {
			context.changeCurrentStateFridge(CoolingState.instance());
		}
	}

	/**
	 * When the CLOCK_TICK_EVENT is observed, raise the freezer temperature.
     * When the desired temperature is reached, change to an idle state
	 */
	private void processClockTickFreezer() {
        // If the current freezer temperature is lower than the allowed
        // variance of the desired temperature
		if (context.getTempFreezer() <= context.getDesiredFreezerTemp() + context.getFreezerRange()) {

            // Increment the time for freezer to warm by 1 degree
			timerFreezerClosedWarm++;

            // If the timer has reached the amount of time for freezer to
            // warm, warm the freezer and restart timer
			if (timerFreezerClosedWarm == context.getTimeToWarmClosedFreezer()) {
                // Set the new temperature
				context.setTempFreezer(context.getTempFreezer() + 1);

                // Change the freezer temperature display
				display.tempDisplay(context.getTempFreezer(), "freezer");

                // Restart timer
				timerFreezerClosedWarm = 0;
			}
		}
        // Otherwise change the state to cooling
		else {
			context.changeCurrentStateFreezer(CoolingState.instance());
		}
	}
}