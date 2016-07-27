/**
 * GUI to interact with BookBinarySearchTree
 *
 * @author Kyle Heneman
 * @since 11/21/2014
 */


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;


class GUI extends JPanel implements ActionListener
{
  //// Input Panel ////
  /////////////////////
  private final JTextField titleField;

  private final JTextField authorField;

  private final JTextField genreField;

  private final JTextField priceField;

  private final JTextField reviewField;

  private final JLabel depthLabel;

  private final JButton addBookButton;


  //// Search Panel ////
  //////////////////////
  private final JTextField searchField;

  private final JButton searchButton;


  //// Display Button Panel ////
  //////////////////////////////
  private final JButton preOrderButton;
  private final JButton inOrderButton;
  private final JButton postOrderButton;


  //// BookBinarySearchTree ///
  /////////////////////////////
  private final BookBST bt;


  /**
   * Inputs and buttons for interacting with BookList
   */
  public GUI()
  {
    bt = new BookBST();

    Border loweredBorder = BorderFactory.createBevelBorder( BevelBorder.LOWERED );

    //// Instantiate and configure Main Panel //////
    ////////////////////////////////////////////////

    this.setLayout( new BorderLayout() );


    //// Instantiate and configure Input Panel ////
    ///////////////////////////////////////////////
    JPanel inputPanel = new JPanel();
    GridBagLayout inputPanelGridBag = new GridBagLayout();
    GridBagConstraints inputPanelCons = new GridBagConstraints();

    inputPanelCons.gridx = 0;
    inputPanelCons.gridy = 0;
    inputPanelCons.gridwidth = 3;
    inputPanelCons.gridheight = 9;
    inputPanelCons.ipadx = 10;
    inputPanelCons.ipady = 10;
    inputPanelCons.insets = new Insets( 5, 5, 5, 5 );

    inputPanelGridBag.setConstraints( inputPanel, inputPanelCons );

    inputPanel.setBorder( BorderFactory.createTitledBorder( loweredBorder, "Add/Edit Book" ) );

    inputPanel.setLayout( inputPanelGridBag );


    // Title Input Label
    JLabel titleLabel = new JLabel( "Title:", JLabel.TRAILING );

    GridBagConstraints titleLabelCons = new GridBagConstraints();
    titleLabelCons.gridx = 0;
    titleLabelCons.gridy = 0;
    titleLabelCons.gridwidth = 1;
    titleLabelCons.ipadx = 10;
    titleLabelCons.ipady = 10;
    titleLabelCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( titleLabel, titleLabelCons );

    inputPanel.add( titleLabel );

    // Title Input Field
    titleField = new JTextField( 16 );
    titleLabel.setLabelFor( titleField );

    GridBagConstraints titleFieldCons = new GridBagConstraints();
    titleFieldCons.gridx = 1;
    titleFieldCons.gridy = 0;
    titleFieldCons.gridwidth = 2;
    titleFieldCons.ipadx = 10;
    titleFieldCons.ipady = 10;
    titleFieldCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( titleField, titleFieldCons );

    inputPanel.add( titleField );


    // Author Input Label
    JLabel authorLabel = new JLabel( "Author:", JLabel.TRAILING );

    GridBagConstraints authorLabelCons = new GridBagConstraints();
    authorLabelCons.gridx = 0;
    authorLabelCons.gridy = 1;
    authorLabelCons.gridwidth = 1;
    authorLabelCons.ipadx = 10;
    authorLabelCons.ipady = 10;
    authorLabelCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( authorLabel, authorLabelCons );

    inputPanel.add( authorLabel );

    // Author Input Field
    authorField = new JTextField( 16 );

    GridBagConstraints authorFieldCons = new GridBagConstraints();
    authorFieldCons.gridx = 1;
    authorFieldCons.gridy = 1;
    authorFieldCons.gridwidth = 2;
    authorFieldCons.ipadx = 10;
    authorFieldCons.ipady = 10;
    authorFieldCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( authorField, authorFieldCons );

    authorLabel.setLabelFor( authorField );
    inputPanel.add( authorField );


    // Genre Input Label
    JLabel genreLabel = new JLabel( "Genre:", JLabel.TRAILING );

    GridBagConstraints genreLabelCons = new GridBagConstraints();
    genreLabelCons.gridx = 0;
    genreLabelCons.gridy = 2;
    genreLabelCons.gridwidth = 1;
    genreLabelCons.ipadx = 10;
    genreLabelCons.ipady = 10;
    genreLabelCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( genreLabel, genreLabelCons );

    inputPanel.add( genreLabel );

    // Genre Input Field
    genreField = new JTextField( 16 );

    GridBagConstraints genreFieldCons = new GridBagConstraints();
    genreFieldCons.gridx = 1;
    genreFieldCons.gridy = 2;
    genreFieldCons.gridwidth = 2;
    genreFieldCons.ipadx = 10;
    genreFieldCons.ipady = 10;
    genreFieldCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( genreField, genreFieldCons );

    genreLabel.setLabelFor( genreField );
    inputPanel.add( genreField );


    // Price Input Label
    JLabel priceLabel = new JLabel( "Price:", JLabel.TRAILING );

    GridBagConstraints priceLabelCons = new GridBagConstraints();
    priceLabelCons.gridx = 0;
    priceLabelCons.gridy = 3;
    priceLabelCons.gridwidth = 1;
    priceLabelCons.ipadx = 10;
    priceLabelCons.ipady = 10;
    priceLabelCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( priceLabel, priceLabelCons );

    inputPanel.add( priceLabel );

    // Price Input Field
    priceField = new JTextField( 16 );

    GridBagConstraints priceFieldCons = new GridBagConstraints();
    priceFieldCons.gridx = 1;
    priceFieldCons.gridy = 3;
    priceFieldCons.gridwidth = 2;
    priceFieldCons.ipadx = 10;
    priceFieldCons.ipady = 10;
    priceFieldCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( priceField, priceFieldCons );

    priceLabel.setLabelFor( priceField );
    inputPanel.add( priceField );


    // Review Input Label
    JLabel reviewLabel = new JLabel( "Review:", JLabel.TRAILING );

    GridBagConstraints reviewLabelCons = new GridBagConstraints();
    reviewLabelCons.gridx = 0;
    reviewLabelCons.gridy = 4;
    reviewLabelCons.gridwidth = 1;
    reviewLabelCons.ipadx = 10;
    reviewLabelCons.ipady = 10;
    reviewLabelCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( reviewLabel, reviewLabelCons );

    inputPanel.add( reviewLabel );

    // Review Input Field
    reviewField = new JTextField( 16 );

    GridBagConstraints reviewFieldCons = new GridBagConstraints();
    reviewFieldCons.gridx = 1;
    reviewFieldCons.gridy = 4;
    reviewFieldCons.gridwidth = 2;
    reviewFieldCons.ipadx = 10;
    reviewFieldCons.ipady = 10;
    reviewFieldCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( reviewField, reviewFieldCons );

    reviewLabel.setLabelFor( reviewField );
    inputPanel.add( reviewField );


    // Add Book Button
    addBookButton = new JButton( "Add" );

    GridBagConstraints addBookButtonCons = new GridBagConstraints();
    addBookButtonCons.gridx = 2;
    addBookButtonCons.gridy = 5;
    addBookButtonCons.gridwidth = 1;
    addBookButtonCons.ipadx = 10;
    addBookButtonCons.ipady = 10;
    addBookButtonCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( addBookButton, addBookButtonCons );

    inputPanel.add( addBookButton );

    addBookButton.addActionListener( this );


    // Add Depth Labels
    depthLabel = new JLabel( "Depth:", JLabel.TRAILING );

    GridBagConstraints depthLabelCons = new GridBagConstraints();
    depthLabelCons.gridx = 0;
    depthLabelCons.gridy = 5;
    depthLabelCons.gridwidth = 1;
    depthLabelCons.ipadx = 10;
    depthLabelCons.ipady = 10;
    depthLabelCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( depthLabel, depthLabelCons );

    inputPanel.add( depthLabel );


    // Add `inputPanel` to `mainPanel`
    this.add( inputPanel, BorderLayout.WEST );


    //// Search Panel instantiation and configuration ////
    //////////////////////////////////////////////////////
    JPanel searchPanel = new JPanel();

    GridBagLayout searchPanelGridBag = new GridBagLayout();
    GridBagConstraints searchPanelCons = new GridBagConstraints();

    searchPanelCons.gridx = 0;
    searchPanelCons.gridy = 7;
    searchPanelCons.gridwidth = 3;
    searchPanelCons.gridheight = 2;
    searchPanelCons.ipadx = 10;
    searchPanelCons.ipady = 10;
    searchPanelCons.insets = new Insets( 5, 5, 5, 5 );

    searchPanelGridBag.setConstraints( searchPanel, searchPanelCons );
    searchPanel.setBorder( BorderFactory.createTitledBorder( loweredBorder, "Search for Book" ) );

    searchPanel.setLayout( searchPanelGridBag );


    // Search Input Label
    JLabel searchLabel = new JLabel( "Search:", JLabel.TRAILING );

    GridBagConstraints searchLabelCons = new GridBagConstraints();
    searchLabelCons.gridx = 0;
    searchLabelCons.gridy = 0;
    searchLabelCons.gridwidth = 1;
    searchLabelCons.ipadx = 10;
    searchLabelCons.ipady = 10;
    searchLabelCons.insets = new Insets( 5, 5, 5, 5 );
    searchPanelGridBag.setConstraints( searchLabel, searchLabelCons );

    searchPanel.add( searchLabel );


    // Search Input Field
    searchField = new JTextField( 16 );
    searchLabel.setLabelFor( searchField );

    GridBagConstraints searchFieldCons = new GridBagConstraints();
    searchFieldCons.gridx = 1;
    searchFieldCons.gridy = 0;
    searchFieldCons.gridwidth = 2;
    searchFieldCons.ipadx = 10;
    searchFieldCons.ipady = 10;
    searchFieldCons.insets = new Insets( 5, 5, 5, 5 );
    searchPanelGridBag.setConstraints( searchField, searchFieldCons );

    searchPanel.add( searchField );


    // Search Button
    searchButton = new JButton( "Search" );
    searchButton.addActionListener( this );

    GridBagConstraints searchButtonCons = new GridBagConstraints();
    searchButtonCons.gridx = 2;
    searchButtonCons.gridy = 1;
    searchButtonCons.ipadx = 10;
    searchButtonCons.ipady = 10;
    searchFieldCons.insets = new Insets( 5, 5, 5, 5 );
    searchPanelGridBag.setConstraints( searchButton, searchButtonCons );

    searchPanel.add( searchButton );


    this.add( searchPanel, BorderLayout.EAST );


    //// Display Buttons Panel ////
    ///////////////////////////////
    JPanel buttonsPanel = new JPanel();
    FlowLayout buttonsLayout = new FlowLayout( FlowLayout.CENTER, 5, 5 );
    buttonsPanel.setLayout( buttonsLayout );

    preOrderButton = new JButton( "Display Pre-Order" );
    preOrderButton.addActionListener( this );

    inOrderButton = new JButton( "Display In-Order" );
    inOrderButton.addActionListener( this );

    postOrderButton = new JButton( "Display Post-Order" );
    postOrderButton.addActionListener( this );

    buttonsPanel.add( preOrderButton );
    buttonsPanel.add( inOrderButton );
    buttonsPanel.add( postOrderButton );

    this.add( buttonsPanel, BorderLayout.PAGE_END );

    updateDepth();
  }


