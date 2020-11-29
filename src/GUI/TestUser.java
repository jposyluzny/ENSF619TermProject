package GUI;

public class TestUser {

	public static void main(String[] args) {
		
		
		BrowseMoviesUI movieView = new BrowseMoviesUI();
		BrowseSeatUI seatView = new BrowseSeatUI();
		PaymentUI paymentView = new PaymentUI();
		CancellationUI cancelView = new CancellationUI();
		AccountUI accountView = new AccountUI();

		MovieApp app = new MovieApp(movieView,seatView,cancelView,accountView,paymentView);
		app.createGUI();
		UIController controller = new UIController(app);


		
	}

}
