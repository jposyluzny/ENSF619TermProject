
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class AccountUI extends GuestUI {
	
	String userEmail;
	private JPanel updateAccountPanel = new JPanel();
	
	//Buttons for registered user UI
	private JButton button4 = new JButton("Update your account");
	private JButton button5 = new JButton("Pay annual registration fee");
	
	
	protected void createGUI(String email) {
		this.userEmail = email;
		makeRegisteredUserPanel(email);
		setUpGUI();
	}
	
	private void makeRegisteredUserPanel(String email) {
		JButton[] buttons = {button1, button2, button4,button5};
		createLeftPanel("Welcome Back "+email,buttons);
		addButtonListeners();		
	}
	
	private void createReserveTicketPanel() {
		reserveTicketPanel.setLayout(new GridLayout(2,1));	
		reserveTicketPanel.add(new BrowseMoviesUI(2));	
	}
	
	private void createCancelTicketPanel() {
		CancellationUI panel = new CancellationUI();
		panel.setEmailField(userEmail);
		panel.getListOfTickets(userEmail);
		cancelTicketPanel = panel;
	}
	
	private void createUpdatePanel() {
		makeAccountButton.setText("Update Account");
		createAccountPanel("Update Your Account",updateAccountPanel);
		
		nameInput.setText("Placeholder name");
		addressInput.setText("Placeholder address");
		emailInput.setText(userEmail);
		passwordInput.setText("Placeholder password");
		creditInput.setText("Placeholder credit");
	}
	
	
	private void addButtonListeners() {
		//button1
		button1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	reserveTicketPanel.removeAll();
		    	rightPanel.removeAll();
		    	createReserveTicketPanel();
		    	rightPanel.add(reserveTicketPanel);
		    	revalidate();
		    	repaint();
		    }
		});
		
		//button2
		button2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	cancelTicketPanel.removeAll();
		    	rightPanel.removeAll();
		    	createCancelTicketPanel();
		    	rightPanel.add(cancelTicketPanel);
		    	revalidate();
		    	repaint();
		    }
		});
		
		//button4
		button4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	updateAccountPanel.removeAll();
		    	rightPanel.removeAll();
		    	createUpdatePanel();
		    	rightPanel.add(updateAccountPanel);
		    	revalidate();
		    	repaint();
		    }
		});
		
		//payment
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaymentUI payment = new PaymentUI();
				String[] paymentInfo = payment.paymentInfoDialog(2,20);
		    }
		});

		makeAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = nameInput.getText();
				String newAddress = addressInput.getText();
		    	String newEmail = emailInput.getText();
		    	String newPassword = passwordInput.getText();
		    	String newCredit = creditInput.getText();
		    	
		    	if(!newName.equals("") && !newAddress.equals("") && !newEmail.equals("") 
		    			&& !newPassword.equals("") && !newCredit.equals("")) {
		    		
		    		System.out.println("Register an account with "+newEmail+";"+newPassword+";"+newCredit);
		    	}
		    	
		    }
		});
	}
	


}
