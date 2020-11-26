package Payment;

public class ProcessPayment {
	
	private FinancialInstitution financialInst;
	
	public ProcessPayment() {
		this.setFinancialInst(new FinancialInstitution());
	}
	
	public String sendPaymentMessageToUser() {
		return "Payment completed successfully.";
	}
	
	public String sendRefundMessageToUser() {
		return "Refund completed successfully.";
	}

	public FinancialInstitution getFinancialInst() {
		return financialInst;
	}

	public void setFinancialInst(FinancialInstitution financialInst) {
		this.financialInst = financialInst;
	}

}
