import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * GUI for user to interact with that handles user input
 *
 * @author Kyle Heneman
 * @since 11/12/2015
 */
public class GUI extends RefrigeratorDisplay implements ActionListener {
    // JFrame to contain the GUI
	private static SimpleDisplay frame;

    // Constructor for GUI initializes the JFrame as well as the
    // RefrigeratorContext
	GUI() {
        // Instantiate a SimpleDisplay
		frame = new SimpleDisplay();

        // Initialize the RefrigeratorContext
		initialize();
	}

	/**
	 * JFrame containing buttons for user to interact with and text to
     * display compartment statuses
	 */
	private class SimpleDisplay extends JFrame {
		// Freezer compartment buttons, fields and labels
		private JLabel desiredFreezerTemp;
		private JTextField desiredFreezerTempTxt;
		private JButton setDesiredFreezerTemp;
		private JButton openFreezerDoor;
		private JButton closeFreezerDoor;
		private JLabel freezerLightOnOff;
		private JLabel freezerTemp;
		private JLabel freezerCoolingCurrentIdle;
		private JLabel freezerDoorStatus;

		// Fridge compartment buttons, fields and labels
		private JLabel desiredFridgeTemp;
		private JTextField desiredFridgeTempTxt;
		private JButton setDesiredFridgeTemp;
		private JButton openFridgeDoor;
		private JButton closeFridgeDoor;
		private JLabel fridgeLightOnOff;
		private JLabel fridgeTemp;
		private JLabel fridgeCoolingCurrentIdle;
		private JLabel fridgeDoorStatus;

		// Room button and field, status labels
		private JLabel roomTemp;
		private JTextField roomTempTxt;
		private JPanel contentPane;
		private JLabel status;
		private JButton setRoomTemp;
		private ArrayList<Integer> configArray = new ArrayList<Integer>();


