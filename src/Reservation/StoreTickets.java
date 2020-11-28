package Reservation;

import java.util.ArrayList;

public class StoreTickets {
	
	private static ArrayList<Ticket> tickets = new ArrayList<Ticket> ();
	
	public static void addTickets(Ticket ticket) {
		getTickets().add(ticket);
	}
	
	public static void clearTickets() {
		getTickets().clear();
	}

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void setTickets(ArrayList<Ticket> tickets) {
		StoreTickets.tickets = tickets;
	}
	

}