    /**
     * ActionListener for buttons
     *
     * @param e ActionEvent with Source data
     */

  public void actionPerformed( ActionEvent e )
  {
    Object source = e.getSource();

    if ( source == addBookButton )
    {
      String priceText = priceField.getText();
      String reviewText = reviewField.getText();

      if ( emptyFieldsExist() )
      {
        JOptionPane.showMessageDialog( this, "All fields must be filled" );
      }
      else if ( !isDouble( priceText ) )
      {
        JOptionPane.showMessageDialog( this, "Price must be a number" );
      }
      else if ( Double.parseDouble( priceText ) < 0 )
      {
        JOptionPane.showMessageDialog( this, "Price cannot be negative" );
      }
      else if ( !isDouble( reviewText ) )
      {
        JOptionPane.showMessageDialog( this, "Review must be a number" );
      }
      else if ( Double.parseDouble( reviewText ) < 0 || Double.parseDouble( reviewText ) > 10 )
      {
        JOptionPane.showMessageDialog( this, "Review must be 0 through 10" );
      }
      else
      {
        addBook();
      }

    }
    else if ( source == searchButton )
    {
      if ( searchField.getText().isEmpty() )
      {
        JOptionPane.showMessageDialog( this, "Search was blank..." );
      }

      try
      {
        searchBooks();
      }
      catch ( CloneNotSupportedException e1 )
      {
        e1.printStackTrace();
      }

    }
    else if ( source == preOrderButton )
    {
      String msg = bt.getPreOrder();
      JOptionPane.showMessageDialog( this, msg );
    }
    else if ( source == inOrderButton )
    {
      String msg = bt.getInOrder();
      JOptionPane.showMessageDialog( this, msg );
    }
    else if ( source == postOrderButton )
    {
      String msg = bt.getPostOrder();
      JOptionPane.showMessageDialog( this, msg );
    }

  }


