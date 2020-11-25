package Reservation;

import java.util.ArrayList;

public class Cancellation {
	
	private ArrayList<Ticket> ticketsList;
	
	public Cancellation() {
		
	}

	public ArrayList<Ticket> getTicketsList() {
		return ticketsList;
	}

	public void setTicketsList(ArrayList<Ticket> ticketsList) {
		this.ticketsList = ticketsList;
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
