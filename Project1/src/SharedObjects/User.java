package SharedObjects;

import java.io.Serializable;

public class User implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private int type;

	
	public User(int id, String firstName, String lastName, String username, int type) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setType(type);
	}

	
	//*********** GETTERS & SETTERS ***********// 
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
}
