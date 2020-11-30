package RegisteredUser;

import java.util.ArrayList;

import Reservation.Reservation;
import Reservation.Ticket;
import User.User;

/**
 * This class will extend the User class as a RegisteredUser is a type of User. It will overload some methods, and will have a associated EmailServer,
 * RUserAccountController, and RegisteredUserAccount objects.
 *
 */
public class RegisteredUser extends User {

	private RUserAccountController rUController;
	private RegisteredUserAccount rUAccount;
	private double annualFee = 20.00;
	
	/**
	 * This constructor will be used to build a new RegisteredUserAccount object if the User chooses to create an account. It will check all of the
	 * input information in the accountData array, and if it is all correct, a new RegisteredUserAccount object will be created with the information.
	 * If not, the User will keep trying to satisfy the input constraints with correct information.
	 * @param accountData is an array of Strings which holds all of the information necessary to create a new account.
	 */
	public RegisteredUser(String[] accountData) {
		setRUController(RUserAccountController.getSingleInstance());
		if(getRUController().checkNewAccountData(accountData).equals("Information is correct!")) {
			RegisteredUserAccount newAccount = getRUController().buildNewAccount(accountData);
			getRUController().addNewUserAccount(newAccount);
			setRUAccount(newAccount);
		}
		else {
			setRUAccount(null);
		}
	}
	
	/**
	 * This constructor will function as a login for an existing user. From the GUI login window, an email and password combination will be passed
	 * to this constructor. If the combination of email and password is found within a RegisteredUserAccount object in the RUserAccountController's 
	 * registeredUsersList, the login will be completed and the corresponding RegisteredUserAccount object will be returned. If there is no match,
	 * null will be returned and the user will be prompted to try again.
	 * @param email is the Registered User's email address
	 * @param password is the Registered User's password
	 */
	public RegisteredUser(String email, String password) {
		setRUController(RUserAccountController.getSingleInstance());
		setRUAccount(login(email,password));
	}
	
	/**
	 * This will traverse RUserAccountController's registeredUsersList for the combination of the input email and password. If the combination is found,
	 * the RegisteredUserAccount object is returned. If it is not found, null is returned.
	 * @param email is the Registered User's email address
	 * @param password is the Registered User's password
	 * @return either the found RegisteredUserAccount or null
	 */
	public RegisteredUserAccount login(String email, String password) {

		for(RegisteredUserAccount account : getRUController().getRegisteredUsersList()) {
			if(email.equals(account.getEmailAddress()) && password.equals(account.getPassword())) {
				return account;
			}
		}
		return null;
	}
	
	/**
	 * This is an overloaded method from the User class. It does not need to take inputs, as all the required information is in the RegisteredUserAccount object.
	 */
	public void makePayment() {
		this.getUserReservation().enterPayment(getRUAccount().getCreditCard(), getRUAccount().getExpiry(), this.getReservationPrice());
	}
	
	/**
	 * This is an overloaded method from the User class. It does not need to take inputs, as all the required information is in the RegisteredUserAccount object.
	 */
	public void makeReservation() {
		this.setUserReservation(new Reservation());
		this.getUserReservation().buildTickets(getRUAccount().getEmailAddress(), userSelectedTheatre, userSelectedMovie, userSelectedShowtime, userSelectedSeats);
	}
	
	/**
	 * This is an overloaded method from the User class. It does not need to take inputs, as all the required information is in the RegisteredUserAccount object.
	 */
	public ArrayList<Ticket> makeCancellation() {
		return this.getMcc().makeCancellation(getRUAccount().getEmailAddress());
	}
	
	/**
	 * This is an overloaded method from the User class. It does not need to take inputs, as all the required information is in the RegisteredUserAccount object.
	 */
	public void confirmCancellation() {
		this.getMcc().confirmCancellation(getRUAccount().getEmailAddress(), getRUAccount().getCreditCard(), getRUAccount().getExpiry(), this.getReservationPrice());
	}
	
	/**
	 * This method is for the Registered User to pay their annual $20.00 fee. It calls the payFee method from the RUserAccountController class, and passes the amount
	 * that needs to be paid and the associated account that is paying.
	 */
	public void payFee() {
		getRUController().payFee(annualFee, getRUAccount());
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
	
}