		/**
		 * Builds GUI components and adds the to SimpleDisplay, as well as
         * adds MouseClicked listeners to buttons
		 */
		SimpleDisplay() {
            // Set title of window
			super("Refrigerator");

            // Gets configuration values from chosen file
			configArray = ReadFile.getConfig();

            // Prints those values to console for reference
			System.out.print(configArray);

            // New JPanel to hold GUI components
			contentPane = new JPanel();

            // Set content to the JPanel above
			setContentPane(contentPane);

            // Null layout
			contentPane.setLayout(null);

			// Default font for the user interface
			Font defaultFont = new Font("Arial", Font.PLAIN, 11);

            // Builds room temperature label and adds to content pane
			roomTemp = new JLabel("Room Temp: ");
			roomTemp.setFont(defaultFont);
			roomTemp.setHorizontalAlignment(SwingConstants.RIGHT);
			roomTemp.setBounds(10, 38, 170, 14);
			contentPane.add(roomTemp);

            // Builds target freezer temperature label and adds to content pane
			desiredFreezerTemp = new JLabel("Desired Freezer Temperature: ");
			desiredFreezerTemp.setFont(defaultFont);
			desiredFreezerTemp.setHorizontalAlignment(SwingConstants.RIGHT);
			desiredFreezerTemp.setBounds(11, 43, 170, 54);
			contentPane.add(desiredFreezerTemp);

            // Builds target fridge temperature label and adds to content pane
			desiredFridgeTemp = new JLabel("Desired Fridge Temperature: ");
			desiredFridgeTemp.setFont(defaultFont);
			desiredFridgeTemp.setHorizontalAlignment(SwingConstants.RIGHT);
			desiredFridgeTemp.setBounds(10, 48, 170, 94);
			contentPane.add(desiredFridgeTemp);

            // Builds room temperature input field and adds to content pane
			roomTempTxt = new JTextField();
			roomTempTxt.setFont(defaultFont);
			roomTempTxt.setBounds(185, 33, 220, 20);
			contentPane.add(roomTempTxt);
			roomTempTxt.setColumns(25);

            // Builds target freezer temperature input field and adds to
            // content pane
			desiredFreezerTempTxt = new JTextField();
			desiredFreezerTempTxt.setFont(defaultFont);
			desiredFreezerTempTxt.setBounds(185, 59, 220, 20);
			contentPane.add(desiredFreezerTempTxt);
			desiredFreezerTempTxt.setColumns(25);

            // Builds target fridge temperature input field and adds to
            // content pane
			desiredFridgeTempTxt = new JTextField();
			desiredFridgeTempTxt.setFont(defaultFont);
			desiredFridgeTempTxt.setBounds(185, 85, 220, 20);
			contentPane.add(desiredFridgeTempTxt);
			desiredFridgeTempTxt.setColumns(25);

            // Builds room temperature button and adds to content pane
			setRoomTemp = new JButton("Set Room Temp");
			setRoomTemp.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					RefrigeratorContext refrigerator = RefrigeratorContext.instance();
					int intRoomTemp = Integer.parseInt(roomTempTxt.getText());
					if (refrigerator.getMinRoomTemp() <= intRoomTemp && intRoomTemp <= refrigerator.getMaxRoomTemp()) {
						refrigerator.setRoomTemp(intRoomTemp);
					}

				}

			});
			setRoomTemp.setFont(defaultFont);
			setRoomTemp.setBounds(430, 33, 120, 20);
			contentPane.add(setRoomTemp);

            // Builds target freezer temperature button and adds to
            // content pane
			setDesiredFreezerTemp = new JButton("Set Freezer Temp");
            setDesiredFreezerTemp.setFont(defaultFont);
            setDesiredFreezerTemp.setBounds(430, 59, 120, 20);

            // Sets MouseListener action for freezer temperature button
            setDesiredFreezerTemp.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
                    // Get refrigerator instance
					RefrigeratorContext refrigerator = RefrigeratorContext.instance();

                    // Parse target freezer temperature from input field
					int intDesiredFreezerTemp = Integer.parseInt(desiredFreezerTempTxt.getText());

                    // If entered temperature is within range
					if (refrigerator.getMinFreezerTemp() >= intDesiredFreezerTemp &&
                        intDesiredFreezerTemp <= refrigerator.getMaxFreezerTemp()) {

                        // Set the target temperature to input value
						refrigerator.setDesiredFreezerTemp(intDesiredFreezerTemp);
					}
				}
			});
			contentPane.add(setDesiredFreezerTemp);

            // Builds target fridge temperature button and adds to
            // content pane
            setDesiredFridgeTemp = new JButton("Set Fridge Temp");
            setDesiredFridgeTemp.setFont(defaultFont);
            setDesiredFridgeTemp.setBounds(430, 85, 120, 20);

            // Sets MouseListener action for fridge temperature button
            setDesiredFridgeTemp.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
                    // Get refrigerator instance
					RefrigeratorContext refrigerator = RefrigeratorContext.instance();

                    // Parse target fridge temperature from input field
					int intDesiredFridgeTemp = Integer.parseInt(desiredFridgeTempTxt.getText());

                    // If entered temperature is within range
					if (refrigerator.getMinFridgeTemp() <= intDesiredFridgeTemp &&
                        intDesiredFridgeTemp <= refrigerator.getMaxFridgeTemp()) {

                        // Set the target temperature to input value
						refrigerator.setDesiredFridgeTemp(intDesiredFridgeTemp);
					}
				}
			});
			contentPane.add(setDesiredFridgeTemp);

            // Builds button to open fridge door, sets ActionListener to this
            // GUI, and adds to content panel
			openFridgeDoor = new JButton("Open Fridge Door");
			openFridgeDoor.setFont(defaultFont);
			openFridgeDoor.setBounds(150, 135, 120, 20);
			contentPane.add(openFridgeDoor);
			openFridgeDoor.addActionListener(GUI.this);

            // Builds button to close fridge door, sets ActionListener to
            // this GUI, and adds to content panel
			closeFridgeDoor = new JButton("Close Fridge Door");
			closeFridgeDoor.setFont(defaultFont);
			closeFridgeDoor.setBounds(320, 135, 120, 20);
			contentPane.add(closeFridgeDoor);
			closeFridgeDoor.addActionListener(GUI.this);

            // Builds button to open freezer door, sets ActionListener to
            // this GUI and adds to content panel
			openFreezerDoor = new JButton("Open Freezer Door");
			openFreezerDoor.setFont(defaultFont);
			openFreezerDoor.setBounds(150, 165, 120, 20);
			contentPane.add(openFreezerDoor);
			openFreezerDoor.addActionListener(GUI.this);

            // Builds button to close freezer door, sets ActionListener to
            // this GUI, and adds to content panel
			closeFreezerDoor = new JButton("Close Freezer Door");
			closeFreezerDoor.setFont(defaultFont);
			closeFreezerDoor.setBounds(320, 165, 120, 20);
			contentPane.add(closeFreezerDoor);
			closeFreezerDoor.addActionListener(GUI.this);

            // Builds status label and adds to content panel
			status = new JLabel("Status");
			status.setFont(defaultFont);
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(230, 200, 120, 20);
			contentPane.add(status);

            // Builds fridge light off status label and adds to content panel
			fridgeLightOnOff = new JLabel("Fridge Light Off");
			fridgeLightOnOff.setFont(defaultFont);
			fridgeLightOnOff.setHorizontalAlignment(SwingConstants.CENTER);
			fridgeLightOnOff.setBounds(150, 230, 120, 20);
			contentPane.add(fridgeLightOnOff);

            // Builds fridge light on status label and adds to content panel
			freezerLightOnOff = new JLabel("Freezer Light Off");
			freezerLightOnOff.setFont(defaultFont);
			freezerLightOnOff.setHorizontalAlignment(SwingConstants.CENTER);
			freezerLightOnOff.setBounds(310, 230, 120, 20);
			contentPane.add(freezerLightOnOff);

            // Builds fridge temperature status label and adds to content panel
			fridgeTemp = new JLabel("Fridge Temp");
			fridgeTemp.setFont(defaultFont);
			fridgeTemp.setHorizontalAlignment(SwingConstants.CENTER);
			fridgeTemp.setBounds(150, 260, 120, 20);
			contentPane.add(fridgeTemp);

            // Builds freezer temperature status label and adds to content panel
			freezerTemp = new JLabel("Freezer Temp");
			freezerTemp.setFont(defaultFont);
			freezerTemp.setHorizontalAlignment(SwingConstants.CENTER);
			freezerTemp.setBounds(310, 260, 120, 20);
			contentPane.add(freezerTemp);

            // Builds fridge compartment status label and adds to content panel
			fridgeCoolingCurrentIdle = new JLabel("Fridge Idle");
			fridgeCoolingCurrentIdle.setFont(defaultFont);
			fridgeCoolingCurrentIdle.setHorizontalAlignment(SwingConstants.CENTER);
			fridgeCoolingCurrentIdle.setBounds(150, 290, 120, 20);
			contentPane.add(fridgeCoolingCurrentIdle);

            // Builds freezer compartment status label and adds to content panel
			freezerCoolingCurrentIdle = new JLabel("Freezer Idle");
			freezerCoolingCurrentIdle.setFont(defaultFont);
			freezerCoolingCurrentIdle.setHorizontalAlignment(SwingConstants.CENTER);
			freezerCoolingCurrentIdle.setBounds(310, 290, 120, 20);
			contentPane.add(freezerCoolingCurrentIdle);

            // Builds freezer door closed status label and adds to content panel
			freezerDoorStatus = new JLabel("Freezer Door Closed");
			freezerDoorStatus.setFont(defaultFont);
			freezerDoorStatus.setHorizontalAlignment(SwingConstants.CENTER);
			freezerDoorStatus.setBounds(310, 320, 120, 20);
			contentPane.add(freezerDoorStatus);

            // Builds freezer door open status label and adds to content panel
			fridgeDoorStatus = new JLabel("Fridge Door Closed");
			fridgeDoorStatus.setFont(defaultFont);
			fridgeDoorStatus.setHorizontalAlignment(SwingConstants.CENTER);
			fridgeDoorStatus.setBounds(150, 320, 120, 20);
			contentPane.add(fridgeDoorStatus);

            // Configures this SimpleDisplay
			setResizable(true);
			setBounds(100, 100, 600, 600);
			pack();
			setTitle("Refrigerator");
			setSize(610, 450);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);

            Clock clock = Clock.instance();
            clock.run();
		}
	}

	/**
	 * Changes the state in accordance with the event initiated by the user.
     *
	 * @param event Action event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
        // Handle opening fridge door
		if (event.getSource().equals(frame.openFridgeDoor)) {
			RefrigeratorContext.instance().processEventFridge(RefrigeratorContext.FridgeStates.FRIDGE_DOOR_OPENED_EVENT);
		}
        // Handle opening freezer door
        else if (event.getSource().equals(frame.openFreezerDoor)) {
			RefrigeratorContext.instance().processEventFreezer(RefrigeratorContext.FreezerStates.FREEZER_DOOR_OPENED_EVENT);
		}
        // Handle closing fridge door
        else if (event.getSource().equals(frame.closeFridgeDoor)) {
			RefrigeratorContext.instance().processEventFridge(RefrigeratorContext.FridgeStates.FRIDGE_DOOR_CLOSED_EVENT);
		}
        // Handle closing freezer door
        else if (event.getSource().equals(frame.closeFreezerDoor)) {
			RefrigeratorContext.instance().processEventFreezer(RefrigeratorContext.FreezerStates.FREEZER_DOOR_CLOSED_EVENT);
		}
	}

	/**
	 * Changes light on text
     *
	 * @param compartment Compartment to change light status of
	 */
	@Override
	public void turnLightOn(String compartment) {
		if (compartment.equals("fridge")) {
			frame.fridgeLightOnOff.setText("Fridge Light On");
		}
		if (compartment.equals("freezer")) {
			frame.freezerLightOnOff.setText("Freezer Light On");
		}
	}

	/**
	 * Changes light off text
     *
	 * @param compartment Compartment to change light status of
	 */
	@Override
	public void turnLightOff(String compartment) {
		if (compartment.equals("fridge")) {
			frame.fridgeLightOnOff.setText("Fridge Light Off");
		}
		if (compartment.equals("freezer")) {
			frame.freezerLightOnOff.setText("Freezer Light Off");
		}
	}

	/**
	 * Changes door closed text
     *
	 * @param compartment Compartment to change door status of
	 */
	@Override
	public void doorClosed(String compartment) {
		if (compartment.equals("fridge")) {
			frame.fridgeDoorStatus.setText("Fridge Door Closed");
		}
		if (compartment.equals("freezer")) {
			frame.freezerDoorStatus.setText("Freezer Door Closed");
		}

	}
	/**
	 * Changes door open text
     *
	 * @param compartment Compartment to change door status of
	 */
	@Override
	public void doorOpened(String compartment) {
		if (compartment.equals("fridge")) {
			frame.fridgeDoorStatus.setText("Fridge Door Opened");
		} else {
			frame.freezerDoorStatus.setText("Freezer Door Opened");
		}

	}

	/**
	 * Changes temp text
     *
	 * @param value Current temperature value
	 * @param compartment Compartment to change temperature value of
	 */
	@Override
	public void tempDisplay(int value, String compartment) {
		if (compartment.equals("fridge")) {
			frame.fridgeTemp.setText("Fridge Temp " + value);
		}
		if (compartment.equals("freezer")) {
			frame.freezerTemp.setText("Freezer Temp " + value);
		}

	}

	/**
	 * Changes state text to idle
     *
	 * @param compartment Compartment to change status of
	 */
	@Override
	public void compartmentIdle(String compartment) {
		if (compartment.equals("fridge")) {
			frame.fridgeCoolingCurrentIdle.setText("Fridge Idle");
		}
		if (compartment.equals("freezer")) {
			frame.freezerCoolingCurrentIdle.setText("Freezer Idle");
		}

	}

	/**
	 * Changes state text to cooling
     *
	 * @param compartment Compartment to change status of
	 */
	@Override
	public void compartmentCooling(String compartment) {
		if (compartment.equals("fridge")) {
			frame.fridgeCoolingCurrentIdle.setText("Fridge Cooling");
		}
		if (compartment.equals("freezer")) {
			frame.freezerCoolingCurrentIdle.setText("Freezer Cooling");
		}
	}
}

