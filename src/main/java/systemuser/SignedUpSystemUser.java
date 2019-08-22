package systemuser;

public class SignedUpSystemUser {
	String userName;
	String userPassword;
	
	
	public SignedUpSystemUser(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
