import java.util.Observable;

/**
 * Handles compartment statuses
 *
 * @author Kyle Heneman
 * @since 11/12/2015
 */
public abstract class RefrigeratorDisplay extends Observable {
	// Context for Refrigerator and Singleton instance of RefrigeratorDisplay
	protected static RefrigeratorDisplay instance;
	protected static RefrigeratorContext context;

    /**
     * Private constructor for Singleton pattern
     */
	protected RefrigeratorDisplay() {
        // Get the RefrigeratorContext
		context = RefrigeratorContext.instance();
	}
	
	/**
	 * Initialize context and add this as observer
	 */
	public void initialize() {
		instance().addObserver(context);
		context.initialize();
	}

	/**
	 * Sets the light to on for a given component (fridge/freezer)
     *
	 * @param compartment Fridge or freezer to turn on light
	 */
	public abstract void turnLightOn(String compartment);

	/**
	 * Sets the light to off for a given component (fridge/freezer)
     *
	 * @param compartment Fridge or freezer to turn off light
	 */
	public abstract void turnLightOff(String compartment);

	/**
	 * Sets the door to closed for a given component (fridge/freezer)
     *
	 * @param compartment Fridge or freezer to close door of
	 */
	public abstract void doorClosed(String compartment);

	/**
	 * Sets the door to open for a given component (fridge/freezer)
     *
	 * @param compartment Fridge or freezer to open door of
	 */
	public abstract void doorOpened(String compartment);

	/**
	 * Sets the temp display for a given component (fridge/freezer)
     *
	 * @param compartment Fridge or freezer to display temperature of
	 */
	public abstract void tempDisplay(int value, String compartment);

	/**
	 * Sets the status to idle for a given component (fridge/freezer)
     *
	 * @param compartment Fridge or freezer to display compartment activity of
	 */
	public abstract void compartmentIdle(String compartment);

	/**
	 * Sets the status to cooling for a given component (fridge/freezer)
     *
	 * @param compartment Fridge or freezer to display compartment activity of
	 */
	public abstract void compartmentCooling(String compartment);

	/**
	 * Returns a Singleton instance of RefrigeratorDisplay
     *
	 * @return a Singleton instance of RefrigeratorDisplay
	 */
	public  static RefrigeratorDisplay instance() {
		return instance;
	}
}