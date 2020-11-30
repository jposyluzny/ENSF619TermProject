package GUI;
import RegisteredUser.RegisteredUser;
import User.User;

public class MovieApp {

	public static void main(String[] args) {
		
		Login loginPrompt = new Login();
		int userType = loginPrompt.getUserType();
		
		BrowseMoviesUI movieView = new BrowseMoviesUI();
		BrowseSeatUI seatView = new BrowseSeatUI();
		PaymentUI paymentView = new PaymentUI();
		CancellationUI cancelView = new CancellationUI();
		AccountUI accountView = new AccountUI();
		
		User user=null;
		
		if(userType==1) {
			user = new User();
		}
		else if(userType==2) {
			user = new RegisteredUser(loginPrompt.getEmailAddress(),loginPrompt.getPasswordInput());
		}

		MovieUI app = new MovieUI(movieView,seatView,cancelView,accountView,paymentView,userType);
		UIController controller = new UIController(app,user);
		controller.runApp(loginPrompt);

	}

}