  /**
   * Adds new Book to `bookList`
   */
  @SuppressWarnings( "unchecked" )
  void addBook()
  {
    Book book;
    book = new Book( titleField.getText(),
                     authorField.getText(),
                     genreField.getText(),
                     Double.parseDouble( priceField.getText() ),
                     Double.parseDouble( reviewField.getText() )
    );

    clearInputFields();

    bt.addBook( book );
    updateDepth();
  }


  /**
   * Searches for Book with matching `title`
   */
  @SuppressWarnings( "unchecked" )
  void searchBooks() throws CloneNotSupportedException
  {
    String query = searchField.getText();
    Book book = bt.search( query );

    if ( book == null )
    {
      JOptionPane.showMessageDialog( this, "No Book found with that title" );
      clearSearchField();
    }
    else
    {
      NumberFormat nf = NumberFormat.getCurrencyInstance();
      DecimalFormat df = new DecimalFormat( "#.#" );

      String msg = "Book Found:\n\n" +
                   "Title: " + book.getTitle() +
                   "\nAuthor: " + book.getAuthor() +
                   "\nGenre: " + book.getGenre() +
                   "\nPrice: " + nf.format( book.getPrice() ) +
                   "\nReview: " + df.format( book.getReview() ) + "/10";

      JOptionPane.showMessageDialog( this, msg );

      clearSearchField();
    }
  }


  /**
   * Updates `depthLabel` to show current depth of BookBinarySearchTree
   */

  public void updateDepth()
  {
    depthLabel.setText( "Depth: " + String.valueOf( bt.getDepth() ) );
  }


  /**
   * Tests if String is parseable to Double
   *
   * @param test String to test if parseable to Double
   *
   * @return isDouble Returns `true` if parseable to Double, `false` if not
   */

  @SuppressWarnings( "ResultOfMethodCallIgnored" )
  public boolean isDouble( String test )
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
   * Clear Search Field
   */
  void clearSearchField()
  {
    searchField.setText( null );
  }


  /**
   * Tests if any empty fields exist
   *
   * @return bool True if at least one is empty, False if all are filled
   */
  boolean emptyFieldsExist()
  {
    return titleField.getText().isEmpty() ||
           authorField.getText().isEmpty() ||
           genreField.getText().isEmpty() ||
           priceField.getText().isEmpty() ||
           reviewField.getText().isEmpty();

  }


  /**
   * Clears add/edit fields
   */
  void clearInputFields()
  {
    titleField.setText( null );
    authorField.setText( null );
    genreField.setText( null );
    priceField.setText( null );
    reviewField.setText( null );
  }

}