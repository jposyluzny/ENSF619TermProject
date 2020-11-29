package GUI;

import Theatre.ModelController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Theatre.Movie;
import Theatre.Theatre;
import Theatre.Showtime;
import Theatre.Seat;

public class UIController {
	
	//CAN DELETE THIS AFTER WE CONNECT TO USER, I PUT THIS HERE FOR TESTING ONLY SO I COULD USE A LOCAL INSTANCE
	ModelController controller = new ModelController();
	ArrayList<Movie> movieList = controller.getMovies();
	
	private MovieApp app;
	private BrowseMoviesUI movieView;
	private BrowseSeatUI seatView;
	private CancellationUI cancelView;
	private PaymentUI paymentView;
	private AccountUI accountView;
	
	Movie movieInput = null;
	Theatre theatreInput = null;
	Showtime showtimeInput=null;
	int selectedSeatNumber = -1;
	
	int userType = 1;
	
	public UIController(MovieApp a) {
		this.app=a;
		movieView = app.getMovieView();
		seatView = app.getSeatView();
		paymentView = app.getPaymentView();
		cancelView = app.getCancelView();
		accountView = app.getAccountView();
		
		addMovieViewListeners();
		addSeatViewListeners();
		addCancelViewListeners();
		addAccountViewListeners();
		
		userType = app.getUserType();
		
	}
	
	//this method adds all action listeners to movie-browsing panel
	private void addMovieViewListeners() {
		//button1
		movieView.getSearchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	DefaultListModel<String> DLM = new DefaultListModel();
		    	movieView.getTheatreList().removeAllItems();		    	
		    	String movie = movieView.getMovieInput().getText();

		    	for(Movie m:movieList) {
		    		if(m.getName().contains(movie)) {
		    			DLM.addElement(m.getName());
		    		}
		    	}
		    	movieView.getSearchList().setModel(DLM);
		    }
		}); 
		
		MouseListener m = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				movieView.getTheatreList().removeAllItems();
				
				if(e.getClickCount()==1) {
					try {
						String movieName = movieView.getSearchList().getSelectedValue();
						for(Movie m:movieList) {
							if(m.getName().equals(movieName)) {
								movieInput=m;
							}
						}

						for(Theatre t:movieInput.getTheatres()) {
							movieView.getTheatreList().addItem(t.getName());
						}
					}
					catch(NullPointerException n) { }
				}
				
			}
		};
		movieView.getSearchList().addMouseListener(m);
	
		movieView.getTheatreList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movieView.getShowTimeList().removeAllItems();
				
		        JComboBox cb = (JComboBox)e.getSource();
		        String theatreName = (String)cb.getSelectedItem();
		        
				for(Theatre t:movieInput.getTheatres()) {
					if(t.getName().equals(theatreName)) {
						theatreInput=t;
					}
				}
		        
				for(Showtime s:theatreInput.getShowtimes()) {
					movieView.getShowTimeList().addItem(s.getDate()+" "+s.getTime());
				}
		    }
		});
		
		movieView.getShowTimeList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JComboBox cb = (JComboBox)e.getSource();
		        String showTime = (String)cb.getSelectedItem();

				for(Showtime s:theatreInput.getShowtimes()) {
					String test = s.getDate()+" "+s.getTime();
					if(test.equals(showTime)) {
						showtimeInput=s;
					}
				}
		    }
		});
		
		movieView.getSeatButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	if(!(movieInput==null) && !(theatreInput==null) && !(showtimeInput==null)) {
		    		ArrayList<Seat> seatList = showtimeInput.getSeats();
		    		JButton[] seats = seatView.getSeats();

		    		for(int i=0;i<20;i++) {
		    			seats[i].setText("");
		    	    }
		    		
		    		for(int i=0;i<seatList.size();i++) {
		    			if(seatList.get(i).isOccupied()==true) {
		    				seats[i].setText("X");
		    			}
		    			else if(seatList.get(i).isOccupied()==false){
		    				seats[i].setText("");
		    			}
			    	}
		    	}
		    }
		});
		
	}
	
	private void addSeatButtonListeners() {
		JButton[] seats = seatView.getSeats();
		
		for(int i=0;i<20;i++) {
			seats[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<20;i++) {
						if(e.getSource()==seats[i]) {
							if(seats[i].getText()=="" && selectedSeatNumber==-1) {
								seats[i].setText("O");
								selectedSeatNumber = i;
							}
							else if(seats[i].getText()=="O" && selectedSeatNumber!=-1) {
								seats[i].setText("");
								selectedSeatNumber = -1;
							}
							else if(seats[i].getText()=="X") {
								
							}
						}
					}
			    }
			});
		}
	}

	
	private void addSeatViewListeners() {
		
		addSeatButtonListeners();
		
		seatView.getTicketButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	if(selectedSeatNumber!=-1) {
		    		System.out.println("Reserving seat number: "+selectedSeatNumber+" for: "+
		    				movieInput.getName()+" "+theatreInput.getName()+" "+showtimeInput.getDate()+" "+showtimeInput.getTime());
		    		
		    		String[] paymentInfo = paymentView.paymentInfoDialog(userType,12);
					System.out.println("Payment from user: "+paymentInfo[0]+" & "+paymentInfo[1]+" & "+paymentInfo[2]);	
		    		paymentView.displaySuccessMessage();
		    	}
		    }
		});
	}
	
	private void addCancelViewListeners() {
		
		cancelView.getSearchButton().addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	String email = cancelView.getEmailInput().getText();
		    }
		});
		
		cancelView.getCancelButton().addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
	    		String[] paymentInfo = paymentView.paymentInfoDialog(userType,12);
				System.out.println("Payment from user: "+paymentInfo[0]+" & "+paymentInfo[1]+" & "+paymentInfo[2]);	
		    }
		});
	}
	
	private void addAccountViewListeners() {
		//pay annual fee
		app.getButton5().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] paymentInfo = paymentView.paymentInfoDialog(userType,20);
				System.out.println("Payment from user: "+paymentInfo[0]+" & "+paymentInfo[1]+" & "+paymentInfo[2]);	
		    }
		});
		
		//button6
		accountView.getMakeAccountButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = accountView.getNameInput().getText();
				String newAddress = accountView.getAddressInput().getText();
		    	String newEmail = accountView.getEmailInput().getText();
		    	String newPassword = accountView.getPasswordInput().getText();
		    	String newCredit = accountView.getCreditInput().getText();
		    	
		    	if(!newName.equals("") && !newAddress.equals("") && !newEmail.equals("") 
		    			&& !newPassword.equals("") && !newCredit.equals("")) {
		    		
		    		PaymentUI payment = new PaymentUI();
			    	String[] paymentInfo = payment.paymentInfoDialog(2,20);
			    	
			    	System.out.println("Register an account with "+newEmail+";"+newPassword+";"+newCredit);
		    	}
		    }
		});   
	}

}
