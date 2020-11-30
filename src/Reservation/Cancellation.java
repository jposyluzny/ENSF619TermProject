package Reservation;

import java.util.ArrayList;
import java.util.Iterator;

import Payment.PaymentInfo;

/**
 * This will handle performing the cancellation required procedures when the User decides to cancel their previously made 
 * reservation.
 */
public class Cancellation {
	
	private PaymentInfo paymentInfo;

	/**
	 * This will look for Ticket objects based on the users input email address and the email address associated with the Ticket
	 * objects.
	 * @param emailAddress is the Users email address.
	 * @return is an ArrayList of Ticket objects matching the Users email address.
	 */
	public ArrayList<Ticket> lookForUsersTickets(String emailAddress) {
		ArrayList<Ticket> arr = new ArrayList<Ticket> ();
		for (Ticket t: StoreTickets.getTickets()) {
			if (t.getEmailAddress().equals(emailAddress))
				arr.add(t);
		}
		return arr;
	}
	
	/**
	 * This will remove Ticket objects from the StoreTickets "Storage" class to simulate canceling Tickets. It will also update
	 * the boolean field indicating whether a seat has been reserved or not.
	 * @param emailAddress is the Users email addresss.
	 */
	public void removeCancelledTickets(String emailAddress) {
		Iterator<Ticket> iter = StoreTickets.getTickets().iterator();
		while (iter.hasNext()) {
		    Ticket ticket = iter.next();
		    if (ticket.getEmailAddress().equals(emailAddress)) {
		    	ticket.getSeat().setOccupied(false);
		        iter.remove();
		    }
		}
	}
	
	/**
	 * This will create a new PaymentInfo class (holding all of the users refund information) and set it to this Cancellation class
	 * @param creditCard is the Users credit card number.
	 * @param description is the Users credit card expiry date.
	 * @param amount is the amount to be refunded to the User.
	 */
	public void enterRefundInfo(String creditCard, String description, double amount) {
		this.setPaymentInfo(new PaymentInfo(creditCard, description, amount, false));
	}
	
	/**
	 * This will create a new PaymentInfo object and pass the users information along to be stored in that class. The Type is fixed 
	 * as false as false represents a cancellation, and true is for reservations.
	 * @param emailAddress is the Users email address.
	 * @param creditCard is the Users credit card number.
	 * @param description is the Users credit card expiry date.
	 * @param amount is the amount to be refunded to the User.
	 */
	public void confirmRefund(String emailAddress, String creditCard, String description, double amount) {
		this.enterRefundInfo(creditCard, description, amount);
		this.getPaymentInfo().confirmPayment();
		this.removeCancelledTickets(emailAddress);
	}
	
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

}
