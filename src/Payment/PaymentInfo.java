package Payment;

/**
 * This class will hold all of the Users payment or refund information. It will also handle passing the object along to the 
 * Financial Institution.
 */
public class PaymentInfo {
	
	private ProcessPaymentController processPaymentController;
	
	//expiration date instead of description
	private String creditCard, description; 
	private double amount; 
	
	//True will be for payments, false will be for refunds.
	private boolean type;
	
	/**
	 * Will set all of the users payment information.
	 * @param creditCard is the users entered credit card information.
	 * @param description is the users credit card expiry date.
	 * @param amount is the amount of the refund or payment.
	 * @param type is the type of the transaction, true is for payments, and false is for refunds.
	 */
	public PaymentInfo (String creditCard, String description, double amount, boolean type) {
		this.setCreditCard(creditCard);
		this.setDescription(description);
		this.setAmount(amount);
		this.setType(type);
		this.setProcessPaymentController(new ProcessPaymentController());
	}

	/**
	 * THis will pass this object along to the Financial Institution.
	 */
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
