import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MovieApp {

	private String emailAddress=null;
	
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
	
	public int makeLoginWindow() { 
		String[] options = new String[] {"Login","Guest"};
	    int response = JOptionPane.showOptionDialog(null, "Would you like to login or continue as guest?", "Login Window",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
	        null, options, options[0]);
		
		if(response==0) {
			int loginSuccess = makeLoginPanel();
			if(loginSuccess==1) {
				return 1;
			}
			else if(loginSuccess==0) {
				JOptionPane.showMessageDialog(null, "No user found with those credentials. Please try again or create an account");
				System.exit(1);
			}
			else if(loginSuccess==2) {
				System.exit(1);
			}
		}
		return 0;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public static void main(String[] args) {
		MovieApp app = new MovieApp();
		
		int i = app.makeLoginWindow();
		
		if(i==0) {
			GuestUI ordinaryUserUI = new GuestUI();
			ordinaryUserUI.createGUI();
		}
		else if(i==1) {
			AccountUI registeredUserUI = new AccountUI();
			registeredUserUI.createGUI(app.getEmailAddress());
		}
	}

}
