package Theatre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller class for handling interactions between user and the database controllers
 */
public class ModelController {
    /**
     * DBController objects
     */
    MovieDBController movieDBController;
    TheatreDBController theatreDBController;
    ShowtimeDBController showtimeDBController;
    SeatDBController seatDBController;
    VoucherDBController voucherDBController;

    /**
     * ArrayList of movies from database
     */
    ArrayList<Movie> movies;
    /**
     * ArrayList of vouchers from database
     */
    ArrayList<Voucher> vouchers;

    /**
     * Basic constructor for ModelController
     */
    public ModelController(){
        movieDBController = MovieDBController.getSingleInstance();
        theatreDBController = TheatreDBController.getSingleInstance();
        showtimeDBController = ShowtimeDBController.getSingleInstance();
        seatDBController = SeatDBController.getSingleInstance();
        voucherDBController = VoucherDBController.getSingleInstance();

        movies = getMovies();
        populateVouchers();
    }

    /**
     * Method to get all movies and their associated information from the database
     * @return ArrayList<Movie>
     */
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

    /**
     * Fills the passed ArrayList<Movie> with its associated theatre and showtimes
     * @param movies
     */
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

    /**
     * Gets showtimes for a movie, returning them as an ArrayList
     * @param movieid Movie to check
     * @return ArrayList<Showtime> containing all showtimes for selected movie
     */
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

    /**
     * Returns an arraylist of seats for the selected showtime
     * @param showid Showtime to check seats for
     * @return ArrayList<Seat> for given showtime id
     */
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

    /**
     * Method to populate vouchers with ones in database
     */
    private void populateVouchers(){
        ResultSet dbvouchers = voucherDBController.getVouchers();
        vouchers = new ArrayList<Voucher>();
        try {
            while (dbvouchers.next()) {
                Voucher newVoucher = new Voucher(dbvouchers.getInt("count"), dbvouchers.getString("email"));
                vouchers.add(newVoucher);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Check if entered email has any attached vouchers
     * @param email Email to check
     * @return Voucher object if it exists, null if not
     */
    public Voucher findVoucher(String email){
        return Voucher.findByEmail(vouchers, email);
    }

    /**
     * Remove voucher from ArrayList
     * @param voucher Voucher to remove
     */
    public void removeVoucher(Voucher voucher) {
    	for (Voucher v: this.getVouchers()) {
    		if (v == voucher) {
    			this.getVouchers().remove(v);
    			return;
    		}
    	}
    }

    /**
     * Development method that is was only called once to populate the initial seat entries in the database
     */
    public static void populateDBwithSeats(){
        ModelController a = new ModelController();
        ArrayList<Movie> b = a.getMovies();
        a.seatDBController.populateInitialDBSeats(b);
        System.out.println("Done");
    }

    /**
     * Method to find movie based on passed id
     * @param movieid ID to check
     * @return Movie object matching ID, null if none found
     */
    public Movie getMovieById(int movieid){
        for(Movie m: movies){
            if(m.getId() == movieid)
                return m;
        }
        System.out.println("Failed to find movie by ID: " + movieid);
        return null;
    }

    /**
     * Basic getter for movielist
     * @return ArrayList of Movie objects
     */
    public ArrayList<Movie> getMovieList(){
        return movies;
    }

    /**
     * Basic getter for vouchers
     * @return ArrayList of Voucher objects
     */
	public ArrayList<Voucher> getVouchers() {
		return vouchers;
	}

    public static void main(String[] args) {
        ModelController a = new ModelController();
        Voucher v = a.findVoucher("testemail@fake.com");
        System.out.println("Done");
    }

}
