package Reservation;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
	
	private ArrayList<Ticket> ticketsList;
	private String date;
	private static int ticketNumber = 10000;
	
	public Reservation() {
		this.setTicketsList(new ArrayList<Ticket> ());
		this.setDate(this.createDate());
	}
	
	public void buildTickets(int ticketNumber, Theatre theatre, Movie movie, Showtime showtime, ArrayList<Seat> seats) {
		for (Seat seat: seats) {
			this.addTicketToTicketsList(new Ticket(ticketNumber, theatre, movie, showtime, seat));
			incrementTicketNumber();
		}
	}
	
	public static void incrementTicketNumber() {
		ticketNumber = getTicketNumber() + 1;
	}
	
	public String createDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}
	
	public void addTicketToTicketsList(Ticket ticket) {
		this.getTicketsList().add(ticket);
	}
	
	public void clearTicketsList() {
		this.getTicketsList().clear();
	}
	
	public static int getTicketNumber() {
		return ticketNumber;
	}
	
	public static void setTicketNumber(int val) {
		ticketNumber = val;
	}

	public ArrayList<Ticket> getTicketsList() {
		return ticketsList;
	}

	public void setTicketsList(ArrayList<Ticket> ticketsList) {
		this.ticketsList = ticketsList;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void displayReservation() {
		System.out.println("Displaying all tickets in reservation.\n");
		System.out.println("Reservation made on: " + this.getDate());
		for (Ticket ticket: this.getTicketsList())
			System.out.println(ticket);
	}

}