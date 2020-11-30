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

import RegisteredUser.RegisteredUser;
import RegisteredUser.RegisteredUserAccount;
import Reservation.Ticket;
import Theatre.Movie;
import Theatre.Theatre;
import User.User;
import Theatre.Showtime;
import Theatre.Seat;

public class UIController {
		
	private MovieUI app;
	private BrowseMoviesUI movieView;
	private BrowseSeatUI seatView;
	private CancellationUI cancelView;
	private PaymentUI paymentView;
	private AccountUI accountView;
	private User user;
	
	//testing
	private ArrayList<Integer> selectedSeatNumbers = new ArrayList<Integer>();
	int userType = 1;
	
	public UIController(MovieUI a,User u) {
		this.app=a;
		movieView = app.getMovieView();
		seatView = app.getSeatView();
		paymentView = app.getPaymentView();
		cancelView = app.getCancelView();
		accountView = app.getAccountView();
		user = u;
		
		if(u instanceof RegisteredUser) {
			userType=2;
			RegisteredUserAccount ruAcc = ((RegisteredUser)user).getRUAccount();
			
			accountView.setFields(ruAcc.getFirstName()+" "+ruAcc.getLastName(), ruAcc.getAddress(), 
					ruAcc.getEmailAddress(), ruAcc.getPassword(), ruAcc.getCreditCard(), ruAcc.getExpiry());
			
			cancelView.setEmailField(ruAcc.getEmailAddress());
		}
		
		
		addMovieViewListeners();
		addSeatViewListeners();
		addCancelViewListeners();
		addAccountViewListeners();			
	}
	
	public void runApp(Login login) {
		if(login.getUserType()==1){
			app.makeOrdinaryGUI();
		}
		else if(login.getUserType()==2) {
			app.makeRegisteredGUI(login.getEmailAddress());
		}
	}
	
