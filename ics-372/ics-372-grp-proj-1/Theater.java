import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Theater class that maintains a collection of customers and
 * clients, along with their respective information.  Theater
 * class will assign unique IDs to each customer and client.
 * Theater will keep track of the balance due to a client.
 * In addition, Theater will sell tickets of any amount only to
 * customers who are registered members of the theater. Lastly,
 * the theater will keep track of the seating capacity.
 * 
 * @author Steven Lee
 * October 16, 2015
 *
 */

/*
 * Originally, Theater extended UserInterface, I have no idea why but I've
 * added the correct Interface implementation to the signature.  Commented
 * out by Kyle Heneman
 */
//public class Theater extends UserInterface {
//	private int NUMBER_OF_TICKETS;
//	private int remainingCapacity;
//	private int remainingTickets = NUMBER_OF_TICKETS;
//	private double balanceDueToClient;


/*
 * Class implemented by Kyle Heneman
 */
class Theater implements Serializable {
    // Customer count as well as ID generator
	private int customerID = 0;
    // Client count as well as ID generator
	private int clientID = 0;

    // Theater name and capacity not currently used
    private final String THEATER_NAME;
    private final int CAPACITY;

    // Lists for Customers and Clients
	private final List<Customer> customerList = new ArrayList<Customer>();
	private final List<Client> clientList = new ArrayList<Client>();

/*
 * We don't need to implement the Singleton pattern in this first iteration.
 * Commented out by Kyle Heneman
 */

////-------------Singleton stuff----------------------------------------------Singleton stuff-------------------------------
//	/**
//	 * Singleton property
//	 */
//	/*
//	protected Theater() {
//	}*/
//
//	/**
//	 * Checks to see whether or not an instance of theater already exists.
//	 * @return singleton the instance of theater if no other instances
//	 *         exits.
//	 */
//	public static Theater instance() {
//		if (singleton == null) {
//			singleton = new Theater();
//		}
//		return singleton;
//	}
//
//
//------------Theater properties ---------------------------------------------Theater properties -----------------------------

    /*
     * We need a constructor since we're not implementing the Singleton
     * pattern.  Commented out by Kyle Heneman
     */
//	/**
//	 * Sets the name of the theater, its seating capacity, and the number
//	 * of tickets that it will sell.
//	 * @param name the name of the theater
//	 * @param seatingCapacity the seating capacity of the theater
//	 * @param totalTickets the number of tickets the theater will sell
//	 */
//	public void setTheater(String name, int seatingCapacity,
//			int totalTickets) {
//		theaterName = name;
//		CAPACITY = seatingCapacity;
//		NUMBER_OF_TICKETS = totalTickets;
//	}


    /**
     * Constructs and initializes a new Theater object.
     * Written by Kyle Heneman
     */
    public Theater(String name, int capacity) {
        this.THEATER_NAME = name;
        this.CAPACITY = capacity;
    }


//------------Customer stuff---------------------------------------------------Customer stuff--------------------------------
	/**
	 * Adds a customer to the customer list.  Also generates a unique ID
	 * for the customer.
     * @param name the name of the customer
     * @param address the address of the customer
     * @param phoneNumber the phone number of the customer
     * //@param creditCardNumber the credit card number of the customer
     */
	public void addCustomer(String name, String address, String phoneNumber,
			                Card card){
        /*
         * The following implemented by Kyle Heneman
         */

        // Build a new Customer with supplied data
		Customer newCustomer = new Customer(name,
                                         address,
                                         phoneNumber,
                                         customerID);
        // Add supplied Card to Customer
        newCustomer.addCard(card);

        // Add new Customer to Customer List
		customerList.add(newCustomer.getID(), newCustomer);
        System.out.println("Customer was successfully added!\n\n");

        // Increment Customer Count
		customerID++;
	}
	
	/**
	 * Removes a customer matching the unique ID, who is stored at the location 
	 * in the list indicated by there unique I.D.
	 * @param uniqueID the unique I.D. that was generated for the customer
	 */
	public void removeCustomer(int uniqueID) {
        // Commented out by Kyle Heneman
        //customerList.set(uniqueID, null);  //customer is null at uniqueID index

        /*
         * The following implemented by Kyle Heneman
         */
        try {
            // Remove Customer from List that has ID matching `uniqueID`
            // (also the index of Customer's position in List)
            customerList.remove(uniqueID);
            System.out.println(String.format("Customer %s successfully " +
                                             "deleted!\n\n", uniqueID));
        }
        // Catch any exceptions raised from invalid indices being entered
        catch(NullPointerException e) {
            System.out.println("There is no Customer with that ID\n\n");
        }
	}
	
