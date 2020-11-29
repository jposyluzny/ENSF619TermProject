package User;

import java.util.ArrayList;

import Theatre.*;
import Reservation.*;
import Payment.*;

public class User {

	ModelController modelController;
	Movie userSelectedMovie;
	
	//additions from branden : for review by team
	Theatre userSelectedTheatre;
	Showtime userSelectedShowtime;
	ArrayList<Seat> userSelectedSeats = new ArrayList<Seat>();
	
	Reservation userReservation;
	ManageCancellationController mcc;
	double reservationPrice;

	public User(){
		modelController = new ModelController();
		this.setMcc(new ManageCancellationController());
	}
	
	//This is for testing fetching the objects from Ryans Theatre segment of this app --> branden: for review: can get rid of this
	public void userSelection(int movieID) {
		this.setUserSelectedMovie(this.modelController.getMovieById(movieID));
	}
	
	public void calculateReservationPrice(ArrayList<Seat> s) {
		reservationPrice=0; //reset to 0 every time we want to re-calculate
		for (Seat seat: s)
			this.reservationPrice = this.reservationPrice + 12;
	}
	
	//This is for making a reservation
	//TESTING
	public void makeReservation(String emailAddress) {
		//branden's edits: for review
		this.setUserReservation(new Reservation());
		this.getUserReservation().buildTickets(emailAddress, userSelectedTheatre, userSelectedMovie, userSelectedShowtime, userSelectedSeats);
		System.out.println("RESERVED A TICKET FOR "+userSelectedTheatre.getName()+
				userSelectedMovie.getName()+userSelectedShowtime.getDate()+userSelectedSeats);
		
//		this.setUserReservation(new Reservation());
//		ArrayList<Seat> s = this.getUserSelectedMovie().getTheatres().get(0).getShowtimes().get(0).getSeats();
//		this.calculateReservationPrice(s);
//		this.getUserReservation().buildTickets(emailAddress, this.getUserSelectedMovie().getTheatres().get(0), this.getUserSelectedMovie(), this.getUserSelectedMovie().getTheatres().get(0).getShowtimes().get(0), s);
	}
	
	public void makePayment(String creditCard, String description) {
		this.getUserReservation().enterPayment(creditCard, description, this.getReservationPrice());
	}
	
	//"Processes and sends the payment to the financial institution
	public void confirmPayment() {
		this.getUserReservation().confirmPayment();
	}
	
	//This will return false if there are No vouchers associated with the users email address. It will return true if
	//there is a voucher.
	public boolean findVoucher(String emailAddress) {
		Voucher v = this.getModelController().findVoucher(emailAddress);
		if (v == null)
			return false;
		else {
			this.applyVoucherDiscount(v);
			return true;
		}
	}
	
	//This will apply a 15% discount to the reservation price.
	public void applyVoucherDiscount(Voucher v) {
		this.setReservationPrice(this.getReservationPrice()*0.85);
		this.getModelController().removeVoucher(v);
	}
	
	//Returns all tickets associated with a cancellation
	public ArrayList<Ticket> makeCancellation(String emailAddress) {
		return this.getMcc().makeCancellation(emailAddress);
	}
	
	//"Processes and sends the cancellation to the financial institution
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

	
//	public static void main(String[] args) {
//		User user = new User();
//		
//		//THIS IS ALL FOR TESTING ***************************************************
//		user.userSelection(1);
//		user.makeReservation("jimboBimbus@yahoo.com");
//		user.makePayment("1234 5678 9874 12", "03-22");
//		user.confirmPayment();
//		
//		for (PaymentInfo p: FinancialInstitution.getPaymentRecords())
//			System.out.println(p);
//		
//		for (Ticket t: StoreTickets.getTickets())
//			System.out.println(t);
//		
//		user.makeCancellation("jimboBimbus@yahoo.com");
//		user.confirmCancellation("jimboBimbus@yahoo.com", "1234-3425-23425-12", "04-22");
//		
//		for (PaymentInfo p: FinancialInstitution.getPaymentRecords())
//			System.out.println(p);
//		
//		for (Ticket t: StoreTickets.getTickets())
//			System.out.println(t);
//		
//		System.out.println("Done");
//	}

}
