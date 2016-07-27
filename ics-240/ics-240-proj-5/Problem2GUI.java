import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Kyle Heneman
 * @since 11/8/14
 *
 * GUI to take input and show output of combinations
 */
public class Problem2GUI extends JPanel implements ActionListener
{
  final JPanel mainPanel;
  final JPanel inputPanel;
  final JPanel outputPanel;

  final JTextField inputField;
  final JButton convertButton;
  final JLabel combinationOutput;

  public Problem2GUI()
  {
    mainPanel = new JPanel();
    mainPanel.setLayout( new BorderLayout( 5, 5 ) );
    mainPanel.setBounds( 300, 300, 100, 200 );

    inputPanel  = new JPanel();
    inputPanel.setLayout( new GridLayout( 1, 2, 5, 5 ) );

    outputPanel = new JPanel();
    outputPanel.setPreferredSize( new Dimension( 200, 50 ) );
    outputPanel.setLayout( new BorderLayout( 5, 5 ) );

    inputField = new JTextField( 8 );
    inputPanel.add( inputField );

    convertButton = new JButton( "Count" );
    convertButton.addActionListener( this );
    inputPanel.add( convertButton );

    combinationOutput = new JLabel( "", SwingConstants.CENTER);
    outputPanel.add( combinationOutput, BorderLayout.CENTER );

    mainPanel.add( inputPanel, BorderLayout.NORTH );
    mainPanel.add( outputPanel, BorderLayout.SOUTH );

    this.add( mainPanel );
  }

  @Override
  public void actionPerformed( ActionEvent e )
  {
    if( e.getSource() == convertButton )
    {
      if( fieldIsEmpty() )
      {
        JOptionPane.showMessageDialog( null, "Input cannot be blank" );
      }
      else if( !isInteger( inputField.getText() ) )
      {
        JOptionPane.showMessageDialog( null, "Input must be a positive Integer" );
      }
      else
      {
        int n = Integer.parseInt( inputField.getText() );

        combinationOutput.setText( String.valueOf( count( n ) ) );
      }
    }
  }

  public int count( int num)
  {
    return CombinationsCounter.count( num );
  }

  // Tests if input field is empty
  public boolean fieldIsEmpty()
  {
    return inputField.getText().isEmpty();
  }

  // Tests if input can be parsed to int
  public boolean isInteger( String test )
  {
    try
    {
      Integer.parseInt( test );
      return true;
    }
    catch ( NumberFormatException e )
    {
      return false;
    }
  }
}
