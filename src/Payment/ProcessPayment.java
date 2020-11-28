package Payment;

public class ProcessPayment {
	
	public void addPaymentToList(PaymentInfo payment) {
		FinancialInstitution.addToFI(payment);
	}
	
	public static void clearPaymentList() {
		FinancialInstitution.clearPayments();
	}
	
	//Will need to change this to a String return to display in the GUI instead of the console
	public void sendPaymentMessageToUser() {
		System.out.println("Payment completed successfully.");
	}
	
	//Will need to change this to a String return to display in the GUI instead of the console
	public void sendRefundMessageToUser() {
		System.out.println("Refund completed successfully.");
	}

}
