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
	
	public User addUser(String username, String password, boolean admin){
		if (DBConnection.addUser(username, password, admin)) return DBConnection.getUser(username, password);
		return null;
	}
	
	
	
}
