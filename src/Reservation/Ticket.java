package Reservation;

import Theatre.*;

/**
 * This class will hold all of the information associated with the Users selections for a reservation.
 */
public class Ticket {
	
	private int ticketNumber;
	private String emailAddress;
	private Theatre theatre;
	private Movie movie;
	private Showtime showtime;
	private Seat seat;
	private int seatNumber;
	
	/**
	 * This will set all of the ticket reservation attributes for each Ticket.
	 * @param ticketNumber is the unique ticket number.
	 * @param emailAddress is the email address the user enters.
	 * @param theatre is the Theatre object the user has selected.
	 * @param movie is the Movie object the user has selected.
	 * @param showtime is the Showtime object the user has selected.
	 * @param seat is the Seat object the user has selected.
	 */
	public Ticket(int ticketNumber, String emailAddress, Theatre theatre, Movie movie, Showtime showtime, Seat seat) {
		this.setTicketNumber(ticketNumber);
		this.setEmailAddress(emailAddress);
		this.setTheatre(theatre);
		this.setMovie(movie);
		this.setShowtime(showtime);
		this.setSeat(seat);
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
		return "".format("Ticket Number: %d\n Theatre Name: %s\n  Movie Name: %s\n Showtime: %s\n Seat Number: %s\n Email Address: %s\n",
				   		  this.getTicketNumber(), this.getTheatre().getName(), this.getMovie().getName(),
				   		  this.getShowtime().getTime(), this.getSeat().getSeatNumber(), this.getEmailAddress());
	}

}
