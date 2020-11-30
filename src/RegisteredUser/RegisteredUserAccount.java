package RegisteredUser;

/**
 * This class holds all of the information associated with a Registered User.
 */
public class RegisteredUserAccount {

	private String firstName, lastName, address, email, password, expiry;
	private int userId;
	String creditCard;
	
	/**
	 * This constructor builds a new RegisteredUserAccount object with all of the required input parameters.
	 * @param userId is the unique ID number of the new user
	 * @param firstName is the first name of the new user
	 * @param lastName is the last name of the new user
	 * @param address is the address of the new user
	 * @param email is the email address of the new user
	 * @param password is the password of the new user
	 * @param creditCard is the credit card of the new user
	 * @param expiry is the expiry date of the new user's credit card
	 */
	public RegisteredUserAccount(int userId, String firstName, String lastName, String address, String email, String password, String creditCard, String expiry) {
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
	
	public String getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(String creditCard) {
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
