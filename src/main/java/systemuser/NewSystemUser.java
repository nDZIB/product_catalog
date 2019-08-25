package systemuser;

public class NewSystemUser extends CurrentSystemUser{
	private String userRealName;

	
	//constructor with parameters
	public NewSystemUser(String userRealName, String userName, String userPassword) {
		super(userName, userPassword);
		this.userRealName = userRealName;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	
	//getters
	public String getUserRealName() {
		return userRealName;
	}
	
	
	//setters
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	


}
