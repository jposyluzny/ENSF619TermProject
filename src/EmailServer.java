
public class EmailServer {

	private RegisteredUser rUser;

	public String sendMoviePaymentConfirmation() {
		return "Ticket payment confirmed!";
	}

	public String sendFeePaymentConfirmation() {
		return "Annual fee payment confirmed!";
	}
	
	public String sendRefundConfirmation() {
		return "Ticket refund confirmed!";
	}
	
	public RegisteredUser getrUser() {
		return rUser;
	}

	public void setrUser(RegisteredUser rUser) {
		this.rUser = rUser;
	}
}
