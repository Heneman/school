import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A context for a Refrigerator to interact with
 *
 * @author Kyle Heneman
 * @since 11/12/2015
 */
public class RefrigeratorContext implements Observer {
	// Fridge states
	public enum FridgeStates {
		FRIDGE_DOOR_OPENED_EVENT, FRIDGE_DOOR_CLOSED_EVENT
	}

	//Freezer States
	public enum FreezerStates {
		FREEZER_DOOR_OPENED_EVENT, FREEZER_DOOR_CLOSED_EVENT
	}

    // Configuration value holders
	private static RefrigeratorDisplay refrigeratorDisplay =
        RefrigeratorDisplay.instance();

	private int timeRemainingFridge;
	private int timeRemainingFreezer;
	private int currentTempFridge;
	private int currentTempFreezer;

	private RefrigeratorState currentFridgeState;
	private RefrigeratorState currentFreezeState;
	private int roomTemp;
	private int desiredFridgeTemp;
	private int desiredFreezerTemp;
	private int timeToCoolFridge;
	private int timeToCoolFreezer;
	private int timeToWarmOpenFridge;
	private int timeToWarmClosedFridge;
	private int timeToWarmOpenFreezer;
	private int timeToWarmClosedFreezer;
	private int timerFridgeCold = 0;
	private int timerFridgeOpenWarm = 0;
	private int timerFreezerCold = 0;
	private int timerFreezerOpenWarm = 0;
	private int timerFreezerClosedWarm = 0;
	private int timerFridgeClosedWarm = 0;
	private int minFridgeTemp;
	private int maxFridgeTemp;
	private int minFreezerTemp;
	private int maxFreezerTemp;
	private int minRoomTemp;
	private int maxRoomTemp;
	private int fridgeRange;
	private int freezerRange;

    // Singleton instance of this RefrigeratorContext
    private static RefrigeratorContext instance;

    /**
     * Constructs new RefrigeratorContext
     */
	private RefrigeratorContext() {
        // Get configuration values
        ArrayList<Integer> configArray = ReadFile.getConfig();

        // Set configuration values
		roomTemp = configArray.get(4);
		currentTempFridge = roomTemp;
		currentTempFreezer = roomTemp;
		minFridgeTemp = configArray.get(0);
		maxFridgeTemp = configArray.get(1);
		minFreezerTemp = configArray.get(2);
		maxFreezerTemp = configArray.get(3);
		minRoomTemp = configArray.get(4);
		maxRoomTemp = configArray.get(5);

		desiredFridgeTemp = configArray.get(0);
		System.out.println("desired fridge temp" + desiredFridgeTemp);
		desiredFreezerTemp = configArray.get(2);

		timeToWarmClosedFridge = configArray.get(6);
		timeToWarmOpenFridge = configArray.get(7);
		timeToWarmClosedFreezer = configArray.get(8);
		timeToWarmOpenFreezer = configArray.get(9);
		fridgeRange = configArray.get(10);
		freezerRange = configArray.get(11);
		timeToCoolFridge = configArray.get(12);
		timeToCoolFreezer = configArray.get(13);
	}

	/**
	 * Returns the Singleton instance of RefrigeratorContext
	 * 
	 * @return the Singleton instance of RefrigeratorContext
	 */
	public static RefrigeratorContext instance() {
        // If an instance of Refrigerator Context does not yet exist
		if (instance == null) {
            // Create a new one
			instance = new RefrigeratorContext();
		}

        // Return instance of RefrigeratorContext
		return instance;
	}

	/**
	 * Initializes fridge and freezer states and adds this instance as an
     * observer of Clock
	 */
	public void initialize() {
        // Set current fridge state to cooling
		instance.changeCurrentStateFridge(CoolingState.instance());

        // Set current freezer state to cooling
		instance.changeCurrentStateFreezer(CoolingState.instance());

        // Add this context as an observer of Clock
		Clock.instance().addObserver(instance);
	}

	/**
	 * Handles Clock events
	 * 
	 * @param observable The Clock object driving the simulation
	 *
	 * @param arg Clock tick event for fridge
	 */
	@Override
	public void update(Observable observable, Object arg) {
        // Handle Clock event for fridge and freezer
		currentFridgeState.handleFridge(arg);
		currentFreezeState.handleFreezer(arg);
	}

	/**
	 * Handle state change events for fridge
	 * 
	 * @param arg State change event
	 */
	public void processEventFridge(Object arg) {
		currentFridgeState.handleFridge(arg);
	}
	
	/**
	 * Handle state change event for freezer
	 * 
	 * @param arg State change event for freezer
	 */
	
	public void processEventFreezer(Object arg) {
		currentFreezeState.handleFreezer(arg);
	}
	
	/**
	 * Changes current state of fridge
	 * 
	 * @param nextState The next state for fridge to enter
	 */
	public void changeCurrentStateFridge(RefrigeratorState nextState) {  
		currentFridgeState = nextState;
		nextState.runFridge();
	}
	
	/**
	 * Changes current state of freezer
	 * 
	 * @param nextState The next state for freezer to enter
	 */
	public void changeCurrentStateFreezer(RefrigeratorState nextState) {  
		currentFreezeState = nextState;
		nextState.runFreezer();
	}

