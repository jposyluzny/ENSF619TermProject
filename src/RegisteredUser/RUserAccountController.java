package RegisteredUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Payment.PaymentInfo;
import Theatre.DBController;

public class RUserAccountController extends DBController{

	private ResultSet dbRegisteredUsers;
	private ArrayList<RegisteredUserAccount> registeredUsersList;
	private RegisteredUser rUser;
	private PaymentInfo feePayment;
	
	public RUserAccountController() {
		registeredUsersList = this.getRegisteredUsers();
	}
	
	public ResultSet initializeRegisteredUsers() {
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
	
	public RegisteredUserAccount buildAccount(ResultSet rs) {
		try {
			return new RegisteredUserAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<RegisteredUserAccount> getRegisteredUsers() {
		ArrayList<RegisteredUserAccount> registeredUsersList = new ArrayList<RegisteredUserAccount>();
		dbRegisteredUsers = initializeRegisteredUsers();
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

	public RegisteredUserAccount buildNewAccount(String[] accountData) {
		int newId = getRegisteredUsersList().get(getRegisteredUsersList().size()-1).getUserId() + 1;
		return new RegisteredUserAccount(newId, accountData[0], accountData[1], accountData[2], accountData[3], accountData[4], accountData[5], accountData[6]);
	}
	
	public void addNewUserAccount(RegisteredUserAccount newAccount) {
		getRegisteredUsersList().add(newAccount);
	}
	
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
	
	public static void main(String[] args) {
		RUserAccountController cont = new RUserAccountController();
		ArrayList<RegisteredUserAccount> list = cont.getRegisteredUsers();
		for(RegisteredUserAccount user : list) {
			System.out.println(user);
		}
		
	}
}