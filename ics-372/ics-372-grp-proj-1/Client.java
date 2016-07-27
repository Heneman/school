import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Roma
 */
class Client implements Serializable
{
    /**
     * attributes of client, self-explanitory
     * 
     * ID is creating using the built in Hash-Code function due to problems with
     * using the size of the list or the position of the client.
     */
    /*
     * The following were given to me with package level access,
     * switched to private by Kyle Heneman
     */
    private String name;
    private String address;
    private String phoneNumber;
    private double balance;
    private final int ID;
    // Show List added by Kyle Heneman
    private final List<Show> shows = new ArrayList<Show>();
    
    public Client(String addr, String phone, String name, int ID)
    {
        // `this` specifier added by Kyle Heneman
        this.name = name;
        this.phoneNumber = phone;
        this.address = addr;
        this.balance = 0;
        this.ID = ID;
    }

    /*
     * This file was given to me without getters, setters, nor helper methods.
     * The following added by Kyle Heneman
     */

    /**
     * Tests if a Client is removable from the list
     *
     * @return boolean True if the Client has no current or upcoming Shows,
     *                 false otherwise
     */
    public boolean isRemovable() {
        Date now = new Date();
        boolean removable = true;

        // For each Show this Client has
        for(Show show : shows) {
            // If the Show starts in the future or is currently playing
            if(now.compareTo(show.getBeginningDate()) < 0 ||
               now.compareTo(show.getEndingDate()) < 0) {
                // The Client cannot be removed
                removable = false;
            }
        }
        return removable;
    }

    /**
     * Returns this Client's name
     *
     * @return String containing this Client's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Client's name to given String
     *
     * @param name String for Client's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns Client's address
     *
     * @return String containing Client's name
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets Client's address to given String
     *
     * @param address String containing Client's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns Client's phone number
     *
     * @return int containing Client's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets Client's phone number to given int
     *
     * @param phoneNumber int containing Client's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Return's Client's current balance
     *
     * @return double containing Client's balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets Client's balance to given double
     *
     * @param balance double containing Client's balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns Client's ID
     *
     * @return int containing this Client's ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Accepts a Show to add to this Client's List
     *
     * @param show Show to add to Client's List
     */
    public void addShow(Show show) {
        this.shows.add(show);
    }

    /**
     * Lists all current and upcoming shows for each Client
     *
     * @return String This Show's data in a format to be displayed
     */
    public String listShows() {
        String info = "";
        String delim = "";

        // Add each Show's data to the info String
        for(Show show : shows) {
            info += delim + show.toString();
            delim = ",\n";
        }

        return info;
    }

    /**
     * Returns number of Shows this Client has on record
     *
     * @return int Number of Shows this Client has on record
     */
    public int showCount() {
        return this.shows.size();
    }

    /**
     * Builds a String representation of this Client
     *
     * @return String Representation of this Client
     */
    public String toString() {
        String info = "";
        // Format phone number for display
        String phone = Formatter.formatPhone(getPhoneNumber());
        // Format currency for display
        String balance = Formatter.formatCurrency(getBalance());

        info += "{\n";
        info += "\tID: " + getID() + ",\n";
        info += "\tName: " + getName() + ",\n";
        info += "\tAddress: " + getAddress() + ",\n";
        info += "\tPhone: " + phone + ",\n";
        info += "\tBalance: " + balance + "\n";
        info += "}";

        return info;
    }
}
