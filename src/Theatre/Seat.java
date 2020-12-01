package Theatre;

/**
 * Seat class, containing information about a seat for a showtime
 */
public class Seat {

	/**
	 * Seat number
	 */
    private int seatNumber;
	/**
	 * Showtime ID
	 */
	private int showid;
	/**
	 * Boolean showing whether seat is already occupied
	 */
    private boolean isOccupied;

	/**
	 * Basic constructor for Seat
	 * @param showid Showtime ID
	 * @param seatNumber Seat number
	 * @param isOccupied Boolean seat status
	 */
    public Seat(int showid, int seatNumber, boolean isOccupied){
        this.showid = showid;
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
    }

	/**
	 * Basic getter for SeatNumber
	 * @return seatNumber
	 */
	public int getSeatNumber() {
		return seatNumber;
	}

	/**
	 * Basic setter for seatNumber
	 * @param seatNumber seatNumber int to set
	 */
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * Basic getter for showid
	 * @return showid
	 */
	public int getShowid() {
		return showid;
	}

	/**
	 * Basic setter for showid
	 * @param showid showid int to set
	 */
	public void setShowid(int showid) {
		this.showid = showid;
	}

	/**
	 * Basic getter for isOccupied
	 * @return isOccupied
	 */
	public boolean isOccupied() {
		return isOccupied;
	}

	/**
	 * Basic setter for isOccupied
	 * @param isOccupied boolean to set
	 */
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

}
