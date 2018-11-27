package Domian;
import SharedObjects.*;
import database.*;

public class LoginController {
	
	private DatabaseInterface DBhelper;
//	private LoginInfo lg;
//	private ObjectOutputStream socketOut;
	
	public LoginController(DatabaseInterface DBhelper) {//, LoginInfo lg, ObjectOutputStream socketOut) {
		this.DBhelper = DBhelper;
//		this.lg = lg;
//		this.setSocketOut(socketOut);
	}
//
//	public void executeMethod() {
//		if(lg.getMethodFlag()== 1) {
//			login();
//		}
//		else if(lg.getMethodFlag() == 2) {
//			registerUser();
//		}
//		else if(lg.getMethodFlag() == 3) {
//			unregisterUser();
//		}
//		else 
//			return;
//	}

	public User login(LoginInfo lg) {
		return DBhelper.checkLogin(lg);
		//socketOut.writeObject(DBhelper.checkLogin(lg));
		//return null;
	}

	
	public User registerUser(User newUser, String pass) {
		return DBhelper.addRegisterBuyer(newUser, pass);
	}
	
	public void unregisterUser(int ID) {
		DBhelper.deleteUserID(ID);

	}


}
