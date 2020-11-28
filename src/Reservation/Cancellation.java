package Reservation;

import java.util.ArrayList;

import Payment.PaymentInfo;

public class Cancellation {
	
	private PaymentInfo paymentInfo;
	
	//returns null if there are no tickets attached to that email address.
	public ArrayList<Ticket> getAllTickets(String emailAddress) {
		ArrayList<Ticket> arr = StoreTickets.getTickets();
		arr = this.lookForUsersTickets(emailAddress, arr);
		if (arr.size() == 0)
			return null;
		else
			return arr;
	}
	
	public ArrayList<Ticket> lookForUsersTickets(String emailAddress, ArrayList<Ticket> arr) {
		ArrayList<Ticket> userTickets = new ArrayList<Ticket> ();
		for (Ticket t: arr) {
			if (t.getEmailAddress().equals(emailAddress))
				userTickets.add(t);
		}
		return userTickets;
	}
	
	public void removeCancelledTickets(ArrayList<Ticket> arr) {
		for (Ticket t: StoreTickets.getTickets()) {
			for (Ticket tick: arr) {
				if (t == tick)
					StoreTickets.getTickets().remove(t);
			}
		}
	}
	
	//Amount here is off?
	public void enterRefundInfo(String creditCard, String description, int amount) {
		this.setPaymentInfo(new PaymentInfo(creditCard, description, amount, false));
	}
	
	public void confirmRefund() {
		this.getPaymentInfo().confirmPayment();
	}
	
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	
}
