package RegisteredUser;

public class RegisteredUserAccount {

	private RUserAccountController ruac;
	private String firstName, lastName, address, creditCard, emailAddress, password;
	private int userId;
	
	
	public RegisteredUserAccount(String firstName, String lastName, String address, String creditCard, String emailAddress, int userId, String password) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(emailAddress);
		this.setCreditCard(creditCard);
		this.setEmailAddress(emailAddress);
		this.setUserId(userId);
		this.setPassword(password);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public RUserAccountController getRuac() {
		return ruac;
	}

	public void setRuac(RUserAccountController ruac) {
		this.ruac = ruac;
	}
	
	public String toString() {
		String st = "";
		st += "First Name: " + getFirstName() + ", ";
		st += "Last Name: " + getLastName() + ", ";
		st += "Address: " + getAddress() + ", ";
		st += "Credit Card: " + getCreditCard() + ", ";
		st += "Email Address: " + getEmailAddress() + ", ";
		st += "User ID: " + getUserId() + ", ";
		st += "Password: " + getPassword();
		return st;
	}
}
