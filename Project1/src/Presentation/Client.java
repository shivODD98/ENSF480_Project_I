package Presentation;

import SharedObjects.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.plaf.synth.Region;

public class Client implements portInformation{
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private LoginWindow loginWindow;
	private Socket clientSocket;
	private OperatorWindow operatorWindow;
	private RegisteredBuyerWindow registeredBuyerWindow;
	private UnregisteredBuyerWindow unRegisteredBuyerWindow;
	//private StudentWindow studentWindow;
	//private ProfessorWindow professorWindow;
	private User user;
	private String userPass;
	private Scanner sc;
	
	public Client() throws IOException
	{
		loginWindow = new LoginWindow();
		loginWindow.setLoginButtonListener(new LoginControl());
		loginWindow.addWindowListener((new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
		    	//close();
		    }
		}));
		//clientSocket = new Socket(HOST_NAME, PORT_NUMBER);
		
		
		//clientSocket = new Socket(InetAddress.getByName("10.13.67.64"), PORT_NUMBER);
		//out = new ObjectOutputStream(clientSocket.getOutputStream());
		//in = new ObjectInputStream(clientSocket.getInputStream());
		
		
		//out.writeObject(new String("HELLO BIATCH"));
		
	}
	
	public void loginCommunicate() throws IOException, ClassNotFoundException
	{	
		while(true)
		{
			Object obj = in.readObject();
			
			if(obj instanceof String) {
				if(((String)obj).contains("SUP FAM"))
					System.out.println("Server Response: " + ((String)obj));

				else if(((String)obj).contains("FK")){
					System.out.println("Server Response: " + ((String)obj));
				}
				else {
					System.out.println("Sorry the server dont give a fk");
				}
				System.out.println("Respond to server on next line: ");
				String response = sc.nextLine();
				out.writeObject(response);
				out.flush();
			}
			
			if(obj instanceof User) {

				if(((User)obj) != null) {
					System.out.println("User " + ((User)obj).getUsername() + " has logged in as Type " + ((User)obj).getType());
					loginWindow.setVisible(false);
				}
				else System.out.println("User DOES NOT EXIST");
			}
//			if(obj instanceof Student) 
//			{	
//				user = (User)obj;
//				loginWindow.setVisible(false);
//				studentWindow = new StudentWindow();
//				studentWindow.setActionListeners(new StudentControl());
//				studentWindow.setName(user.getFirstName() +" "+ user.getLastName());
//				studentWindow.addWindowListener((new WindowAdapter()
//				{
//				    @Override
//				    public void windowClosing(WindowEvent we)
//				    {
//				    	close();
//				    }
//				}));
//				updateCoursesStudent(user);
//				break;
//			}
//			else if(obj instanceof Professor) 
//			{	
//				user = (User) obj;
//				loginWindow.setVisible(false);
//				professorWindow = new ProfessorWindow();
//				professorWindow.setActionListeners(new ProfessorControl());
//				professorWindow.setName(user.getFirstName() + " " + user.getLastName());
//				updateCoursesProf(user);
//				professorWindow.addWindowListener((new WindowAdapter()
//				{
//				    @Override
//				    public void windowClosing(WindowEvent we)
//				    {
//				    	close();
//				    }
//				}));
//				break;
//			}
//			else
//				loginWindow.incorrectLogin();
//				break;
//			
		}

	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, UnknownHostException, SocketException {

		try {
			Client client = new Client();
			//client.continueUnregistered();
			client.loginCommunicate();
		} catch (IOException e) {e.printStackTrace();}
		
	}

	public void operatorAfterLogin() {
		loginWindow.setVisible(false);
		operatorWindow = new OperatorWindow();
		operatorWindow.addActionListener(new OperatorController());
		
		//get promotion and docs from server, this is test data to simulate
		ArrayList<Document> docs = new ArrayList<Document>();
		Document d1 = new Document(1, "title1", "james mike", "123123", "this book is pretty figgen lit my man", DocumentType.Book, 20.00);
		Document d2 = new Document(2, "title2", "Bob Sam", "123123", "this book is pretty figgen lit my man",DocumentType.Magazine, 14.00);
		Document d3 = new Document(3, "title3", "Rya asdas", "123123", "this book is pretty figgen lit my man",DocumentType.Book,99.99);
		Document d4 = new Document(4, "title4", "stfu", "123123", "this book is pretty figgen lit my man",DocumentType.Journal,90.00);
		docs.add(d1);docs.add(d2);docs.add(d3);docs.add(d4);
		operatorWindow.updateDocumentsListModel(docs);


	}
	
	public void registeredAfterLogin() {
		loginWindow.setVisible(false);
		registeredBuyerWindow = new RegisteredBuyerWindow();
		registeredBuyerWindow.addActionListener(new RegisteredBuyerController());
		
		//get promotion and docs from server, this is test data to simulate
		ArrayList<Document> docs = new ArrayList<Document>();
		Document d1 = new Document(1, "title1", "james mike", "123123", "this book is pretty figgen lit my man", DocumentType.Book, 20.00);
		Document d2 = new Document(2, "title2", "Bob Sam", "123123", "this book is pretty figgen lit my man",DocumentType.Magazine, 14.00);
		Document d3 = new Document(3, "title3", "Rya asdas", "123123", "this book is pretty figgen lit my man",DocumentType.Book,99.99);
		Document d4 = new Document(4, "title4", "stfu", "123123", "this book is pretty figgen lit my man",DocumentType.Journal,90.00);
		docs.add(d1);docs.add(d2);docs.add(d3);docs.add(d4);
		registeredBuyerWindow.updateDocumentsListModel(docs);
		registeredBuyerWindow.updatePromotionsListModel(docs);
	}
	
	public void continueUnregistered() {
		loginWindow.setVisible(false);
		unRegisteredBuyerWindow = new UnregisteredBuyerWindow();
		unRegisteredBuyerWindow.setActionListener(new unRegisteredBuyerController());
		
		//get promotion and docs from server, this is test data to simulate
		ArrayList<Document> docs = new ArrayList<Document>();
		Document d1 = new Document(1, "title1", "james mike", "123123", "this book is pretty figgen lit my man", DocumentType.Book, 20.00);
		Document d2 = new Document(2, "title2", "Bob Sam", "123123", "this book is pretty figgen lit my man",DocumentType.Magazine, 14.00);
		Document d3 = new Document(3, "title3", "Rya asdas", "123123", "this book is pretty figgen lit my man",DocumentType.Book,99.99);
		Document d4 = new Document(4, "title4", "stfu", "123123", "this book is pretty figgen lit my man",DocumentType.Journal,90.00);
		docs.add(d1);docs.add(d2);docs.add(d3);docs.add(d4);
		unRegisteredBuyerWindow.updateDocumentsListModel(docs);
	}
	
	public class LoginControl implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == loginWindow.getLoginButton())
			{
				LoginInfo log = loginWindow.getLoginInfo();
				log.setMethodFlag(1);
				try {out.writeObject(log);}	
				catch (IOException e1) {e1.printStackTrace();}
			}
			
