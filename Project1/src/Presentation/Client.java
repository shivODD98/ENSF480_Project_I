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
import java.io.FileNotFoundException;
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
	static private User user;
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
		    	close();
		    }
		}));
		//clientSocket = new Socket(HOST_NAME, PORT_NUMBER);
		
		
		clientSocket = new Socket(InetAddress.getByName("10.13.79.125"), PORT_NUMBER);
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
		
		
		
	}
	
	public void loginCommunicate() throws IOException, ClassNotFoundException
	{	
		while(true)
		{
			Object obj = in.readObject();
			if(obj instanceof User) {
				if(((User)obj) != null) {
					if(((User)obj).getType() == UserType.Operator) {
						operatorAfterLogin();
						break;
					}
					else if(((User)obj).getType() == UserType.RegisteredBuyer) {
						registeredAfterLogin();
						break;
					}
				}
			}
		
		}

	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, UnknownHostException, SocketException {

		try {
			Client client = new Client();
		} catch (IOException e) {e.printStackTrace();}
		
	}

	@SuppressWarnings("unchecked")
	public void operatorAfterLogin() {
		loginWindow.setVisible(false);
		operatorWindow = new OperatorWindow();
		operatorWindow.operatorFrame.addWindowListener((new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
		    	close();
		    }
		}));
		operatorWindow.addActionListener(new OperatorController());
		
		//get promotion and docs from server, this is test data to simulate
		try {
			out.writeObject(new String("GET ALL DOCUMENTS"));
			ArrayList<Document> docs = (ArrayList<Document>) in.readObject();
			operatorWindow.updateDocumentsListModel(docs);
			out.writeObject(new String("GET ALL PROMOTIONS"));
			docs = (ArrayList<Document>) in.readObject();
			operatorWindow.updatePromotionListModel(docs);
		} catch (IOException e) {e.printStackTrace();}catch(ClassNotFoundException e) {e.printStackTrace();}


	}
	
	@SuppressWarnings("unchecked")
	public void registeredAfterLogin() {
		loginWindow.setVisible(false);
		registeredBuyerWindow = new RegisteredBuyerWindow();
		registeredBuyerWindow.addActionListener(new RegisteredBuyerController());
		registeredBuyerWindow.frame.addWindowListener((new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
		    	close();
		    }
		}));
		try {
			out.writeObject(new String("GET ALL DOCUMENTS"));
			ArrayList<Document> docs = (ArrayList<Document>) in.readObject();
			registeredBuyerWindow.updateDocumentsListModel(docs);
			out.writeObject(new String("GET ALL PROMOTIONS"));
			docs = (ArrayList<Document>) in.readObject();
			registeredBuyerWindow.updatePromotionsListModel(docs);

		} catch (IOException e) {e.printStackTrace();}catch(ClassNotFoundException e) {e.printStackTrace();}

	}
	
	@SuppressWarnings("unchecked")
	public void continueUnregistered() {
		loginWindow.setVisible(false);
		unRegisteredBuyerWindow = new UnregisteredBuyerWindow();
		unRegisteredBuyerWindow.setActionListener(new unRegisteredBuyerController());
		unRegisteredBuyerWindow.frame.addWindowListener((new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent we)
		    {
		    	close();
		    }
		}));
		//get promotion and docs from server, this is test data to simulate
		try {
			out.writeObject(new String("GET ALL DOCUMENTS"));
			ArrayList<Document> docs = (ArrayList<Document>) in.readObject();
			unRegisteredBuyerWindow.updateDocumentsListModel(docs);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		
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

				try {
					out.writeObject(log);
					Object obj = in.readObject();
					if(obj instanceof User) {
						if(((User)obj) != null) {
							if(((User)obj).getType() == UserType.Operator) {
								operatorAfterLogin();
							}
							else if(((User)obj).getType() == UserType.RegisteredBuyer) {
								registeredAfterLogin();
							}
							Client.user = (User)obj;
							System.out.println("user id is:"+Client.user.getId());
						}
					}
				}	
				catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
			}
			
			if(e.getSource() == loginWindow.getCreateAccount())
			{
				continueUnregistered();
			}

		}
	}
	
	public class OperatorController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == operatorWindow.addButton) {
				operatorWindow.createAddWindow();
				operatorWindow.createButton.addActionListener(this);
				try {
					out.writeObject(new String("ADD THIS DOCUMENT"));
				} catch (IOException e1) {e1.printStackTrace();}
			}
			
			else if(e.getSource() == operatorWindow.updateDocument) {
				operatorWindow.createAddWindow();
				operatorWindow.createButton.addActionListener(this);
				operatorWindow.modifyDocument();
				try {
					out.writeObject(new String("UPDATE THIS DOCUMENT"));
				} catch (IOException e1) {e1.printStackTrace();}
			}
			
			else if(e.getSource() == operatorWindow.deleteButton) {
				Document doc;
				if((doc = operatorWindow.removeFromList()) != null)
					System.out.println("removing "+doc.getTitle());
					try {
						out.writeObject(new String("DELETE THIS DOCUMENT"));
						out.flush();
						out.writeObject(doc);
					} catch (IOException e1) {e1.printStackTrace();}
			}
			
			else if(e.getSource() == operatorWindow.moveToPromotionButton) {
				if(operatorWindow.isDocumentsSelected()) {
					Document doc;
					if((doc = operatorWindow.addToPromotionList())!=null) {
						try {
							out.writeObject(new String("ADD TO PROMOTION"));
							out.flush();
							out.writeObject(doc);
						} catch (IOException e1) {e1.printStackTrace();}
						System.out.println("adding "+doc.getTitle()+" to promotion");
					}
				}
				else if(operatorWindow.isPromotionListSeleted()) {
					Document doc;
					doc = operatorWindow.removeFromPromotionList();
					if(doc!=null) {
						try {
							out.writeObject(new String("DELETE FROM PROMOTION"));
							out.flush();
							out.writeObject(doc);
						} catch (IOException e1) {e1.printStackTrace();}
						System.out.println("removing "+doc.getTitle()+" from promotion");
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
					
					long length = file.length();
					byte[] content = new byte[(int)length];
					try {
						FileInputStream fis = new FileInputStream(file);
						BufferedInputStream bos = new BufferedInputStream(fis);
						bos.read(content, 0, (int)length);
						bos.close();
						fis.close();
					}catch(FileNotFoundException e1) {e1.printStackTrace();}catch(IOException e2) {e2.printStackTrace();}
					doc.setBytes(content);
					doc.setFilePath(file.getAbsolutePath());
					//talk to server
					try {
						out.flush();
						out.writeObject(doc);
						Document newDoc = (Document)in.readObject();
						if(newDoc != null)
							operatorWindow.documentsListModel.addElement(newDoc);
					} catch (IOException e1) {e1.printStackTrace();} catch (ClassNotFoundException e1) {e1.printStackTrace();}
					
				}
			}
			
		}
		
	}

	public class RegisteredBuyerController implements ActionListener{

		public String getExtension(String s) {
			String ext[] = s.split("\\.");
			return ext[ext.length - 1];
		}
		Document docToSend;
		@SuppressWarnings("unchecked")
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
					info.setId(Client.user.getId());
					try {
						out.writeObject(new String("BUY DOCUMENT"));
						out.writeObject(info);
						Document doc = (Document)in.readObject();
						File file = new File("paied."+getExtension(doc.getFilePath()));
						FileOutputStream fs = new FileOutputStream(file);
						fs.write(doc.getBytes());
						fs.close();
						Desktop.getDesktop().open(file);
					} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
					
				}
			}
			
			else if(e.getSource() == registeredBuyerWindow.unsubButton) {
				//delete user from db
				registeredBuyerWindow.frame.dispose();
				loginWindow.setVisible(true);
				try {
					out.writeObject(new String("UNREGISTER USER"));
					out.writeObject(Client.user);
					
				} catch (IOException e1) {e1.printStackTrace();}
			}
			
			else if(e.getSource() == registeredBuyerWindow.btnRefresh) {
				try {
					out.writeObject(new String("GET ALL DOCUMENTS"));
					ArrayList<Document> docs = (ArrayList<Document>) in.readObject();
					registeredBuyerWindow.updateDocumentsListModel(docs);
					out.writeObject(new String("GET ALL PROMOTIONS"));
					docs = (ArrayList<Document>) in.readObject();
					registeredBuyerWindow.updatePromotionsListModel(docs);

				} catch (IOException e1) {e1.printStackTrace();}catch(ClassNotFoundException e2) {e2.printStackTrace();}
			}
			
		}
		
	}

	public class unRegisteredBuyerController implements ActionListener{		
		Document docToSend;
		public String getExtension(String s) {
			String ext[] = s.split("\\.");
			return ext[ext.length - 1];
		}
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
					
					try {
						out.writeObject(new String("BUY DOCUMENT"));
						out.writeObject(info);
						Document doc = (Document)in.readObject();
						File file = new File("paied."+getExtension(doc.getFilePath()));
						FileOutputStream fs = new FileOutputStream(file);
						fs.write(doc.getBytes());
						fs.close();
						Desktop.getDesktop().open(file);
					} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
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
					String pass = unRegisteredBuyerWindow.passwordField.getText();
					unRegisteredBuyerWindow.registerFrame.dispose();
					unRegisteredBuyerWindow.frame.dispose();
					
					//communcate with server to get new user
					try {
						out.writeObject(new String("ADD USER"));
						out.flush();
						out.writeObject(newUser);
						out.flush();
						out.writeObject(pass);
						User user = (User)in.readObject();
						Client.user = user;
					} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
					registeredAfterLogin();
				}
			}
			
			
		}
		
	}
	
	public void close()
	{
		try {
			out.writeObject("QUIT");
			in.close();
			out.close();
			clientSocket.close(); 
		}catch(IOException e) {e.printStackTrace();}
	}
		
}
