package Payment;

import RegisteredUser.RUserAccountController;

public class PaymentInfo {
	
	private ProcessPaymentController processPaymentController;
	private String creditCard, description; //expiration date instead of description
	private double amount; 
	//True will be for payments, false will be for refunds.
	private boolean type;
	
	public PaymentInfo (String creditCard, String description, double amount, boolean type) {
		this.setCreditCard(creditCard);
		this.setDescription(description);
		this.setAmount(amount);
		this.setType(type);
		this.setProcessPaymentController(new ProcessPaymentController());
	}

	public void confirmPayment() {
		this.getProcessPaymentController().confirmPayment(this);
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
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
		return "".format("Creditcard Number: %s\n Payment amount: %f\n", this.getCreditCard(), this.getAmount());
	}

}
