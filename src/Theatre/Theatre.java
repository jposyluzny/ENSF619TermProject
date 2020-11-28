package Theatre;

import java.util.ArrayList;

public class Theatre {

    public String name;
    public ArrayList<Showtime> showtimes;

    public String getName() {
        return name;
    }

    public Theatre(String name){
        this.name = name;
    }

    public void setShowtimes(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }
}
