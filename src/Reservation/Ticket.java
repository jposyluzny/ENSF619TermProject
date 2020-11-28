package Reservation;

public class Ticket {
	
	//NOTE: I have a Seat object here instead of a primitive seatNumber type (int).
	private int ticketNumber;
	private String emailAddress;
	private Theatre theatre;
	private Movie movie;
	private Showtime showtime;
	private Seat seat;
	
	public Ticket(int ticketNumber, String emailAddress, Theatre theatre, Movie movie, Showtime showtime, Seat seat) {
		this.setTicketNumber(ticketNumber);
		this.setEmailAddress(emailAddress);
		this.setTheatre(theatre);
		this.setMovie(movie);
		this.setShowtime(showtime);
		this.setSeat(seat);
		storeTickets();
	}
	
	public static void storeTickets() {
		StoreTickets.addTickets(this);
	}
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public int getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Showtime getShowtime() {
		return showtime;
	}
	
	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}
	
	public Seat getSeat() {
		return seat;
	}
	
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	@Override
	public String toString() {
		return "".format("Ticket Number: %d\n Theatre Name: %s\n  Movie Name: %s\n Showtime: %s\n Seat Number: %s\n",
				   		  this.getTicketNumber(), this.getTheatre().getTheatreName(), this.getMovie().getMovieName(),
				   		  this.getShowtime().getTime(), this.getSeat().getSeatNumber());
	}

}
