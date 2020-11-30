package User;

import java.util.ArrayList;

import Theatre.*;
import Reservation.*;

//TODO: How to integrate emailServer into this class, or can we just remove that class since we are replicating this with
//GUI pop up windows in lieu of an actual email server.

/**
 * This class will serve to function as storing the Users selections, and calling the proper logic from other classes to 
 * handle the Users requests.
 */
public class User {

	protected ModelController modelController;
	protected Movie userSelectedMovie;
	protected Theatre userSelectedTheatre;
	protected Showtime userSelectedShowtime;
	protected ArrayList<Seat> userSelectedSeats;
	protected Reservation userReservation;
	protected ManageCancellationController mcc;
	protected double reservationPrice;

	/**
	 * This constructor will instantiate and set a new modelController class that the user will "use" to choose the movie, theatre,
	 * showtime, and seat objects they would like to make a reservation for. It will also instantiate the ArrayList holding all of
	 * the selected Seat objects.
	 */
	public User(){
		this.setModelController(new ModelController());
		this.setUserSelectedSeats(new ArrayList<Seat> ());
	}
	
	/**
	 * This method will calculate the price of the reservation the User is to make. It will calculate this price by counting the
	 * number of seats selected by the User as the User must purchase a ticket for each Seat they want to reserve.
	 * This method assumes that each Ticket will cost 12 dollars.
	 * @param s is the list of Seat objects the user has selected to reserve.
	 */
	public void calculateReservationPrice(ArrayList<Seat> s) {
		if (s == null)
			return;
		this.setReservationPrice(0);
		for (Seat seat: s)
			this.reservationPrice = this.reservationPrice + 12;
	}
	
	/**
	 * This will create a new Reservation object, and set it for the User. It will then call the method in the Reservation class
	 * to create tickets based on the users selections.
	 * @param emailAddress is the email address of the user.
	 */
	public void makeReservation(String emailAddress) {
		this.setUserReservation(new Reservation());
		this.getUserReservation().buildTickets(emailAddress, userSelectedTheatre, userSelectedMovie, userSelectedShowtime, userSelectedSeats);
	}
	
	/**
	 * This will pass the users payment information along to the reservation class.
	 * @param creditCard is the users credit card number.
	 * @param description is the users credit card expiry date.
	 */
	public void makePayment(String creditCard, String description) {
		this.getUserReservation().enterPayment(creditCard, description, this.getReservationPrice());
	}
	
	/**
	 * This will "tell" the Reservation class to send the users payment information to the Financial Institution class to be stored.
	 * This will simulate confirming the payment and processing the users money to the Bank.
	 */
	public void confirmPayment() {
		this.getUserReservation().confirmPayment();
	}
	
	/**
	 * This method will check the stored discount vouchers to see if the user trying to make a reservation has any current vouchers
	 * that can be applied to their checkout.
	 * @param emailAddress is the users entered email address.
	 * @return is a boolean that will return false if there are no vouchers associated with the users email address. It will 
	 * return true if there is a voucher.
	 */
	public boolean findVoucher(String emailAddress) {
		Voucher v = this.getModelController().findVoucher(emailAddress);
		if (v == null)
			return false;
		else {
			this.applyVoucherDiscount(v);
			return true;
		}
	}
	
	/**
	 * This will apply the voucher to the reservation price by decreasing the reservation amount by 15%. It will then remove 
	 * the voucher from the storage location so the Voucher cannot be used more than once.
	 * @param v is the Voucher object being used.
	 */
	public void applyVoucherDiscount(Voucher v) {
		this.setReservationPrice(this.getReservationPrice()*0.85);
		this.getModelController().removeVoucher(v);
	}
	
	/**
	 * This will create a new instance of a ManageCancellationController to be used by the User to make a Cancellation. It will
	 * then call the makeCancellation methods in the ManageCancellationController class and pass along the users email address.
	 * @param emailAddress is the users email address.
	 * @return is the list of all the users tickets that have been reserved with that email address.
	 */
	public ArrayList<Ticket> makeCancellation(String emailAddress) {
		this.setMcc(new ManageCancellationController());
		return this.getMcc().makeCancellation(emailAddress);
	}
	
	/**
	 * This will process the users entered refund information and send the information to the Financial Institution to be stored.
	 * This will simulate sending a refund to the Bank.
	 * @param emailAddress is the users entered email address.
	 * @param creditCard is the users entered credit card number.
	 * @param description is the users credit card expiry date.
	 */
	public void confirmCancellation(String emailAddress, String creditCard, String description) {
		this.getMcc().confirmCancellation(emailAddress, creditCard, description, this.getReservationPrice());
	}
	
	public ModelController getModelController() {
		return modelController;
	}

	public void setModelController(ModelController modelController) {
		this.modelController = modelController;
	}
	
	public Movie getUserSelectedMovie() {
		return userSelectedMovie;
	}

	public void setUserSelectedMovie(Movie userSelectedMovie) {
		this.userSelectedMovie = userSelectedMovie;
	}

	public Theatre getUserSelectedTheatre() {
		return userSelectedTheatre;
	}

	public void setUserSelectedTheatre(Theatre userSelectedTheatre) {
		this.userSelectedTheatre = userSelectedTheatre;
	}

	public Showtime getUserSelectedShowtime() {
		return userSelectedShowtime;
	}

	public void setUserSelectedShowtime(Showtime userSelectedShowtime) {
		this.userSelectedShowtime = userSelectedShowtime;
	}

	public ArrayList<Seat> getUserSelectedSeats() {
		return userSelectedSeats;
	}

	public void setUserSelectedSeats(ArrayList<Seat> userSelectedSeats) {
		this.userSelectedSeats = userSelectedSeats;
	}

	public Reservation getUserReservation() {
		return userReservation;
	}

	public void setUserReservation(Reservation userReservation) {
		this.userReservation = userReservation;
	}
	
	public ManageCancellationController getMcc() {
		return mcc;
	}

	public void setMcc(ManageCancellationController mcc) {
		this.mcc = mcc;
	}
	
	public double getReservationPrice() {
		return reservationPrice;
	}

	public void setReservationPrice(double reservationPrice) {
		this.reservationPrice = reservationPrice;
	}
	
}
