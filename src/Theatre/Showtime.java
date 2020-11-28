package Theatre;

import java.util.ArrayList;
import java.util.Date;

public class Showtime {

    private int id;
    private int movieid;
    private int screen;

    private String date;
    private String time;

    private ArrayList<Seat> seats;

    public Showtime(int id, int movieid, int screen, String date, String time){
        this.id = id;
        this.movieid = movieid;
        this.screen = screen;
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}