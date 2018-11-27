package database;

import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Presentation.Client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import SharedObjects.*;

public class DatabaseInterface {
	public Connection jdbc_connection;
	public Statement statement;
	public String databaseName = "D3LDataBase";

	public String connectionInfo = "jdbc:mysql://localhost:3306/BOOKgang", login = "root", password = "Pudge.1998";

	public DatabaseInterface() {
		try {
			// If this throws an error, make sure you have added the mySQL connector JAR to
			// the project
			Class.forName("com.mysql.jdbc.Driver");

			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Checks the login information of either a registered buyer or a operator. Used
	 * by the loginController
	 * 
	 * @param log is the credentials of the client trying to login
	 * @return the instance of the user the client is or null if the user DNE in the
	 *         database
	 */
	public User checkLogin(LoginInfo log) {
		String sql = "SELECT * FROM users WHERE Username=" + "'" + log.getUsername() + "'" + " AND Password=" + "'"
				+ log.getPassword() + "'";
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			result.beforeFirst();
			if (result.first()) {
				if (result.getString("type").equals("1")) {
					return new User(result.getInt("UserID"), result.getString("Fname"), result.getString("Lname"),
							result.getString("Username"), UserType.RegisteredBuyer);
				}
				if (result.getString("type").equals("2")) {
					return new User(result.getInt("UserID"), result.getString("Fname"), result.getString("Lname"),
							result.getString("Username"), UserType.Operator);
				}
			} else
				return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Deletes the appropriate user from the database using the users user name. Use
	 * for Unregistration
	 * 
	 * @param log is the object that contains the user to be deleted user name
	 * @return the success of the deletion, either true if successful or false is
	 *         unsuccessful
	 */
	public Boolean deleteUserLg(LoginInfo log) {

		String sql = "DELETE FROM users WHERE Username=" + "'" + log.getUsername() + "'" + " AND Password=" + "'"
				+ log.getPassword() + "'";

		try {
			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Deletes the appropriate user from the database using the users user ID
	 * 
	 * @param ID is the int that is equal to user to be deleted ID
	 * @return the success of the deletion, either true if successful or false is
	 *         unsuccessful
	 */
	public Boolean deleteUserID(int ID) {

		String sql = "DELETE FROM users WHERE UserID=" + "'" + ID + "'";

		try {
			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * Adds a clients as registered buyer to the database
	 * 
	 * @param newUser the public credentials of the new registered buyer
	 * @param pass    the password for the new registered buyer
	 * @return if the operation was successful
	 */
	public User addRegisterBuyer(User newUser, String pass) {

		String sql = null;

		if (newUser.getType() == UserType.Operator) {

			sql = "INSERT INTO users(Fname,Lname,Username,Password,Type)" + "VALUES(" + "'" + newUser.getFirstName()
					+ "'," + "'" + newUser.getLastName() + "'," + "'" + newUser.getUsername() + "'," + "'" + pass + "',"
					+ "'" + 2 + "'" + ");";
		} else {
			sql = "INSERT INTO users(Fname,Lname,Username,Password,Type)" + "VALUES(" + "'" + newUser.getFirstName()
					+ "'," + "'" + newUser.getLastName() + "'," + "'" + newUser.getUsername() + "'," + "'" + pass + "',"
					+ "'" + 2 + "'" + ");";
		}

		try {
			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if (result == 1)
				try (ResultSet generatedKeys = statement.getGeneratedKeys()){
					if(generatedKeys.next())
						newUser.setId(generatedKeys.getInt("ISBN"));
					return newUser;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User getUserID(int ID) {
		String sql = "SELECT * FROM users WHERE UserID=" + "'" + ID + "';";
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			result.beforeFirst();
			if (result.first()) {

				if (result.getString("type").equals("1"))
					return new User(result.getInt("UserID"), result.getString("Fname"), result.getString("Lname"),
							result.getString("Username"), UserType.RegisteredBuyer);

				else if (result.getString("type").equals("2"))
					return new User(result.getInt("UserID"), result.getString("Fname"), result.getString("Lname"),
							result.getString("Username"), UserType.Operator);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserUN(String username) {
		String sql = "SELECT * FROM users WHERE Username=" + "'" + username + "';";
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			result.beforeFirst();
			if (result.first()) {

				if (result.getString("type").equals("1"))
					return new User(result.getInt("UserID"), result.getString("Fname"), result.getString("Lname"),
							result.getString("Username"), UserType.RegisteredBuyer);

				else if (result.getString("type").equals("2"))
					return new User(result.getInt("UserID"), result.getString("Fname"), result.getString("Lname"),
							result.getString("Username"), UserType.Operator);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean updateUser(User update, int ID) {
		
		String sql = null;
		
		if(update.getType()==UserType.Buyer)
			sql = "UPDATE users SET Fname='" + update.getFirstName() + "',"
									 +"Lname='" + update.getLastName() +  "',"
									 +"Username='" + update.getUsername() + "',"
									 +"Type='0' WHERE UserId='" + update.getId() + "';";
		else if(update.getType()==UserType.RegisteredBuyer)
			sql = "UPDATE users SET Fname='" + update.getFirstName() + "',"
					 +"Lname='" + update.getLastName() +  "',"
					 +"Username='" + update.getUsername() + "',"
					 +"Type='1' WHERE UserId='" + update.getId() + "';";
		else if(update.getType()==UserType.Operator)
			sql = "UPDATE users SET Fname='" + update.getFirstName() + "',"
					 +"Lname='" + update.getLastName() +  "',"
					 +"Username='" + update.getUsername() + "',"
					 +"Type='2' WHERE UserId='" + update.getId() + "';";
		
		try {
			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if(result == 1)
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
									 
	}
	
	public ArrayList<User> getAllUsers(){
		String sql = "SELECT * FROM users WHERE Type=1"; 
		ArrayList<User> users = new ArrayList<User>();
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			result.beforeFirst();
			while (result.next())
				users.add(new User(result.getInt("UserID"), result.getString("Fname"), result.getString("Lname"),
						result.getString("Username"), UserType.RegisteredBuyer));

			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Document addDocument(Document Doc) {
		try {
			java.sql.PreparedStatement pStatement;
			String sql = null;
			User authorID = getUserUN(Doc.getAuthor());
			if(authorID == null) {
				return null;
			}
			if (Doc.getType() == DocumentType.Book) {
				sql = "INSERT INTO documents (Title, AuthorID, Description, FilePath, Type, Price) " + "VALUES ('" 
						+ Doc.getTitle() + "', '" + authorID.getId() + "', '"
						+ Doc.getDescription() + "','" + Doc.getFilePath() + "','" + 1 + "','" // needs to be changed
						+ Doc.getPrice() + "');";
			} else if (Doc.getType() == DocumentType.Magazine) {
				sql = "INSERT INTO documents (Title, AuthorID, Description, FilePath, Type, Price) " + "VALUES ('" 
						+ Doc.getTitle() + "', '" + authorID.getId() + "', '"
						+ Doc.getDescription() + "','" + Doc.getFilePath() + "','" + 2 + "','" // needs to be changed
						+ Doc.getPrice() + "');";
			} else if (Doc.getType() == DocumentType.Journal) {
				sql = "INSERT INTO documents (Title, AuthorID, Description, FilePath, Type, Price) " + "VALUES ('" 
						+ Doc.getTitle() + "', '" + authorID.getId() + "', '"
						+ Doc.getDescription() + "','" + Doc.getFilePath() + "','" + 3 + "','" // needs to be changed
						+ Doc.getPrice() + "');";
			}

			//statement = jdbc_connection.createStatement();
			//int result = statement.executeUpdate(sql);

			pStatement = jdbc_connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pStatement.executeUpdate();
			try (ResultSet generatedKeys = pStatement.getGeneratedKeys()) {
				if (generatedKeys.next())
					Doc.setISBN(generatedKeys.getInt(1));
				return Doc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean deleteDocument(int ISBN) {
		String sql = "DELETE FROM documents WHERE ISBN='" + ISBN + "';";
		try {
			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Document updateDoc(Document update) {
		String sql = null;
		
		User newU = getUserUN(update.getAuthor());
		
		if(update.getType()==DocumentType.Book)
			sql = "UPDATE documents SET Title='" + update.getTitle() + "',"
									 +"AuthorID='" + newU.getId() +  "',"
									 +"Description='" + update.getDescription() + "',"
									 +"Price='" + update.getPrice() + "',"
									 +"Type='1' WHERE ISBN='" + update.getISBN() + "';";
		else if(update.getType()==DocumentType.Journal)
			sql = "UPDATE documents SET Title='" + update.getTitle() + "',"
					 +"AuthorID='" + newU.getId() +  "',"
					 +"Description='" + update.getDescription() + "',"
					 +"Price='" + update.getPrice() + "',"
					 +"Type='3' WHERE ISBN='" + update.getISBN() + "';";
		else if(update.getType()==DocumentType.Magazine)
			sql = "UPDATE documents SET Title='" + update.getTitle() + "',"
					 +"AuthorID='" + newU.getId() +  "',"
					 +"Description='" + update.getDescription() + "',"
					 +"Price='" + update.getPrice() + "',"
					 +"Type='2' WHERE ISBN='" + update.getISBN() + "';";
		
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);

			return update;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
									 
		
	}
	
	public Document getDocument(int ISBN) {
		String sql = "SELECT * FROM documents WHERE ISBN=" + "'" + ISBN + "';";
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			result.beforeFirst();
			if (result.first()) {
				if (result.getString("Type").equals("1"))
					return new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Book, result.getDouble("Price"));

				else if (result.getString("Type").equals("2"))
					return new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Magazine, result.getDouble("Price"));

				else if (result.getString("Type").equals("3")) {
					return new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Journal, result.getDouble("Price"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Document> getAllDocuments() {
		String sql = "SELECT * FROM documents";
		ArrayList<Document> docs = new ArrayList<Document>();
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			result.beforeFirst();
			while (result.next()) {
				if (result.getString("Type").equals("1"))
					docs.add(new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Book, result.getDouble("Price")));

				else if (result.getString("Type").equals("2"))
					docs.add(new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Magazine, result.getDouble("Price")));

				else if (result.getString("Type").equals("3")) {
					docs.add(new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Journal, result.getDouble("Price")));
				}
			}

			return docs;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Document> getAllPromotions() {
		String sql = "SELECT * FROM documents WHERE Promotion=1";
		ArrayList<Document> docs = new ArrayList<Document>();
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			result.beforeFirst();
			while (result.next()) {
				if (result.getString("Type").equals("1"))
					docs.add(new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Book, result.getDouble("Price")));

				else if (result.getString("Type").equals("2"))
					docs.add(new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Magazine, result.getDouble("Price")));

				else if (result.getString("Type").equals("3")) {
					docs.add(new Document(result.getInt("ISBN"), result.getString("Title"),
							getUserID(result.getInt("AuthorID")).getUsername(), result.getString("FilePath"),
							result.getString("Description"), DocumentType.Journal, result.getDouble("Price")));
				}
			}

			return docs;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Boolean addDocPromotion(int ISBN) {
		String sql = "UPDATE documents SET Promotion=1 WHERE ISBN='" + ISBN + "';";

		try {
			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Boolean removeDocPromotion(int ISBN) {
		String sql = "UPDATE documents SET Promotion=0 WHERE ISBN='" + ISBN + "';";

		try {
			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public double getDocPrice(int ISBN) {
		String sql = "SELECT * FROM documents WHERE ISBN='"+ ISBN + "';";
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			result.beforeFirst();
			if (result.first()) {
				return result.getDouble("Price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public double getDocISBN() {
		return 0;
	}
	
	public Boolean placePayment(PaymentInfo pay, double sum) {

		String sql = null;
		try {
			if (pay.getType() == PaymentType.Cash) {
				sql = "INSERT INTO receipt (UserID, BookID, SumAmount, PMethod) "
						+ "VALUES((SELECT UserID FROM users WHERE UserID='" + pay.getId() + "')," + "'"
						+ pay.getDoc().getISBN() + "'," + "'" + sum + "'," + "'" + "CASH" + "'" + ");";

//				"SELECT UserID FROM users WHERE UserID='" + pay.getId()"'" 
			} else if (pay.getType() == PaymentType.Credit) {
				sql = "INSERT INTO receipt (UserID,BookID,SumAmount,PMethod)"
						+ "VALUES((SELECT UserID FROM users WHERE UserID='" + pay.getId() + "')," + "'"
						+ pay.getDoc().getISBN() + "'," + "'" + sum + "'," + "'" + "DEBIT" + "'" + ");";
			} else if (pay.getType() == PaymentType.Credit) {
				sql = "INSERT INTO receipt (UserID,BookID,SumAmount,PMethod)"
						+ "VALUES((SELECT UserID FROM users WHERE UserID='" + pay.getId() + "')," + "'"
						+ pay.getDoc().getISBN() + "'," + "'" + sum + "'," + "'" + "CREDIT" + "'" + ");";
			}

			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public Boolean deletePayment(int payId) {
		String sql = "DELETE FROM receipt WHERE ReceiptID='" + payId + "';";
		try {
			statement = jdbc_connection.createStatement();
			int result = statement.executeUpdate(sql);
			if (result == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {

		DatabaseInterface db = new DatabaseInterface();

		LoginInfo lg = new LoginInfo("bigdummy", "test2");
		LoginInfo lg1 = new LoginInfo("testerguy", "test");

		Boolean testAdd = false;
		Boolean testGet = false;
		Boolean testGetAll = false;
		Boolean testAddDoc = true;
		User blah1 = new User(-1, "devan", "odd", "dodd", UserType.RegisteredBuyer);
		User blah2 = new User(-1, "mum", "dad", "momdad", UserType.RegisteredBuyer);

		if (testGetAll) {
			ArrayList<User> users = new ArrayList<User>();
			users = db.getAllUsers();
			for (User thisguy : users)
				System.out.println("Username: " + thisguy.getUsername());
		}

		if (testAddDoc) {
			Document d = new Document(1, "blad", "shivODD98", "Shit book", "filepath", DocumentType.Book, 69.99);
			//PaymentInfo info = new PaymentInfo(1, PaymentType.Cash, new Date(0), d, 69.99);
			// db.placePayment(info);
			db.deletePayment(2);
		}

	}

}
