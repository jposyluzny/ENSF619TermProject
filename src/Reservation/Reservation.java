package Reservation;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import Payment.*;
import Theatre.*;

/**
 * This class will handle processing the users selections, directing the stages of the reservation process (booking the Tickets,
 * accepting the payment information, and sending it along to the proper classes to proceed). 
 */
public class Reservation {
	
	private PaymentInfo paymentInfo;
	private String date;
	private static int ticketNumber = 10000;
	private boolean paymentValid;
	
	/**
	 * This sets the date of the reservation.
	 */
	public Reservation() {
		this.setDate(this.createDate());
	}
	
	/**
	 * This will build the Ticket objects (one for each Seat object the User has selected), store the Ticket objects in the StoreTickets
	 * class, update the seat objects to true, which will signify that the seat has been reserved by a User and cannot be reserved by
	 * another User, and increment the ticket number so no other tickets will have the same unique ticket Number field.
	 * @param emailAddress is the users email address.
	 * @param theatre is the Theatre object the User has selected.
	 * @param movie is the Movie object the User has selected.
	 * @param showtime is the Showtime object the User has selected.
	 * @param seats is an ArrayList holding all of the Seat objects the User has selected.
	 */
	public void buildTickets(String emailAddress, Theatre theatre, Movie movie, Showtime showtime, ArrayList<Seat> seats) {
		if (seats == null)
			return;
		for (Seat seat: seats) {
			StoreTickets.addTickets(new Ticket(getTicketNumber(), emailAddress, theatre, movie, showtime, seat));
			seat.setOccupied(true);
			incrementTicketNumber();	
		}
	}
	
	/**
	 * Increments the ticketNumber field to ensure that no 2 Ticket objects have the same Ticket Number field.
	 */
	public static void incrementTicketNumber() {
		ticketNumber++;
	}
	
	/**
	 * Creates the current date.
	 * @return the current Date as a String.
	 */
	public String createDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}
	
	/**
	 * This will create a new PaymentInfo object and pass the users information along to be stored in that class. The Type is fixed 
	 * as true as true represents a reservation, and false is for cancellations
	 * @param creditCard is the Users credit card number;
	 * @param description is the Users credit card expiry date.
	 * @param amount is the cost of the users reservation based on how many Seats they select.
	 */
	public void enterPayment(String creditCard, String description, double amount) {
		this.setPaymentInfo(new PaymentInfo(creditCard, description, amount, true));
	}
	
	/**
	 * Calls the PaymentInfo class to pass the information to the Financial Institution class simulating the Bank.
	 */
	public void confirmPayment() {
		this.getPaymentInfo().confirmPayment();
	}
	
	public static int getTicketNumber() {
		return ticketNumber;
	}
	
	public static void setTicketNumber(int val) {
		ticketNumber = val;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public boolean isPaymentValid() {
		return paymentValid;
	}

	public void setPaymentValid(boolean paymentValid) {
		this.paymentValid = paymentValid;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

}
