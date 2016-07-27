import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import static java.awt.Color.*;

/**
 * An extension of JFrame to contain FiguresPanel and
 * the components to interact with it
 *
 * @author Kyle Heneman
 * @since 09/09/2015
 */
public class FigureGUI extends JFrame implements ActionListener {
    /*
     * Enum for specifying which shape
     * each Figure will be
     */
    private enum Shape{RECTANGLE, CIRCLE}

    // Stores currently selected shape
    private Shape shape;
    // Stores currently selected color
    private Color color;

    // Button for selecting the color red
    private final JButton redButton = new JButton("Red");
    // Button for selecting the color green
    private final JButton greenButton = new JButton("Green");
    // Button for selecting the color blue
    private final JButton blueButton = new JButton("Blue");
    // Button for selecting the Rectangle shape
    private final JButton rectangleButton = new JButton("Rectangle");
    // Button for selecting the Circle shape
    private final JButton circleButton = new JButton("Circle");

    // Displays list of currently drawn Figures
    private final JTextArea listArea;
    // Collects MouseEvents and displays Figures
    private final FiguresPanel inputArea = new FiguresPanel();
    // Displays current date
    private JLabel dateLabel;

    /**
     * Builds interface and populates Figure List
     * with Figures stored in 'figures' file, if
     * any exist
     */
    public FigureGUI() {
        // Sets title of window
        super("Figures GUI");

        // New TextArea of size 10 rows x 10 columns
        listArea = new JTextArea(10, 10);
        // Make TextArea not editable
        listArea.setEditable(false);

        // Label to display current date
        dateLabel = new JLabel("", JLabel.CENTER);

        // Panel to hold FiguresPanel
        JPanel inputPanel = new JPanel(new BorderLayout());
        // Panel to hold the date JLabel
        JPanel datePanel = new JPanel(new BorderLayout());
        // Add invisible padded border
        datePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));

        // Add dateLabel at the very beginning of datePanel
        datePanel.add(dateLabel, BorderLayout.LINE_START);
        // Put datePanel at the very bottom of inputPanel
        inputPanel.add(datePanel, BorderLayout.PAGE_END);
        // Put inputPanel in center to fill remaining space
        inputPanel.add(inputArea, BorderLayout.CENTER);

        // Panel for color and shape buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        // Panel for shape list
        JPanel listPanel = new JPanel(new GridLayout(1, 1));

        // Button to exit the application
        JButton exitButton = new JButton("Exit");

        // Add color and shape buttons to buttonPanel
        buttonPanel.add(redButton);
        buttonPanel.add(greenButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(rectangleButton);
        buttonPanel.add(circleButton);
        buttonPanel.add(exitButton);

        // Make listPanel scrollable
        listPanel.add(new JScrollPane(listArea));

        // FigureGUI monitors button actions
        redButton.addActionListener(this);
        greenButton.addActionListener(this);
        blueButton.addActionListener(this);
        rectangleButton.addActionListener(this);
        circleButton.addActionListener(this);
        exitButton.addActionListener(this);

        // inputArea listens for mouse clicks in inputArea
        inputPanel.addMouseListener(inputArea);

        // Set layout for FigureGUI Frame
        this.getContentPane().setLayout(new GridLayout(1, 3));

        // Add input-, button-, and listPanels to FigureGUI
        this.getContentPane().add(inputPanel);
        this.getContentPane().add(buttonPanel);
        this.getContentPane().add(listPanel);

        /*
         * Attempt to populate `FiguresPanel.figures` with
         * previously stored Figures
         */
        populateFigures();
    }

    /**
     * Monitors for button clicks
     *
     * @param event ActionEvent from button press
     */
	@Override
	public void actionPerformed(ActionEvent event) {
        // Get source of ActionEvent (button select)
        Object source = event.getSource();

        /*
         * Sets `color` and `shape` to repective
         * values for each button, otherwise
         * saves any Figures currently displayed
         * into a file and successfully exits the
         * program
         */
        if(source == redButton) {
            color = RED;
        }
        else if(source == greenButton) {
            color = GREEN;
        }
        else if(source == blueButton) {
            color = BLUE;
        }
        else if(source == rectangleButton) {
            shape = Shape.RECTANGLE;
        }
        else if(source == circleButton) {
            shape = Shape.CIRCLE;
        }
        else {
            writeFigures();
            System.exit(0);
        }
	}


    /**
     * Writes any current Figures to a file
     */
    private void writeFigures() {
        // File to store created Figures in
        File file = new File("figures");
        // Output stream to add data to file
        FileOutputStream fileOutput = null;
        /*
         * Output stream to give objects to
         * fileOutput
         */
        ObjectOutputStream objectOutput = null;

        try{
            // Output will go in file named "figures"
            fileOutput = new FileOutputStream("figures");
            // Objects will go through the fileOutput stream
            objectOutput = new ObjectOutputStream(fileOutput);

            // Get the List of Figures from FiguresPanel
            ArrayList<Figure> figures = inputArea.getFigures();

            // Write each Object in List to the file
            for(Figure figure : figures) {
                objectOutput.writeObject(figure);
            }
        }
        catch(IOException e) {
            // Lazily handle exceptions
            e.printStackTrace();
        }
        finally {
            if(objectOutput != null) {
                try{
                    // Try to close output stream
                    objectOutput.close();
                }
                catch(IOException e){
                    // Lazily handle exceptions
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Loads the Figures saved in the file "figures"
     * when application starts, if any exist
     */
    public void populateFigures() {
        // New File object with title "figures"
        File file = new File("figures");
        // New input stream to read from file
        FileInputStream fileInput = null;
        // New input stream to read objects from file
        ObjectInputStream objectInput = null;

        try {
            // Open "figures"
            fileInput = new FileInputStream("figures");
            // Read Objects from "figures"
            objectInput = new ObjectInputStream(fileInput);

            // Figure object to contain Objects read from "figures"
            Figure figure = null;
            // Get List of Figures from FiguresPanel
            ArrayList<Figure> figures = inputArea.getFigures();

            /*
             * Reads each object from "figures", and if it's
             * not null, add it to the `figures` List, then
             * add that Figure's String representation to
             * listPanel
             */
            do {
                figure = (Figure) objectInput.readObject();
                if(figure != null) {
                    figures.add(figure);
                    listArea.append(figure.toString());
                    listArea.append("\n");
                }
            }
            // While there are still figures
            while(figure != null);
        }
        // Catch and lazily handle EOFExceptions
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            /*
             * If the input streams are still open
             * after populating the List of Figures...
             */
            if(objectInput != null) {
                try{
                    // Close it
                    objectInput.close();
                }
                // Lazily catch and handle exceptions
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Panel that holds the mouse input area and handles
     * the logic of creating Figures
     */
    @SuppressWarnings("ConstantConditions")
    public class FiguresPanel extends JPanel implements MouseListener{
        /*
         * Placeholders for initial click coordinates and
         * Figures being built
         */
        private int xPlaceHolder;
        private int yPlaceHolder;
        private Figure figurePlaceHolder;

        // List of completed Figures
        private ArrayList<Figure> figures;

        // Calendar and Formatter for dateLabel
        private GregorianCalendar currentDate = new GregorianCalendar();
        private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        /**
         * Instantiate `figures` upon construction
         */
        public FiguresPanel() {
            figures = new ArrayList<Figure>();
        }

        /**
         * Paints each Figure from `figures` onto FiguresPanel
         *
         * @param g Graphics context from FigureGUI
         */
        @Override
        public void paintComponent(Graphics g) {
            // Be sure to call the super or nothing displays
            super.paintComponent(g);

            // Add the current date to the dateLabel
            dateLabel.setText(dateFormat.format(currentDate.getTime()));

            // Draw each Figure in the List
            for(Figure figure : figures) {
                figure.draw(g);
            }
        }

        /**
         * Waits for the mouse click and creates the appropriate figures.
         */
        @Override
        public void mouseClicked(MouseEvent event) {
            /*
             * If no Figure is currently in the placeholder,
             * it's almost certainly the first click, so we
             * use the x and y coordinates from the event as
             * the Figure's origin
             */
            if(figurePlaceHolder == null){
                xPlaceHolder = event.getX();
                yPlaceHolder = event.getY();

                /*
                 * Adds coordinates and color to the new Figure
                 * as well as assigning it a shape based upon
                 * which shape button was last clicked
                 */
                if(shape == Shape.CIRCLE){
                    figurePlaceHolder = new Circle();
                    ((Circle) figurePlaceHolder).setX(xPlaceHolder);
                    ((Circle) figurePlaceHolder).setY(yPlaceHolder);
                    ((Circle) figurePlaceHolder).setColor(color);
                }
                else{
                    figurePlaceHolder = new Rectangle();
                    ((Rectangle) figurePlaceHolder).setX(xPlaceHolder);
                    ((Rectangle) figurePlaceHolder).setY(yPlaceHolder);
                    ((Rectangle) figurePlaceHolder).setColor(color);
                }
            }
            /*
             * Not sure how to handle creating the origin for one shape
             * and assigning the second click to a different shape with
             * the original data, so I'm not allowing it
             */
            else if((figurePlaceHolder instanceof Circle &&
                     shape == Shape.RECTANGLE) ||
                    (figurePlaceHolder instanceof Rectangle &&
                     shape == Shape.CIRCLE)) {
                System.out.println("Can't switch shapes in between clicks!");
                System.out.println("Clearing clicks...");

                clearPlaceHolders();
            }
            /*
             * Otherwise it's presumably the second click
             */
            else {
                // If the current Figure is a Circle
                if(figurePlaceHolder instanceof Circle) {
                    // Get the click coordinates
                    int x = event.getX();
                    int y = event.getY();

                    /*
                     * Find the distance between the original and
                     * second coordinates
                     */
                    int xDelta = x - xPlaceHolder;
                    int yDelta = y - yPlaceHolder;

                    /*
                     * Use the distance formula to find a double value
                     * for the Circle's radius size
                     */
                    Double distance = Math.sqrt(Math.pow((xDelta), 2) +
                                                Math.pow((yDelta), 2));

                    // Convert the radius to an int
                    int radius = distance.intValue();
                    // And assign the Circle it's radius length
                    ((Circle) figurePlaceHolder).setRadius(radius);
                }
                // Otherwise it's a Rectangle
                else {
                    /*
                     * The differences between the initial coordinates
                     * and the second will be the width and height of
                     * the new Rectangle
                     */
                    int width = event.getX() - xPlaceHolder;
                    int height = event.getY() - yPlaceHolder;

                    // Assign the width and height
                    ((Rectangle) figurePlaceHolder).setWidth(width);
                    ((Rectangle) figurePlaceHolder).setHeight(height);
                }

                // Add the completed Figure to the List
                figures.add(figurePlaceHolder);

                // Add the Figure's data to listPanel
                listArea.append(figurePlaceHolder.toString());
                listArea.append("\n");

                // Repaint all the Figures in `figures`
                repaint();

                // Clear placeholders so no old date is used
                clearPlaceHolders();
            }
        }

        /**
         * Resets the coordinate and Figure placeholders
         * to empty values
         */
        private void clearPlaceHolders() {
            xPlaceHolder = 0;
            yPlaceHolder = 0;
            figurePlaceHolder = null;
        }

        /**
         * Returns the List of built Figures
         *
         * @return ArrayList<Figure> containing built Figures
         */
        private ArrayList<Figure> getFigures() {
            return this.figures;
        }

        /**
         * Not used
         */
        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        /**
         * Not used
         */
        @Override
        public void mouseExited(MouseEvent arg0) {
        }

        /**
         * Not used
         */
        @Override
        public void mousePressed(MouseEvent arg0) {
        }

        /**
         * Not used
         */
        @Override
        public void mouseReleased(MouseEvent arg0) {
        }
    }


	/**
	 * The method creates an FigureGUI object
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		FigureGUI gui = new FigureGUI();
		gui.setSize( 1000, 400 );
		gui.setResizable( false );
		gui.setVisible( true );
	}
}
