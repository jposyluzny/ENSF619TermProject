package Payment;

/**
 * This will handle taking the PaymentInfo entity class and storing it in the Financial Institution class.
 */
public class ProcessPaymentController {
	
	private ProcessPayment processPayment;
	
	/**
	 * This creates and sets a new instance of a ProcessPayment object.
	 */
	public ProcessPaymentController() {
		this.setProcessPayment(new ProcessPayment());
	}
	
	/**
	 * This will pass the PaymentInfo objects along to be stored in the Financial Institution class. It will also confirm
	 * if a payment or refund had been stored.
	 * @param payment is the PaymentInfo object.
	 */
	public void confirmPayment(PaymentInfo payment) {
		this.getProcessPayment().addPaymentToList(payment);
		this.checkTransactionType(payment);
	}
	
	/**This method call will display a confirmation of payment message to the user.
	 * True is for a reservation, false is for a refund
	 * @param payment is the PaymentInfo object.
	 */
	public void checkTransactionType(PaymentInfo payment) {
		if (payment.getType())
			this.getProcessPayment().sendPaymentMessageToUser();
		else
			this.getProcessPayment().sendRefundMessageToUser();
	}

	public ProcessPayment getProcessPayment() {
		return processPayment;
	}

	public void setProcessPayment(ProcessPayment processPayment) {
		this.processPayment = processPayment;
	}

}
