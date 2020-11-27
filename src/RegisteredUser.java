
public class RegisteredUser extends User{

	private EmailServer eServ;
	
	public void login(int userId, String password) {
		
	}
	
	public void payFees() {
		
	}

	public EmailServer geteServ() {
		return eServ;
	}

	public void seteServ(EmailServer eServ) {
		this.eServ = eServ;
	}
}
