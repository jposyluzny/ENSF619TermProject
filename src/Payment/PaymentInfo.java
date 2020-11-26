package Payment;

import Reservation.*;

public class PaymentInfo {
	
	private ProcessPaymentController processPaymentController;
	private String creditCard, description;
	private int amount; 
	//true is for regular payments, false is for cancellation refunds.
	private boolean type;

	//This method will be called to pass info the getPaymentInfoFields method.
	public void fetchPaymentInformation(Cancellation c) {

		//NEED TO HAVE CODE HERE
		//		this.getPaymentInfoFields(creditCard, description, amount, type);
	}

	//This method will be called to pass info the getPaymentInfoFields method.
	public void fetchPaymentInformation(Reservation r) {

		//NEED TO HAVE CODE HERE
		//			this.getPaymentInfoFields(creditCard, description, amount, type);
	}
	
	//Need to have information fetched into this method.
	public void getPaymentInfoFields(String creditCard, String description, int amount, boolean type) {
		this.setCreditCard(creditCard);
		this.setDescription(description);
		this.setAmount(amount);
		this.setType(type);
		this.setProcessPaymentController(new ProcessPaymentController());
	}
	
	public void sendToProcessPaymentController() {
		this.getProcessPaymentController().processPayment(this);
	}
	
	public ProcessPaymentController getProcessPaymentController() {
		return processPaymentController;
	}

	public void setProcessPaymentController(ProcessPaymentController processPaymentController) {
		this.processPaymentController = processPaymentController;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public boolean getType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "".format("Creditcard Number: %s\n Payment amount: %d\n", this.getCreditCard(), this.getAmount());
	}

}
