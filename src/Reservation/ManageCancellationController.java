package Reservation;

import java.util.ArrayList;

public class ManageCancellationController {
	
	private Cancellation cancellation;
	
	public ManageCancellationController() {
		this.setCancellation(new Cancellation());
	}

	//Returns all tickets associated with a cancellation
	public ArrayList<Ticket> makeCancellation(String emailAddress) {
		this.setCancellation(new Cancellation());
		return this.getCancellation().lookForUsersTickets(emailAddress);
	}

	//"Processes and sends the cancellation to the financial institution
	public void confirmCancellation(String emailAddress, String creditCard, String description, int amount) {
		this.getCancellation().confirmRefund(emailAddress, creditCard, description, amount);
	}

	public Cancellation getCancellation() {
		return cancellation;
	}

	public void setCancellation(Cancellation cancellation) {
		this.cancellation = cancellation;
	}
	
}
