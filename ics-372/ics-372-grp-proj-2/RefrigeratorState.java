/**
 * Parent state for Refrigerator states to inherent from
 */
public abstract class RefrigeratorState {
	    // Context and Display objects for child classes to hold
		protected static RefrigeratorContext context;
		protected static RefrigeratorDisplay display;

		/**
		 * Initialzies the context and display for the Refridgerator
		 */
		protected RefrigeratorState() {
			context = RefrigeratorContext.instance();
			display = context.getDisplay();
		}

		/**
		 * Initializes the state of Fridge
		 */
		public abstract void runFridge();
		
		/**
		 * Initializes the state of Fridge
		 */
		public abstract void runFreezer();

		/**
		 * Handles an event for Fridge
		 * 
		 * @param event Event to be processed
		 */
		public abstract void handleFridge(Object event);
		
		/**
		 * Handles an event for Fridge
		 * 
		 * @param event Event to be processed
		 */
		public abstract void handleFreezer(Object event);
}