import java.io.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An interface for interacting with a Theater performing CRUD
 * operations for Customers, Clients, Cards and Shows
 * 
 * @author Kyle Heneman
 * @since 10/11/2015
 */
class UserInterface {
    // Keeps track of whether a Theater state has been loaded this session
    // or not
    private static boolean hasLoaded = false;
    // Theater object to store Clients, Customers, Cards and Shows
    private static Theater theater;
    // Scanner utility to get user input
    private static final Scanner input = new Scanner(System.in);

    // Constants named after actions they perform
    private static final int INVALID_INPUT = -1;
    private static final int EXIT_PROGRAM = 0;
    private static final int ADD_CLIENT = 1;
    private static final int REMOVE_CLIENT = 2;
    private static final int LIST_ALL_CLIENTS = 3;
    private static final int ADD_CUSTOMER = 4;
    private static final int REMOVE_CUSTOMER = 5;
    private static final int ADD_CREDIT_CARD = 6;
    private static final int REMOVE_CREDIT_CARD = 7;
    private static final int LIST_ALL_CUSTOMERS = 8;
    private static final int ADD_SHOW = 9;
    private static final int LIST_ALL_SHOWS = 10;
    private static final int SAVE_SESSION = 11;
    private static final int LOAD_SESSION = 12;
    private static final int HELP = 13;

    // This gets printed regularly so there's a constant for it too
    private static final String MENU_TEXT =
        "*** Theater System Main Menu ***\n\n" +
        "Enter one of the following numbers:\n" +
        "0. Exit program\n" +
        "1. Add Client\n" +
        "2. Remove Client\n" +
        "3. List All Clients\n" +
        "4. Add Customer\n" +
        "5. Remove Customer\n" +
        "6. Add Credit Card\n" +
        "7. Remove Credit Card\n" +
        "8. List All Customers\n" +
        "9. Add Show\n" +
        "10. List All Shows\n" +
        "11. Save session\n" +
        "12. Load session\n" +
        "13. Help\n";


    /**
     * Main method that drives the main menu interface and handles user
     * interaction
     *
     * @param s Any arguments passed when program was executed
     */
    public static void main(String[] s) {
        // Keeps user in a loop for making selections in the Menu
        boolean mainMenu = true;
        // Default the selection input to an invalid value
        int selection = INVALID_INPUT;

        // Give Theater a name and capacity.  Not used right now
        String name = "Theater Foo";
        int capacity = 700;

        // Instantiate new Theater with name and capacity
        theater = new Theater(name, capacity);

        // Welcome message
        System.out.println("===========================" +
                           "\nWelcome to the Theater System!\n" +
                           "===========================\n");

        // Main Menu loop
        while(mainMenu) {
            // Print the Main Menu prompts
            printPrompt();

            try{
                // Attempts to parse user input into an Integer
                selection = Integer.parseInt(input.nextLine());
            }
            // Input couldn't be parsed to an Integer
            catch(Exception e) {
                System.out.println("There was a problem parsing input into an" +
                                   " integer.  You must enter an integer to " +
                                   "navigate!\"\n\n");
            }

            switch(selection) {
                // If menu selection is not a valid integer
                case INVALID_INPUT:
                    System.out.println("Input was not a valid integer");
                    break;

                // Allows main menu loop to break
                case EXIT_PROGRAM:
                    mainMenu = false;
                    break;

                // Starts process to add a Client
                case ADD_CLIENT:
                    addClient();
                    break;

                // Starts process to remove a Client
                case REMOVE_CLIENT:
                    removeClient();
                    break;

                // Starts process to list all Clients
                case LIST_ALL_CLIENTS:
                    listAllClients();
                    break;

                // Starts process to add a new Customer
                case ADD_CUSTOMER:
                    addCustomer();
                    break;

                // Starts process to remove a Customer
                case REMOVE_CUSTOMER:
                    removeCustomer();
                    break;

                // Starts process to add a Credit Card to a Customer
                case ADD_CREDIT_CARD:
                    addCreditCard();
                    break;

                // Starts process to remove a Credit Card from a Customer
                case REMOVE_CREDIT_CARD:
                    removeCreditCard();
                    break;

                // Starts process to display all Customers
                case LIST_ALL_CUSTOMERS:
                    listAllCustomers();
                    break;

                // Starts process to add a Show to a Client
                case ADD_SHOW:
                    addShow();
                    break;

                // Starts process to list all current and future Shows
                case LIST_ALL_SHOWS:
                    listAllShows();
                    break;

                // Starts process to save Theater data
                case SAVE_SESSION:
                    saveSession();
                    break;

                // Starts process to load a saved Theater
                case LOAD_SESSION:
                    // If a Theater has been loaded once already during this
                    // session...
                    if(hasLoaded)
                        // Give error message
                        System.out.println("\nTheater has already been loaded" +
                                           " once this session.  Unable to " +
                                           "load again\n\n");
                    // Otherwise load the saved Theater
                    else
                        loadSession();
                    break;

                // Displays descriptions for each option in Main Menu
                case HELP:
                    help();
                    break;
            }
        }

        // When program is exited with a selection of '0',
        // save the session and exit with a success status
        saveSession();
        System.exit(0);
    }

