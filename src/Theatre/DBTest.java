package Theatre;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        MovieDBController a = new MovieDBController();
        ShowtimeDBController b = new ShowtimeDBController();
        SeatDBController c = new SeatDBController();
        TheatreDBController d = new TheatreDBController();
    }
}
