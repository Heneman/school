import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI to give simulation inputs
 *
 * @author Kyle Heneman
 * @since 10/11/2014
 *
 * <a href="http://www.kyleheneman.com">kyleheneman.com</a>
 */
class GUI extends JPanel implements ActionListener
{
  /**
   * Invariants of GUI
   * *******************
   *
   * - `runButton` runs simulation
   *
   * - `clearButton` clears input fields
   *
   * - `arrivalDurField` contains valid value for Arrival Duration
   *
   * - `arrivalProbField` contains valid value for Arrival Probability
   *
   * - `departureDurField` contains valid value for Departure Duration
   *
   * - `departureProbField` contains valid value for Departure Probability
   *
   * - `fuelAmountField` contains valid value for amount of 'Fuel' a Plane contains
   *
   * - `runwayCountField` contains valid value for number of Runways Airports have
   *
   * - `totalDurField` contains valid value for amount of 'time' to run Simulation
   *
   * - `outputText` displays `reportFigures` when simulation terminates
   */

  private final JButton runButton;
  private final JButton clearButton;
  private final JTextField arrivalDurField;
  private final JTextField arrivalProbField;
  private final JTextField departureDurField;
  private final JTextField departureProbField;
  private final JTextField fuelAmountField;
  private final JTextField runwayCountField;
  private final JTextField totalDurField;
  private final JTextArea outputText;

  /**
   * Creates a new GUI to interact with an Airport
   *
   * - Preconditions: A new GUI is being created
   *
   * - Postconditions: A new GUI is returned
   */

  public GUI()
  {
    Border loweredBorder = BorderFactory.createLoweredBevelBorder();

    this.setLayout( new BorderLayout() );


     //// Input Panel ////
    /////////////////////

    JPanel inputPanel = new JPanel();
    inputPanel.setBorder(BorderFactory.createTitledBorder( loweredBorder, "Inputs" ));
    inputPanel.setLayout( new GridLayout( 8, 2, 5, 5 ) );

    JLabel arrivalDurLabel = new JLabel( "Duration of Arrivals:" );
    arrivalDurLabel.setHorizontalAlignment( SwingConstants.TRAILING );
    inputPanel.add( arrivalDurLabel );

    arrivalDurField = new JTextField( 8 );
    inputPanel.add( arrivalDurField );

    JLabel departureDurLabel = new JLabel( "Duration of Departures:" );
    departureDurLabel.setHorizontalAlignment( SwingConstants.TRAILING );
    inputPanel.add( departureDurLabel );

    departureDurField = new JTextField( 8 );
    inputPanel.add( departureDurField );

    JLabel arrivalProbLabel = new JLabel( "Probability of new Arrival:" );
    arrivalProbLabel.setHorizontalAlignment( SwingConstants.TRAILING );
    inputPanel.add( arrivalProbLabel );

    arrivalProbField = new JTextField( 8 );
    inputPanel.add( arrivalProbField );

    JLabel departureProbLabel = new JLabel( "Probability of ready Departure:" );
    departureProbLabel.setHorizontalAlignment( SwingConstants.TRAILING );
    inputPanel.add( departureProbLabel );

    departureProbField = new JTextField( 8 );
    inputPanel.add( departureProbField );

    JLabel fuelAmountLabel = new JLabel( "Fuel Amount:" );
    fuelAmountLabel.setHorizontalAlignment( SwingConstants.TRAILING );
    inputPanel.add( fuelAmountLabel );

    fuelAmountField = new JTextField( 8 );
    inputPanel.add( fuelAmountField );

    JLabel runwayCountLabel = new JLabel( "Number of Runways:" );
    runwayCountLabel.setHorizontalAlignment( SwingConstants.TRAILING );
    inputPanel.add( runwayCountLabel );

    runwayCountField = new JTextField( 8 );
    inputPanel.add( runwayCountField );

    JLabel totalDurLabel = new JLabel( "Amount of Time to Simulate:" );
    totalDurLabel.setHorizontalAlignment( SwingConstants.TRAILING );
    inputPanel.add( totalDurLabel );

    totalDurField = new JTextField( 8 );
    inputPanel.add( totalDurField );

    clearButton = new JButton( "Clear All" );
    clearButton.addActionListener( this );
    inputPanel.add( clearButton );

    runButton = new JButton( "Run" );
    runButton.addActionListener( this );
    inputPanel.add( runButton );

    inputPanel.setBorder( loweredBorder );

    this.add( inputPanel, BorderLayout.PAGE_START );



     //// Output Panel ////
    //////////////////////

    JPanel outputPanel = new JPanel();
    outputPanel.setBorder( BorderFactory.createTitledBorder( loweredBorder, "Output" ) );

    outputText = new JTextArea( 25, 35 );
    outputText.setEditable( false );

    outputPanel.add( outputText );

    JScrollPane scrollPane = new JScrollPane( outputText );
    scrollPane.setBounds( 0, 0, 25, 25);

    outputPanel.add( scrollPane );

    this.add( outputPanel );
  }


