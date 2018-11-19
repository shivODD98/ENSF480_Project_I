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

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Client implements portInformation{
	private ObjectInputStream in;
	private ObjectOutputStream out;
	//private LoginWindow loginWindow;
	private Socket clientSocket;
	//private StudentWindow studentWindow;
	//private ProfessorWindow professorWindow;
	private User user;
	private String userPass;
	private Scanner sc;
	
	public Client() throws IOException
	{
//		loginWindow = new LoginWindow();
//		loginWindow.setLoginButtonListener(new LoginControl());
//		loginWindow.addWindowListener((new WindowAdapter()
//		{
//		    @Override
//		    public void windowClosing(WindowEvent we)
//		    {
//		    	close();
//		    }
//		}));
		clientSocket = new Socket(HOST_NAME, PORT_NUMBER);
		
		sc = new Scanner(System.in);
		
		//clientSocket = new Socket(InetAddress.getByName("10.13.67.64"), PORT_NUMBER);
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
		
		
		out.writeObject(new String("HELLO BIATCH"));
		
	}
	
	public void loginCommunicate() throws IOException, ClassNotFoundException
	{	
		while(true)
		{
			Object obj = in.readObject();
			
			if(obj instanceof String) {
				if(((String)obj).contains("SUP FAM"))
					System.out.println("Server Response: " + ((String)obj));

				else {
					System.out.println("Server Response: " + ((String)obj));
				}
				System.out.println("Respond to server on next line: ");
				String response = sc.nextLine();
				out.writeObject(response);
				out.flush();
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
			client.loginCommunicate();
		} catch (IOException e) {e.printStackTrace();}
		
	}

//	public void updateCoursesProf(User obj) throws IOException, ClassNotFoundException
//	{
//		out.writeObject(new String("UPDATE COURSES FOR PROF ("+ ((User) obj).getId() + ")"));
//		Object object = in.readObject();
//		if(object instanceof ArrayList<?>)
//		{
//			ArrayList<Course> courses = (ArrayList<Course>) object;
//			professorWindow.addCourses(courses);
//		}
//	}
//	
//	public void updateCoursesStudent(User obj) throws IOException, ClassNotFoundException
//	{
//		out.writeObject(new String("UPDATE COURSES FOR STUDENT ("+ ((User) obj).getId() + ")"));
//		Object object = in.readObject();
//		if(object instanceof ArrayList<?>)
//		{
//			ArrayList<Course> courses = (ArrayList<Course>) object;
//			studentWindow.addCourses(courses);
//		}		
//	}
//

//
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
//	public class LoginControl implements ActionListener
//	{
//		@Override
//		public void actionPerformed(ActionEvent e) 
//		{
//			if(e.getSource() == loginWindow.getLoginButton())
//			{
//				LoginInfo log = loginWindow.getLoginInfo();
//				try {out.writeObject(log);}	
//				catch (IOException e1) {e1.printStackTrace();}
//			}
//			
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
//		}
//	}
//
//	public class ProfessorControl implements ActionListener
//	{
//		public void getSubmissions()
//		{
//			int assignID = ((Assignment) professorWindow.getAssignmentBox().getSelectedItem()).getId();
//			try {
//				out.writeObject(new String("GET SUBMISSIONS ("+ assignID + ")"));
//				out.flush();
//				ArrayList<Submission> subs = (ArrayList<Submission>)in.readObject();
//				if(subs == null){
//					JOptionPane.showMessageDialog(professorWindow, "No submissions yet", null, JOptionPane.INFORMATION_MESSAGE);
//					return;
//				}
//				professorWindow.setSubmissions(subs);
//				
//			} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
//		}
//
//		public void updateEnrolledSTudents() {
//			ArrayList<Student> students = new ArrayList<Student>();
//			try {
//				out.writeObject(new String(	professorWindow.getCourseNameLabel().getText() + " UPDATE STUDENTS FOR COURSE"));
//				out.flush();
//				students = (ArrayList<Student>) in.readObject();
//				if(students != null)
//					professorWindow.addEnrolledStudents(students);
//				
//			} catch (IOException | ClassNotFoundException e1) {
//				System.out.println("Problem getting Enrolled Students");
//				e1.printStackTrace();
//			}
//		}
//
//		
//		@Override
//		public void actionPerformed(ActionEvent e) 
//		{
//			if(e.getSource() == professorWindow.getCourseActive())
//			{
//				if(professorWindow.getCourseActive().isSelected())
//				{
//					try {
//						out.writeObject(new String("UPDATE COURSE ACTIVE 1"));
//						Course course = professorWindow.getCourseInList();
//						course.setActive(true);
//						out.writeObject(course);
//						out.flush();
//					} catch (IOException e1) {e1.printStackTrace();}
//										
//				}
//				else if(!professorWindow.getCourseActive().isSelected())
//				{
//					try {
//						out.writeObject(new String("UPDATE COURSE ACTIVE 0"));
//						Course course = professorWindow.getCourseInList();
//						course.setActive(false);
//						out.writeObject(course);
//						out.flush();
//					} catch (IOException e1) {e1.printStackTrace();}
//				}
//
//			}
//	
//			if(e.getSource() == professorWindow.getViewCourse())
//			{
//				professorWindow.setCourseInfo();
//				professorWindow.getEnrolledStudents().removeAllElements(); 
//				updateEnrolledSTudents();
//			}
//			
//			
//			if(e.getSource() == professorWindow.getCreateCourse())
//			{
//				professorWindow.createCourse();
//			}
//			if(e.getSource() == professorWindow.getMakeCourse())
//			{
//				Course course = professorWindow.makeCourse(user.getId());
//
//				try {							
//					out.writeObject(course);
//					updateCoursesProf(user);
//				} catch (IOException | ClassNotFoundException e1) {
//					e1.printStackTrace();
//				}
//				professorWindow.setCourseInfo(course);
//				
//				updateEnrolledSTudents();
//				
//			}
//			
//			if(e.getSource() == professorWindow.getAssignmentBox())
//			{
//				professorWindow.updateAssignment();
//			}
//			
//			if(e.getSource() == professorWindow.getAssignActive())
//			{
//				if(professorWindow.getAssignActive().isSelected())
//				{
//					((Assignment) professorWindow.getAssignmentBox().getSelectedItem()).setActive(true);
//					try {
//						out.writeObject(new String("UPDATE ASSIGNMENT ACTIVE 1"));
//						Assignment assignment = professorWindow.getAssignmentFromCourse();
//						out.writeObject(assignment);
//						out.flush();
//					} catch (IOException e1) {e1.printStackTrace();}
//				}
//				else if(!professorWindow.getAssignActive().isSelected())
//				{
//					((Assignment) professorWindow.getAssignmentBox().getSelectedItem()).setActive(false);
//					try {
//						out.writeObject(new String("UPDATE ASSIGNMENT ACTIVE 0"));
//						Assignment assignment = professorWindow.getAssignmentFromCourse();
//						out.writeObject(assignment);
//						out.flush();
//					} catch (IOException e1) {e1.printStackTrace();}
//				}
//			}
//			
//			if(e.getSource() == professorWindow.getUploadAssign())
//			{
//				professorWindow.openDropBox();
//				professorWindow.setDropBoxActionListener(this);
//			}
//			if(e.getSource() == professorWindow.getSubmitAssign())
//			{
//				File file = professorWindow.submitFile();
//				if(file == null)
//				{
//					JOptionPane.showMessageDialog(professorWindow, "No file chosen", null, JOptionPane.ERROR_MESSAGE);
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
//					out.writeObject(content);
//					out.flush();
//					out.writeObject(professorWindow.getAssignmentBox().getSelectedItem());
//					out.flush();
//					out.writeObject(new String(extension));
//					professorWindow.getDropboxFrame().dispose();
//				} catch (IOException e1) {e1.printStackTrace();}
//				
//			}
//			
//			if(e.getSource() == professorWindow.getSearchStudents()) 
//			{
//				professorWindow.getAllStudentsList().removeAllElements();
//				
//				if(professorWindow.getRadioID().isSelected()) {
//					try {
//						out.writeObject(new String(professorWindow.getSearch_feild().getText()) + " SEARCH STUDENT ID"  );
//						out.flush();
//						Object obj = in.readObject();
//				
//						if(obj instanceof Student)
//						{
//							Student s = (Student) obj;
//							professorWindow.showStudentSearch(s);
//						}
//						else
//						{
//							professorWindow.SearchStudentError();
//						}
//						
//					} catch (IOException e1) {
//						System.out.println("Problem Searcing ID");
//						e1.printStackTrace();
//					} catch (ClassNotFoundException e1) {
//						System.out.println("Problem Searcing ID");
//						e1.printStackTrace();
//					} 
//
//
//				}
//
//				else if(professorWindow.getRadioLastName().isSelected()) {
//					try {
//						out.writeObject(new String(professorWindow.getSearch_feild().getText()) + " SEARCH STUDENT LAST");
//						out.flush();
//						Object obj = in.readObject();
//		
//						if(obj instanceof Student)
//						{
//							Student s = (Student) obj;
//							professorWindow.showStudentSearch(s);
//						}
//						else
//							professorWindow.SearchStudentError();
//					} catch (IOException e2) {
//						System.out.println("Problem Searcing Last Name");
//						e2.printStackTrace();
//					} catch (ClassNotFoundException e2) {
//						System.out.println("Problem Searcing Last Name");
//						e2.printStackTrace();
//					} 
//
//					
//
//				}
//				else 
//					JOptionPane.showMessageDialog(professorWindow.getContentPane(),
//						    "Please select either 'Student Last Name' or Student ID' for search parameters.");
//			}
//			
//			if(e.getSource() == professorWindow.getBtnEnroll()) {
//				Student enrollDb = professorWindow.enrollThisStudent();
//				if(enrollDb != null)
//				{	
//					String enrollClass = new String(professorWindow.getCourseNameLabel().getText());
//					try {
//						out.writeObject(new String(enrollClass + " " + enrollDb.getId() + "  ENROLL STUDENT"));
//						out.flush();
//					} catch (IOException e1) {
//						System.out.println("Problem enrolling student in DB");
//						e1.printStackTrace();
//					}
//				}
//			}
//			
//
//			if(e.getSource() == professorWindow.getBtnUnenroll()) {
//				Student unenrollDb = professorWindow.unenrollThisStudent();
//				String unenrollClass = new String (professorWindow.getCourseNameLabel().getText());
//				try {
//					out.writeObject(new String(unenrollClass + " " + unenrollDb.getId() + "  UNENROLL STUDENT"));
//					out.flush();
//				} catch (IOException e1) {
//					System.out.println("Problem unenrolling student in DB");
//					e1.printStackTrace();
//				}
//			}
//			
//			if(e.getSource() == professorWindow.getViewSubmissions())
//			{
//				getSubmissions();
//			}
//			
//			if(e.getSource() == professorWindow.getViewSubmission())
//			{
//				try {
//					out.writeObject(new String("GET STUDENT SUBMISSION"));
//					Submission s = professorWindow.getSubmissionList().getSelectedValue();
//					out.writeObject(s);
//					byte[] content = (byte[])in.readObject();
//					if(content == null)
//					{
//						JOptionPane.showInputDialog(professorWindow, "ERROR: NO FILE FOUND", null, JOptionPane.ERROR_MESSAGE);
//						return;
//					}
//					else {
//						String extension = (String)in.readObject();
//						File newFile = new File("assignmentDownloads" + "\\\\" +"Assignment" + s.getAssignmentID() + "_" +
//												"StudentID" + s.getStudent().getId()+"." + extension);
//						if(! newFile.exists())
//							newFile.createNewFile();
//						FileOutputStream writer = new FileOutputStream(newFile);
//						BufferedOutputStream bos = new BufferedOutputStream(writer);
//						bos.write(content);
//						bos.close();
//						Desktop desktop = Desktop.getDesktop();
//						desktop.open(newFile);
//					}
//				} catch (IOException | ClassNotFoundException e1) {e1.printStackTrace();}
//			}
//			
//			if(e.getSource() == professorWindow.getGradeButton())
//			{
//				if(professorWindow.getSubmissionList().isSelectionEmpty())
//				{
//					JOptionPane.showMessageDialog(professorWindow, "No submission selected", null, JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				professorWindow.createGradeWindow();
//				professorWindow.setGradeActionListener(this);
//			}
//			if(e.getSource() == professorWindow.getGradeSubmitButton())
//			{
//				Submission sub = professorWindow.getGrade();
//				if(sub == null) {
//					JOptionPane.showMessageDialog(professorWindow, "Grade must be between 0-100", null, JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				try {
//					out.writeObject(new String("UPDATE GRADE"));
//					out.flush();
//					out.writeObject(sub);
//					out.flush();
//				} catch (IOException e1) {e1.printStackTrace();}
//				getSubmissions();
//				professorWindow.getGradeWindow().dispose();
//			}
//			
//			if(e.getSource() == professorWindow.getEmailbtn())
//			{
//				if(professorWindow.getCourseList().isSelectionEmpty()) {
//					JOptionPane.showMessageDialog(professorWindow, "No Course selected", null, JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//				if(userPass == null)
//				{
//					JPasswordField pass = new JPasswordField();
//					int option = JOptionPane.showConfirmDialog(professorWindow, pass, "Enter Gmail Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
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
//				professorWindow.openEmail();
//				professorWindow.getButtonSendEmail().addActionListener(this);
//				
//			}
//			
//			if(e.getSource() == professorWindow.getButtonSendEmail())
//			{
//				Email email = new Email(professorWindow.getEmailSubject().getText(),professorWindow.getEmailBody().getText(),
//				professorWindow.getCourseList().getSelectedValue().getCourseId(),1);
//				try {
//					out.writeObject(email);
//				} catch (IOException e1) {e1.printStackTrace();}
//				professorWindow.getEmailWindow().dispose();
//			}
//			
//			
//		}
//		
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
