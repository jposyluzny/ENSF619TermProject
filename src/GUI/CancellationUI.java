import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		addButtonListeners();
		
		//for testing only
		tickets=fakedata(); 
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
		
		MouseListener m = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1) {
					try {
						String line = emailList.getSelectedValue();
						cancelledTicketNumber = line.replace("Ticket ", "").replace(": Movie X, Theatre Y, ShowTime Z", "");
					}
					catch(NullPointerException n) { }
				}
				
			}
		};
		emailList.addMouseListener(m);
		
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
	
	private ArrayList<String> fakedata() {
		ArrayList<String> tickets = new ArrayList<String>();
		tickets.add("email1@fake.com;Ticket 1: Movie X, Theatre Y, ShowTime Z");
		tickets.add("email1@fake.com;Ticket 2: Movie X, Theatre Y, ShowTime Z");
		tickets.add("email1@fake.com;Ticket 3: Movie X, Theatre Y, ShowTime Z");
		tickets.add("email2@fake.com;Ticket 4: Movie X, Theatre Y, ShowTime Z");
		tickets.add("email3@fake.com;Ticket 5: Movie X, Theatre Y, ShowTime Z");
		tickets.add("email3@fake.com;Ticket 6: Movie X, Theatre Y, ShowTime Z");
		tickets.add("email4@fake.com;Ticket 7: Movie X, Theatre Y, ShowTime Z");
		tickets.add("email4@fake.com;Ticket 8: Movie X, Theatre Y, ShowTime Z");
		
		return tickets;
	}
	
	public void setEmailField(String email) {
		emailInput.setText(email);
	}
	
	public void getListOfTickets(String email) {
		DefaultListModel<String> DLM = new DefaultListModel();
		System.out.println("printing all tickets for: "+email);
		    	
    	for(String s:tickets) {
    		if(s.contains(email)) {
    			s = s.replace(email, "");
    			String[] temp = s.split(";");
    			for(String s2:temp) {
    				DLM.addElement(s2);
    			}
    		}
    	}
    	emailList.setModel(DLM);
    }
	
	private void addButtonListeners() {
		
		searchButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	String email = emailInput.getText();
		    	getListOfTickets(email);
		    }
		});
		cancelButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	System.out.println("cancelling ticket "+cancelledTicketNumber);
		    }
		});		
	}
	

}
