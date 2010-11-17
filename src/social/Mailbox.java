package social;

import java.util.List;
/**
 * Nearly static class. Contains no information other than the name of the owner. Every method accesses the database for current information.
 * 
 *
 */
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
		return DBConnection.getNotes(user, DBConnection.SENT);
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Note> getRecievedNotes(){
		return DBConnection.getNotes(user, DBConnection.RECIEVED);
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Note> getAllNotes(){
		return DBConnection.getNotes(user, DBConnection.ALL);
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Challenge> getSentChallenges(){
		return DBConnection.getChallenges(user, DBConnection.SENT);
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Challenge> getRecievedChallenge(){
		return DBConnection.getChallenges(user, DBConnection.RECIEVED);
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Challenge> getAllChallenge(){
		return DBConnection.getChallenges(user, DBConnection.ALL);
		
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
	
	
	/**
	 * Sends a message. Does nothing if m is a FriendRequest that is still pending or if the users are already friends.
	 * @param m
	 */
	public void sendMessage(Message m){
		if(m.getClass().equals(FriendRequest.class)) System.out.println("fr");
		if(m.getClass().equals(Note.class)) System.out.println("n");
		if(m.getClass().equals(Challenge.class)) System.out.println("c");
	}
}
