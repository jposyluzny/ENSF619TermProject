import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GuestUI extends JFrame {

	protected JPanel reserveTicketPanel = new JPanel();
	protected JPanel cancelTicketPanel = new JPanel();
	protected JPanel leftPanel = new JPanel();
	protected JPanel rightPanel = new JPanel();
	
	//Buttons for ordinary user UI
	protected JButton button1 = new JButton("Purchase a ticket");
	protected JButton button2 = new JButton("Cancel a ticket");
	private JButton button3 = new JButton("Become a registered user");
	

	//Make registered account
	private JPanel makeAccountPanel = new JPanel();
	protected JButton makeAccountButton = new JButton("Create Account");
	protected JTextField emailInput = new JTextField(12);
	protected JTextField passwordInput = new JTextField(12);
	protected JTextField creditInput = new JTextField(12);
	protected JTextField nameInput = new JTextField(12);
	protected JTextField addressInput = new JTextField(12);
	
	public GuestUI() { }
	

	protected void createGUI() {
		makeOrdinaryUserPanel();
		setUpGUI();
	}
	
	protected void setUpGUI() {
	    //Sets up the left and right halves of the GUI
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		this.setLayout(new GridLayout(1,2));
		this.add(leftPanel);
		this.add(rightPanel);
		
		//display GUI
		JSplitPane panes = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        panes.setDividerLocation(300);
        panes.setBackground(Color.white);
        panes.setBorder(null);
        setContentPane(panes);
        
		setPreferredSize(new Dimension(750,500));
		setTitle("Movie Application system");   
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	
	private void makeOrdinaryUserPanel() {
		JButton[] buttons = {button1, button2, button3};
		createLeftPanel("Welcome Guest",buttons);
		addButtonListeners();
	}

	protected void createLeftPanel(String welcomeMsg, JButton[] buttons) {
		leftPanel.setLayout(new GridLayout(buttons.length+1,1));
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(2,1));
		JLabel title = new JLabel(welcomeMsg,SwingConstants.CENTER);
		title.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

		titlePanel.add(title);
		titlePanel.add(new JLabel("Please make a selection: ",SwingConstants.CENTER));
		
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		leftPanel.add(titlePanel);
		
		JPanel[] gluePanel = new JPanel[buttons.length+1];
		for(int i=0; i<buttons.length;i++) {
			gluePanel[i] = new JPanel(new FlowLayout());
			buttons[i].setPreferredSize(new Dimension(200,50));
			gluePanel[i].add(buttons[i]);
			leftPanel.add(gluePanel[i]);
		}
	}
	
	private void createReserveTicketPanel() {
		reserveTicketPanel.setLayout(new GridLayout(2,1));	
		reserveTicketPanel.add(new BrowseMoviesUI(1));	
	}
	
	private void createCancelTicketPanel() {
		cancelTicketPanel = new CancellationUI();
	}
	
	protected void createAccountPanel(String title,JPanel panel) {
		JLabel panelTitle = new JLabel(title,SwingConstants.CENTER);
		panelTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		panel.setLayout(new BorderLayout());
		panel.add(panelTitle, BorderLayout.NORTH);
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;

		gbc.gridy = 1;
		innerPanel.add(formatLabelAndInput("Name   ",nameInput),gbc);
		gbc.gridy = 2;
		innerPanel.add(formatLabelAndInput("Address   ",addressInput),gbc);
		gbc.gridy = 3;
		innerPanel.add(formatLabelAndInput("Email   ",emailInput),gbc);
		gbc.gridy = 4;
		innerPanel.add(formatLabelAndInput("Password   ",passwordInput),gbc);
		gbc.gridy = 5;
		innerPanel.add(formatLabelAndInput("Credit   ",creditInput),gbc);
		gbc.gridy = 6;
		innerPanel.add(makeAccountButton,gbc);
		
		panel.add(innerPanel, BorderLayout.SOUTH);
	}
		
	protected JPanel formatLabelAndInput(String text, JTextField textField) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(new JLabel(text));
		panel.add(textField);
	    return panel;
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
		
		//button3
		button3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	makeAccountPanel.removeAll();
		    	rightPanel.removeAll();
		    	createAccountPanel("Creating a New Account",makeAccountPanel);
		    	rightPanel.add(makeAccountPanel);
		    	revalidate();
		    	repaint();
		    }
		});

		//button6
		makeAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = nameInput.getText();
				String newAddress = addressInput.getText();
		    	String newEmail = emailInput.getText();
		    	String newPassword = passwordInput.getText();
		    	String newCredit = creditInput.getText();
		    	
		    	if(!newName.equals("") && !newAddress.equals("") && !newEmail.equals("") 
		    			&& !newPassword.equals("") && !newCredit.equals("")) {
		    		
		    		PaymentUI payment = new PaymentUI();
			    	String[] paymentInfo = payment.paymentInfoDialog(2,20);
			    	
			    	System.out.println("Register an account with "+newEmail+";"+newPassword+";"+newCredit);
		    	}
		    	
		    }
		});
		   
	}

}
