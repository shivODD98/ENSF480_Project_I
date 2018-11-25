package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import SharedObjects.*;

public class DatabaseInterface {
	public Connection jdbc_connection;
	public Statement statement;
	public String databaseName = "D3LDataBase";
	
	
	public String connectionInfo = "jdbc:mysql://localhost:3306/BOOKgang",  
				  login          = "root",
			      password       = "Pudge.1998";
	
	public DatabaseInterface()
	{
		try{
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");
			
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
		
		
	}
	
	
	
	public User checkLogin(LoginInfo log)
	{
		String sql = "SELECT * FROM users WHERE Username=" + "'"+log.getUsername()+"'" +
					 " AND Password=" + "'"+log.getPassword()+"'";
		try {
			statement = jdbc_connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if(!result.first())
			{
				
			}
			result.beforeFirst();
			if(result.first())
			{
				if(result.getString("type").equals("1"))
				{
					//return new User(result.getInt("UserID"), result.getString("Fname"), result.getString("Lname"), result.getString("Username"), result.getInt("Type"));
				}
			}
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
