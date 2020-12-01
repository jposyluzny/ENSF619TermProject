package GUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Constructor used to creates the JFrame GUI. Has instances of all other UI's in this package, and 
 * creates the GUI by placing the different panels on the GUI in their proper locations.
 */
public class MovieUI extends JFrame {

	private JPanel reserveTicketPanel = new JPanel();
	private JPanel cancelTicketPanel = new JPanel();
	private JPanel accountPanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	
	//Buttons for ordinary user UI
	private JButton button1 = new JButton("Purchase a ticket");
	private JButton button2 = new JButton("Cancel a ticket");
	private JButton button3 = new JButton("Become a registered user");
	private JButton button4 = new JButton("Update your account");
	private JButton button5 = new JButton("Pay annual registration fee");

	//Aggregation relationships
	private BrowseMoviesUI movieView;
	private BrowseSeatUI seatView;
	private CancellationUI cancelView;
	private AccountUI accountView;
	private PaymentUI paymentView;
	
	//user type
	int userType=1; //1 is guest, 2 is registered
	
	/**
	 * Constructor used to create MovieUI user interface
	 * 
	 * @param mV BrowseMovieUI panel
	 * @param sV BrowseSeatUI panel
	 * @param cV CancellationUI panel
	 * @param aV AccountUI panel
	 * @param pV PaymentUI panel
	 * @param i user type, 1 is guest 2 is registered
	 */
	public MovieUI(BrowseMoviesUI mV, BrowseSeatUI sV,CancellationUI cV, AccountUI aV, PaymentUI pV, int i) {
		movieView = mV;
		seatView = sV;
		cancelView = cV;
		accountView = aV;
		paymentView = pV;
		userType=i;
	}
	
	/**
	 * Function used to clear all user input fields from this JPanel. Will be called by 
	 * UIController when it needs to clear the view.
	 */
	public void resetDisplays() {
		rightPanel.removeAll();
		rightPanel.revalidate();
		rightPanel.repaint();
		cancelView.setEmailField("");
		accountView.clearInputFields();
	}
	
	/**
	 * Function used to swap between a guest UI to a registeredUI. This is called by the UIController
	 * when a guest user creates a registered user account.
	 * 
	 * @param login Login instance, which holds info of registered uesr email, password, and user type
	 */
	public void swapUserGUI(Login login) {
		leftPanel.removeAll();
		leftPanel.revalidate();
		leftPanel.repaint();
		makeRegisteredUserPanel(login.getEmailAddress());
		cancelView.setEmailField(login.getEmailAddress());
		accountView.getMakeAccountButton().setText("Update Account");
	}
	
	/**
	 * Function used to create a UI for a guest user
	 */
	public void makeOrdinaryGUI() {
		makeOrdinaryUserPanel();
		setUpGUI();
	}
	
	/**
	 * Function used to create a UI for a registered user
	 */
	public void makeRegisteredGUI(String email) {
		makeRegisteredUserPanel(email);
		setUpGUI();
	}
	
	/**
	 * Function used to place all panels and java swing components for GUI display in appropriate locations.
	 */
	public void setUpGUI() {
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
	

	/**
	 * Used to create the registered user UI panel
	 * 
	 * @param email registered user's email address
	 */
	private void makeRegisteredUserPanel(String email) {
		JButton[] buttons = {button1, button2, button4,button5};
		createLeftPanel("Welcome Back "+email,buttons);
		addButtonListeners();		
	}
	
	/**
	 * Used to create the guest user UI panel
	 */
	private void makeOrdinaryUserPanel() {
		JButton[] buttons = {button1, button2, button3};
		createLeftPanel("Welcome Guest",buttons);
		addButtonListeners();
	}
	
	/**
	 * Creates the panel on the left side, by adding either the registered or the guest user panels
	 */
	private void createLeftPanel(String welcomeMsg, JButton[] buttons) {
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
	
	/**
	 * Creates the panel on the left side, by adding either the registered or the guest user panels
	 */
	private void createReserveTicketPanel() {
		reserveTicketPanel.setLayout(new GridLayout(2,1));	
		reserveTicketPanel.add(movieView);	
		reserveTicketPanel.add(seatView);
	}
	
	/**
	 * Creates the ticket cancellation panel
	 */
	private void createCancelTicketPanel() {
		cancelTicketPanel.add(cancelView);
	}

	/**
	 * Creates the registered user update info panel
	 */
	private void createUpdatePanel() {
		accountView.getMakeAccountButton().setText("Update Account");
		accountPanel.add(accountView);
	}
	
	/**
	 * Creates the guest user create user panel
	 */
	private void createAccountPanel() {
		accountPanel.add(accountView);
	}
	
	/**
	 * Add listeners to all buttons in GUI panel
	 */
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
		    	accountPanel.removeAll();
		    	rightPanel.removeAll();
		    	createAccountPanel();
		    	rightPanel.add(accountPanel);
		    	revalidate();
		    	repaint();
		    }
		});
		
		//button4
		button4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	accountPanel.removeAll();
		    	rightPanel.removeAll();
		    	createUpdatePanel();
		    	rightPanel.add(accountPanel);
		    	revalidate();
		    	repaint();
		    }
		});

	}
	
	//Getters and Setters
	public BrowseMoviesUI getMovieView() {
		return movieView;
	}
	public BrowseSeatUI getSeatView() {
		return seatView;
	}
	public CancellationUI getCancelView() {
		return cancelView;
	}
	public AccountUI getAccountView() {
		return accountView;
	}
	public PaymentUI getPaymentView() {
		return paymentView;
	}
	public JButton getButton5() {
		return button5;
	}

}