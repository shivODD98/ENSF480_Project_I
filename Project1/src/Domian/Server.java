package Domian;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import SharedObjects.*;
import database.DatabaseInterface;

public class Server implements portInformation{

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket aSocket;
	private ServerSocket serverSocket;
	private ExecutorService pool;
	private int numClients = 0;
	private DatabaseInterface DBhelper;
	//private JDBCHelper sqlHelper;
	//private FileHelper fileHelper;
	
	/**
	 * 
	 * @param portNumber
	 */
	public Server() {
		//sqlHelper = new JDBCHelper();
		//fileHelper = new FileHelper();
		DBhelper = new DatabaseInterface();
		NotifySingleton.getInstance().updateList(new DocsController(new DatabaseInterface()));
		try {
			System.out.println(InetAddress.getLocalHost());
			serverSocket = new ServerSocket(PORT_NUMBER);
			//serverSocket = new ServerSocket(PORT_NUMBER, 20, InetAddress.getLocalHost());
			pool = Executors.newCachedThreadPool();
			
		} catch (IOException e) {
			System.out.println("Create new socket error");
			System.out.println(e.getMessage());
		} 
		
	}
	
	private void communicate() {
		System.out.println("D3L Server is now RUNNING");
		
	     try {
	   	  
	    	 while(true) {
	    		 aSocket = serverSocket.accept();   
				 
	    		 System.out.println("Client #" + ++numClients + " Connected");
	    		 
	    		 SystemRunnable system = new SystemRunnable(aSocket, DBhelper);//, sqlHelper, fileHelper);
	    		 pool.execute(system);
	    	 }

	       } catch (IOException e) {
	    	   System.out.println("Server Error: @ Client#" + numClients);
	    	   e.getMessage();
	    	   pool.shutdown();
	       }
			try {
				in.close();
				out.close();
				aSocket.close();
				System.out.println("Sever Shutdown");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
	}
	
	
	public static void main(String[] args) {
		Server thisServer = new Server();	
		thisServer.communicate();
	}

}
