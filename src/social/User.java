package social;

import java.util.List;

public class User {

	private String name;
	private String password;
	private boolean admin;
	private History history;
	
	/**
	 * Password given in plain text, saved hashed.
	 * @param name
	 * @param password
	 * @param admin
	 */
	public User(String name, String password, boolean admin){
		this.name = name;
		this.admin = admin;
	}
	/**
	 * Gets a list of the user's friends.
	 * @return
	 */
	public List<String> getFriends(){
		return DBConnection.getUserFriends(name);
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
		return new Mailbox(name);
	}
	
	/**
	 * Gets the associated Achievement object.
	 * @return
	 */
	public Achievements getAchievements(){
		
		
		return DBConnection.getAchievements(this);
	}
	/**
	 * Gets a list of History objects in descending order by time.
	 * @return
	 */
	public List<History> getHistory(){
		return DBConnection.getUserHistory(name);
	}
	
	/**
	 * Adds a new history entry for the user, given the url to the quiz and their score.
	 * @param url
	 * @param score
	 */
	public void addToHistory(String url, int score){
		DBConnection.addToHistory(this, url, score);
	}
	
	/**
	 * Returns true if user is an admin, false if not.
	 * @return
	 */
	public boolean isAdmin(){
		return admin;
	}
	/**
	 * Gets users username.
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	
	
}
