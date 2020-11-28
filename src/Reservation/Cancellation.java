package Reservation;

import java.util.ArrayList;

import Payment.PaymentInfo;

public class Cancellation {
	
	private PaymentInfo paymentInfo;
	public ArrayList<Ticket> ticketsList;
	
	//gets ticket numbers to be cancelled from GUI
	public void confirmCancellation(ArrayList<Integer> ticketNumbers) {
		//Will be the kick off method to begin the cancellation process of removing tickets and processing refund.
	}
	
	public void removeTicketsFromDatabase(ArrayList<Integer> ticketNumbers) {
		TicketDBController tDBC = new TicketDBController();
		tDBC.deleteTicketsFromDatabase(ticketNumbers);
	}
	
	//processes cancellation
	public void verifyCancellation() {
		this.setPaymentInfo(new PaymentInfo());
		this.getPaymentInfo().fetchPaymentInformation(this);
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
					   ticket.getMovie().getName()));
		return str.toString();
	}

}
