package Theatre;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Controller class for the Movie database controller. This class functions on the assumption that the SQL database
 * has already been initialized and populated (this is done through two .sql scripts)
 */
public class SeatDBController extends DBController{

    static SeatDBController singleInstance = null;

    public static SeatDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new SeatDBController();
        }
        return singleInstance;
    }

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
