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
	private JList<String> ticketList = new JList();
	private JButton searchButton = new JButton("Search");
	private JPanel searchBar = new JPanel();
	private JButton cancelButton = new JButton("Cancel all tickets");
	private JPanel outPanel = new JPanel();
	
	//for testing only
	
	private String cancelledTicketNumber;
	
	public CancellationUI(){
		makePanel();
	}
		
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
	
	private void createInputPanel() {
		searchBar.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		searchBar.add(emailInput,gbc);
		gbc.gridx = 1;
		searchBar.add(searchButton,gbc);
	}
	
	public void clearDisplay() {
		emailInput.setText("");
		DefaultListModel<String> DLM = new DefaultListModel();
		ticketList.setModel(DLM);
	}
	
	public void displayNoMatchMessage(){
		JOptionPane.showMessageDialog(null, "No tickets found for this email");
	}

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
