import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author bmwil
 *
 */

// Class name was lowercase, corrected and refactored by Kyle Heneman to
// include Serializable implementation
class Card implements Serializable {

    // These were given to me with package level access.
    // Private level access added by Kyle Heneman
    private String cardNumber;
    private Date expDate;
    // Attribute not needed, commented out by Kyle Heneman
    // Integer cardID;

  //Constructor
    /*
     * cardId parameter removed by Kyle Heneman
     */
    public Card(String cardNumber, Date expDate){
        this.cardNumber = cardNumber;
        this.expDate = expDate;

        // Commented out by Kyle Heneman
        //this.cardID = cardID;
    }

// Unneeded second constructor?  Commented out by Kyle Heneman
//    public Card(){
//    }

/*
 * This Class was given to me without any mutators and accessors.
 * The following added by Kyle Heneman
 */

    /**
     * Returns this Card's cardNumber
     *
     * @return String representing this Card's `cardNumber`
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets this Card's `cardNumber`
     *
     * @param cardNumber String
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Returns this Card's expiration date
     *
     * @return String containing this Card's expiration date
     */
    private Date getExpirationDate() {
        return expDate;
    }

    /**
     * Sets this Card's expiration date
     *
     * @param expDate String containing date for Card to expire
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    /**
     * Builds a String representation of this Card
     *
     * @return String String representing this Card's data
     */
    public String toString() {
        String info = "";
        String expires = Formatter.formatDate(getExpirationDate());

        info += "\t\t{\n";
        info += "\t\t\tCard Number: " + getCardNumber() + ",\n";
        info += "\t\t\tExpiration Date: " + expires + "\n";
        info += "\t\t}";

        return info;
    }
}
