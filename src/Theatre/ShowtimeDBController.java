package Theatre;

import java.sql.*;

/**
 * Controller class for the theatre database controller. This class functions on the assumption that the SQL database
 * has already been initialized and populated (this is done through two .sql scripts)
 */
public class ShowtimeDBController extends DBController{

    static ShowtimeDBController singleInstance = null;

    public static ShowtimeDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new ShowtimeDBController();
        }
        return singleInstance;
    }

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
