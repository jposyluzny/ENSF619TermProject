package GUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {

	private String emailAddress=null;
	private int userType=0;
	
	public Login() {
		userType = makeLoginWindow();
	}
	
	public int makeLoginWindow() { 
		String[] options = new String[] {"Login","Guest"};
	    int response = JOptionPane.showOptionDialog(null, "Would you like to login or continue as guest?", "Login Window",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
	        null, options, options[0]);
		
		if(response==0) {
			int loginSuccess = makeLoginPanel();
			if(loginSuccess==1) {
				return 2;
			}
			else if(loginSuccess==0) {
				JOptionPane.showMessageDialog(null, "No user found with those credentials. Please try again or create an account");
				System.exit(1);
			}
			else if(loginSuccess==2) {
				System.exit(1);
			}
		}
		return 1;
	}
	
	
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
	    	if(emailAddress.equals("email1@fake.com") && password.getText().equals("password")){
	    		return 1;
	    	}
	    	else {
	    		return 0;
	    	}
	    }
	    return 2;
	}
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public int getUserType() {
		return userType;
	}
	
	
	public static void main(String[] args) {
		Login login = new Login();
	}
	
}
