package Theatre;

import java.sql.*;

/**
 * Controller class for the Showtime database controller. This class functions on the assumption that the SQL database
 * has already been initialized and populated (this is done through two .sql scripts)
 */
public class ShowtimeDBController extends DBController{

    /**
     * Single instance of ShowtimeDBController
     */
    static ShowtimeDBController singleInstance = null;

    /**
     * Method for getting single instance of ShowtimeDBController
     * @return ShowtimeDBController instance
     */
    public static ShowtimeDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new ShowtimeDBController();
        }
        return singleInstance;
    }

    /**
     * Method to get all Showtimes for a given movieid
     * @param movieid movieid to check
     * @return ResultSet containing all Showtimes
     */
    public ResultSet getShowtimes(int movieid){
        String sql = "SELECT * FROM showtime WHERE showtime.movieid=?";
        ResultSet showtimes = null;
        try {
            statement = jdbc_connection.prepareStatement(sql);
            statement.setInt(1, movieid);
            showtimes = statement.executeQuery();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return showtimes;
    }
}
