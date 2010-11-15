package social;

public class FriendRequest extends Message{
	/**
	 * 
	 * @param sender
	 * @param recipient
	 */
	public FriendRequest(String sender, String recipient) {
		super(sender, recipient);
	}
	
	/**
	 * Accepts a friends request. Only usable by the recipient. Removes FriendRequest from list. 
	 * @param answer
	 */
	public void accept(boolean answer){
		
	}
	/**
	 * Declines a friends request. Only usable by the recipient. Removes FriendRequest from list.
	 * @param answer
	 */
	public void decline(boolean answer){
		
	}

}
