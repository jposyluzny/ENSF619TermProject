package Reservation;

public class ManageCancellationController {
	
	private Cancellation cancellation;
	
	public ManageCancellationController() {
		this.setCancellation(new Cancellation());
	}

	public Cancellation getCancellation() {
		return cancellation;
	}

	public void setCancellation(Cancellation cancellation) {
		this.cancellation = cancellation;
	}
	
}
