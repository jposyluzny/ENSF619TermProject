package Theatre;

import java.util.ArrayList;

/**
 * Theatre class, containing information about a Movie object's Theatre
 */
public class Theatre {

    /**
     * Name of Theatre
     */
    public String name;

    /**
     * ArrayList containing all showtimes at this theatre for a given movie
     */
    public ArrayList<Showtime> showtimes;

    /**
     * Basic constructor for Theatre
     * @param name name of theatre
     */
    public Theatre(String name){
        this.name = name;
    }

    /**
     * Basic getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Basic setter for showtimes
     * @param showtimes ArrayList of Showtime objects
     */
    public void setShowtimes(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * Basic getter for showtimes
     * @return showtimes
     */
    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }
}