//			if(e.getSource() == loginWindow.getCreateAccount())
//			{
//				loginWindow.createAccountWindow();
//				loginWindow.getCreateButton().addActionListener(this);
//			}
//			
//			if(e.getSource() == loginWindow.getCreateButton())
//			{
//				Account user = loginWindow.getNewUser();
//				if(user == null)
//					return;
//				try {
//					out.writeObject(user);
//				} catch (IOException e1) {e1.printStackTrace();}
//				loginWindow.getCreateAccountWindow().dispose();
//			}
		}
	}
	
	public class OperatorController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == operatorWindow.addButton) {
				operatorWindow.createAddWindow();
				operatorWindow.createButton.addActionListener(this);
			}
			
			else if(e.getSource() == operatorWindow.updateDocument) {
				operatorWindow.createAddWindow();
				operatorWindow.createButton.addActionListener(this);
				operatorWindow.modifyDocument();
			}
			
			else if(e.getSource() == operatorWindow.deleteButton) {
				Document doc;
				if((doc = operatorWindow.removeFromList()) != null)
					System.out.println("removing "+doc.getTitle());
				//communicate with server
			}
			
			else if(e.getSource() == operatorWindow.moveToPromotionButton) {
				if(operatorWindow.isDocumentsSelected()) {
					Document doc;
					if((doc = operatorWindow.addToPromotionList())!=null) {
						System.out.println("adding "+doc.getTitle()+" to promotion");
						//communicate with server
					}
				}
				else if(operatorWindow.isPromotionListSeleted()) {
					Document doc;
					doc = operatorWindow.removeFromPromotionList();
					if(doc!=null) {
						System.out.println("removing "+doc.getTitle()+" from promotion");
						//communicate with server
					}
				}
			}
			
			else if(e.getSource() == operatorWindow.createButton) {
				Document doc = operatorWindow.createDocument();
				if(doc != null) {
					JFileChooser fc = new JFileChooser();
					JButton open = new JButton();
					fc.setDialogTitle("Choose Document");
					if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {}
					File file = fc.getSelectedFile();
					//talk to server
					
				}
			}
			
		}
		
	}

	public class RegisteredBuyerController implements ActionListener{

		Document docToSend;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == registeredBuyerWindow.orderButton) {
				Document doc;
				if((doc=registeredBuyerWindow.getSelectedDoc())!=null) {
					registeredBuyerWindow.createOrderWindow(doc.getTitle(), doc.getPrice());
					registeredBuyerWindow.confirmButton.addActionListener(this);
					docToSend = doc;
				}
				
			}
			
			else if (e.getSource() == registeredBuyerWindow.confirmButton) {
				PaymentInfo info = registeredBuyerWindow.createOrder(docToSend);
				if(info!=null) {
					registeredBuyerWindow.orderFrame.dispose();
					//communicate with server
				}
			}
			
			else if(e.getSource() == registeredBuyerWindow.unsubButton) {
				//delete user from db
				registeredBuyerWindow.frame.dispose();
				loginWindow.setVisible(true);
				//loginCommunicate();
			}
			
		}
		
	}

	public class unRegisteredBuyerController implements ActionListener{

		Document docToSend;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == unRegisteredBuyerWindow.orderButton) {
				Document doc;
				if((doc=unRegisteredBuyerWindow.getSelectedDoc())!=null) {
					unRegisteredBuyerWindow.createOrderWindow(doc.getTitle(), doc.getPrice());
					unRegisteredBuyerWindow.confirmButton.addActionListener(this);
					docToSend = doc;
				}
				
			}
			
			else if (e.getSource() == unRegisteredBuyerWindow.confirmButton) {
				PaymentInfo info = unRegisteredBuyerWindow.createOrder(docToSend);
				if(info!=null) {
					unRegisteredBuyerWindow.orderFrame.dispose();
					System.out.println("payment for "+ info.getDoc().getTitle());
					//communicate with server
				}
			}
			
			else if(e.getSource() == unRegisteredBuyerWindow.registerButton) {
				unRegisteredBuyerWindow.createRegisterWindow();
				unRegisteredBuyerWindow.registerButtonConfirm.addActionListener(this);
			}
			
			else if(e.getSource() == unRegisteredBuyerWindow.registerButtonConfirm) {
				User newUser;
				if((newUser = unRegisteredBuyerWindow.registerUser())!= null) {
					unRegisteredBuyerWindow.registerFrame.dispose();
					unRegisteredBuyerWindow.frame.dispose();
					
					//communcate with server to get new user
					//set personal user as this user
					registeredAfterLogin();
				}
			}
			
			
		}
		
	}
	
	
