package Domian;
import SharedObjects.*;
import database.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.DatabaseMetaData;

public class LoginController {
	
	private DatabaseInterface DBhelper;
	private LoginInfo lg;
	private ObjectOutputStream socketOut;
	
	public LoginController(DatabaseInterface DBhelper, LoginInfo lg, ObjectOutputStream socketOut) {
		this.DBhelper = DBhelper;
		this.lg = lg;
		this.setSocketOut(socketOut);
	}

	public void executeMethod() {
		if(lg.getMethodFlag()== 1) {
			login();
		}
		else if(lg.getMethodFlag() == 2) {
			registerUser();
		}
		else if(lg.getMethodFlag() == 3) {
			unregisterUser();
		}
		else 
			return;
	}

	private void login() {
		try {
			socketOut.writeObject(DBhelper.checkLogin(lg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void registerUser() {
		try {
			socketOut.writeObject(DBhelper.addRegisterBuyer(newUser, pass));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void unregisterUser() {
		try {
			if(DBhelper.deleteUserID(ID)) {
				socketOut.writeObject("User Was Succesfully Deleted..");
			}
			else
				socketOut.writeObject("User Could Not Be Deleted...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObjectOutputStream getSocketOut() {
		return socketOut;
	}

	public void setSocketOut(ObjectOutputStream socketOut) {
		this.socketOut = socketOut;
	}

}
