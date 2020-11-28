package Theatre;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Movie {

    private String name;
    private int id;

    private ArrayList<Theatre> theatres;
    public MovieDBController dbController;

    public Movie(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setTheatres(ArrayList<Theatre> theatres) {
        this.theatres = theatres;
    }

    public ArrayList<Theatre> getTheatres() {
        return theatres;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

}