//	public void close()
//	{
//		try {
//			out.writeObject("QUIT");
//			in.close();
//			out.close();
//			clientSocket.close();
//		}catch(IOException e) {e.printStackTrace();}
//	}
//	



//	public class StudentControl implements ActionListener
//	{
//		public void setCourseContent()
//		{
//			studentWindow.setCourseInfo();
//			setAssignmentContent();
//	
//		}
//		public void setAssignmentContent()
//		{
//			try {
//				Assignment a = (Assignment) studentWindow.getAssignments().getSelectedItem();
//				if(a == null)
//				{
//					studentWindow.updateAssignment(null);
//					return;
//				}
//				out.writeObject(new String("GET GRADE AND COMMENT"));
//				out.flush();
//				out.writeInt(user.getId());
//				out.flush();
//				out.writeInt(a.getId());
//				out.flush();
//				Submission s = (Submission)in.readObject();
//				studentWindow.updateAssignment(s);
//			} catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
//		}
//		
//		@Override
//		public void actionPerformed(ActionEvent e) 
//		{
//			if(e.getSource() == studentWindow.getViewCourse())
//			{
//				setCourseContent();
//			}
//			
//			if(e.getSource() == studentWindow.getDownload())
//			{
//				try {
//					Assignment a = (Assignment) studentWindow.getAssignments().getSelectedItem();
//					if(a == null)
//						return;
//					out.writeObject(new String("GET ASSIGNMENT ("+a.getId()+")"));
//					Object obj = in.readObject();
//						byte[] content = null;
//					if(obj instanceof byte[])
//						content = (byte[]) obj;
//					if(content == null)
//						JOptionPane.showMessageDialog(studentWindow, "No assignment uploaded yet", 
//													   null, JOptionPane.ERROR_MESSAGE);
//				
//					
//					else
//					{
//						String extension = (String)in.readObject();
//						File newFile = new File("assignmentDownloads" + "\\\\" + a.getId() +"." + extension);
//						try{
//							if(! newFile.exists())
//								newFile.createNewFile();
//							FileOutputStream writer = new FileOutputStream(newFile);
//							BufferedOutputStream bos = new BufferedOutputStream(writer);
//							bos.write(content);
//							bos.close();
//							} catch(IOException e2){e2.printStackTrace();}
//						Desktop desktop = Desktop.getDesktop();
//						desktop.open(newFile);
//
//					}
//					
//				} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
//			}
//			
//			if(e.getSource() == studentWindow.getDropbox())
//			{
//				studentWindow.openDropBox();
//				studentWindow.setDropboxActionListener(this);
//			}
//			
//			if(e.getSource() == studentWindow.getSubmitAssignment())
//			{
//				File file = studentWindow.submitFile();
//				if(file == null)
//				{
//					JOptionPane.showMessageDialog(studentWindow, "No file chosen", null, JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				String extension = "";
//
//				int i = file.getPath().lastIndexOf('.');
//				if (i > 0) 
//				    extension = file.getPath().substring(i+1);
//				
//				long length = file.length();
//				byte[] content = new byte[(int) length]; // Converting Long to Int
//				try {
//					FileInputStream fis = new FileInputStream(file);
//					BufferedInputStream bos = new BufferedInputStream(fis);
//					bos.read(content, 0, (int)length);
//					out.writeObject(new String("UPLOAD SUBMISSION"));
//					out.flush();
//					out.writeObject(content);
//					out.flush();
//					out.writeObject(new String(extension));
//					out.flush();
//					String path = "assignmentSubmissions\\\\" + ((Assignment)studentWindow.getAssignments().getSelectedItem()).getId()
//									+"_"+user.getId();
//					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
//					Calendar cal = Calendar.getInstance();
//					String timeStamp = dateFormat.format(cal.getTime());
//					
//					Submission sub = new Submission(((Assignment)studentWindow.getAssignments().getSelectedItem()).getId(),
//							(Student)user, path, "No Comments", -1, timeStamp);
//					out.writeObject(sub);
//							
//					studentWindow.getDropboxFrame().dispose();
//				} catch (IOException e1) {e1.printStackTrace();}
//			}
//			
//			if(e.getSource() == studentWindow.getAssignments())
//			{
//				setAssignmentContent();
//			}
//			
//			if(e.getSource() == studentWindow.getRefresh())
//			{
//				try {
//					updateCoursesStudent(user);
//				} catch (ClassNotFoundException | IOException e1) {e1.printStackTrace();}
//				setCourseContent();
//			}
//			
//			if(e.getSource() == studentWindow.getEmailProf())
//			{
//				if(studentWindow.getCourseList().isSelectionEmpty()) {
//					JOptionPane.showMessageDialog(studentWindow, "No Course selected", null, JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				if(userPass == null)
//				{
//					JPasswordField pass = new JPasswordField();
//					int option = JOptionPane.showConfirmDialog(studentWindow, pass, "Enter Gmail Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//					if(option == JOptionPane.OK_OPTION)
//						userPass = pass.getText();
//					else
//						return;
//					
//					try {
//						out.writeObject(new String("CREATE EMAIL HELPER"));
//						out.flush();
//						out.writeInt(user.getId());
//						out.flush();
//						out.writeObject(new String(userPass));
//						out.flush();
//					} catch (IOException e1) {e1.printStackTrace();}
//				}
//				studentWindow.openEmail();
//				studentWindow.getButtonSendEmail().addActionListener(this);
//			}
//			
//			if(e.getSource() == studentWindow.getButtonSendEmail())
//			{
//				Email email = new Email(studentWindow.getEmailSubject().getText(),studentWindow.getEmailBody().getText(),
//						studentWindow.getCourseList().getSelectedValue().getProfId(),0);
//				try {
//					out.writeObject(email);
//					out.flush();
//				} catch (IOException e1) {e1.printStackTrace();}
//				studentWindow.getEmailWindow().dispose();
//			}
//			
//		}
//		
//	}
//	
	
}
