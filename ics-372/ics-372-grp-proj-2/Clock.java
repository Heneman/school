import java.util.Observable;

/**
 * An object that represents the passage of time and alerts its Observers of
 * each second that passes
 *
 * @author Kyle Heneman
 * @since 11/12/2015
 */
public class Clock extends Observable implements Runnable {
    // Singleton instance for this Clock object
	private static Clock instance;

    // Enum event for Thread to send after each passage of specified duration
	public enum Events {
		CLOCK_TICKED_EVENT
	}

    /**
     * Private Constructor to follow Singleton pattern
     */
	 private Clock() {
         // New Thread to mark passage of specified time
         Thread thread = new Thread(this);
         // Start the Thread
         thread.start();
	}

	/**
	 * Returns Singleton instance of this Clock
	 * 
	 * @return Singleton instance of this Clock
	 */
	public static Clock instance() {
        // If an instance of this Clock doesn't exist
		if (instance == null) {
            // Create one
			instance = new Clock();
		}
        // Otherwise return the already instantiated instance of this Clock
		return instance;
	}

    /**
     * Processes passage of time and sends the CLOCK_TICKED_EVENT to this
     * Clock's Observers after specified duration
     */
	@Override
	public void run() {
        // Try to run the Thread
		try {
            // Run forever
			while (true) {
                // Let specified duration pass...
				Thread.sleep(1000);
                // And then notify this Clock's Observers of passage of time
				notifyObservers(Events.CLOCK_TICKED_EVENT);
			}
		} catch (InterruptedException ie) {
            System.out.println("Something went wrong while trying to run " +
                               "Clock's Thread");
		}
	}
}


