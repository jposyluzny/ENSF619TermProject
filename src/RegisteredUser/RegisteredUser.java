package RegisteredUser;

import EmailServer.EmailServer;

public class RegisteredUser extends User{

	private EmailServer eServ;
	
	public String[] login(String email, String password) {
		String[] credentials = {email, password};
		return credentials;
	}
	
	public void payFees(String email) {
		
	}

	public EmailServer geteServ() {
		return eServ;
	}

	public void seteServ(EmailServer eServ) {
		this.eServ = eServ;
	}
}
