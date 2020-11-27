
public class EmailServer {

	private RegisteredUser rUser;

	public String sendPaymentConfirmation() {
		return "Payment confirmed!";
	}

	public String sendRefundConfirmation() {
		return "Refund confirmed!";
	}
	
	public RegisteredUser getrUser() {
		return rUser;
	}


	public void setrUser(RegisteredUser rUser) {
		this.rUser = rUser;
	}
}
