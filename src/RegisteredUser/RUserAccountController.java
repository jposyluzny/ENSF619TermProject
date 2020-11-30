package RegisteredUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import Payment.PaymentInfo;
import Theatre.DBController;

/**
 * This class will query the SQL database to build all of the existing Registered Users into the ArrayList registeredUsersList, 
 * and will handle the updating of RegisteredUserAccount data as well as adding new accounts to the registeredUsersList upon a 
 * user requesting to create an account.
 */
public class RUserAccountController extends DBController{

	private ResultSet dbRegisteredUsers;
	private ArrayList<RegisteredUserAccount> registeredUsersList;
	private RegisteredUser rUser;
	private PaymentInfo feePayment;
	static RUserAccountController singleInstance = null;
	
	/**
	 * This method employs the Singleton method, so only one RUserAccountController object can be constructed at runtime.
	 */
	public static RUserAccountController getSingleInstance() {
		if(singleInstance == null) {
			singleInstance = new RUserAccountController();
		}
		
		return singleInstance;
	}
	
	/**
	 * This constructor calls the getRegisteredUsers method, which populates the registeredUsersList.
	 */
	public RUserAccountController() {
		registeredUsersList = this.initializeRegisteredUsers();
	}
	
	/**
	 * This method queries the SQL database to return a ResultSet containing all Registered Users. 
	 * @return the ResultSet of Registered Users
	 */
	public ResultSet getRegisteredUsers() {
		String query = "SELECT * FROM RUSER";
		ResultSet registeredUsers = null;
		try {
			statement = jdbc_connection.prepareStatement(query);
			registeredUsers = statement.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return registeredUsers;
	}
	
	/**
	 * This method will be fed a ResultSet and will build a new RegisteredUserAccount object with the data
	 * @param rs is the ResultSet from the SQL query
	 * @return the new RegisteredUserAccount object or null
	 */
	public RegisteredUserAccount buildAccount(ResultSet rs) {
		try {
			return new RegisteredUserAccount(rs.getInt(1), 
												rs.getString(2), 
												rs.getString(3), 
												rs.getString(4), 
												rs.getString(5), 
												rs.getString(6), 
												rs.getString(7), 
												rs.getString(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method will instantiate a new ArrayList of RegisteredUserAccount objects, and go through the ResultSet returned by the getRegisteredUsers method
	 * in order to build a new RegisteredAccount object for each row and add that new object to the registeredUsersList.
	 * @return the populated registeredUsersList ArrayList.
	 */
	public ArrayList<RegisteredUserAccount> initializeRegisteredUsers() {
		ArrayList<RegisteredUserAccount> registeredUsersList = new ArrayList<RegisteredUserAccount>();
		dbRegisteredUsers = getRegisteredUsers();
		try {
			while(dbRegisteredUsers.next()) {
				RegisteredUserAccount newAccount = buildAccount(dbRegisteredUsers);
				registeredUsersList.add(newAccount);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return registeredUsersList;
	}
	
	/**
	 * This method will be called when a new user is to be added to the existing users. It checks every input to ensure
	 * proper formatting and types of data, as well as seeing if the input email and credit card already exist in the list.
	 * @param accountData is the String array of accountData being passed from the GUI
	 * @return Strings which are messages, specifying what went wrong
	 */
	public String checkNewAccountData(String[] accountData) {
		// Check existing users for matching email and credit card
		for(RegisteredUserAccount account : registeredUsersList) {
			if(accountData[3].equals(account.getEmailAddress())) {
				return "That email address already exists!";
			}
			if(accountData[5].equals(account.getCreditCard())) {
				return "That credit card already exists!";
			}
		}
		return checkExistingAccountData(accountData);
	}
	
	/**
	 * This method will be called when a user wants to update their account information. It checks every input to ensure
	 * proper formatting and types of data.
	 * @param accountData is the String array of accountData being passed from the GUI
	 * @return Strings which are messages, specifying what went wrong
	 */
	public String checkExistingAccountData(String[] accountData) {
		// Check to make sure the credit card number is in the correct format
		try {
			Integer.parseInt(accountData[5]);
			if(accountData[5].length() != 6) {
				return "That credit card number is wrong!";
			}
		}catch(NumberFormatException e) {
			return "That credit card number is wrong!";
		}

		// Check to make sure the email address is in the correct format
		try{
			String firstHalf = accountData[3].split("@")[0];
			String secondHalf = accountData[3].split("@")[1];
			String dotCom = secondHalf.substring(secondHalf.length() - 4);
			if(!dotCom.equals(".com")) {
				return "Email format is wrong!";
			}
		} catch(IndexOutOfBoundsException e) {
			return "Email format is wrong!";
		}
		
		// Check the credit card's expiry date
		try {
			LocalDate localDate = LocalDate.now();
			int currentDay = localDate.getDayOfMonth();
			int currentMonth = localDate.getMonthValue();
			int currentYear = localDate.getYear();

			String[] expiryInfo = accountData[6].split("/");
			if(expiryInfo.length > 3) {
				return "Expiry date is wrong!";
			}
			for(String element : expiryInfo) {
				Integer.parseInt(element);
			}
			
			int inputDay = Integer.parseInt(expiryInfo[0]);
			int inputMonth = Integer.parseInt(expiryInfo[1]);
			int inputYear = Integer.parseInt(expiryInfo[2]) + 2000;

			if(inputDay < 1 || inputDay > 31) {
				return "Expiry date is wrong!";
			}
			if(inputMonth < 1 || inputMonth > 12) {
				return "Expiry date is wrong!";
			}
			if(inputYear < currentYear) {
				return "Expiry date is wrong!";
			}
			if(inputYear == currentYear) {
				if(inputMonth < currentMonth) {
					return "Expiry date is wrong!";
				}
				else if(inputMonth == currentMonth) {
					if(inputDay < currentDay) {
						return "Exiry date is wrong!";
					}
				}
			}
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			return "Expiry date is wrong!";
		}
		return "Information is correct!";
	}
	
	/**
	 * This method will take a String array accountData with the necessary information required to build a new RegisteredUserAccount object, and will build a new object.
	 * @param accountData is the String array containing all the necessary information
	 * @return the new RegisteredUserAccount object
	 */
	public RegisteredUserAccount buildNewAccount(String[] accountData) {
		int newId = getRegisteredUsersList().get(getRegisteredUsersList().size()-1).getUserId() + 1;
		return new RegisteredUserAccount(newId, 
											accountData[0], 
											accountData[1], 
											accountData[2], 
											accountData[3], 
											accountData[4], 
											accountData[5], 
											accountData[6]);
	}
	
	/**
	 * This method will add a newly constructed RegisteredUserObject to the registeredUsersList.
	 * @param newAccount
	 */
	public void addNewUserAccount(RegisteredUserAccount newAccount) {
		getRegisteredUsersList().add(newAccount);
	}
	
	/**
	 * This method will build a new PaymentInfo object to pay the annual fee associated with having a Registered User Account.
	 * @param fee is the annual cost of the membership
	 * @param rUserAcc is the RegisteredUserAccount that the fee is being paid for
	 */
	public void payFee(double fee, RegisteredUserAccount rUserAcc) {
		this.setFeePayment(new PaymentInfo(rUserAcc.getCreditCard(), rUserAcc.getExpiry(), fee, true));
		feePayment.confirmPayment();
		
	}
	
	public RegisteredUser getRUser() {
		return rUser;
	}

	public void setrUser(RegisteredUser rUser) {
		this.rUser = rUser;
	}

	public ArrayList<RegisteredUserAccount> getRegisteredUsersList() {
		return registeredUsersList;
	}

	public void setRegisteredUsersList(ArrayList<RegisteredUserAccount> registeredUsersList) {
		this.registeredUsersList = registeredUsersList;
	}

	public PaymentInfo getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(PaymentInfo feePayment) {
		this.feePayment = feePayment;
	}
	
}