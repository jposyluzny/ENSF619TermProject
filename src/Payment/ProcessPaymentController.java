package Payment;

public class ProcessPaymentController {
	
	private ProcessPayment processPayment;
	
	public ProcessPaymentController() {
		this.setProcessPayment(new ProcessPayment());
	}
	
	public void processPayment(PaymentInfo payment) {
		FinancialInstitution.addPaymentToList(payment);
		
		//this method call will display a confirmation of payment message to the user.
		if (payment.getType())
			this.getProcessPayment().sendPaymentMessageToUser();
		else
			this.getProcessPayment().sendRefundMessageToUser();
	}
	
	public void processRefund(PaymentInfo payment) {
		
	}

	public ProcessPayment getProcessPayment() {
		return processPayment;
	}

	public void setProcessPayment(ProcessPayment processPayment) {
		this.processPayment = processPayment;
	}

}