    /**
     * Displays main menu options and prompts user for input
     */
    private static void printPrompt() {
        System.out.println(MENU_TEXT);
        System.out.println("Enter a number: ");
    }

    /**
     * Prompts user for data input to create a new Client
     */
    private static void addClient() {
        System.out.println("\n~~~Menu 1:  Add a Client~~~");

        // I didn't want to start down the hole of validating names or
        // addresses so those two attributes can be whatever is input
        System.out.println("Enter Client's name: ");
        String name = input.nextLine();

        System.out.println("\nEnter Client's address: ");
        String address = input.nextLine();

        // Phone numbers are validated so a helper method is used to get the
        // input from the user
        System.out.println("\nEnter Client's phone number: ");
        String phone = getPhoneNumber();

        // Pass the Theater the data to create a new Client
        theater.addClient(name, address, phone);
    }

    /**
     * Prompts user for data input to remove a Client from the Theater
     */
    private static void removeClient() {
        System.out.println("\n~~~Menu 2:  Remove a Client~~~");
        System.out.println("Enter ID of the Client to remove: ");

        // Prompts user for Client ID using helper method
        int id = getID();

        // Passes Client ID to Theater for removal from records
        theater.removeClient(id);
    }

    /**
     * Displays all Clients on record
     */
    private static void listAllClients() {
        System.out.println("\n~~~Menu 3:  List all Clients~~~");
        System.out.println("Listing Clients...");

        // Calls on Theater to display list of its Clients
        theater.listAllClients();
    }

    /**
     * Prompts user for data input to create a new Customer
     */
    private static void addCustomer() {
        System.out.println("\n~~~Menu 4:  Add a Customer~~~");

        // Name and address aren't validated so they are whatever String is
        // entered by the user
        System.out.println("Enter Customer's name: ");
        String name = input.nextLine();

        System.out.println("\nEnter Customer's address: ");
        String address = input.nextLine();

        // Prompt user for Customer phone number using helper method
        System.out.println("\nEnter Customer's phone number: ");
        String phone = getPhoneNumber();

        // Get Customer's Credit Card number using helper method
        System.out.println("\nCustomer needs a Credit Card on file...");
        System.out.println("Enter Customer's Credit Card number: ");
        String cardNumber = getCardNumber();

        // Get Customer's Credit Card expiration date using helper method
        System.out.println("Enter Card's expiration date: ");
        Date cardExpires = getDate();

        // Create a new Card object with collected data
        Card card = new Card(cardNumber, cardExpires);

        // Pass Customer info and new Card to Theater for processing
        theater.addCustomer(name, address, phone, card);
    }

    /**
     * Prompts user for ID of Customer to remove
     */
    private static void removeCustomer() {
        System.out.println("\n~~~Menu 5:  Remove a Customer~~~");
        System.out.println("Enter ID of the Customer to remove: ");

        // Gets Customer ID from helper method
        int id = getID();

        // Passes ID to Theater for processing
        theater.removeCustomer(id);
    }

    /**
     * Prompts user for Credit Card information to add to a Customer
     */
    private static void addCreditCard() {
        System.out.println("\n~~~Menu 6:  Add a Credit Card~~~");
        System.out.println("Enter ID for Customer to add Card to: ");

        // Get Customer ID with helper method
        int id = getID();

        System.out.println("Enter Customer's Credit Card number: ");

        // Get Card number with helper method
        String cardNumber = getCardNumber();

        System.out.println("Enter Card's expiration date: ");

        // Get Card expiration date with helper method
        Date cardExpires = getDate();

        // Create new Card object with gathered info
        Card card = new Card(cardNumber, cardExpires);

        // Pass data to Theater for processing
        theater.addCreditCard(id, card);
    }

    /**
     * Prompts user for Customer ID and Card number to remove Customer's Card
     * with matching number
     */
    private static void removeCreditCard() {
        System.out.println("\n~~~Menu 7:  Remove a Credit Card~~~");
        System.out.println("Enter Customer ID of the owner of the Card to " +
                         "be removed: ");

        // Get Customer ID from helper method
        int id = getID();

        System.out.print("\nEnter the number of the Card to be removed: ");

        // Get Card number from helper method
        String cardNumber = getCardNumber();

        // Pass data to Theater for processing
        theater.removeCreditCard(id, cardNumber);
    }

