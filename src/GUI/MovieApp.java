package GUI;
import Theatre.Seat;
import User.User;

public class MovieApp {

	public static void main(String[] args) {
				
		BrowseMoviesUI movieView = new BrowseMoviesUI();
		BrowseSeatUI seatView = new BrowseSeatUI();
		PaymentUI paymentView = new PaymentUI();
		CancellationUI cancelView = new CancellationUI();
		AccountUI accountView = new AccountUI();

		User user = new User();
		MovieUI app = new MovieUI(movieView,seatView,cancelView,accountView,paymentView);
		app.createGUI();
		UIController controller = new UIController(app,user);
		
	}

}
