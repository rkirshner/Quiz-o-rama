package social;

public class FriendRequest extends Message{
	/**
	 * 
	 * @param sender
	 * @param recipient
	 */
	public FriendRequest(String sender, String recipient, long time) {
		super(sender, recipient, time);
	}
	
	/**
	 * Accepts a friends request. Only usable by the recipient. Removes FriendRequest from database. 
	 * @param answer
	 */
	public void accept(boolean answer){
		read = true;
	}
	/**
	 * Declines a friends request. Only usable by the recipient. Removes FriendRequest from database.
	 * @param answer
	 */
	public void decline(boolean answer){
		
	}

}
