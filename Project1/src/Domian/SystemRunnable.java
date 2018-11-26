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
					System.out.println("in login");
					// call method that creates LoginController and does the login shit
					LoginController lc = new LoginController(DBhelper);//, (LoginInfo) obj, socketOut);
					//lc.executeMethod();
					socketOut.writeObject(lc.login((LoginInfo)obj));		
				}
				
				if(obj instanceof String) {
					if(((String)obj).contains("GET ALL DOCUMENTS")) {
						DocsController dc = new DocsController(DBhelper);
						socketOut.writeObject(dc.getAllDocuments());
					}
					if(((String)obj).contains("GET ALL PROMOTIONS")) {
						DocsController dc = new DocsController(DBhelper);
						socketOut.writeObject(dc.getPromotions());
					}
					if(((String)obj).contains("DELETE FROM PROMOTION")) {
						Document doc = (Document) socketIn.readObject(); 
						System.out.println(((Document)doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						dc.removePromotion(((Document)doc).getISBN());
					}
					if(((String)obj).contains("ADD TO PROMOTION")) {
						Document doc = (Document) socketIn.readObject(); 
						System.out.println(((Document)doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						dc.addPromotion(((Document)doc).getISBN());
					}
					if(((String)obj).contains("ADD THIS DOCUMENT")) {
						Document doc = (Document) socketIn.readObject(); 
						System.out.println(((Document)doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						dc.addDocument(doc);
						//might have to write all docs and promotion back
					}
					if(((String)obj).contains("DELETE THIS DOCUMENT")) {
						Document doc = (Document) socketIn.readObject(); 
						System.out.println(((Document)doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						dc.deleteDocument(((Document)doc).getISBN());
						//might have to write all docs and promotion back
					}
					if(((String)obj).contains("UPDATE THIS DOCUMENT")) {
						Document doc = (Document) socketIn.readObject(); 
						System.out.println(((Document)doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						dc.deleteDocument(((Document)doc).getISBN());
						dc.addDocument(doc);
						//might have to write all docs and promotion back
					}
					if(((String)obj).contains("ADD USER")) {
						User newUser = (User) socketIn.readObject();
						String pass = (String) socketIn.readObject();
						LoginController lc = new LoginController(DBhelper);
						lc.registerUser(newUser,pass);
					}
					if(((String)obj).contains("UNREGISTER USER")) {
						User newUser = (User) socketIn.readObject();
						LoginController lc = new LoginController(DBhelper);
						lc.unregisterUser(newUser.getId());
					}
					if(((String)obj).contains("UPDATE USER")) {
						User newUser = (User) socketIn.readObject();
						
						//NEED SOME EXTRA INFO
						
						
						//DBhelper.updateUser(newUser)
					}
					if(((String)obj).contains("BUY DOCUMENT")) {
						PaymentInfo info = (PaymentInfo) socketIn.readObject();
						OrderController oc = new OrderController(DBhelper);
						oc.placeOrder(info);
					}
					
					socketOut.flush();
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
