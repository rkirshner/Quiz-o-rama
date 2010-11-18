package social;

public class UserFactory {

	
	public UserFactory(){
		
	}
	
	/**
	 * Returns null if the username and password do not match.
	 * @param username
	 * @param password
	 * @return User
	 */
	public static User getUser(String username, String password){
		return DBConnection.getUser(username, password);
	}
	/**
	 * Adds a user to the database. Returns null if the add was unsuccessful.
	 * @param username
	 * @param password
	 * @param admin
	 * @return User
	 */
	public static User addUser(String username, String password, boolean admin){
		return (DBConnection.addUser(username, password, admin));
	}
	
	
	
}
