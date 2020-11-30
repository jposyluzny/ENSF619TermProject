package RegisteredUser;

import java.util.ArrayList;

import EmailServer.EmailServer;
import Payment.FinancialInstitution;
import Payment.PaymentInfo;
import Reservation.Reservation;
import Reservation.StoreTickets;
import Reservation.Ticket;
import User.User;

public class RegisteredUser extends User {

	private EmailServer eServ;
	private RUserAccountController rUController;
	private RegisteredUserAccount rUAccount;
	private double annualFee = 20.00;
	
	/**
	 * The commented lines in this constructor are for testing purposes
	 */
	public RegisteredUser(String[] accountData) {
		setEServ(new EmailServer());
		setRUController(new RUserAccountController());
//		for(RegisteredUserAccount account :getRUController().getRegisteredUsersList()) {
//			System.out.println(account);
//		}
		RegisteredUserAccount newAccount = getRUController().buildNewAccount(accountData);
		getRUController().addNewUserAccount(newAccount);
		setRUAccount(newAccount);
//		for(RegisteredUserAccount account :getRUController().getRegisteredUsersList()) {
//			System.out.println(account);
//		}
	}
	
	public RegisteredUser(String email, String password) {
		setEServ(new EmailServer());
		setRUController(new RUserAccountController());
		setRUAccount(login(email,password));
	}
	
	public RegisteredUserAccount login(String email, String password) {

		for(RegisteredUserAccount account : getRUController().getRegisteredUsers()) {
			if(email.equals(account.getEmailAddress()) && password.equals(account.getPassword())) {
				return account;
			}
		}
		return null;
	}
	
	public void makePayment() {
		this.getUserReservation().enterPayment(getRUAccount().getCreditCard(), getRUAccount().getExpiry(), this.getReservationPrice());
	}
	
	public void makeReservation() {
		this.setUserReservation(new Reservation());
		this.getUserReservation().buildTickets(getRUAccount().getEmailAddress(), userSelectedTheatre, userSelectedMovie, userSelectedShowtime, userSelectedSeats);
		System.out.println(getEServ().sendMoviePaymentConfirmation());
	}
	
	public ArrayList<Ticket> makeCancellation() {
		return this.getMcc().makeCancellation(getRUAccount().getEmailAddress());
	}
	
	public void confirmCancellation() {
		this.getMcc().confirmCancellation(getRUAccount().getEmailAddress(), getRUAccount().getCreditCard(), getRUAccount().getExpiry(), this.getReservationPrice());
		System.out.println(getEServ().sendRefundConfirmation());
	}
	
	public void payFee() {
		getRUController().payFee(annualFee, getRUAccount());
		System.out.println(getEServ().sendFeePaymentConfirmation());
	}

	public EmailServer getEServ() {
		return eServ;
	}

	public void setEServ(EmailServer eServ) {
		this.eServ = eServ;
	}
	
	public RUserAccountController getRUController() {
		return rUController;
	}
	
	public void setRUController(RUserAccountController rUController) {
		this.rUController = rUController;
	}
	
	public RegisteredUserAccount getRUAccount() {
		return rUAccount;
	}

	public void setRUAccount(RegisteredUserAccount rUAccount) {
		this.rUAccount = rUAccount;
	}
	
//	************ALL BELOW IS FOR TESTING**************//
//	public static void main(String[] args) {
//		String[] newData = {"Jimbo", "Jones", "85 Balls Ave", "jjones@fake.com", "pass123", "648315", "1/1/21"};
//		RegisteredUser user = new RegisteredUser(newData);
//		user.userSelection(1);
//		user.makeReservation();
//		user.makePayment();
//		user.confirmPayment();
//		
//		for (PaymentInfo p: FinancialInstitution.getPaymentRecords())
//			System.out.println(p);
//		
//		for (Ticket t: StoreTickets.getTickets())
//			System.out.println(t);
//		
//		user.makeCancellation();
//		user.confirmCancellation();
//		
//		for (PaymentInfo p: FinancialInstitution.getPaymentRecords())
//			System.out.println(p);
//		
//		for (Ticket t: StoreTickets.getTickets())
//			System.out.println(t);
//		
//		user.payFee();
//		System.out.println("Done");
//	}
}
