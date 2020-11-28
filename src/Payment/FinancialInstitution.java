package Payment;

import java.util.ArrayList;

public class FinancialInstitution {
	
	private static ArrayList<PaymentInfo> paymentRecords;
	
	public FinancialInstitution() {
		setPaymentRecords(new ArrayList<PaymentInfo> ());
	}
	
	public static void addToFI(PaymentInfo payment) {
		getPaymentRecords().add(payment);
	}
	
	public static void clearPayments() {
		getPaymentRecords().clear();
	}

	public static ArrayList<PaymentInfo> getPaymentRecords() {
		return paymentRecords;
	}

	public static void setPaymentRecords(ArrayList<PaymentInfo> pR) {
		paymentRecords = pR;
	}

}
