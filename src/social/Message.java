package social;

public class Message {
	
	private String sender;
	private String recipient;

	private String message;
	private String subject;
	private long timeStamp;
	protected boolean read;
	
	/**
	 * Allocates a new message, setting the subject to null.
	 * @param sender
	 * @param recipient
	 * @param message
	 */
	public Message(String sender, String recipient, long timeStamp){
		this.sender = sender;
		this.recipient = recipient;
		this.timeStamp = timeStamp;
	}
	/**
	 * Sets the subject for the message.
	 * @param subject
	 */
	public void setSubject(String subject){
		
	}
	/**
	 * Returns the sender.
	 * @return
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * Returns the recipient.
	 * @return
	 */
	public String getRecipient() {
		return recipient;
	}
	
	/**
	 * Returns the subject.
	 * @return
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * Returns the time the message was created in milliseconds from the epoch.
	 * @return
	 */
	public long getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * Returns if the message has been read or not.
	 * @return
	 */
	public boolean read(){
		return read;
	}
	/**
	 * Sets if the message has been read.
	 * @param read
	 */
	public void setRead(boolean read){
		this.read = read;
	}
	
	
	
	
}