	/**
	 * Prints information about every customer.
	 */
	public void listAllCustomers(){
        /*
         * Commented out by Kyle Heneman
         */
        //for(int i = 0; i < customerList.size(); i++) {
        //    System.out.println(customerList.get(i));
        //    System.out.println(customer.getCustomerCreditCard() //<--- or however you name the method in your class
        //}

        /*
         * The following implemented by Kyle Heneman
         */
        // If Customers exist
        if(customerList.size() >0) {
            String delim = "";

            // For each Customer
            for(Customer customer : customerList) {
                // Print their data
                System.out.print(delim + customer.toString());
                delim = ",\n";
            }
            System.out.println("\n\n");
        }
        else {
            // Otherwise let user know that no Customers exist
            System.out.println("There are no Customers yet!\n\n");
        }
	}

    /**
     * Adds supplied Card to the Customer's List that has an ID matching `id`
     * Implemented by Kyle Heneman
     */
    public void addCreditCard(int id, Card card) {
        // Add Card to Customer with matching ID
        customerList.get(id).addCard(card);
        System.out.println(String.format("Card added to Customer %s " +
                                         "successfully!\n\n", id));
    }

    /**
     * Removes Card matching `cardNumber` from Customer matching `id`.
     * Implemented by Kyle Heneman
     *
     * @param id ID of Customer to remove Card from
     * @param cardNumber Number of Card to be removed
     */
    public void removeCreditCard(int id, String cardNumber) {
        // If there is only 1 Card left it cannot be removed
        if(customerList.get(id).getCardCount() == 1) {
            System.out.println("That's the only Card on file for this " +
                               "Customer!  It cannot be removed");
        }
        else {
            // Remove the Card from the corresponding Customer's List
            customerList.get(id).removeCard(cardNumber);
            System.out.println(String.format("Successfully removed Card %s " +
                                             "from Customer %s!\n\n",
                                             cardNumber, id));
        }
    }

	
//--------------------------Client Stuff---------------------------------------Client Stuff---------------------------
	
	/**
	 * Adds a client to the client list.  Also generates a unique ID
	 * for the client. Balance due to client is set to zero for this Iteration.
	 * @param name the name of the client
	 * @param address the address of the client
	 * @param phoneNumber the phone number of the client
	 */
	public void addClient(String name, String address, String phoneNumber){
//        client = new Client(name, address, phoneNumber, clientID);
//        customerList.add(clientID, client); /* adds client at the index
//												 * indicated by their unique
//												 * ID.                     */
//        clientID++;
//        balanceDueToClient = 0;

        /*
         * The following implemented by Kyle Heneman
         */
		Client client = new Client(address, phoneNumber, name, clientID);
		clientList.add(clientID, client);

        System.out.println("\nClient was successfully added!\n\n");
		clientID++;
	}
	
	/**
	 * Removes a client matching the unique ID, who is stored at the location 
	 * in the list indicated by there unique I.D.  Removes client only if they
	 * don't have a current show or any future showings.
	 * 
	 * @param uniqueID the unique I.D. that was generated for the customer
	 */
	public void removeClient(int uniqueID) {
        //if(!hasCurrentShow() && !futureShows()) {  //<---------Or however you named or handled it in your class (I'll change this to match what you have).
        //    clientList.set(uniqueID, null);  //customer is null at uniqueID index
        //}

        /*
         * The following implemented by Kyle Heneman
         */
        try {
            // If Client doesn't have a future or current Show
            if(clientList.get(uniqueID).isRemovable()) {
                // Remove Client from List
                clientList.remove(uniqueID);
                System.out.println(String.format("\nClient %s was " +
                                                 "successfully deleted!\n\n",
                                                 uniqueID));
            }
            // Otherwise let user know they can't be removed
            else {
                System.out.println("\nThis Client has either a currently " +
                                   "showing or upcoming Show.  They cannot" +
                                   " be removed\n\n");
            }
        }
        // Catch any exceptions raised from incorrect IDs
        catch(NullPointerException e) {
            System.out.println("There is no Client with that ID\n\n");

        }
	}
	
	/**
	 * Prints information about every client.
	 */
	public void listAllClients() {
        // Commented out by Kyle Heneman
        //for(int j = 0; j < clientList.size(); j++) {
        //    System.out.println(clientList.get(j));
        //    System.out.println(client.getClientShowDates() //<--------Or however you named your method to retrieve a client's show dates
        //}

        /*
         * The following implemented by Kyle Heneman
         */
        // If Clients exist
        if(clientList.size() > 0) {
            String delim = "";

            // Print each Client's information
            for(Client client : clientList) {
                System.out.print(delim + client.toString());
                delim = ",\n";
            }
            System.out.println("\n\n");
        }
        // Otherwise let user know no Clients yet exist
        else {
            System.out.println("There are no Clients yet!\n\n");
        }
	}

