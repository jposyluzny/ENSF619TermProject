package Reservation;

import java.util.ArrayList;

/**
 * This will simulate a database storing the Ticket objects in lieu of writing these objects to the database everytime new Tickets are
 * created.
 */
public class StoreTickets {
	
	private static ArrayList<Ticket> tickets = new ArrayList<Ticket> ();
	
	/**
	 * Stores Ticket objects in the tickets ArrayList.
	 * @param ticket is the Ticket object.
	 */
	public static void addTickets(Ticket ticket) {
		getTickets().add(ticket);
	}
	
	/**
	 * This will clear the List object holding the Ticket objects.
	 */
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
