package Reservation;

import java.util.ArrayList;

public class ManageCancellationController {
	
	private Cancellation cancellation;
	private ArrayList<Integer> ticketNumbers;

	
	public ManageCancellationController() {
		this.setCancellation(new Cancellation());
	}
	
	public void getCancellationInfo() {
		//NEED TO FETCH ticketNumbers TO BE CANCELLED FROM GUI
		this.getCancellation().confirmCancellation(ticketNumbers);
	}

	public Cancellation getCancellation() {
		return cancellation;
	}

	public void setCancellation(Cancellation cancellation) {
		this.cancellation = cancellation;
	}
	
}
