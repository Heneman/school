import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author bmwil
 *
 */
class Customer implements Serializable {
    // Attributes were package level, made private by Kyle Heneman
    private String name;
    private String address;

    // Missing phone number field, added by Kyle Heneman
    private String phoneNumber;

    private final int ID;
    private final List<Card> cards = new ArrayList<Card>();

//Constructor
    /*
     * Constructor was missing phone number parameter.  Added by Kyle Heneman
     */
    public Customer(String name, String address, String phoneNumber, int ID){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ID = ID;
    }

/*
 * Unneeded second constructor?  Commented out by Kyle Heneman
 */
//    public Customer(){
//    }


/*
 * Class was missing accessors, mutators, and helper methods when given to me...
 * The following written by Kyle Heneman
 */

    /**
     * Adds new Card to this Customer's List
     *
     * @param newCard Card to add to Customer's List
     */
    public void addCard(Card newCard) {
        this.cards.add(newCard);
    }

    /**
     * Removes a Card that matches `cardNumber` from this Client
     *
     * @param cardNumber Number for Card to be removed
     */
    public void removeCard(String cardNumber) {
        // Placeholder Card
        Card card;
        // `cards` iterator
        ListIterator<Card> iterator = cards.listIterator();

        // While `iterator` has more Cards
        while(iterator.hasNext()) {
            // Placeholder holds current Card
            card = iterator.next();

            // If current Card has a `number` that equals `cardNumber`
            if(card.getCardNumber().equals(cardNumber)) {
                // Remove it
                iterator.remove();
            }
        }
    }

    /**
     * Returns the quantity of Cards held by this Customer
     *
     * @return int Count of Cards held by this Customer
     */
    public int getCardCount() {
        return cards.size();
    }

    /**
     * Returns this Customer's ID
     *
     * @return int representing this Customers ID
     */
    public Integer getID(){
        return ID;
    }

    /**
     * Returns this Customer's name
     *
     * @return String representing name of this Costumer
     */
    public String getName(){
        return name;
    }

    /**
     * Sets this Customer's name
     *
     * @param name String to set this Customer's name to
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns the address of this Customer
     *
     * @return String containing this Customer's address
     */
    public String getAddress(){
        return address;
    }

    /**
     * Sets this Customer's address
     *
     * @param address String to set this Customer's address to
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Returns this Customer's phone number
     *
     * @return int representing this Customer's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets this Customer's phone number
     *
     * @param phoneNumber String to be this Customer's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Builds a String representation of this Customer
     *
     * @return String String representation of this Customer
     */
    public String toString() {
        String info = "";
        String phone = Formatter.formatPhone(getPhoneNumber());

        info += "{\n";
        info += "\tID: " + getID() + ",\n";
        info += "\tName: " + getName() + ",\n";
        info += "\tAddress: " + getAddress() + ",\n";
        info += "\tPhone: " + phone + ",\n";
        info += "\tCredit Cards: [\n";

        String delim = "";
        for(Card card : cards) {
            info += delim + card.toString();
            delim = ",\n";
        }

        info += "\n\t]\n";
        info += "}";

        return info;
    }
}