	//this method adds all action listeners to movie-browsing panel
	private void addMovieViewListeners() {
		
		//Action 1: Search Button to search movies
		movieView.getSearchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	DefaultListModel<String> DLM = new DefaultListModel();
		    	movieView.getTheatreList().removeAllItems();		    	
		    	String movie = movieView.getMovieInput().getText();

		    	for(Movie m:user.getModelController().getMovies()) {
		    		if(m.getName().contains(movie)) {
		    			DLM.addElement(m.getName());
		    		}
		    	}
		    	movieView.getSearchList().setModel(DLM);
		    }
		}); 
		
		//Action 2: Clicking JList of Movies
		MouseListener m = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				movieView.getTheatreList().removeAllItems();
				
				if(e.getClickCount()==1) {
					try {
						String movieName = movieView.getSearchList().getSelectedValue();
						for(Movie m:user.getModelController().getMovieList()) {
							if(m.getName().equals(movieName)) {
								user.setUserSelectedMovie(m);
							}
						}

						for(Theatre t:user.getUserSelectedMovie().getTheatres()) {
							movieView.getTheatreList().addItem(t.getName());
						}
					}
					catch(NullPointerException n) { }
				}
				
			}
		};
		movieView.getSearchList().addMouseListener(m);
	
		//Action 3: Clicking list of theatres in JCombobox
		movieView.getTheatreList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				movieView.getShowTimeList().removeAllItems();
				
		        JComboBox cb = (JComboBox)e.getSource();
		        String theatreName = (String)cb.getSelectedItem();
		        
				for(Theatre t:user.getUserSelectedMovie().getTheatres()) {
					if(t.getName().equals(theatreName)) {
						user.setUserSelectedTheatre(t);
					}
				}
		        
				for(Showtime s:user.getUserSelectedTheatre().getShowtimes()) {
					movieView.getShowTimeList().addItem(s.getDate()+" "+s.getTime());
				}
		    }
		});
		
		//Action 4: User selects their showtime from JCombobox
		movieView.getShowTimeList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JComboBox cb = (JComboBox)e.getSource();
		        String showTime = (String)cb.getSelectedItem();

				for(Showtime s:user.getUserSelectedTheatre().getShowtimes()) {
					String test = s.getDate()+" "+s.getTime();
					if(test.equals(showTime)) {
						user.setUserSelectedShowtime(s);
					}
				}
		    }
		});
		
		//Action 5: User presses "View available seats"
		movieView.getSeatButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	if(!(user.getUserSelectedMovie()==null) && !(user.getUserSelectedTheatre()==null) && !(user.getUserSelectedShowtime()==null)) {
		    		selectedSeatNumbers.clear(); //user has selected a new movie, so clear any previous selections
		    		ArrayList<Seat> seatList = user.getUserSelectedShowtime().getSeats();	
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
	
	//Clicking button in seat diagram lets user select seats to reserve
	private void addSeatButtonListeners() {
		JButton[] seats = seatView.getSeats();
		
		for(int i=0;i<20;i++) {
			seats[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<20;i++) {
						if(e.getSource()==seats[i]) {
							if(seats[i].getText()=="") {
								seats[i].setText("O");
								selectedSeatNumbers.add(i);
							}
							else if(seats[i].getText()=="O") {
								seats[i].setText("");
								selectedSeatNumbers.remove(Integer.valueOf(i));
							}
						}
					}
			    }
			});
		}
	}

	private void resetDisplays() {
		movieView.clearDisplay();
		seatView.clearDisplay();
		
		user.setUserSelectedMovie(null);
		user.setUserSelectedTheatre(null);
		user.setUserSelectedShowtime(null);
		user.getUserSelectedSeats().clear();
	}
	
	private void addSeatViewListeners() {
		//Action 6: Adds action listener to seat buttons in diagram
		addSeatButtonListeners();
		
		//Action 7: Reserving a ticket button
		seatView.getTicketButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.getUserSelectedSeats().clear();
				
				if(user.getUserSelectedMovie()!=null && user.getUserSelectedTheatre()!=null
						&& user.getUserSelectedShowtime()!=null && selectedSeatNumbers.isEmpty()==false) {
					for(int i:selectedSeatNumbers) {
						for(Seat s:user.getUserSelectedShowtime().getSeats()) {
							if(s.getSeatNumber()==i) {
								user.getUserSelectedSeats().add(s);
							}
						}						
					}
					
					//use payment view to prompt user for payment info
					user.calculateReservationPrice(user.getUserSelectedSeats());
					ArrayList<String> paymentInfo = paymentView.paymentInfoDialog(userType,user.getReservationPrice(),1);
					
					if(paymentInfo.isEmpty()==false && userType==1) {
						//To reserve ticket, create reservation object
						boolean hasVoucher = user.findVoucher(paymentInfo.get(0));
						if(hasVoucher==true) {
							paymentView.displayVoucherAppliedMessage();
						}
						user.makeReservation(paymentInfo.get(0));
						user.makePayment(paymentInfo.get(1), paymentInfo.get(2));
						user.confirmPayment();
						resetDisplays();

					}
					
					else if(userType==2) { //registered user reserving a ticket
						((RegisteredUser)user).makeReservation(); //explicitly down-casting to registered user 
						((RegisteredUser)user).makePayment();
						((RegisteredUser)user).confirmPayment();
						resetDisplays();
					}
				}
		    }
		});
	}
	
	private void addCancelViewListeners() {
		
		//Action 8: User types email address to view the tickets they have booked
		cancelView.getSearchButton().addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	String email = cancelView.getEmailInput().getText();
		    	ArrayList<Ticket> temp = user.makeCancellation(email);
		    	
		    	DefaultListModel<String> DLM = new DefaultListModel();
		    	cancelView.getEmailList().setModel(DLM);
		    	
		    	if(temp.isEmpty()==false) {
			    	for(Ticket t:temp) {
			    		String s = "Ticket "+t.getTicketNumber()+":"+t.getMovie().getName()
			    				+","+t.getTheatre().getName()+","+t.getShowtime().getDate()+","+t.getShowtime().getTime();
			    		DLM.addElement(s);
			    	}
			    	cancelView.getEmailList().setModel(DLM);	
		    	}
		    }
		});
		
		//Action 9: User cancels their reservation
		cancelView.getCancelButton().addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	int numberOfReservations = user.makeCancellation(cancelView.getEmailInput().getText()).size();
		    	if(userType==1) {
		    		ArrayList<String> paymentInfo = paymentView.paymentInfoDialog(userType,numberOfReservations*12*0.85,2); //not 100% sure if i need this 85% here
		    		if(paymentInfo.isEmpty()==false) {
		    			user.confirmCancellation(paymentInfo.get(0), paymentInfo.get(1), paymentInfo.get(2));
		    		}
		    	}
		    	else if(userType==2) {
		    		ArrayList<String> paymentInfo = paymentView.paymentInfoDialog(userType,numberOfReservations, 2);
		    		((RegisteredUser)user).confirmCancellation();
		    	}
		    	
	    		cancelView.clearDisplay();
		    }
		});
	}
	
	private void addAccountViewListeners() {
		//Action 10: Registered user pays annual fee
		app.getButton5().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> paymentInfo = paymentView.paymentInfoDialog(userType,20.00,1);
				((RegisteredUser)user).payFee();
		    }
		});
		
		//Action 11: User makes a new account or updates their existing information
		accountView.getMakeAccountButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = accountView.getNameInput().getText();
				String newAddress = accountView.getAddressInput().getText();
		    	String newEmail = accountView.getEmailInput().getText();
		    	String newPassword = accountView.getPasswordInput().getText();
		    	String newCredit = accountView.getCreditInput().getText();
		    	String newExpiry = accountView.getExpiryInput().getText();
		    	
		    	if(!newName.equals("") && !newAddress.equals("") && !newEmail.equals("") 
		    			&& !newPassword.equals("") && !newCredit.equals("")) {
		    		
		    		if(userType==1) {
			    		PaymentUI payment = new PaymentUI();
			    		ArrayList<String> paymentInfo = payment.paymentInfoDialog(2,20,1);
			    		
			    		String[] accountData = {newName.split(" ")[0], newName.split(" ")[1], newAddress, newEmail, newPassword, newCredit, newExpiry};
			    		
			    		RegisteredUser newAccount = new RegisteredUser(accountData);
			    		
				    	accountView.displayRegisterMessage();
		    		}
		    		
		    		else if(userType==2){
		    			RegisteredUserAccount acc = ((RegisteredUser) user).getRUAccount();
		    			acc.setFirstName(newName.split(" ")[0]);
		    			acc.setLastName(newName.split(" ")[1]);
		    			acc.setAddress(newAddress);
		    			acc.setEmailAddress(newEmail);
		    			acc.setPassword(newPassword);
		    			acc.setCreditCard(newCredit);
		    			acc.setExpiry(newExpiry);
		    			
		    			accountView.displayUpdateMessage();
		    		}
		    	}
		    }
		});   
	}

}
