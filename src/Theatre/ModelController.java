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
    VoucherDBController voucherDBController;

    ArrayList<Movie> movies;
    ArrayList<Voucher> vouchers;

    public ModelController(){
        movieDBController = MovieDBController.getSingleInstance();
        theatreDBController = TheatreDBController.getSingleInstance();
        showtimeDBController = ShowtimeDBController.getSingleInstance();
        seatDBController = SeatDBController.getSingleInstance();
        voucherDBController = VoucherDBController.getSingleInstance();

        movies = getMovies();
        populateVouchers();
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

    public Voucher findVoucher(String email){
        return Voucher.findByEmail(vouchers, email);
    }
    
    public void removeVoucher(Voucher voucher) {
    	for (Voucher v: this.getVouchers()) {
    		if (v == voucher) {
    			this.getVouchers().remove(v);
    			return;
    		}
    	}
    }

    // Call this once to setup database
    public static void populateDBwithSeats(){
        ModelController a = new ModelController();
        ArrayList<Movie> b = a.getMovies();
        a.seatDBController.populateInitialDBSeats(b);
        System.out.println("Done");
    }

    public Movie getMovieById(int movieid){
        for(Movie m: movies){
            if(m.getId() == movieid)
                return m;
        }
        System.out.println("Failed to find movie by ID: " + movieid);
        return null;
    }

    public ArrayList<Movie> getMovieList(){
        return movies;
    }
    
	public ArrayList<Voucher> getVouchers() {
		return vouchers;
	}

    public static void main(String[] args) {
        ModelController a = new ModelController();
        Voucher v = a.findVoucher("testemail@fake.com");
        System.out.println("Done");
    }

}
