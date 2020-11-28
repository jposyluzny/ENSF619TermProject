package Payment;

public class ProcessPayment {
	
	public void addPaymentToList(PaymentInfo payment) {
		FinancialInstitution.addToFI(payment);
	}
	
	public static void clearPaymentList() {
		FinancialInstitution.clearPayments();
	}
	
	public String sendPaymentMessageToUser() {
		return "Payment completed successfully.";
	}
	
	public String sendRefundMessageToUser() {
		return "Refund completed successfully.";
	}

}
