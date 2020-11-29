package RegisteredUser;

import java.util.ArrayList;

import EmailServer.EmailServer;
import Payment.FinancialInstitution;
import Payment.PaymentInfo;
import Reservation.Reservation;
import Reservation.StoreTickets;
import Reservation.Ticket;
import Theatre.Seat;
import User.User;

public class RegisteredUser extends User {

	private EmailServer eServ;
	private RUserAccountController rUController;
	private RegisteredUserAccount rUAccount;
	private int annualFee = 20;
	
	public RegisteredUser(String email, String password) {
		setEServ(new EmailServer());
		setRUController(new RUserAccountController());
		setRUAccount(login(email, password));
	}
	
	public RegisteredUserAccount login(String email, String password) {
		return rUController.queryLogin(email, password);
	}
	
	// ENTERPAYMENT() METHOD IN RESERVATION CLASS NEEDS TO TAKE A CREDIT CARD INTEGER VALUE, NOT STRING
	public void makePayment() {
		this.getUserReservation().enterPayment(Integer.toString(getRUAccount().getCreditCard()), getRUAccount().getExpiry(), this.getReservationPrice());
	}
	
	public void makeReservation() {
		this.setUserReservation(new Reservation());
		ArrayList<Seat> s = this.getUserSelectedMovie().getTheatres().get(0).getShowtimes().get(0).getSeats();
		this.calculateReservationPrice(s);
		this.getUserReservation().buildTickets(getRUAccount().getEmailAddress(), this.getUserSelectedMovie().getTheatres().get(0), this.getUserSelectedMovie(), this.getUserSelectedMovie().getTheatres().get(0).getShowtimes().get(0), s);
		System.out.println(getEServ().sendMoviePaymentConfirmation());
	}
	
	public ArrayList<Ticket> makeCancellation() {
		return this.getMcc().makeCancellation(getRUAccount().getEmailAddress());
	}
	
	public void confirmCancellation() {
		this.getMcc().confirmCancellation(getRUAccount().getEmailAddress(), Integer.toString(getRUAccount().getCreditCard()), getRUAccount().getExpiry(), this.getReservationPrice());
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
	
	//************ALL BELOW IS FOR TESTING**************//
	public static void main(String[] args) {
		RegisteredUser user = new RegisteredUser("jsmith@fake.com", "pass123");
		user.userSelection(1);
		user.makeReservation();
		user.makePayment();
		user.confirmPayment();
		
		for (PaymentInfo p: FinancialInstitution.getPaymentRecords())
			System.out.println(p);
		
		for (Ticket t: StoreTickets.getTickets())
			System.out.println(t);
		
		user.makeCancellation();
		user.confirmCancellation();
		
		for (PaymentInfo p: FinancialInstitution.getPaymentRecords())
			System.out.println(p);
		
		for (Ticket t: StoreTickets.getTickets())
			System.out.println(t);
		
		user.payFee();
		System.out.println("Done");
	}
}
