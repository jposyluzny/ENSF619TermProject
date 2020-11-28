package Reservation;

import java.util.ArrayList;

public class ManageCancellationController {
	
	private Cancellation cancellation;
	private ArrayList<Integer> ticketNumbers;

	
	public ManageCancellationController() {
		this.setCancellation(new Cancellation());
	}
	
	//returns null if no tickets are found
	public ArrayList<Ticket> makeCancellation(String emailAddress) {
		ArrayList<Ticket> arr = this.getCancellation().getAllTickets(emailAddress);
		if (arr == null)
			return null;
		else
			return arr;
	}
	
	public void removeCancelledTickets(ArrayList<Ticket> arr) {
		this.getCancellation().removeCancelledTickets(arr);
	}
	
	public void confirmCancellation(String creditCard, String description, int amount) {
		this.getCancellation().enterRefundInfo(creditCard, description, amount);
	}

	public Cancellation getCancellation() {
		return cancellation;
	}

	public void setCancellation(Cancellation cancellation) {
		this.cancellation = cancellation;
	}
	
}
