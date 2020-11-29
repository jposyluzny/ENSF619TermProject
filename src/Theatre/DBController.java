package Theatre;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Abstract class containing general database information and methods.
 * This class functions on the assumption that the SQL database
 * has already been initialized and populated (this is done through two .sql scripts)
 */
public class DBController {
    /**
     * Connection to database
     */
    protected Connection jdbc_connection;

    /**
     * Prepared statement for making queries
     */
    protected PreparedStatement statement;

    /**
     * Server connection information
     */
    protected String connectionInfo = "jdbc:mysql://localhost:3306/movieapp";

    /**
     * Username
     */
    protected String login = "dbuser";

    /**
     * Password
     */
    protected String password = "dbpass";

    /**
     * Constructor for DBController.
     */
    protected DBController(){
        if(jdbc_connection == null) {
            try {
                // If this throws an error, make sure you have added the mySQL connector JAR to the project
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);

                // If this fails make sure your connectionInfo and login/password are correct
                jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
                System.out.println("Connected to: " + connectionInfo + "\n");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
