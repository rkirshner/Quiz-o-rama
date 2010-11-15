package social;

public class UserFactory {

	DBConnection c = new DBConnection();
	public UserFactory(){
		
	}
	
	/**
	 * Returns null if the username and password do not match.
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUser(String username, String password){
		return c.getUser(username, password);
	}
	
	public boolean addUser(String username, String password, boolean admin){
		return c.addUser(username, password, admin);
	}
	
}
