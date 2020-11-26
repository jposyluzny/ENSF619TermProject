package Reservation;

import java.util.ArrayList;

import Payment.PaymentInfo;

public class Cancellation {
	
	private ArrayList<Ticket> ticketsList;
	private PaymentInfo paymentInfo;
	
	public Cancellation() {
		this.setTicketsList(new ArrayList<Ticket> ());
	}
	
	public void confirmCancellation(int ticketNumber) {
		//fetch input from GUI
		//will 
	}
	
	public void verifyCancellation() {
		this.setPaymentInfo(new PaymentInfo());
		this.getPaymentInfo().fetchPaymentInformation(this);
	}
	
	public void removeTicketsFromDB() {
		//call method from TicketDBController to delete tickets from DB
	}

	public ArrayList<Ticket> getTicketsList() {
		return ticketsList;
	}

	public void setTicketsList(ArrayList<Ticket> ticketsList) {
		this.ticketsList = ticketsList;
	}
	
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for (Ticket ticket: this.getTicketsList())
			str.append("".format("Your ticket number %d for %s has been successfully cancelled.\n", ticket.getTicketNumber(),
					   ticket.getMovie().getMovieName()));
		return str.toString();
	}

}
