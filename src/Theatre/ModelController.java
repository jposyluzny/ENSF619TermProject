package Theatre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO: Consider renaming
public class ModelController {
    MovieDBController movieDBController;
    TheatreDBController theatreDBController;
    ShowtimeDBController showtimeDBController;
    SeatDBController seatDBController;

    public ModelController(){
        movieDBController = MovieDBController.getSingleInstance();
        theatreDBController = TheatreDBController.getSingleInstance();
        showtimeDBController = ShowtimeDBController.getSingleInstance();
        seatDBController = SeatDBController.getSingleInstance();
    }

    public ArrayList<Movie> getMovies(){
        ArrayList<Movie> movielist = new ArrayList<>();
        ResultSet dbmovies = movieDBController.initializeMovies();
        try {
            while (dbmovies.next()) {
                Movie newMovie = new Movie(dbmovies.getInt("movieid"), dbmovies.getString("name"));
                movielist.add(newMovie);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        populateTheatresAndShowtimes(movielist);
        return movielist;
    }

    // We only have one theatre, as specified
    private void populateTheatresAndShowtimes(ArrayList<Movie> movies){
        for(Movie m: movies){
            ArrayList<Theatre> theatres = new ArrayList<>();
            Theatre newTheatre = new Theatre("MainTheatre");
            newTheatre.setShowtimes(showtimesToArray(m.getId()));
            // Add all seats to each showtime
            for(Showtime s: newTheatre.getShowtimes()){
                s.setSeats(seatsToArray(s.getId()));
            }
            theatres.add(newTheatre);
            m.setTheatres(theatres);
        }
    }

    private ArrayList<Showtime> showtimesToArray(int movieid){
        ResultSet dbshowtimes = showtimeDBController.getShowtimes(movieid);
        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
        try {
            while (dbshowtimes.next()) {
                Showtime newShowtime = new Showtime(dbshowtimes.getInt("showid"),
                        dbshowtimes.getInt("movieid"),
                        dbshowtimes.getInt("screen"),
                        dbshowtimes.getString("date"),
                        dbshowtimes.getString("time"));
                showtimes.add(newShowtime);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return showtimes;
    }

    private ArrayList<Seat> seatsToArray(int showid){
        ResultSet dbseats = seatDBController.getSeats(showid);
        ArrayList<Seat> seats = new ArrayList<Seat>();
        try {
            while (dbseats.next()) {
                Seat newSeat = new Seat(dbseats.getInt("showid"),
                        dbseats.getInt("number"),
                        dbseats.getBoolean("isoccupied"));
                seats.add(newSeat);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seats;
    }

    private static void populateDBwithSeats(){
        ModelController a = new ModelController();
        ArrayList<Movie> b = a.getMovies();
        a.seatDBController.populateInitialDBSeats(b);
        System.out.println("Done");
    }

    public static void main(String[] args) {
        ModelController a = new ModelController();
        ArrayList<Movie> b = a.getMovies();

        System.out.println("Done");
    }
}
