package Domian;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import SharedObjects.*;

public class UserSystem implements Runnable {

	
	/**
	 * Sends outputs through the clientSocket to the Client
	 */
	private ObjectOutputStream socketOut;
	
	/**
	 * Receives Inputs from the clientSocket to the Client
	 */
	private ObjectInputStream socketIn;
	
	private Socket aSocket;
	
	
//	private FileHelper fileHelper;
//	private EmailHelper emailHelper;
//	private JDBCHelper sqlHelper;
	
	
	
	/**
	 * Constructor for UserSystem 
	 * @param thisSocket the Client that is using this UserSystem
	 */
	public UserSystem(Socket aSocket)//, JDBCHelper sqlHelper, FileHelper file)
	{	
		this.aSocket = aSocket;
		try {
			socketOut = new ObjectOutputStream(aSocket.getOutputStream());
			socketIn = new ObjectInputStream(aSocket.getInputStream());
		} catch (IOException e) {e.printStackTrace();}	
//		this.sqlHelper = sqlHelper;
//		this.fileHelper = file;
	}

	@Override
	public void run() {
		while(true)
		{
//			if(aSocket.isClosed()) {
//				System.out.println("GOODBYE CLIENT");
//			}
				try {
					Object obj = socketIn.readObject();
					if(obj instanceof String) {
						if(((String) obj).contains("HELLO BIATCH")) {
							System.out.println("Message From Client: " + ((String)obj));
							socketOut.writeObject(new String("SUP FAM"));
							socketOut.flush();
						}
						else if(((String) obj).contains("I hate 511")) {
							System.out.println("Message From Client: " + ((String)obj));
							socketOut.writeObject(new String("WORD ME TO FK THAT CLASS"));
							socketOut.flush();
						}
					}
					
					
					
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			

		}
	}
				
//				//check login
//				if(obj instanceof LoginInfo) 
//				{
//					LoginInfo log = (LoginInfo)obj;
//					User user = sqlHelper.checkLogin(log);
//					socketOut.writeObject(user);
//				}
//				
//				//add a new user to the database
//				if(obj instanceof Account)
//				{
//					sqlHelper.addNewUser((Account)obj);
//					LoginInfo log = new LoginInfo(((Account)obj).getLast(), ((Account)obj).getPassword());
//					User user = sqlHelper.checkLogin(log);
//					socketOut.writeObject(user);
//				}
//				
//				//if course id == 0 then add new course  to data base
//				if(obj instanceof Course && ((Course) obj).getCourseId() == 0)
//				{
//					Course course = (Course) obj;
//					sqlHelper.addCourse(course);
//				}
//				//getting file
//				if(obj instanceof byte[])
//				{
//					Assignment a = (Assignment) socketIn.readObject();
//					String extension = (String)socketIn.readObject();
//					byte[] content = (byte[]) obj;
//					fileHelper.storeFile(content, "assignmentUploads" + "\\\\" +Integer.toString(a.getId()), extension);
//					sqlHelper.setPathProf(Integer.toString(a.getId()), "assignmentUploads" + "\\\\" +a.getId()+"." + extension);
//					
//				}
//				
//				if(obj instanceof Email) 
//				{
//					Email thisEmail = (Email) obj;
//
//					//professor e-mailing students
//					if(thisEmail.getType() == 1) {
//						ArrayList<String> studentEmails = sqlHelper.getStudentsEmailAdyys(thisEmail.getId());
//						if(studentEmails != null)
//							emailHelper.profSendEmail(studentEmails, thisEmail.getContent(), thisEmail.getSubject());
//					}	
//					
//					//student e-mailing professor
//					else if(thisEmail.getType() == 0) 
//						emailHelper.stuSendEmail(sqlHelper.getEmailAddy(thisEmail.getId()),thisEmail.getContent(), thisEmail.getSubject());
//
//				}
//								
//				//messages to tell the UserSystem what command to run
//				if(obj instanceof String)
//				{
//					//In the form "UPDATE COURSES FOR PROF (id)"
//					if(((String) obj).contains("UPDATE COURSES FOR PROF"))
//					{	
//						String id = (String) obj;
//						id = id.substring(id.indexOf("(") + 1);
//						id = id.substring(0, id.indexOf(")"));
//						ArrayList<Course> courses = sqlHelper.getProfCourses(Integer.parseInt(id));
//						socketOut.writeObject(courses);
//						socketOut.flush();
//					}
//					else if(((String) obj).contains("UPDATE COURSES FOR STUDENT"))
//					{
//						String id = (String) obj;
//						id = id.substring(id.indexOf("(") + 1);
//						id = id.substring(0, id.indexOf(")"));
//						ArrayList<Course> courses = sqlHelper.getStudentCourses(Integer.parseInt(id));
//						socketOut.writeObject(courses);
//						socketOut.flush();
//					}
//					
//					else if(((String) obj).contains("UPDATE COURSE ACTIVE"))
//					{
//						socketOut.flush();
//						Course course = (Course)socketIn.readObject();
//						String type =  ((String) obj).substring(((String) obj).length() - 1); 
//						sqlHelper.setCourseActive(course, type);
//					}
//					else if(((String) obj).contains("UPDATE ASSIGNMENT ACTIVE"))	
//					{
//						socketOut.flush();
//						Assignment assignment = (Assignment)socketIn.readObject();
//						String type = ((String) obj).substring(((String) obj).length() - 1); 
//						sqlHelper.setAssignmentActive(assignment, type);
//					}
//					
//					else if (((String) obj).contains("SEARCH STUDENT LAST")) {
//						Student thisStu = null;
//						
//						StringTokenizer toke = new StringTokenizer((String) obj);
//						thisStu = sqlHelper.getStudentLast(toke.nextToken());
//						
//						socketOut.writeObject(thisStu);
//						socketOut.flush();
//						
//					}
//					else if (((String) obj).contains("SEARCH STUDENT ID")) {
//						Student thisStu = null;
//						
//						StringTokenizer toke = new StringTokenizer((String) obj);
//						thisStu = sqlHelper.getStudentId(Integer.parseInt(toke.nextToken()));
//						
//						socketOut.writeObject(thisStu);
//						socketOut.flush();
//					}
//					else if (((String) obj).contains(" UPDATE STUDENTS FOR COURSE")) {
//						ArrayList<Student> students = new ArrayList<Student>();
//						ArrayList<Integer> ids = new ArrayList<Integer>();
//						StringTokenizer toke = new StringTokenizer((String) obj);
//						String s = new String(toke.nextToken() + " " + toke.nextToken());
//						int id = sqlHelper.getCourseId(s);
//
//						ids = sqlHelper.getCourseStudents(id);
//						
//						if(ids == null) {
//							students = null;
//							socketOut.writeObject(students);
//							socketOut.flush();
//						}
//						else {
//						for(Integer element:ids) {
//							students.add(sqlHelper.getStudentId(element));
//						}
//						
//						socketOut.writeObject(students);
//						socketOut.flush();
//						}
//					}
//					
//					else if(((String) obj).contains(" ENROLL STUDENT")) {
//						StringTokenizer toke = new StringTokenizer((String) obj);
//						String s = new String(toke.nextToken() + " " + toke.nextToken());
//						int id = sqlHelper.getCourseId(s);
//						sqlHelper.enrollStudent(id, Integer.parseInt(toke.nextToken()));
//					}
//					
//					else if (((String) obj).contains(" UNENROLL STUDENT")) {
//						StringTokenizer toke = new StringTokenizer((String) obj);
//						String s = new String(toke.nextToken() + " " + toke.nextToken());
//						int id = sqlHelper.getCourseId(s);
//						sqlHelper.unenrollStudent(id, Integer.parseInt(toke.nextToken()));
//					}
//					
//					else if (((String) obj).contains("GET ASSIGNMENT")) {
//						String id = (String) obj;
//						id = id.substring(id.indexOf("(") + 1);
//						id = id.substring(0, id.indexOf(")"));
//						String path = sqlHelper.findFile(id);
//						if(path == null || path.equals("no path"))
//						{
//							socketOut.writeObject(null);
//							return;
//						}
//						else
//						{
//							String extension = "";
//							int i = path.lastIndexOf('.');
//							extension = path.substring(i+1);
//							byte[] content = fileHelper.getFile(path);
//							socketOut.writeObject(content);
//							socketOut.flush();
//							socketOut.writeObject(new String(extension));
//							socketOut.flush();
//						}
//						
//					}
//					
//					else if (((String) obj).contains("UPLOAD SUBMISSION")) {
//						
//						byte[] content = (byte[]) socketIn.readObject();
//						String extension = (String)socketIn.readObject();
//						Submission sub = (Submission)socketIn.readObject();
//						//File stored as assignmentID-StudentID.extention
//						fileHelper.storeFile(content, sub.getPath(), extension);
//						sqlHelper.setSubmission(sub.getAssignmentID(), sub.getStudent().getId(), 
//						sub.getPath() + "." +extension, sub.getTimeStamp());
//					
//					}
//					else if (((String) obj).contains("GET SUBMISSIONS")) {
//						String id = (String) obj;
//						id = id.substring(id.indexOf("(") + 1);
//						id = id.substring(0, id.indexOf(")"));
//						ArrayList<Submission> subs = sqlHelper.getSubmissions(Integer.parseInt(id));
//						socketOut.writeObject(subs);
//						socketOut.flush();
//					}
//					
//					else if (((String) obj).contains("GET STUDENT SUBMISSION")) {
//						Submission s = (Submission)socketIn.readObject();
//						String path = sqlHelper.getSubmissionPath(s);
//						if(path == null)
//							socketOut.writeObject(null);
//						else {
//							byte[] content = fileHelper.getFile(path);
//							String extension = "";
//							int i = path.lastIndexOf('.');
//							extension = path.substring(i+1);
//							socketOut.writeObject(content);
//							socketOut.flush();
//							socketOut.writeObject(new String(extension));
//							socketOut.flush();	
//						}
//					}
//					else if (((String) obj).contains("UPDATE GRADE")) {
//						Submission s = (Submission)socketIn.readObject();
//						sqlHelper.updateGrade(s);
//					}
//					else if(((String)obj).contains("GET GRADE AND COMMENT")){
//						int stuId = socketIn.readInt();
//						int assignId = socketIn.readInt();
//						Submission s = sqlHelper.getASubmission(stuId, assignId);
//						socketOut.writeObject(s);
//						socketOut.flush();
//					}
//					
//					else if (((String) obj).contains("CREATE EMAIL HELPER")) {
//						int userID = socketIn.readInt();
//						String pass = (String) socketIn.readObject();
//						
//						//Only need to create ONCE!
//						if(emailHelper == null)
//							emailHelper = new EmailHelper(sqlHelper.getEmailAddy(userID), pass);
//						
//					}
//
//
//				}
//				
//				
//			} catch (ClassNotFoundException | IOException e) {e.printStackTrace();}
//			
//		}
//		
}
