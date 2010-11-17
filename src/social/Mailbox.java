package social;

import java.util.List;
/**
 * Nearly static class. Contains no information other than the name of the owner. Every method accesses the database for current information.
 * 
 *
 */
public class Mailbox {
	String user;
	
	/**
	 * 
	 * @param user
	 */
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
		return DBConnection.getNotes(user, DBConnection.BOTH);

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
		return DBConnection.getChallenges(user, DBConnection.BOTH);

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
	 * Sends a message. Throws a runtime exception if the message is not ready to send (ie a note does not have its 
	 * message filled or a challenge is missing a url or highscore), the sender of the message is not the same as the 
	 * mailbox, or the recipient does not exist. Does nothing if m is a FriendRequest that is still pending or if the 
	 * users are already friends.
	 * @param m
	 * @throws RuntimeException
	 */
	public void sendMessage(Message m) throws RuntimeException{
		if (!m.readyToSend()) throw new RuntimeException("All required message fields are not filled in");
		if (!m.getSender().equals(user))throw new RuntimeException("Mailbox is not owned by sender.");
		try{
			if(m.getClass().equals(FriendRequest.class)) DBConnection.sendFriendRequest((FriendRequest) m);
			if(m.getClass().equals(Note.class))DBConnection.sendNote((Note) m);
			if(m.getClass().equals(Challenge.class)) DBConnection.sendChallenge((Challenge) m);
		} catch (RuntimeException e){
			throw new RuntimeException("Recipient does not exist");
		}
		
	}
}