    /**
     * Displays all scheduled Shows
     */
	public void listAllShows() {
        String showDelim = "";

        // If there are no Clients there can be no Shows
        if(clientList.isEmpty()) {
            System.out.println("There are no Clients to add Shows to yet!");
        }

        // For each Client
        for(Client client : clientList) {
            // If they have at least one show
            if(client.showCount() > 0) {
                // Print Show's information
                System.out.println(showDelim + client.listShows());
                showDelim = ",\n";
            }
        }
        System.out.println("\n\n");
	}

    /**
     * Adds Show to a Client's List
     *
     * @param id ID of Client to add Show to
     * @param show Show to add to specified Client's List
     */
    public void addShow(int id, Show show) {
        try {
            // Add show to Client's List
            clientList.get(id).addShow(show);
            System.out.println(String.format("Show %s successfully added to " +
                                             "Client %s!", show.getName(),id));
        }
        // Catch any exceptions raised from incorrect IDs
        catch(IndexOutOfBoundsException e) {
            System.out.println(String.format("Client %s doesn't exist yet!",
                                             id));
        }

    }

	
//-----------Getters, setters, and other methods specific to theater class---------------------------------------

    /*
     * These are all duplicate methods.  Commented out by Kyle Heneman
     */

//	/**
//	 * Retrieve a single customer and their information from the customer
//	 * list.
//	 * @param uniqueID the unique ID that was generated for the customer at
//	 * 					a prior time.
//	 * @return the customer and their information listed at the unique ID
//	 * 			index.
//	 */
//	public Customer getCustomer(int uniqueID) {
//		return customerList.get(uniqueID);
//	}
//
//	/**
//	 * Edits an existing customer's personal information.
//	 * @param uniqueID the ID unique to the specific customer
//	 */
//	public void setCustomer(int uniqueID) {
//		Scanner editCustomer = new Scanner(System.in);
//		String newName;
//		String newAddress;
//		String newPhoneNumber;
//
//		System.out.println("Please enter the changes you would like to make "
//				+ "to the existing customer.\n");
//
//		System.out.println("Name: ");
//		newName = editCustomer.nextLine();
//
//		System.out.println("Address: ");
//		newAddress = editCustomer.nextLine();
//
//		System.out.println("Phone number: ");
//		newPhoneNumber = editCustomer.nextLine();
//
//		customer.setCustomerName(newName);
//		customer.setCustomerAddress(newAddress);
//		customer.setCustomerPhoneNumber(newPhoneNumber);
//	}
//
//	/**
//	 * Retrieve a single client and their information from the client
//	 * list.
//	 * @param uniqueID the unique ID that was generated for the customer at
//	 * 			a prior time.
//	 * @return the customer and their information listed at the unique ID
//	 * 			index.
//	 */
//	public Client getClient(int uniqueID) {
//		return clientList.get(uniqueID);
//	}
//
//	/**
//	 * Edits an existing client's personal information.
//	 * @param uniqueID the ID unique to the specific client
//	 */
//	public void setClient(int uniqueID) {
//		Scanner editClient = new Scanner(System.in);
//		String newName;
//		String newAddress;
//		String newPhoneNumber;
//
//		System.out.println("Please enter the changes you would like to make "
//				+ "to the existing customer.\n");
//
//		System.out.println("Name: ");
//		newName = editClient.nextLine();
//
//		System.out.println("Address: ");
//		newAddress = editClient.nextLine();
//
//		System.out.println("Phone number: ");
//		newPhoneNumber = editClient.nextLine();
//
//		customer.setClientName(newName);
//		customer.setClientAddress(newAddress);
//		customer.setClientPhoneNumber(newPhoneNumber);
//	}
//
//	/**
//	 * Sells the specified amount of tickets to a theater member (customer).
//	 * Verified by the customer's name and address on record.
//	 *
//	 * @param customerName the customer's name
//	 * @param customerAddress the customer's address
//	 */
//	public void sellTicket(String customerName, String customerAddress,
//			int numberOfTicketsRequested) {
//		if(customer.getCustomerName().equals(customerName)
//				&& customer.getCustomerAddress().equals(customerAddress)){
//			remainingTickets -= numberOfTicketsRequested;
//			remainingCapacity -= numberOfTicketsRequested;
//		}
//	}
}
