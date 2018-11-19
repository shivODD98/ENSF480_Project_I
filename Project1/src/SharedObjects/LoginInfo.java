package SharedObjects;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	/*	methodFlag indicates which method to execute:
		1:Login
		2:Register
		3:Unregister
	*/
	private int methodFlag;
	
	public LoginInfo(String user, String pass)
	{
		username = user;
		password = pass;
	}
	public String getUsername() { return username; }
	
	public void setUsername(String username) { this.username = username; }
	
	public String getPassword() { return password;}
	
	public void setPassword(String password) { this.password = password; }
	
	public int getMethodFlag() { return methodFlag;}
	
	public void setMethodFlag(int methodFlag) { this.methodFlag = methodFlag; }
	
	
}
