package systemuser;

public class SystemUser {
	private String userRealName;
	private String userName;
	private String userPassword;

	
	//constructor with parameters
	public SystemUser(String userRealName, String userName, String userPassword) {
		this.userRealName = userRealName;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	
	//getters
	public String getUserRealName() {
		return userRealName;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	
	//setters
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	

}
