package social;

import com.sun.tools.javac.util.List;

public class User {

	private String name;
	private String password;
	private boolean admin;
	private History history;
	
	/**
	 * Password given in plain text, saved hased.
	 * @param name
	 * @param password
	 * @param admin
	 */
	public User(String name, String password, boolean admin){
		
	}
	/**
	 * Gets a list of the user's friends.
	 * @return
	 */
	public List<String> getFriends(){
		return null;
	}
	/**
	 * Decides of the password (Plain text) given is correct
	 * @param password 
	 * @return
	 */
	public boolean correctPassword(String password){
		return true;
	}
	/**
	 * Returns the associated Mailbox object.
	 * @return
	 */
	public Mailbox getMailbox(){
		return null;
	}
	
	/**
	 * Gets the associated Achievement object.
	 * @return
	 */
	public Achievements getAchievements(){
		return null;
	}
	/**
	 * Gets a list of History objects in descending order by time.
	 * @return
	 */
	public List<History> getHistory(){
		return null;
	}
	
	
	
	
}
