package Theatre;

import java.util.ArrayList;

/**
 * Showtime class, containing information about a Movie object's showtime
 */
public class Showtime {

    /**
     * Showtime id
     */
    private int id;

    /**
     * Associated ID for Movie Object
     */
    private int movieid;

    /**
     * Screen the Movie is playing on
     */
    private int screen;

    /**
     * Date of show
     */
    private String date;
    /**
     * Time of show
     */
    private String time;

    /**
     * ArrayList containing all seat information for the given showtime
     */
    private ArrayList<Seat> seats;

    /**
     * Basic constructor for Showtime
     * @param id ID of Showtime
     * @param movieid ID for associated Movie object
     * @param screen Screen number
     * @param date Date of show
     * @param time Time of show
     */
    public Showtime(int id, int movieid, int screen, String date, String time){
        this.id = id;
        this.movieid = movieid;
        this.screen = screen;
        this.date = date;
        this.time = time;
    }

    /**
     * Basic getter for time
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * Basic getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Basic getter for movieid
     * @return movieid
     */
    public int getMovieid() {
        return movieid;
    }

    /**
     * Basic getter for screen
     * @return screen
     */
    public int getScreen() {
        return screen;
    }

    /**
     * Basic getter for date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Basic getter for seats
     * @return seats
     */
    public ArrayList<Seat> getSeats() {
        return seats;
    }

    /**
     * Basic setter for seats
     * @param seats ArrayList of seats
     */
    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}
