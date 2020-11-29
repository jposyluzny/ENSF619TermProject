package GUI;

import java.awt.*;
import javax.swing.*;

public class BrowseMoviesUI extends JPanel {
	
	private JPanel moviePanel = new JPanel();
	private JPanel movieInputPanel = new JPanel();
	private JPanel theatrePanel = new JPanel();	
	private JPanel showTimePanel = new JPanel();
	private JTextField movieInput = new JTextField(15);
	private JButton searchButton = new JButton("Search");
	private JButton seatButton = new JButton("View available seats");
	
	private JList<String> searchList;
	private JComboBox<String> theatreList = new JComboBox<String>();
	private JComboBox<String> showTimeList = new JComboBox<String>();

	public BrowseMoviesUI(){  
		makePanel(); 
	}
	
	private void makePanel() { 
		this.setLayout(new GridBagLayout());	
		JLabel panelTitle = new JLabel("Reserve a Ticket",SwingConstants.CENTER);
		panelTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(panelTitle,gbc);
		
		gbc.gridy = 1;
		searchPanel();
		this.add(movieInputPanel,gbc);
		
		gbc.gridy = 2;
 
		theatreList.setPreferredSize(new Dimension(200,20));
		
		createSearchField(new JLabel("Search Theatres"),theatrePanel,theatreList);
		this.add(theatrePanel,gbc);
		
		gbc.gridy = 3;
		showTimeList.setPreferredSize(new Dimension(200,20));
		createSearchField(new JLabel("Search ShowTimes"),showTimePanel,showTimeList);
		this.add(showTimePanel,gbc);
		
		
		gbc.gridy = 4;
		this.add(seatButton,gbc);
		
	}
	
	
	private void createSearchField(JLabel label,JPanel panel,JComboBox combobox) {
        panel.add(label);
        panel.add(combobox); 
	}
	
	private void searchPanel() {
		JPanel temp = new JPanel();
		temp.setLayout(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		
		temp.add(movieInput,gbc1);
		
		gbc1.gridx = 1;
		temp.add(searchButton,gbc1);
		
		moviePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		moviePanel.add(temp,gbc2);
		
		gbc2.gridy=1;
		
		searchList = new JList();
		searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		JScrollPane listScrollPane = new JScrollPane(searchList);
		listScrollPane.setPreferredSize(new Dimension(270,50));
		moviePanel.add(listScrollPane,gbc2);
		
		//Spring layout for input
		SpringLayout layout = new SpringLayout();
		movieInputPanel.setLayout(layout);
		JLabel label = new JLabel("Search Movies");
		movieInputPanel.add(label);
		movieInputPanel.add(moviePanel);
		
		layout.putConstraint(SpringLayout.WEST, label,5, SpringLayout.WEST, movieInputPanel); 
        layout.putConstraint(SpringLayout.NORTH, label,5, SpringLayout.NORTH, movieInputPanel); 
        layout.putConstraint(SpringLayout.WEST, moviePanel,5, SpringLayout.EAST, label); 
        layout.putConstraint(SpringLayout.NORTH, moviePanel, 5, SpringLayout.NORTH, movieInputPanel); 
        layout.putConstraint(SpringLayout.EAST, movieInputPanel, 5, SpringLayout.EAST, moviePanel); 
        layout.putConstraint(SpringLayout.SOUTH, movieInputPanel, 5, SpringLayout.SOUTH, moviePanel); 
	}
	
	
	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getSeatButton() {
		return seatButton;
	}
	public JTextField getMovieInput() {
		return movieInput;
	}

	public JList<String> getSearchList() {
		return searchList;
	}

	public JComboBox<String> getTheatreList() {
		return theatreList;
	}

	public JComboBox<String> getShowTimeList() {
		return showTimeList;
	}

}
