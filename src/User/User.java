package User;

import java.util.ArrayList;

import Theatre.*;
import Reservation.*;
import Payment.*;

public class User {

	ModelController modelController;
	Movie userSelectedMovie;
	Reservation userReservation;
	ManageCancellationController mcc;
	int reservationPrice;

	public User(){
		modelController = new ModelController();
		this.setMcc(new ManageCancellationController());
	}
	
	//This is for testing fetching the objects from Ryans Theatre segment of this app
	public void userSelection(int movieID) {
		this.setUserSelectedMovie(this.modelController.getMovieById(movieID));
	}
	
	
	
	public void calculateReservationPrice(ArrayList<Seat> s) {
		for (Seat seat: s)
			this.reservationPrice = this.reservationPrice + 12;
	}
	
	//This is for making a reservation
	//TESTING
	public void makeReservation(String emailAddress) {
		this.setUserReservation(new Reservation());
		ArrayList<Seat> s = this.getUserSelectedMovie().getTheatres().get(0).getShowtimes().get(0).getSeats();
		this.calculateReservationPrice(s);
		this.getUserReservation().buildTickets(emailAddress, this.getUserSelectedMovie().getTheatres().get(0), this.getUserSelectedMovie(), this.getUserSelectedMovie().getTheatres().get(0).getShowtimes().get(0), s);
	}
	
	public void makePayment(String creditCard, String description) {
		this.getUserReservation().enterPayment(creditCard, description, this.getReservationPrice());
	}
	
	//"Processes and sends the payment to the financial institution
	public void confirmPayment() {
		this.getUserReservation().confirmPayment();
	}
	
	//Returns all tickets associated with a cancellation
	public ArrayList<Ticket> makeCancellation(String emailAddress) {
		return this.getMcc().makeCancellation(emailAddress);
	}
	
	//"Processes and sends the cancellation to the financial institution
	public void confirmCancellation(String emailAddress, String creditCard, String description) {
		this.getMcc().confirmCancellation(emailAddress, creditCard, description, this.getReservationPrice());
	}
	
	public Movie getUserSelectedMovie() {
		return userSelectedMovie;
	}

	public void setUserSelectedMovie(Movie userSelectedMovie) {
		this.userSelectedMovie = userSelectedMovie;
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
	
	public int getReservationPrice() {
		return reservationPrice;
	}

	public void setReservationPrice(int reservationPrice) {
		this.reservationPrice = reservationPrice;
	}
	
	public static void main(String[] args) {
		User user = new User();
		
		//THIS IS ALL FOR TESTING ***************************************************
		user.userSelection(1);
		user.makeReservation("jimboBimbus@yahoo.com");
		user.makePayment("1234 5678 9874 12", "03-22");
		user.confirmPayment();
		
		for (PaymentInfo p: FinancialInstitution.getPaymentRecords())
			System.out.println(p);
		
		for (Ticket t: StoreTickets.getTickets())
			System.out.println(t);
		
		user.makeCancellation("jimboBimbus@yahoo.com");
		user.confirmCancellation("jimboBimbus@yahoo.com", "1234-3425-23425-12", "04-22");
		
		for (PaymentInfo p: FinancialInstitution.getPaymentRecords())
			System.out.println(p);
		
		for (Ticket t: StoreTickets.getTickets())
			System.out.println(t);
		
		System.out.println("Done");
	}

}
