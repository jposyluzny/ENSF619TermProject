package Theatre;

import java.sql.*;

/**
 * Controller class for the theatre database controller. This class functions on the assumption that the SQL database
 * has already been initialized and populated (this is done through two .sql scripts)
 * NOTE: This class is a stub and is unused, as we were told to assume we're making an app for one theatre.
 */
public class TheatreDBController extends DBController{

    /**
     * Single instance of TheatreDBController
     */
    static TheatreDBController singleInstance = null;

    /**
     * Method for getting single instance of TheatreDBController
     * @return TheatreDBController instance
     */
    public static TheatreDBController getSingleInstance() {
        if(singleInstance == null){
            singleInstance = new TheatreDBController();
        }
        return singleInstance;
    }
}
