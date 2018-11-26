package Domian;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import SharedObjects.*;
import database.*;

public class SystemRunnable implements Runnable, Observer {

	/**
	 * Sends outputs through the clientSocket to the Client
	 */
	private ObjectOutputStream socketOut;

	/**
	 * Receives Inputs from the clientSocket to the Client
	 */
	private ObjectInputStream socketIn;

	private Socket aSocket;
	private DatabaseInterface DBhelper;
	private NotifySingleton subject;

	public SystemRunnable(Socket aSocket, DatabaseInterface DBhelper)// , JDBCHelper sqlHelper, FileHelper file)
	{
		this.aSocket = aSocket;
		this.DBhelper = DBhelper;
		try {
			socketOut = new ObjectOutputStream(aSocket.getOutputStream());
			socketIn = new ObjectInputStream(aSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		subject = NotifySingleton.getInstance();
		subject.registerObserver(this);
		update();
	}

	@Override
	public void run() {
		System.out.println("in run");
//		DatabaseInterface DBhelper = new DatabaseInterface();

		while (true) {

			try {
				Object obj = socketIn.readObject();

				// Check Login
				if (obj instanceof LoginInfo) {
					// call method that creates LoginController and does the login shit
					LoginController lc = new LoginController(DBhelper, (LoginInfo) obj, socketOut);
					lc.executeMethod();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void update() {
		// send list of promotion items from NotifySingleton to User or notify user somehow that the list has changed

	}

}
