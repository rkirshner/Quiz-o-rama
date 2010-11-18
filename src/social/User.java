package social;

import java.util.List;
/**
 * Nearly static class. Contains very little information, everything is pulled from the database for up to date information.
 * @author rexkirshner
 *
 */
public class User {

	private String name;
	private String password;
	private boolean admin;
	
	/**
	 * Password given in plain text, saved hashed.
	 * @param name
	 * @param password
	 * @param admin
	 */
	protected User(String name, String password, boolean admin){
		this.name = name;
		this.password = PasswordHandler.plainToHash(password);
		this.admin = admin;
	}
	
	protected User(String name, String password, boolean admin, boolean db){
		if(db){
			this.name = name;
			this.password = password;
			this.admin = admin;
		}
	}
	/**
	 * Gets a list of the user's friend's names.
	 * @return
	 */
	public List<String> getFriends(){
		return DBConnection.getUserFriends(name);
	}
	
	
	/**
	 * Returns if the user is friends with the passed in user.
	 * @param user
	 * @return
	 */
	public boolean areFriends(User user){
		return false;
	}
	/**
	 * Decides of the password (Plain text) given is correct
	 * @param password 
	 * @return
	 */
	public boolean correctPassword(String password){
		return PasswordHandler.passwordsSame(password, this.password);
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
