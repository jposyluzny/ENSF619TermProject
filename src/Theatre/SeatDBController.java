package Theatre;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Controller class for the Seat database controller. This class functions on the assumption that the SQL database
 * has already been initialized and populated (this is done through two .sql scripts)
 */
public class SeatDBController extends DBController{

    /**
     * Single instance of SeatDBController
     */
    static SeatDBController singleInstance = null;

    /**
     * Method for getting single instance of SeatDBController
     * @return SeatDBController instance
     */
    public static SeatDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new SeatDBController();
        }
        return singleInstance;
    }

    /**
     * Method to get all seats for a given showid
     * @param showid showid to check
     * @return ResultSet containing all seats
     */
    public ResultSet getSeats(int showid){
        String sql = "SELECT * FROM seat WHERE seat.showid=?";
        ResultSet showtimes = null;
        try {
            statement = jdbc_connection.prepareStatement(sql);
            statement.setInt(1, showid);
            showtimes = statement.executeQuery();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return showtimes;
    }

    /**
     * Developer Method called only once for creating initial seats in database
     * @param movies ArrayList of movies to add seats for
     */
    public void populateInitialDBSeats(ArrayList<Movie> movies){
        String sql = "INSERT INTO seat VALUES(?,?,?)";
        for(Movie m: movies){
            for(Theatre t: m.getTheatres()){
                for(Showtime s: t.getShowtimes()){
                    for(int i = 0; i < 20; i++){
                        Random random = new Random();
                        try {
                            statement = jdbc_connection.prepareStatement(sql);
                            statement.setInt(1, s.getId());
                            statement.setInt(2, i);
                            statement.setBoolean(3, random.nextBoolean());

                            statement.executeUpdate();

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
