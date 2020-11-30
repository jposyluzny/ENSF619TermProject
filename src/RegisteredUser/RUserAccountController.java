package RegisteredUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Payment.PaymentInfo;
import Theatre.DBController;

public class RUserAccountController extends DBController{

	private Statement stmt;
	private ResultSet rs;
	private RegisteredUserAccount rUserAccount;
	private RegisteredUser rUser;
	private PaymentInfo feePayment;
	
	public RegisteredUserAccount queryLogin(String email, String password) {
		String loginQuery = "SELECT * FROM RUSER WHERE EMAIL = '" + email + "' AND PASSWORD = '" + password + "'";
		try {
			stmt = jdbc_connection.createStatement();
			rs = stmt.executeQuery(loginQuery);
			if(rs.isBeforeFirst()) {
				rs.next();
				return buildAccount(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public RegisteredUserAccount buildAccount(ResultSet rs) {
		try {
			return new RegisteredUserAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void payFee(double fee, RegisteredUserAccount rUserAcc) {
		this.setFeePayment(new PaymentInfo(Integer.toString(rUserAcc.getCreditCard()), rUserAcc.getExpiry(), fee, true));
		feePayment.confirmPayment();
		
	}
	
	public RegisteredUser getRUser() {
		return rUser;
	}

	public void setrUser(RegisteredUser rUser) {
		this.rUser = rUser;
	}

	public RegisteredUserAccount getRUserAccount() {
		return rUserAccount;
	}

	public void setRUserAccount(RegisteredUserAccount rUserAccount) {
		this.rUserAccount = rUserAccount;
	}

	public PaymentInfo getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(PaymentInfo feePayment) {
		this.feePayment = feePayment;
	}
}