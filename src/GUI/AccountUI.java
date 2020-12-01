package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class creates a JPanel that allows a user to either create or update their registered
 * user information. This UI will have input fields for the user's name, address, email, password, 
 * credit card, and expiry date. Because this class is used for both creating a new account OR
 * updating an existing account, the UIController will handle the differences in functionality.
 */
public class AccountUI extends JPanel {
	
	private JButton makeAccountButton = new JButton("Create Account");
	private JTextField emailInput = new JTextField(12);
	private JTextField passwordInput = new JTextField(12);
	private JTextField creditInput = new JTextField(12);
	private JTextField firstNameInput = new JTextField(12);
	private JTextField lastNameInput = new JTextField(12);
	private JTextField addressInput = new JTextField(12);
	private JTextField expiryInput = new JTextField(12);
	
	/**
	 * Constructor used to create an AccountUI panel by setting up all java swing GUI
	 * components in the appropriate locations.
	 */
	public AccountUI() {
		makePanel();
	}

	/**
	 * Function used to place all java swing components for GUI display in appropriate locations.
	 * This includes a several input fields and labels for user input.
	 */
	private void makePanel() {
		JLabel panelTitle = new JLabel("Enter Information",SwingConstants.CENTER);
		panelTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		this.setLayout(new BorderLayout());
		this.add(panelTitle, BorderLayout.NORTH);
		
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;

		gbc.gridy = 1;
		innerPanel.add(formatLabelAndInput("First Name   ",firstNameInput),gbc);
		gbc.gridy = 2;
		innerPanel.add(formatLabelAndInput("Last Name   ",lastNameInput),gbc);
		gbc.gridy = 3;
		innerPanel.add(formatLabelAndInput("Address   ",addressInput),gbc);
		gbc.gridy = 4;
		innerPanel.add(formatLabelAndInput("Email   ",emailInput),gbc);
		gbc.gridy = 5;
		innerPanel.add(formatLabelAndInput("Password   ",passwordInput),gbc);
		gbc.gridy = 6;
		innerPanel.add(formatLabelAndInput("Credit   ",creditInput),gbc);
		gbc.gridy = 7;
		innerPanel.add(formatLabelAndInput("Expiry   ",expiryInput),gbc);
		gbc.gridy = 8;
		innerPanel.add(makeAccountButton,gbc);
		
		this.add(innerPanel, BorderLayout.SOUTH);
	}
		
	/**
	 * Function used to format a label and an input JTextField side-by-side horizontally
	 * to a panel.
	 * 
	 * @param text label to put beside input field component
	 * @param textField input field component
	 * @return panel that holds both the input label and input field
	 */
	private JPanel formatLabelAndInput(String text, JTextField textField) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(new JLabel(text));
		panel.add(textField);
	    return panel;
	}

	/**
	 * Sets the text of all input fields (name, address, email, password, credit, expiry). This
	 * is called by the UIController to pre-set the fields for a registered user.
	 * 
	 * @param name name of registered user
	 * @param addr address of registered user
	 * @param email email of registered user
	 * @param pass password of registered user
	 * @param string credit of registered user
	 * @param expr expiry date of registered user
	 */
	public void setFields(String name, String addr, String email, String pass, String string, String expr) {
		firstNameInput.setText(name.split(" ")[0]);
		lastNameInput.setText(name.split(" ")[1]);
		addressInput.setText(addr);
		emailInput.setText(email);
		passwordInput.setText(pass);
		creditInput.setText(""+string);
		expiryInput.setText(expr);
	}
	
	/**
	 * Function used to clear all user input fields from this JPanel. Will be called by 
	 * UIController when it needs to clear the view.
	 */

	public void clearInputFields() {
		firstNameInput.setText("");
		lastNameInput.setText("");
		addressInput.setText("");
		emailInput.setText("");
		passwordInput.setText("");
		creditInput.setText("");
		expiryInput.setText("");
	}

	/**
	 * Displays a message indicating updating of user info
	 */
	public void displayUpdateMessage() {
		JOptionPane.showMessageDialog(null, "User information updated");
	}
	
	/**
	 * Displays a message indicating successful registration of a user
	 */
	public void displayRegisterMessage() {
		JOptionPane.showMessageDialog(null, "Successfully registered account. Please login to your new account.");
	}

	/**
	 * Displays an error message
	 * 
	 * @param message error message to display to user
	 */
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	//Getters and Setters
	public JButton getMakeAccountButton() {
		return makeAccountButton;
	}

	public JTextField getEmailInput() {
		return emailInput;
	}

	public JTextField getPasswordInput() {
		return passwordInput;
	}

	public JTextField getCreditInput() {
		return creditInput;
	}

	public JTextField getFirstNameInput() {
		return firstNameInput;
	}

	public JTextField getLastNameInput() {
		return lastNameInput;
	}
	
	public JTextField getAddressInput() {
		return addressInput;
	}
	public JTextField getExpiryInput() {
		return expiryInput;
	}

}