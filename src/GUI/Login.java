package GUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import RegisteredUser.RegisteredUser;

/**
 * Class used to create a Login interface, using JOptionPanes. Prompts a user to either select the
 * guest UI or login to their registered account. 
 */
public class Login {
	
	private RegisteredUser rUser;
	private String emailAddress=null;
	private String passwordInput=null;
	private int userType=1; //1 for ordinary, 2 for registered (default ordinary)
	
	/**
	 * Constructor used to create a Login UI, using a series of JOptionPanes.
	 */
	public Login() {
		userType = makeLoginWindow();
	}
	
	/**
	 * Makes login window used to get input from user, to determine if the GuestUI or RegisteredUI should
	 * be launched by the UIController. If user chooses to login, will store the email address and password
	 * the user entered to find any matching credentials.
	 * 
	 * @return 1 if ordinary user, returns 2 if registered user
	 */
	public int makeLoginWindow() { 
		String[] options = new String[] {"Login","Guest"};
	    int response = JOptionPane.showOptionDialog(null, "Would you like to login or continue as guest?", "Login Window",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
	        null, options, options[0]);
		
		if(response==0) { //response 0 = login
			int loginSuccess = makeLoginPanel();
			if(loginSuccess==1) {
				return 2; //returns 2 if login successful
			}
			else if(loginSuccess==0) {
				JOptionPane.showMessageDialog(null, "No user found with those credentials. Please try again or create an account");
				System.exit(1);
			}
			else if(loginSuccess==2) {
				System.exit(1);
			}
		}
		return 1; //returns 1 if guest
	}
	 
	/**
	 * Makes login window dialog, prompting user to enter their credentials. Uses a RegisteredUser
	 * object to verify the combination of login credentials. 
	 * 
	 * @return 0 if login fails, 1 if login successful, 2 if user cancels operation
	 */
	private int makeLoginPanel() {
		
		JTextField username = new JTextField(10);
		JTextField password = new JTextField(10);
		JPanel loginPanel = new JPanel();
		loginPanel.add(new JLabel("Email"));
		loginPanel.add(username);
		loginPanel.add(new JLabel("Password"));
		loginPanel.add(password);
		
	    int login = JOptionPane.showConfirmDialog(null, loginPanel, "Please enter your credentials", JOptionPane.OK_CANCEL_OPTION);
	    if (login == JOptionPane.OK_OPTION) {
	    	emailAddress=username.getText();
	    	passwordInput = password.getText();
	    	
	    	rUser = new RegisteredUser(emailAddress, passwordInput); //registered user instance used to test login
	    	
	    	if(rUser.getRUAccount()!=null){
	    		return 1; //login successful
	    	}
	    	else {
	    		return 0; //login un-successful
	    	}
	    }
	    return 2; //user exit window
	}
	
	//Getters and Setters
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getPasswordInput() {
		return passwordInput;
	}
	public int getUserType() {
		return userType;
	}
	
}
