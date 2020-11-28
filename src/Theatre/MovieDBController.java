package Theatre;

import java.sql.*;
import java.util.ArrayList;

/**
 * Controller class for the Movie database controller. This class functions on the assumption that the SQL database
 * has already been initialized and populated (this is done through two .sql scripts)
 */
public class MovieDBController extends DBController{

    static MovieDBController singleInstance = null;

    public ResultSet initializeMovies(){
        String sql = "SELECT * FROM movie";
        ResultSet movies = null;
        try {
            statement = jdbc_connection.prepareStatement(sql);
            movies = statement.executeQuery();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movies;
    }

    public static MovieDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new MovieDBController();
        }
        return singleInstance;
    }

    public static void main(String[] args) {
        MovieDBController a = new MovieDBController();
    }
}
