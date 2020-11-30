package Payment;

import java.util.ArrayList;

/**
 * This class will serve to represent the Bank in a transaction. It will hold all of the payments made from Users.
 */
public class FinancialInstitution {
	
	private static ArrayList<PaymentInfo> paymentRecords = new ArrayList<PaymentInfo> ();
	
	/**
	 * This will add Payments or Refunds to the ArrayList simulating the Banks databases, etc.
	 * @param payment is the PaymentInfo object holding all of the users Payment or Refund information.
	 */
	public static void addToFI(PaymentInfo payment) {
		getPaymentRecords().add(payment);
	}
	
	/**
	 * Will simulate clearing the Banks records (should'nt be needed).
	 */
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
