package Theatre;

public class Seat {

    private int seatNumber;
    private int showid;
    private boolean isOccupied;

    public Seat(int showid, int seatNumber, boolean isOccupied){
        this.showid = showid;
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getShowid() {
        return showid;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

}
