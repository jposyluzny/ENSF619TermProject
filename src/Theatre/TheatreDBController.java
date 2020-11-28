package Theatre;

import java.sql.*;

/**
 * Controller class for the theatre database controller. This class functions on the assumption that the SQL database
 * has already been initialized and populated (this is done through two .sql scripts)
 * NOTE: This class is a stub and is unused, as we were told to assume we're making an app for one theatre.
 */
public class TheatreDBController extends DBController{

    static TheatreDBController singleInstance = null;

    public static TheatreDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new TheatreDBController();
        }
        return singleInstance;
    }
}
