
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

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
	private BrowseSeatUI seatDiagramPanel;
	
	
	//user inputs
	String movieName=null;
	String theatreName=null;
	String showTime=null;
	
	//fake data here
	private ArrayList<String> movies = new ArrayList<String>();
	private ArrayList<String> theatreMovies = new ArrayList<String>();
	private String[] theatres = {"Crowfoot","Country Hills","Signal Hill","Westbrook"};
	
	public BrowseMoviesUI(int choice){ 
		makePanel(choice);
		makeFakeData();
		addButtonListeners();
		addComboBoxListener();
		addListListener();
	}
	
	//make fake data
	private void makeFakeData() {
		movies.add("Greenland");
		movies.add("The New Mutants");
		movies.add("Ludo");
		movies.add("Jiu Jitsu");
		movies.add("Tenet");

		theatreMovies.add("Greenland,The New Mutants,Ludo");
		theatreMovies.add("Ludo,Jiu Jitsu");
		theatreMovies.add("The New Mutants,Jiu Jitsu,Tenet");
		theatreMovies.add("Greenland,The New Mutants,Ludo,Jiu Jitsu,Tenet");
		
	}
		
	private void makePanel(int choice) { //choice = registered(2) or ordinary(1)
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
		showTimeList.setPreferredSize(new Dimension(100,20));
		createSearchField(new JLabel("Search ShowTimes"),showTimePanel,showTimeList);
		this.add(showTimePanel,gbc);
		
		
		gbc.gridy = 4;
		this.add(seatButton,gbc);
		
		gbc.gridy=5;
		seatDiagramPanel = new BrowseSeatUI(choice);
		this.add(seatDiagramPanel,gbc);
		
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

	//DUMMY METHODS TO CHANGE
	private void findTheatres(String movie) {
		for(int i=0;i<theatreMovies.size();i++) {
			if(theatreMovies.get(i).contains(movie)) {
				theatreList.addItem(theatres[i]);
			}
		}
	}
	
	private void findShowTimes() {
		showTimeList.removeAllItems();
		showTimeList.addItem("1:00");
		showTimeList.addItem("3:00");
		showTimeList.addItem("5:00");
		showTimeList.addItem("7:00");
	}
	
	
	private void addButtonListeners() {
		//button1
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	DefaultListModel<String> DLM = new DefaultListModel();
		    	theatreList.removeAllItems();		    	
		    	String movie = movieInput.getText();

		    	for(String s:movies) {
		    		if(s.contains(movie)) {
		    			DLM.addElement(s);
		    		}
		    	}
		    	searchList.setModel(DLM);
		    }
		});  
		
		//button2
		seatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	if(movieName!=null && theatreName!=null && showTime!=null) {
		    		System.out.println("Viewing available seats for: "+movieName+";"+theatreName+";"+showTime);
		    		seatDiagramPanel.getAvailableSeats(movieName,theatreName,showTime);
		    	}
		    }
		});
	}
	
	private void addListListener() {
		MouseListener m = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				theatreList.removeAllItems();
				if(e.getClickCount()==1) {
					try {
						movieName = searchList.getSelectedValue();
						findTheatres(movieName);
					}
					catch(NullPointerException n) { }
				}
				
			}
		};
		searchList.addMouseListener(m);
	}
	
	private void addComboBoxListener() {
		theatreList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JComboBox cb = (JComboBox)e.getSource();
		        theatreName = (String)cb.getSelectedItem();
		        findShowTimes();
		    }
		});
		
		showTimeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JComboBox cb = (JComboBox)e.getSource();
		        showTime = (String)cb.getSelectedItem();
		    }
		});
	}


}