	/**
	 * Gets the display
	 * 
	 * @return the display
	 */
	public RefrigeratorDisplay getDisplay() {
		return refrigeratorDisplay;
	}
	
	/**
	 * Returns the current temperature of the fridge
     *
	 * @return the current temperature of the fridge
	 */
	public int getTempFridge() {
		return currentTempFridge;
	}
	
	/**
	 * Returns the current temperature of the freezer
     *
	 * @return the current temperature of the freezer
	 */
	public int getTempFreezer() {
		return currentTempFreezer;
	}
	
	/**
	 * Sets the new Fridge temperature
     *
	 * @param newTemp New temperature for fridge
	 */
	public void setTempFridge(int newTemp) {
		this.currentTempFridge = newTemp;
	}
	
	/**
	 * Sets the new Freezer temperature
     *
	 * @param newTemp New temperature for freezer
	 */
	public void setTempFreezer(int newTemp) {
		this.currentTempFreezer = newTemp;
	}
	
	/**
	 * Gets the current set room temperature
	 * @return The current room temperature
	 */
	public int getMinRoomTemp() {
		return minRoomTemp;
	}
	
	/**
	 * Gets the max allowed room temperature setting
     *
	 * @return Max allowed temperature for room
	 */
	public int getMaxRoomTemp() {
		return maxRoomTemp;
	}
	
	/**
	 * Sets the room temperature to a new temperature setting
     *
	 * @param roomTemp the new temp used to update the room temp setting
	 */
	public void setRoomTemp(int roomTemp) {
		this.roomTemp = roomTemp;
	}
	
	/**
	 * Gets the current minimum setting for freezer temp
     *
	 * @return The min freezer temp value
	 */
	public int getMinFreezerTemp() {
		return minFreezerTemp;
	}
	
	/**
	 * Gets the current maximum setting for freezer temp
     *
	 * @return returns the max freezer temp value
	 */
	public int getMaxFreezerTemp() {
		return maxFreezerTemp;
	}
	
	/**
	 * Sets the preferred Freezer temperature
     *
	 * @param desiredFreezerTemp New temperature for freezer
	 */
	public void setDesiredFreezerTemp(int desiredFreezerTemp) {
		this.desiredFreezerTemp = desiredFreezerTemp;
	}
	
	/**
	 * Gets the current minimum setting for fridge temp
     *
	 * @return Min fridge temp value
	 */
	public int getMinFridgeTemp() {
		return minFridgeTemp;
	}
	
	/**
	 * Gets the current maximum setting for fridge temp
     *
	 * @return Max fridge temp value
	 */
	public int getMaxFridgeTemp() {
		return maxFridgeTemp;
	}

	/**
	 * Sets the preferred Fridge temp
     *
	 * @param desiredFridgeTemp New Fridge temp
	 */
	public void setDesiredFridgeTemp(int desiredFridgeTemp) {
		this.desiredFridgeTemp = desiredFridgeTemp;
	}
	
	/**
	 * Returns the amount of time is required to cool the fridge
     *
	 * @return The time to cool the Fridge
	 */
	public int getTimeToCoolFridge() {
		return timeToCoolFridge;
	}
	
	/**
	 * Returns the amount of time is required to cool the freezer
     *
	 * @return The time to cool the Freezer
	 */
	public int getTimeToCoolFreezer(){
		return timeToCoolFreezer;
	}
	
	/**
	 * Gets the Desired temperature for Fridge
     *
	 * @return Value of desired fridge temp
	 */
	public int getDesiredFridgeTemp(){
		return desiredFridgeTemp;
	}

	/**
	 * Gets the Desired temperature for Freezer
     *
	 * @return Value of desired freezer temp
	 */
	public int getDesiredFreezerTemp() {
		return desiredFreezerTemp;
	}
	
	/**
	 * Returns the current room temp
     *
	 * @return The current room temp in int value
	 */
	public int getRoomTemp(){
		return roomTemp;
	}
	
	/**
	 * Returns the time it takes for the Fridge to warm up when the door is open
     *
	 * @return The time it takes for the fridge temp to rise one degree
	 */
	public int getTimeToWarmOpenFridge() {
		return timeToWarmOpenFridge;
	}
	
	/**
	 * Returns the time it takes for the Fridge to warm up when the door is
     * closed
     *
	 * @return The time it takes for the fridge temp to rise one degree
	 */
	public int getTimeToWarmClosedFridge() {
		return timeToWarmClosedFridge;
	}
	
	/**
	 * Returns the time it takes for the Freezer to warm up when the door is open
     *
	 * @return The time it takes for the freezer temp to rise one degree
	 */
	public int getTimeToWarmOpenFreezer() {
		return timeToWarmOpenFreezer;
	}
	
	/**
	 * Returns the time it takes for the Freezer to warm up when the door is closed
     *
	 * @return The time it takes for the freezer temp to rise one degree
	 */
	public int getTimeToWarmClosedFreezer() {
		return timeToWarmClosedFreezer;
	}
	
	/**
	 * Gets the temperature range that the fridge can sit at
     *
	 * @return The temperature range for fridge
	 */
	public int getFridgeRange() {
		return fridgeRange;
	}
	
	/**
	 * Gets the temperature range that the freezer can sit at
     *
	 * @return The temperature range for freezer
	 */
	public int getFreezerRange() {
		return freezerRange;
	}
}