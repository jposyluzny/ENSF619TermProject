package Reservation;

import java.util.ArrayList;

/**
 * This will handle cancellation interactions between the User and the Cancellation class.
 */
public class ManageCancellationController {
	
	private Cancellation cancellation;
	
	/**
	 * This will create and set a new Cancellation object.
	 */
	public ManageCancellationController() {
		this.setCancellation(new Cancellation());
	}

	/**
	 * This will call the Cancellation objects method to look for all of the Users reserved Tickets. It will pass along the Users
	 * email address as an argument.
	 * @param emailAddress is the Users email address.
	 * @return is an ArrayList of Ticket objects holding all of the Tickets that have a matching email addresses.
	 */
	public ArrayList<Ticket> makeCancellation(String emailAddress) {
		this.setCancellation(new Cancellation());
		return this.getCancellation().lookForUsersTickets(emailAddress);
	}

	/**
	 * This will handle passing all of the Users refund information along to the Cancellation class.
	 * @param emailAddress is the Users email address.
	 * @param creditCard is the Users credit card number.
	 * @param description is the Users credit card expiry date.
	 * @param amount is the amount to be refunded to the User.
	 */
	public void confirmCancellation(String emailAddress, String creditCard, String description, double amount) {
		this.getCancellation().confirmRefund(emailAddress, creditCard, description, amount);
	}

	public Cancellation getCancellation() {
		return cancellation;
	}

	public void setCancellation(Cancellation cancellation) {
		this.cancellation = cancellation;
	}
	
}
