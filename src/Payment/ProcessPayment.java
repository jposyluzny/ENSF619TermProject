package Payment;

/**
 * This class will pass along information to the Financial Institution class.
 */
public class ProcessPayment {
	
	/**
	 * This will add the PaymentInfo objects to the List in the Financial Institution class.
	 * @param payment is the PaymentInfo object holding the users Payment information.
	 */
	public void addPaymentToList(PaymentInfo payment) {
		FinancialInstitution.addToFI(payment);
	}
	
	/**
	 * Clears the "banks" records (list holding the PaymentInfo objects).
	 */
	public static void clearPaymentList() {
		FinancialInstitution.clearPayments();
	}
	
	/**
	 * Notifies of a successful payment
	 */
	public void sendPaymentMessageToUser() {
		System.out.println("Payment completed successfully.");
	}
	
	/**
	 * Notifies of a successful refund.
	 */
	public void sendRefundMessageToUser() {
		System.out.println("Refund completed successfully.");
	}

}
