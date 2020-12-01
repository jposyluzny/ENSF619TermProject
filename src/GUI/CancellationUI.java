package GUI;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class creates a JPanel that gives the user an interface to search for the tickets
 * they have registered, and cancel their reservation.
 */
public class CancellationUI extends JPanel {
	
	private JTextField emailInput = new JTextField(15);
	private JList<String> ticketList = new JList();
	private JButton searchButton = new JButton("Search");
	private JPanel searchBar = new JPanel();
	private JButton cancelButton = new JButton("Cancel all tickets");
	private JPanel outPanel = new JPanel();
	private String cancelledTicketNumber;
	
	/**
	 * Constructor used to create a CancellationUI panel by setting up all java swing GUI
	 * components in the appropriate locations.
	 */
	public CancellationUI(){
		makePanel();
	}
		
	/**
	 * Function used to place all java swing components for GUI display in appropriate locations.
	 * This includes an input box to type in an email address, and a display JList to show all
	 * tickets associated with the given email.
	 */
	private void makePanel() {	
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel panelTitle = new JLabel("Cancel Tickets",SwingConstants.CENTER);
		panelTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(panelTitle,gbc);

		gbc.gridy = 1;
		this.add(new JLabel("Enter your email address below: "),gbc);
		
		gbc.gridy = 2;
		createInputPanel();
		this.add(searchBar,gbc);

		outPanel.setLayout(new FlowLayout());
		ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		JScrollPane listScrollPane = new JScrollPane(ticketList);
		listScrollPane.setPreferredSize(new Dimension(270,100));
		outPanel.add(listScrollPane);

		gbc.gridy = 3;
		this.add(outPanel,gbc);
		
		gbc.gridy = 4;
		this.add(cancelButton,gbc);
	}
	
	/**
	 * Function used to create input panel, formatted with a search input field horizontally
	 * beside a search button.
	 */
	private void createInputPanel() {
		searchBar.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		searchBar.add(emailInput,gbc);
		gbc.gridx = 1;
		searchBar.add(searchButton,gbc);
	}
	
	/**
	 * Function used to clear all user input fields from this JPanel. Will be called by 
	 * UIController when it needs to clear the view.
	 */
	public void clearDisplay() {
		emailInput.setText("");
		DefaultListModel<String> DLM = new DefaultListModel();
		ticketList.setModel(DLM);
	}
	
	/**
	 * Display message used to tell user there were no tickets found
	 */
	public void displayNoMatchMessage(){
		JOptionPane.showMessageDialog(null, "No tickets found for this email");
	}

	//Getters and Setters
	public String getCancelledTicketNumber() {
		return cancelledTicketNumber;
	}
	public JTextField getEmailInput() {
		return emailInput;
	}
	public JList<String> getEmailList() {
		return ticketList;
	}
	public JButton getSearchButton() {
		return searchButton;
	}
	public JPanel getSearchBar() {
		return searchBar;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public void setEmailField(String email) {
		emailInput.setText(email);
	}
}
