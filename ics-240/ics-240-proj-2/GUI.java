/**
 * GUI to interact with BookList
 *
 * @author Kyle Heneman <a href="http://www.kyleheneman.com"> www.kyleheneman.com </a> <a
 *         href="mailto:holla@kyleheneman.com"> holla@kyleheneman.com </a>
 */


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class GUI extends JPanel implements ActionListener
{
   //// Hidden field for temporary `title` storage ////
  //////////////////////////////////////////////////
  private final JTextField hiddenTitleField;

   //// Input Panel ////
  /////////////////////
  private final JTextField titleField;

  private final JTextField authorField;

  private final JTextField genreField;

  private final JTextField priceField;

  private final JTextField reviewField;

  private final JButton addBookButton;
  private final JButton saveBookButton;


  //// Search Panel ////
  //////////////////////
  private final JTextField searchField;

  private final JButton searchButton;


  //// Output Panel ////
  //////////////////////
  private final JButton deleteBookButton;


  //// Display Panel ////
  ///////////////////////
  private final JTable listTable;
  private final JButton sortButton;
  private final JLabel bookCount;


  //// BookList ////
  //////////////////
  private final ArrayList bl;


  /**
   * Inputs and buttons for interacting with BookList
   */
  public GUI()
  {
    int listSize = 5;

    bl = new ArrayList( listSize );

    Border loweredBorder = BorderFactory.createBevelBorder( BevelBorder.LOWERED );

    //// Instantiate and configure Main Panel ////
    ////////////////////////////////////////////////

    this.setLayout( new BorderLayout() );

    hiddenTitleField = new JTextField( 16 );
    hiddenTitleField.setVisible( false );

    this.add( hiddenTitleField );


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

    inputPanel.setBorder( BorderFactory.createTitledBorder( loweredBorder,
                                                            "Add/Edit Book" )
    );

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


    // Save Book Button
    saveBookButton = new JButton( "Save" );

    GridBagConstraints saveBookButtonCons = new GridBagConstraints();
    saveBookButtonCons.gridx = 1;
    saveBookButtonCons.gridy = 5;
    saveBookButtonCons.gridwidth = 1;
    saveBookButtonCons.ipadx = 10;
    saveBookButtonCons.ipady = 10;
    saveBookButtonCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( saveBookButton, saveBookButtonCons );

    inputPanel.add( saveBookButton );

    saveBookButton.addActionListener( this );

    // Delete Button
    deleteBookButton = new JButton( "Delete" );

    GridBagConstraints deleteButtonCons = new GridBagConstraints();
    deleteButtonCons.gridx = 0;
    deleteButtonCons.gridy = 5;
    deleteButtonCons.gridwidth = 1;
    deleteButtonCons.ipadx = 10;
    deleteButtonCons.ipady = 10;
    deleteButtonCons.insets = new Insets( 5, 5, 5, 5 );
    inputPanelGridBag.setConstraints( deleteBookButton, deleteButtonCons );

    inputPanel.add( deleteBookButton );

    deleteBookButton.addActionListener( this );


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
    searchPanel.setBorder( BorderFactory.createTitledBorder( loweredBorder,
                                                             "Search for Book")
    );

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


    //// List Panel instantiation and configuration ////
    /////////////////////////////////////////////////////
    JPanel displayPanel = new JPanel();

    GridBagLayout displayPanelGridBag = new GridBagLayout();
    GridBagConstraints displayPanelCons = new GridBagConstraints();

    displayPanelCons.gridx = 0;
    displayPanelCons.gridy = 1;
    displayPanelCons.gridwidth = 5;
    displayPanelCons.gridheight = 4;
    displayPanelCons.ipadx = 10;
    displayPanelCons.ipady = 10;
    displayPanelCons.insets = new Insets( 5, 5, 5, 5 );

    displayPanelGridBag.setConstraints( displayPanel, displayPanelCons );

    displayPanel.setBorder(
                              BorderFactory.createTitledBorder( loweredBorder,
                                                                "Book List"
                              )
    );

    displayPanel.setLayout( displayPanelGridBag );


    // Separate panel for ScrollPane
    JScrollPane listPanel = new JScrollPane();

    GridBagConstraints listPanelCons = new GridBagConstraints();
    listPanelCons.gridx = 0;
    listPanelCons.gridy = 0;
    listPanelCons.ipadx = 10;
    listPanelCons.ipady = 10;
    listPanelCons.fill = GridBagConstraints.BOTH;
    listPanelCons.insets = new Insets( 5, 5, 5, 5 );

    displayPanelGridBag.setConstraints( listPanel, listPanelCons );

    listTable = new JTable();

    listTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

    String[] colHeaders = { "Title", "Author", "Genre", "Price", "Review" };
    listTable.setModel(
                          new DefaultTableModel( colHeaders, 0 )
                          {
                            final boolean[] colEditables = { false,
                                                               false,
                                                               false,
                                                               false,
                                                               false };

                            public boolean isCellEditable( int row, int col )
                            {
                              return colEditables[ col ];
                            }
                          }
    );

    listPanel.setViewportView( listTable );
    displayPanel.add( listPanel );


    bookCount = new JLabel( "", JLabel.LEADING );

    GridBagConstraints bookCountCons = new GridBagConstraints();
    bookCountCons.gridx = 0;
    bookCountCons.gridy = 1;
    bookCountCons.ipadx = 10;
    bookCountCons.ipady = 10;
    bookCountCons.insets = new Insets( 5, 5, 5, 5 );

    displayPanelGridBag.setConstraints( bookCount, bookCountCons );

    displayPanel.add( bookCount );


    sortButton = new JButton( "Sort" );

    GridBagConstraints sortButtonCons = new GridBagConstraints();
    sortButtonCons.gridx = 1;
    sortButtonCons.gridy = 1;
    sortButtonCons.ipadx = 10;
    sortButtonCons.ipady = 10;
    sortButtonCons.insets = new Insets( 5, 5, 5, 5 );

    displayPanelGridBag.setConstraints( sortButton, sortButtonCons );

    displayPanel.add( sortButton );

    this.add( displayPanel, BorderLayout.SOUTH );

    sortButton.addActionListener( this );

    updateBookCount();

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
        JOptionPane.showMessageDialog( this, "Review must be between 0 and 10 (inclusive)" );
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
    else if ( source == saveBookButton )
    {
      if ( emptyFieldsExist() )
      {
        JOptionPane.showMessageDialog( this, "All fields must be filled" );
      }
      else
      {
        try
        {
          saveBook();
        }
        catch ( CloneNotSupportedException e1 )
        {
          e1.printStackTrace();
        }
      }
    }
    else if ( source == deleteBookButton )
    {
      int response = JOptionPane.showConfirmDialog( null,
                                                    "Are you sure you want to delete this Book?",
                                                    "Confirm",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.WARNING_MESSAGE
      );

      if ( response == JOptionPane.YES_OPTION )
      {
        deleteBook();
      }
    }
    else if ( source == sortButton )
    {
      sort();
    }
  }


  /**
   * Taken from UI.java Copyright (c) Sue Fitzgerald All rights reserved 8/25/13 Updates `bookCount` whenever table is
   * rebuilt
   */
  @SuppressWarnings( "unchecked" )
  void displayList()
  {
    // Clear the search results table 
    DefaultTableModel model = ( DefaultTableModel ) listTable.getModel();
    for ( int i = ( model.getRowCount() - 1 ); i >= 0; i-- )
    {
      model.removeRow( i );
    }

    // add books to display table, one by one
    try
    {
      Lister lister = new Lister( bl.clone() );
      Object current;

      while ( lister.hasNext() )
      {
        current = lister.next();

        if ( current != null )
        {
          Book book = ( Book ) current;

          model.addRow( new Object[]{
                                        book.getTitle(),
                                        book.getAuthor(),
                                        book.getGenre(),
                                        book.getPrice(),
                                        book.getReview()
                        }
          );
        }
      }
    }
    catch ( CloneNotSupportedException e )
    {
      JOptionPane.showMessageDialog( this, "This class does not implement Cloneable" );
    }

    updateBookCount();
  }

  /**
   * Adds new Book to `bookList`
   */
  void addBook()
  {
    if ( bl.getLength() == bl.getBookCount() )
    {
      JOptionPane.showMessageDialog( this, "No more room for another book!" );
    }
    else
    {
      Book book;
      book = new Book( titleField.getText(),
                       authorField.getText(),
                       genreField.getText(),
                       Double.parseDouble( priceField.getText() ),
                       Double.parseDouble( reviewField.getText() )
      );

      clearInputFields();

      bl.add( book );

      displayList();
    }
  }


  /**
   * Searches for Book with matching `title`
   */
  void searchBooks() throws CloneNotSupportedException
  {
    int index = bl.find( searchField.getText() );

    if ( index < 0 )
    {
      JOptionPane.showMessageDialog( null, "No Book found with that title" );
    }
    else
    {
      Book book = ( Book ) bl.getBook( index );

      clearSearchField();

      titleField.setText( book.getTitle() );
      hiddenTitleField.setText( book.getTitle() );
      authorField.setText( book.getAuthor() );
      genreField.setText( book.getGenre() );
      priceField.setText( String.valueOf( book.getPrice() ) );
      reviewField.setText( String.valueOf( book.getReview() ) );
    }
  }


  /**
   * Saves edited Book to `bookList`
   */
  void saveBook() throws CloneNotSupportedException
  {
    if ( !isDouble( priceField.getText() ) && !isDouble( reviewField.getText() ) )
    {
      JOptionPane.showMessageDialog( this, "Price and Review must be numbers" );
    }
    else if( emptyFieldsExist() )
    {
      JOptionPane.showMessageDialog( this, "All fields must be filled" );
    }
    else
    {
      bl.edit( hiddenTitleField.getText(),
               titleField.getText(),
               authorField.getText(),
               genreField.getText(),
               Double.parseDouble( priceField.getText() ),
               Double.parseDouble( reviewField.getText() )
      );

      clearInputFields();

      displayList();
    }
  }


  /**
   * Deletes selected Book
   */
  void deleteBook()
  {
    bl.delete( titleField.getText() );

    displayList();
  }


  /**
   * Calls `.sort()` on `bookList`
   */
  void sort()
  {
    bl.sort();
    this.displayList();
  }


  /**
   * Tests if String is parseable to Double
   *
   * @param test String to test if parseable to Double
   *
   * @return isDouble Returns `true` if parseable to Double, `false` if not
   */

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
   * Updates `bookCount` label
   */
  void updateBookCount()
  {
    bookCount.setText( "Book Count: " + bl.getBookCount() );
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
    return titleField.getText().isEmpty()  ||
           authorField.getText().isEmpty() ||
           genreField.getText().isEmpty()  ||
           priceField.getText().isEmpty()  ||
           reviewField.getText().isEmpty();

  }


  /**
   * Clears add/edit fields
   */
  void clearInputFields()
  {
    titleField.setText( null );
    hiddenTitleField.setText( null );
    authorField.setText( null );
    genreField.setText( null );
    priceField.setText( null );
    reviewField.setText( null );
  }

}