    /**
     * Displays all Customers currently on record
     */
    private static void listAllCustomers() {
        System.out.println("\n~~~Menu 8:  List of All Customers~~~");
        System.out.println("Listing Customers...");

        // Prompts Theater to display Customers
        theater.listAllCustomers();
    }

    /**
     * Prompts user for data to create a new Show for a specified Client
     */
    private static void addShow() {
        System.out.println("\n~~~Menu 9:  Add a Show~~~");
        System.out.println("Enter ID for Client to add Show to: ");

        // Get Client ID from helper method
        int id = getID();

        // Name not validated so whatever is entered is accepted
        System.out.println("\nEnter a name for the new Show: ");
        String name = input.nextLine();

        // Prompt user for start and end dates of new Show
        Date start, end;
        boolean invalidDates = true;
        do {
            System.out.println("\nEnter a Date for the Show to start " +
                               "showing\n:");
            // Get Show's starting date from helper method
            start = getDate();

            System.out.println("\nEnter a Date for the Show to stop " +
                               "showing:\n");
            // Get Show's ending date from helper method
            end = getDate();

            // Validate entered Dates to ensure the showing period is
            // realistically possible
            if(Validator.validShowDates(start, end)) {
                invalidDates = false;
            }
        }
        // Repeat until Dates entered are valid
        while(invalidDates);

        // Create a new Show with gathered data
        Show show = new Show(name, start, end);

        // Pass data to Theater for processing
        theater.addShow(id, show);
    }

    /**
     * Displays all currently scheduled Shows
     */
    private static void listAllShows() {
        System.out.println("\n~~~Menu 10:  List of All Shows~~~");
        System.out.println("Listing Shows...");

        // Call on Theater to display Shows
        theater.listAllShows();
    }

    /**
     * Saves all current Theater data
     */
    private static void saveSession() {
        System.out.println("\n~~~Menu 11:  Save Session~~~");
        System.out.println("Saving Theater...");

        // Create a new File object named 'theater'
        File file = new File("theater");

        try {
            // If File doesn't already exist, create a new one
            if(!file.exists() && file.createNewFile()) {
                System.out.println("New save file created");
            }
        }
        // Catch any Exceptions from issues creating the File object
        catch(IOException e) {
            System.out.println("Something happened during the creation of the" +
                               " save file\n\n");
            e.printStackTrace();
        }

        try {
            // Output stream to write to a file
            FileOutputStream fileOut = new FileOutputStream("theater");
            // Output stream to convert object to byte stream
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            // Write Theater to save file
            objectOut.writeObject(theater);
            // Close Object output stream
            objectOut.close();
            // Close file output stream
            fileOut.close();
            System.out.println("Save successful!\n\n");
        }
        // Catch any exceptions raised from File not existing
        catch(FileNotFoundException e) {
            System.out.println("Something went wrong and the save file cannot" +
                               " be found\n\n");
            e.printStackTrace();
        }
        // Catch any exceptions raised from Output stream creation
        catch(IOException e) {
            System.out.println("Creating ObjectOutputStream failed\n\n");
            e.printStackTrace();
        }
    }

    /**
     * Loads previously saved Theater object into current session
     */
    private static void loadSession() {
        System.out.println("\n~~~Menu 12:  Load Session~~~");
        System.out.println("Loading Theater...");

        try {
            // Input stream to read bytes from file
            FileInputStream fileIn = new FileInputStream("theater");
            // Input stream to read object from byte stream
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            // Load Theater with previous session's data
            theater = (Theater) objectIn.readObject();
            // Close Object input stream
            objectIn.close();
            // Close File input stream
            fileIn.close();

            System.out.println("Theater successfully loaded!\n\n");
            // Flag session as having loaded saved data
            hasLoaded = true;
        }
        // Catch any exceptions raised from trying to load a file
        catch(FileNotFoundException e) {
            System.out.println("File not found\n\n");
        }
        // Catch any exceptions raised from trying to read an object from file
        catch(IOException e) {
            System.out.println("There was an issue reading the Theater from " +
                               "file\n\n");
            e.printStackTrace();
        }
        // Catch any exceptions raised from casting saved object into a Theater
        catch(ClassNotFoundException e) {
            System.out.println("There was a problem casting saved Object to " +
                               "Theater\n\n");
            e.printStackTrace();
        }
    }

