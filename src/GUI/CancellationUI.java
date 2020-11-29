package GUI;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.*;


public class CancellationUI extends JPanel {
	
	private JTextField emailInput = new JTextField(15);
	private JList<String> emailList = new JList();
	private JButton searchButton = new JButton("Search");
	private JPanel searchBar = new JPanel();
	private JButton cancelButton = new JButton("Cancel this ticket");
	private JPanel outPanel = new JPanel();
	
	//for testing only
	private ArrayList<String> tickets = new ArrayList<String>();
	private String cancelledTicketNumber;
	
	public CancellationUI(){
		makePanel();
	}
		
	private void makePanel() {	
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel panelTitle = new JLabel("Cancel a ticket",SwingConstants.CENTER);
		panelTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		this.add(panelTitle,gbc);

		gbc.gridy = 1;
		this.add(new JLabel("Enter your email address below: "),gbc);
		
		gbc.gridy = 2;
		createInputPanel();
		this.add(searchBar,gbc);

		outPanel.setLayout(new FlowLayout());
		emailList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		JScrollPane listScrollPane = new JScrollPane(emailList);
		listScrollPane.setPreferredSize(new Dimension(270,100));
		outPanel.add(listScrollPane);

		gbc.gridy = 3;
		this.add(outPanel,gbc);
		
		gbc.gridy = 4;
		this.add(cancelButton,gbc);
	}
	
	private void createInputPanel() {
		searchBar.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		searchBar.add(emailInput,gbc);
		gbc.gridx = 1;
		searchBar.add(searchButton,gbc);
	}

	public String getCancelledTicketNumber() {
		return cancelledTicketNumber;
	}

	public JTextField getEmailInput() {
		return emailInput;
	}

	public JList<String> getEmailList() {
		return emailList;
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

	public ArrayList<String> getTickets() {
		return tickets;
	}
	public void setEmailField(String email) {
		emailInput.setText(email);
	}

}
