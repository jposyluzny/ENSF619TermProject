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

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getShowid() {
		return showid;
	}

	public void setShowid(int showid) {
		this.showid = showid;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

}
