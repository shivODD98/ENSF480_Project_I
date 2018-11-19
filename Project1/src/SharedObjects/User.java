package SharedObjects;

import java.io.Serializable;

public class User implements Serializable {

	private int id;
	private String firstName;
	private String lastName;

	
	public User(int id, String firstName, String lastName) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
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
	
}
