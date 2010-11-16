package social;

public class Note extends Message{
	private String message;
	
	/**
	 * @param sender
	 * @param recipient
	 */
	public Note(String sender, String recipient, long time) {
		super(sender, recipient, time);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Sets the message.
	 * @param Message
	 */
	public void setMessage(String message){
		this.message = message;
	}
	/**
	 * Gets the message.
	 * @return
	 */
	public String getMessage(){
		return message;
	}

}
