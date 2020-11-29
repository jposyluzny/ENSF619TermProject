package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class AccountUI extends JPanel {
	
	private JButton makeAccountButton = new JButton("Create Account");
	private JTextField emailInput = new JTextField(12);
	private JTextField passwordInput = new JTextField(12);
	private JTextField creditInput = new JTextField(12);
	private JTextField nameInput = new JTextField(12);
	private JTextField addressInput = new JTextField(12);
	
	public AccountUI() {
		makePanel();
	}

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
		
		this.add(innerPanel, BorderLayout.SOUTH);
	}
		
	private JPanel formatLabelAndInput(String text, JTextField textField) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(new JLabel(text));
		panel.add(textField);
	    return panel;
	}

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

	public JTextField getNameInput() {
		return nameInput;
	}

	public JTextField getAddressInput() {
		return addressInput;
	}

}