  /**
   * Action listener for Run and Clear buttons
   *
   * - Precondition: A component with `this` as ActionListener is triggered
   *
   * - Postcondition: The function corresponding with triggered component is called
   *
   * @param e Event of button being pressed
   */

  public void actionPerformed( ActionEvent e )
  {
    Object source = e.getSource();

    if( source == runButton )
    {
      if( inputsValid() )
      {
        String reportValues = Airport.simulate( Integer.parseInt( arrivalDurField.getText() ),
                                                Double.parseDouble( arrivalProbField.getText() ),
                                                Integer.parseInt( departureDurField.getText() ),
                                                Double.parseDouble( departureProbField.getText() ),
                                                Integer.parseInt( fuelAmountField.getText() ),
                                                Integer.parseInt( runwayCountField.getText() ),
                                                Integer.parseInt( totalDurField.getText() )
        );

        outputText.append( reportValues );
      }
    }
    else if ( source == clearButton )
    {
      clearAll();
    }

  }


  /**
   * Tests whether inputs adhere to requirements
   *
   * - Precondition: Input fields have been initialized
   *
   * - Postcondition: Input fields are filled and have valid values
   */

  boolean inputsValid()
  {

    return ( !(emptyFieldsExist()) &&

             isInteger( arrivalDurField.getText() ) &&
             Integer.parseInt( arrivalDurField.getText() ) > 0 &&

             isDouble( arrivalProbField.getText() ) &&
             Double.parseDouble( arrivalProbField.getText() ) > 0 &&
             Double.parseDouble( arrivalProbField.getText() ) < 1 &&

             isInteger( departureDurField.getText() ) &&
             Integer.parseInt( departureDurField.getText() ) > 0 &&

             isDouble( departureProbField.getText() ) &&
             Double.parseDouble( departureProbField.getText() ) > 0 &&
             Double.parseDouble( departureProbField.getText() ) < 1 &&

             isInteger( fuelAmountField.getText() ) &&
             Integer.parseInt( fuelAmountField.getText() ) > 0 &&

             isInteger( runwayCountField.getText() ) &&
             Integer.parseInt( runwayCountField.getText() ) > 0 &&

             isInteger( totalDurField.getText() ) &&
             Integer.parseInt( totalDurField.getText() ) > 0
           );
      }


  /**
   * Tests whether empty fields exist
   *
   * - Precondition: Input fields have been initialized
   *
   * - Postcondition: Input fields have input values
   *
   * @return true if a field is empty
   */

  boolean emptyFieldsExist()
  {
    return ( arrivalDurField.getText().isEmpty() ||
             arrivalProbField.getText().isEmpty() ||
             departureDurField.getText().isEmpty() ||
             departureProbField.getText().isEmpty() ||
             fuelAmountField.getText().isEmpty() ||
             runwayCountField.getText().isEmpty() ||
             totalDurField.getText().isEmpty() );
  }


  /**
   * Tests whether input can be parsed to Integer
   *
   * - Precondition: `test` is a String from an Input Field
   *
   * - Postcondition: `test` can be parsed to an Integer
   *
   * @param test Input to test
   * @return true if input can be parsed to Integer
   */

  boolean isInteger( String test )
  {
    try
    {
      Integer.parseInt( test );
      return true;
    }
    catch( NumberFormatException e )
    {
      return false;
    }
  }


  /**
   * Tests if String is can be parsed to Double
   *
   * - Precondition: `test` is a String from an Input Field
   *
   * - PostconditioN: `test` can be parsed to a Double
   *
   * @param test String to test if can be parsed to Double
   *
   * @return isDouble Returns `true` if can be parsed to Double, `false` if not
   */

  boolean isDouble( String test )
  {
    try
    {
      Double.parseDouble( test );
      return true;
    }
    catch ( NumberFormatException e )
    {
      return false;
    }
  }


  /**
   * Clears input fields
   *
   * - Precondition: `clearButton` was clicked
   *
   * - Postcondition: All Input Fields values are cleared
   */

  void clearAll()
  {
    arrivalDurField.setText( null );
    arrivalProbField.setText( null );
    departureDurField.setText( null );
    departureProbField.setText( null );
    fuelAmountField.setText( null );
    runwayCountField.setText( null );
    totalDurField.setText( null );

    outputText.setText( null );
  }
}
