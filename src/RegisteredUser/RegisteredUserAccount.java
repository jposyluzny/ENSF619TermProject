package RegisteredUser;

public class RegisteredUserAccount {

	private String firstName, lastName, address, email, password, expiry;
	private int userId, creditCard;
	
	
	public RegisteredUserAccount(int userId, String firstName, String lastName, String address, String email, String password, int creditCard, String expiry) {
		this.setUserId(userId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setEmailAddress(email);
		this.setPassword(password);
		this.setCreditCard(creditCard);
		this.setExpiry(expiry);
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
	
	public int getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(int creditCard) {
		this.creditCard = creditCard;
	}
	
	public String getEmailAddress() {
		return email;
	}
	
	public void setEmailAddress(String email) {
		this.email = email;
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
	
	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String toString() {
		String st = "";
		st += "User ID: " + getUserId() + ", ";
		st += "First Name: " + getFirstName() + ", ";
		st += "Last Name: " + getLastName() + ", ";
		st += "Address: " + getAddress() + ", ";
		st += "Email Address: " + getEmailAddress() + ", ";
		st += "Password: " + getPassword() + ", ";
		st += "Credit Card: " + getCreditCard() + ", ";
		st += "Expiry date: " + getExpiry();
		return st;
	}
}
