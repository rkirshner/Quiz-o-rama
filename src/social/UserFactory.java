package social;

public class UserFactory {

	
	public UserFactory(){
		
	}
	
	/**
	 * Returns null if the username and password do not match.
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUser(String username, String password){
		return DBConnection.getUser(username, password);
	}
	/**
	 * Adds a user to the database. Returns null if the add was unsuccessful.
	 * @param username
	 * @param password
	 * @param admin
	 * @return
	 */
	public User addUser(String username, String password, boolean admin){
		if (DBConnection.addUser(username, password, admin)) return DBConnection.getUser(username, password);
		return null;
	}
	
	
	
}
