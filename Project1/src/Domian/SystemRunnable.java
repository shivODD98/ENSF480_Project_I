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
					LoginController lc = new LoginController(DBhelper);// , (LoginInfo) obj, socketOut);
					// lc.executeMethod();
					if(lc.login((LoginInfo) obj).getType()==UserType.RegisteredBuyer) {
						subject = NotifySingleton.getInstance();
						subject.registerObserver(this);
					}
				}

				if (obj instanceof String) {
					if (((String) obj).contains("GET ALL DOCUMENTS")) {
						DocsController dc = new DocsController(DBhelper);
						socketOut.writeObject(dc.getAllDocuments());
					}
					if (((String) obj).contains("GET ALL PROMOTIONS")) {
						DocsController dc = new DocsController(DBhelper);
						socketOut.writeObject(dc.getPromotions());
					}
					if (((String) obj).contains("DELETE FROM PROMOTION")) {
						Document doc = (Document) socketIn.readObject();
						System.out.println(((Document) doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						dc.removePromotion(((Document) doc).getISBN());
					}
					if (((String) obj).contains("ADD TO PROMOTION")) {
						Document doc = (Document) socketIn.readObject();
						System.out.println(((Document) doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						dc.addPromotion(((Document) doc).getISBN());
					}
					if (((String) obj).contains("ADD THIS DOCUMENTS")) {
						Document doc = (Document) socketIn.readObject();
						System.out.println(((Document) doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						Document rt = DBhelper.addDocument(doc);
						socketOut.writeObject(rt);
						System.out.println(rt.getISBN());
						//dc.addDocument(doc);
						//might have to write all docs and promotion back
					}
					if (((String) obj).contains("DELETE THIS DOCUMENTS")) {
						Document doc = (Document) socketIn.readObject();
						System.out.println(((Document) doc).getISBN());
						DocsController dc = new DocsController(DBhelper);
						dc.deleteDocument(((Document) doc).getISBN());
						// might have to write all docs and promotion back
					}
					if(((String)obj).contains("UPDATE THIS DOCUMENT")) {
						Document doc = (Document) socketIn.readObject(); 
						System.out.println(((Document)doc).getISBN());
						//DocsController dc = new DocsController(DBhelper);
						socketOut.writeObject(DBhelper.updateDoc(doc));
//						dc.addDocument(doc);
						//might have to write all docs and promotion back
					}
					if (((String) obj).contains("ADD USER")) {
						User newUser = (User) socketIn.readObject();
						String pass = (String) socketIn.readObject();
						LoginController lc = new LoginController(DBhelper);
						socketOut.writeObject(lc.registerUser(newUser,pass));
					}
					if (((String) obj).contains("UNREGISTER USER")) {
						User newUser = (User) socketIn.readObject();
						LoginController lc = new LoginController(DBhelper);
						lc.unregisterUser(newUser.getId());
					}
					if (((String) obj).contains("UPDATE USER")) {
						User newUser = (User) socketIn.readObject();

						// NEED SOME EXTRA INFO

						// DBhelper.updateUser(newUser)
					}
					if (((String) obj).contains("BUY DOCUMENT")) {
						PaymentInfo info = (PaymentInfo) socketIn.readObject();
						OrderController oc = new OrderController(DBhelper);
						oc.placeOrder(info);
					}
					if(((String)obj).contains("QUIT")) {
						socketIn.close();
						socketOut.close();
						break;
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
		if (subject != null) {
			try {
				socketOut.writeObject(subject.documents);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
