package Payment;

public class ProcessPaymentController {
	
	private ProcessPayment processPayment;
	
	public ProcessPaymentController() {
		this.setProcessPayment(new ProcessPayment());
	}
	
	public void confirmPayment(PaymentInfo payment) {
		this.getProcessPayment().addPaymentToList(payment);
		checkTransactionType(payment);
	}
	
	//this method call will display a confirmation of payment message to the user.
	//True is for a reservation, false is for a refund
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
