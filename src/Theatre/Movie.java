package Theatre;

import java.util.ArrayList;

/**
 * Movie class, for storing various information about a movie
 */
public class Movie {

    /**
     * Name of movie
     */
    private String name;
    /**
     * ID of movie
     */
    private int id;

    /**
     * ArrayList of Theatres that are playing this movie
     */
    private ArrayList<Theatre> theatres;

    /**
     * Basic constructor for movie
     * @param id MovieID
     * @param name Name of Movie
     */
    public Movie(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Basic setter for Theatres
     * @param theatres ArrayList of Theatre objects
     */
    public void setTheatres(ArrayList<Theatre> theatres) {
        this.theatres = theatres;
    }

    /**
     * Basic getter for Theatres
     * @return ArrayList of Theatre objects
     */
    public ArrayList<Theatre> getTheatres() {
        return theatres;
    }

    /**
     * Basic getter for Movie Name
     * @return String of Movie name
     */
    public String getName() {
        return name;
    }

    /**
     * Basic getter for Movie ID
     * @return Int of Movie
     */
    public int getId() {
        return id;
    }

}
