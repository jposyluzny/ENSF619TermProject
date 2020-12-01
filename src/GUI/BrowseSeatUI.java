package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


/**
 * Class that creates the movie seat input UI as a JPanel. Each seat is represented by
 * a JButton that will allow a user to view available seats and also select a new seat to reserve.
 */
public class BrowseSeatUI extends JPanel {

	JButton seat1, seat2, seat3, seat4, seat5,
		seat6,seat7,seat8,seat9,seat10,
		seat11,seat12,seat13,seat14,seat15,
		seat16,seat17,seat18,seat19,seat20;
	
	JButton[] seats = {seat1, seat2, seat3, seat4, seat5,
			seat6,seat7,seat8,seat9,seat10,
			seat11,seat12,seat13,seat14,seat15,
			seat16,seat17,seat18,seat19,seat20};

	private JPanel seatDiagram = new JPanel();
	private JPanel theatreDiagram = new JPanel();
	private JButton ticketButton = new JButton("Reserve this seat!");
	

	/**
	 * Constructor used to create a BrowseSeatUI panel by setting up all java swing GUI
	 * components in the appropriate locations.
	 */
	public BrowseSeatUI() { //choice = registered(2) or ordinary(1)
		createButtons(seatDiagram);
		createPanel();
	}

	/**
	 * Function used to place all java swing components for GUI display in appropriate locations.
	 * This includes a 2D grid of buttons representing seats, and a button used to reserve selected
	 * seats. 
	 */
	private void createPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JLabel("  "),gbc);

		gbc.gridy = 1;
		createTheatreDiagram();
		this.add(theatreDiagram,gbc);
		
		gbc.gridy = 2;
		this.add(new JLabel("  "),gbc);
		
		gbc.gridy = 3;
		this.add(ticketButton,gbc);
		
	}
	
	
	/**
	 * Creates a 2D, 4x5 grid of JButtons representing the 20 seats in the movie theatre.
	 * This allows user to view available seats, and select their desired seats.
	 * 
	 * @param panel container panel to place the seat buttons
	 */
	private void createButtons(JPanel panel) {
		for(int i=0;i<20;i++) {
			seats[i] = new JButton();
			seats[i].setPreferredSize(new Dimension(30,30));
			panel.add(seats[i]);
		}
		panel.setLayout(new GridLayout(4,5));
	}

	/**
	 * This panel is a container for the grid of seats, and serves to better display the schematic
	 * of the theatre by adding a "Screen" to the display, so that a user knows where the screen
	 * is when selecting their seat.
	 */
	private void createTheatreDiagram() {
		theatreDiagram.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Screen",SwingConstants.CENTER);
		theatreDiagram.add(label,gbc);
		
		gbc.gridy = 1;
		theatreDiagram.add(new JLabel("  "),gbc);
		
		gbc.gridy = 2;
		theatreDiagram.add(seatDiagram,gbc);
		
		gbc.gridy = 3;
		theatreDiagram.add(new JLabel("  "),gbc);
		
		theatreDiagram.setBorder(new LineBorder(Color.BLACK, 2));
		theatreDiagram.setBackground(Color.WHITE);
	}
	
	/**
	 * Function used to clear the seat diagram. Will be called by UIController when it needs to clear the view.
	 */
	public void clearDisplay() {
		for(int i=0;i<20;i++) {
			seats[i].setText("");
		}
	}
	
	
	//Getters and Setters
	public JButton[] getSeats() {
		return seats;
	}

	public JButton getTicketButton() {
		return ticketButton;
	}
	
}


