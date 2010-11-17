package social;

public class FriendRequest extends Message{
	private boolean processed;
	/**
	 * 
	 * @param sender
	 * @param recipient
	 */
	public FriendRequest(String sender, String recipient, long time) {
		super(sender, recipient, time);
		processed = false;
	}
	
	/**
	 * Accepts a friends request. Only usable by the recipient. Removes FriendRequest from database. Calling it after it or decline has already been called will have no effect.
	 */
	public void accept(){
		read = true;
		DBConnection.processFriendRequest(this, DBConnection.ACCEPT);
		processed = true;
	}
	/**
	 * Declines a friends request. Only usable by the recipient. Removes FriendRequest from database.Calling it after it or accept has already been called will have no effect.
	 */
	public void decline(boolean answer){
		read = true;
		DBConnection.processFriendRequest(this, DBConnection.DECLINE);
		processed = true;
	}
	
	protected boolean processed(){
		return processed;
	}

}
