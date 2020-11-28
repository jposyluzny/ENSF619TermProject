package RegisteredUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.cj.jdbc.Driver;
import java.util.ArrayList;

public class RUserAccountController {

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	
	private RegisteredUserAccount rUserAcc;
	private RegisteredUser rUser;
	
	public RegisteredUserAccount queryLogin(String[] credentials) {
		String registeredUserQuery = "SELECT * FROM RUSER WHERE EMAIL = '" + credentials[0] + "' AND PASSWORD = '" + credentials[1];
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(registeredUserQuery);
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
			return new RegisteredUserAccount(rs.getString(2), rs.getString(4), rs.getString(7), rs.getString(5), rs.getInt(1), rs.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public RegisteredUserAccount getrUserAcc() {
		return rUserAcc;
	}

	public void setrUserAcc(RegisteredUserAccount rUserAcc) {
		this.rUserAcc = rUserAcc;
	}

	public RegisteredUser getrUser() {
		return rUser;
	}

	public void setrUser(RegisteredUser rUser) {
		this.rUser = rUser;
	}
}