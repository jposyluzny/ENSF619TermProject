package Reservation;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import Payment.*;
import Theatre.*;

public class Reservation {
	
	private PaymentInfo paymentInfo;
	private ArrayList<Ticket> ticketsList;
	private String date;
	private static int ticketNumber = 10000;
	private boolean paymentValid;
	
	public Reservation() {
		this.setTicketsList(new ArrayList<Ticket> ());
		this.setDate(this.createDate());
	}
	
	public void buildTickets(String emailAddress, Theatre theatre, Movie movie, Showtime showtime, ArrayList<Seat> seats) {
		for (Seat seat: seats) {
			this.addTicketToTicketsList(new Ticket(getTicketNumber(), emailAddress, theatre, movie, showtime, seat));
			incrementTicketNumber();
		}
	}
	
	public static void incrementTicketNumber() {
		ticketNumber++;
	}
	
	public String createDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}
	
	//type is fixed as true as true represents a reservation, false is for Cancellations
	public void enterPayment(String creditCard, String description, double amount) {
		this.setPaymentInfo(new PaymentInfo(creditCard, description, amount, true));
	}
	
	public void confirmPayment() {
		this.getPaymentInfo().confirmPayment();
	}
	
	public void displayReservation() {
		System.out.println("Displaying all tickets in reservation.\n");
		System.out.println("Reservation made on: " + this.getDate());
		for (Ticket ticket: this.getTicketsList())
			System.out.println(ticket);
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
	
	public boolean isPaymentValid() {
		return paymentValid;
	}

	public void setPaymentValid(boolean paymentValid) {
		this.paymentValid = paymentValid;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

}
