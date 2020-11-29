package GUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
	String email;
	
	public MovieUI(BrowseMoviesUI mV, BrowseSeatUI sV,CancellationUI cV, AccountUI aV, PaymentUI pV) {
		movieView = mV;
		seatView = sV;
		cancelView = cV;
		accountView = aV;
		paymentView = pV;
	}
	

	public void createGUI() {
		Login login = new Login();
		userType = login.getUserType();
		email = login.getEmailAddress();
		if(userType==1) {
			makeOrdinaryUserPanel();
		}
		else if(userType==2) {
			makeRegisteredUserPanel(email);
		}
		setUpGUI();
	}
	
	private void setUpGUI() {
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
	
	private void makeRegisteredUserPanel(String email) {
		JButton[] buttons = {button1, button2, button4,button5};
		createLeftPanel("Welcome Back "+email,buttons);
		addButtonListeners();		
	}
	
	private void makeOrdinaryUserPanel() {
		JButton[] buttons = {button1, button2, button3};
		createLeftPanel("Welcome Guest",buttons);
		addButtonListeners();
	}
	
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
	
	private void createReserveTicketPanel() {
		reserveTicketPanel.setLayout(new GridLayout(2,1));	
		reserveTicketPanel.add(movieView);	
		reserveTicketPanel.add(seatView);
	}
	
	private void createCancelTicketPanel() {
		cancelView.setEmailField(email);
		cancelTicketPanel.add(cancelView);
	}
	
	private void createAccountPanel() {
		accountPanel.add(accountView);
	}
	
	private void createUpdatePanel() {
		accountView.getMakeAccountButton().setText("Update Account");
		accountView.getNameInput().setText("Placeholder name");
		accountView.getAddressInput().setText("Placeholder address");
		accountView.getEmailInput().setText(email);
		accountView.getPasswordInput().setText("Placeholder password");
		accountView.getCreditInput().setText("Placeholder credit");
		
		accountPanel.add(accountView);
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
	public int getUserType() {
		return userType;
	}
	public JButton getButton5() {
		return button5;
	}

}