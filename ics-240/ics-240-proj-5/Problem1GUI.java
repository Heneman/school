import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Kyle Heneman
 * @since 11/8/14
 *
 * GUI to take input and show output of binary conversion
 */

class Problem1GUI extends JPanel implements ActionListener
{

  private final JTextField inputField;
  private final JButton convertButton;
  private final JLabel binaryOutput;

  public Problem1GUI()
  {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout( new BorderLayout( 5, 5 ) );
    mainPanel.setBounds( 300, 300, 100, 200 );

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout( new GridLayout( 1, 2, 5, 5 ) );

    JPanel outputPanel = new JPanel();
    outputPanel.setPreferredSize( new Dimension( 200, 50 ) );
    outputPanel.setLayout( new BorderLayout( 5, 5 ) );

    inputField = new JTextField( 8 );
    inputPanel.add( inputField );

    convertButton = new JButton( "Convert" );
    convertButton.addActionListener( this );
    inputPanel.add( convertButton );

    binaryOutput = new JLabel( "", SwingConstants.CENTER);
    outputPanel.add( binaryOutput, BorderLayout.CENTER );

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
        int num = Integer.parseInt( inputField.getText() );
        StringBuilder sb = new StringBuilder();

        binaryOutput.setText( convert( num, sb ) );
      }
    }
  }

  // Calls BinaryConverter.convert()
  String convert( int num, StringBuilder sb )
  {
    return BinaryConverter.convert( num, sb );
  }

  // Tests if input field is empty
  boolean fieldIsEmpty()
  {
    return inputField.getText().isEmpty();
  }

  // Tests if input can be parsed to an int
  boolean isInteger( String test )
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
