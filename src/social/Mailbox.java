package social;

import java.util.List;

public class Mailbox {
	String user;
	
	public Mailbox(String user){
		this.user = user;
	}
	/**
	 * Returns the number unread.
	 * @return
	 */
	public int unread(){
		return DBConnection.getNumUnreadMessages(user);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Note> getSentNotes(){
		return null;
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Note> getRecievedNotes(){
		return null;
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Note> getAllNotes(){
		return null;
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Challenge> getSentChallenges(){
		return null;
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Challenge> getRecievedChallenge(){
		return null;
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Challenge> getAllChallenge(){
		return null;
		
	}
	/**
	 * 
	 * @return
	 */
	public List<FriendRequest> getSentFriendRequests(){
		return DBConnection.getFriendRequests(user, DBConnection.SENT);
	}
	/**
	 * 
	 * @return
	 */
	public List<FriendRequest> getRecievedFriendRequests(){
		return DBConnection.getFriendRequests(user, DBConnection.RECIEVED);
		
	}
	/**
	 * 
	 * @return
	 */
	public List<FriendRequest> getAllFriendRequests(){
		return DBConnection.getFriendRequests(user, DBConnection.BOTH);
	}
}
