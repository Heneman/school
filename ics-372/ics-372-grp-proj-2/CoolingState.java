/**
 * A State for the Refrigerator that handles cooling of the fridge and freezer
 *
 * @author Kyle Heneman
 * @since 11/12/2015
 */
public class CoolingState extends RefrigeratorState {

    // Timers for the fridge and freezer to cool by 1 degree
	private int timerFridgeCold;
	private int timerFreezerCold;

    // Singleton instance of this CoolingState
	private static CoolingState instance;

    /**
     * Private Constructor to follow the Singleton pattern.  Initializes
     * timers to 0
     */
	private CoolingState() {
		timerFridgeCold = 0;
        timerFreezerCold = 0;
	}

	/**
	 * Returns Singleton instance of this CoolingState
	 * 
	 * @return Singleton instance of this CoolingState
	 */
	public static CoolingState instance() {
        // If there isn't an instance of CoolingState already
		if(instance == null) {
            // Create one
            instance = new CoolingState();
        }

        // Return the instance of CoolingState
        return instance;
	}

	/**
     * When the CLOCK_TICK_EVENT is observed, lower the fridge temperature.
     * When the desired temperature is reached, change to an idle state
	 */
	public void processClockTickFridge() {
        // Increment the time for fridge to cool by 1 degree
		timerFridgeCold++;

        // If the timer has reached the amount of time for fridge to cool,
        // cool fridge and restart timer
		if (timerFridgeCold == context.getTimeToCoolFridge()) {
            // Set the new temperature
			context.setTempFridge(context.getTempFridge() - 1);

            // Change the fridge temperature display
			display.tempDisplay(context.getTempFridge(), "fridge");

            // Reset timer
			timerFridgeCold = 0;
		}

        // If the desired fridge temperature is reached, put into an idle
        // state and restart timer
		if (context.getTempFridge() <= context.getDesiredFridgeTemp()) {
            // Set state to idle
			context.changeCurrentStateFridge(DoorClosedState.instance());

            // Change the fridge state display to idle
			display.compartmentIdle("fridge");

            // Restart timer
			timerFridgeCold = 0;
		}
	}

	/**
	 * When the CLOCK_TICK_EVENT is observed, lower the freezer temperature.
     * When the desired temperature is reached, change to an idle state
	 */
	public void processClockTickFreezer() {
        // Increment the time for freezer to cool by 1 degree
		timerFreezerCold++;

        // If the timer has reached the amount of time for freezer to cool,
        // cool freezer and restart timer
		if (timerFreezerCold == context.getTimeToCoolFreezer()) {
            // Set the new freezer temperature
			context.setTempFreezer(context.getTempFreezer() - 1);

            // Change the freezer temperature display
			display.tempDisplay(context.getTempFreezer(), "freezer");

            // Reset the timer
			timerFreezerCold = 0;
		}

        // If the desired freezer temperature is reached, put into idle state
        // and restart the timer
		if (context.getTempFreezer() <= context.getDesiredFreezerTemp()) {
            // Set state to idle
			context.changeCurrentStateFreezer(DoorClosedState.instance());

            // Change the freezer state display to idle
			display.compartmentIdle("freezer");

            // Restart timer
			timerFreezerCold = 0;
		}
	}

	/**
	 * Sets fridge door state to open
	 */
	public void processDoorOpenFridge() {
        // Sets current fridge state to open
		context.changeCurrentStateFridge(DoorOpenedState.instance());
	}

	/**
	 * Sets freezer door state to open
	 */
	public void processDoorOpenFreezer() {
        // Sets current freezer state to open
		context.changeCurrentStateFreezer(DoorOpenedState.instance());
	}

	/**
	 * Changes the display text for fridge.
	 */
	@Override
	public void runFridge() {
        // Sets fridge state to cooling
		display.compartmentCooling("fridge");

        // Sets fridge temperature
		display.tempDisplay(context.getTempFridge(), "fridge");
	}

	/**
	 * Changes the display text for freezer.
	 */
	@Override
	public void runFreezer() {
        // Sets freezer state to cooling
		display.compartmentCooling("freezer");

        // Sets freezer temperature
		display.tempDisplay(context.getTempFreezer(), "freezer");

	}

	/**
	 * Handles Clock and state change events for fridge
     *
	 * @param event Event indicating passage of time or a change of state for
     *              fridge
	 */
	@Override
	public void handleFridge(Object event) {
        // If the event is from Clock
		if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
            // Process the passage of time
			processClockTickFridge();
		}
        // Or if the event is a change of state
        else if(event.equals(RefrigeratorContext.FridgeStates.FRIDGE_DOOR_OPENED_EVENT)) {
			// Process the door opening
            processDoorOpenFridge();
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
        // If the event is from Clock
		if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
            // Process the passage of time
			processClockTickFreezer();
		}
        // Or if the event is a change of state
        else if (event.equals(RefrigeratorContext.FreezerStates.FREEZER_DOOR_OPENED_EVENT)){
            // Process the door opening
			processDoorOpenFreezer();
		}

	}

}