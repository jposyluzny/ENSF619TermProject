package Reservation;

import java.util.ArrayList;
import java.util.Iterator;

import Payment.PaymentInfo;

public class Cancellation {
	
	private PaymentInfo paymentInfo;
	public ArrayList<Ticket> ticketsList;
	
	public Cancellation() {
		this.setTicketsList(new ArrayList<Ticket> ());
	}
	
	public ArrayList<Ticket> lookForUsersTickets(String emailAddress) {
		ArrayList<Ticket> arr = new ArrayList<Ticket> ();
		for (Ticket t: StoreTickets.getTickets()) {
			if (t.getEmailAddress().equals(emailAddress))
				arr.add(t);
		}
		if (arr.size() == 0)
			return null;
		else
			return arr;
	}
	
	public void removeCancelledTickets(String emailAddress) {
		Iterator<Ticket> iter = StoreTickets.getTickets().iterator();

		while (iter.hasNext()) {
		    Ticket ticket = iter.next();
		    if (ticket.getEmailAddress().equals(emailAddress))
		        iter.remove();
		}
	}
	
	public void enterRefundInfo(String creditCard, String description, int amount) {
		this.setPaymentInfo(new PaymentInfo(creditCard, description, amount, false));
	}
	
	public void confirmRefund(String emailAddress, String creditCard, String description, int amount) {
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

	public ArrayList<Ticket> getTicketsList() {
		return ticketsList;
	}

	public void setTicketsList(ArrayList<Ticket> ticketsList) {
		this.ticketsList = ticketsList;
	}

}
