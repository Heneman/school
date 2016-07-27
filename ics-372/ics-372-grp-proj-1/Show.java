import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Roma
 */

// Serializable implemented by Kyle Heneman
class Show implements Serializable
{
    /**
     * attributes of Show. Names pretty self explanatory.
     * The dates are ints that = (year*10000)+(month*100)+day
     */
    /*
     * This file was given to me with package level access for this Class'
     * fields.  Private access given by Kyle Heneman
     */
    private String name;
    private Date beginningDate;
    private Date endingDate;
    
    
/*
 * Commented out by Kyle Heneman
 */
//    /**
//     * conflictsWith is a helper for ShowList method AddShow.
//     * it checks if any of the dates of the current show and the show passed in
//     * as input, conflict.
//     * @param other
//     * @return
//     */
//    public boolean conflictsWith(Show other)
//    {
//        if (beginningDate>other.endingDate)
//        {
//            return true;
//        }
//
//        if (endingDate<other.beginningDate)
//        {
//            return true;
//        }
//
//        else return false;
//    }


    /**
     * Documented by Kyle Heneman
     *
     * @param name Name of new Show
     * @param beginningDate Starting Date for new Show
     * @param endingDate Ending Date for new Show
     */
    public Show(String name, Date beginningDate, Date endingDate)
    {
        this.name = name;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
    }

/*
 * Since the ID shouldn't ever change I've made it `final` and commented out
 * the setter method
 */
//    public void setID(int ID)
//    {
//        this.ID = ID;
//    }


    /*
     * This file was given to me without setters, getters, or helper methods.
     * The following added by Kyle Heneman
     */

    /**
     * Returns Show's name beginning date
     *
     *
     * @return String containing Show's name beginning date
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Show's name to supplied String beginning date
     *
     *
     * @param name String containing Show's name beginning date
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns Show's beginning date
     *
     * @return String containing Show's beginning date
     */
    public Date getBeginningDate() {
        return beginningDate;
    }

    /**
     * Sets Show's beginning date to supplied String
     *
     * @param beginningDate String containing Show's beginning date
     */
    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    /**
     * Returns Show's ending date
     *
     * @return String containing Show's ending date
     */
    public Date getEndingDate() {
        return endingDate;
    }

    /**
     * Sets Client's ending date to supplied String
     *
     * @param endingDate String containing Show's ending date
     */
    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    /**
     * Builds String representation of this Show
     *
     * @return String containing a representation of this Show
     */
    public String toString() {
        String info = "";
        String start = Formatter.formatDate(getBeginningDate());
        String end = Formatter.formatDate(getEndingDate());

        info += "{\n";
        info += "\tName: " + getName() + ",\n";
        info += "\tBeginning Date: " + start + ",\n";
        info += "\tEnding Date: " + end + "\n";
        info += "}";

        return info;
    }
}