    /**
     * Print descriptions of main menu options
     */
    private static void help() {
        String HELP_TEXT = "\n~~~13:  Help Menu:~~~\n" +
            "0. Exit Program:  Stores session on disk and exits program.\n" +
            "1. Add Client:  Creates a new Client with the supplied name, " +
            "address, and phone number.\n" +
            "2. Remove Client:  Deletes Client with the given ID if there "+
            "are no current or future Plays scheduled.  Any past Plays " +
            "associated with the Client will be deleted as well.\n" +
            "3. List All Clients:  Displays ID, name, address, and phone " +
            "number of each Client.\n" +
            "4. Add Customer:  Creates a new Customer with the supplied name," +
            " address, phone number, credit card number, and credit card " +
            "expiration date.\n" +
            "5. Remove Customer:  Delete a Customer with the given ID.  All " +
            "credit card information associated with the Client is deleted " +
            "as well.\n" +
            "6. Add Credit Card:  Creates new Card for specified Customer" +
            "with supplied card number and expiration date.\n" +
            "7. Remove Credit Card:  Deletes Card with supplied number.  If " +
            "the Card with supplied number is the only Card that it's " +
            "Customer has on record it will not be removed.\n" +
            "8. List All Customers:  Displays ID, name, address, and phone " +
            "number of all Customers including their credit card(s) " +
            "information.\n" +
            "9. Add Show:  Creates new Show for specified Client with " +
            "supplied title, and beginning and ending Dates for Show's " +
            "duration.\n" +
            "10. List All Shows:  List the name and Dates of all shows.\n" +
            "11. Save Session:  Store all data related to the theater to " +
            "disk.\n" +
            "12. Load Session:  Retrieve information related to the theater. " +
            "A saved session can only be loaded once. Load option is " +
            "available at the start of the session or during the session.\n" +
            "13. Help:  Displays everything above.\n\n\n";

        System.out.println(HELP_TEXT);
    }

    /**
     * Processes user input and ensures it's valid for a Card number
     *
     * @return boolean True if input is valid as a Card number, false otherwise
     */
    private static String getCardNumber() {
        String cardNumber = "";

        // While-loop exit flag
        boolean invalidNumber = true;
        do {
            try {
                // Get user input
                cardNumber = input.nextLine();
                // Ensure input is valid Card number and break loop
                if(Validator.validCardNumber(cardNumber)) {
                    invalidNumber = false;
                }
            }
            // Catch any exceptions raised from incorrect input
            catch(InputMismatchException e) {
                System.out.println("Credit Card number must contain only " +
                                   "positive integers");
            }
        }
        // Repeat until valid Card number is entered
        while(invalidNumber);

        return cardNumber;
    }

    /**
     * Processes user input and ensures it's valid for a Date
     *
     * @return boolean True if user input can be parsed into a valid Date,
     *                 false otherwise
     */
    private static Date getDate() {
        Date date;

        // While-loop exit flag
        boolean invalidDate = true;
        do {
            // Get user input
            String expirationDate = input.nextLine();
            // Parse into a Date
            date = Parser.parseDate(expirationDate);

            // If Date is not null, break loop
            if(date != null) {
                invalidDate = false;
            }
        }
        // Repeat until valid Date is entered
        while(invalidDate);

        return date;
    }

    /**
     * Processes user input and ensures it's valid for an ID
     *
     * @return boolean True if user input is valid ID, false otherwise
     */
    private static int getID() {
        int id = -1;

        // While-loop exit flag
        boolean invalidID = true;
        do {
            try {
                // If user input can be parsed into an Integer...
                id = Integer.parseInt(input.nextLine());
                // break loop
                invalidID = false;
            }
            // Catch any exception raised from trying to parse a String not
            // parseable to an Integer
            catch(Exception e) {
                System.out.println("ID must be a positive integer");
            }
        }
        // Repeat until valid ID is entered
        while(invalidID);

        return id;
    }

    /**
     * Processes user input and ensures it's valid for a phone number
     *
     * @return boolean True if input can be parsed into a phone number, false
     *                 otherwise
     */
    private static String getPhoneNumber() {
        String phone = "";
        // While-loop exit flag
        boolean invalidPhone = true;
        do {
            try {
                // Get user input
                phone = input.nextLine();
                // If it can be used as a phone number...
                if(Validator.validPhone(phone)) {
                    // break loop
                    invalidPhone = false;
                }
            }
            // Catch any exceptions raised from parsing input containing
            // anything but integers
            catch(InputMismatchException e) {
                System.out.println("Phone number must contain only numbers");
            }
        }
        // Repeat until valid input received
        while(invalidPhone);

        return phone;
    }
}
