package social;

public class Challenge extends Message{
	/**
	 * 
	 * @param sender
	 * @param recipient
	 */
	public Challenge(String sender, String recipient,long time) {
		super(sender, recipient, time);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Sets the link to the quiz.
	 */
	public void setLink(){
		
	}
	/**
	 * Gets the sender's high score on the quiz.
	 * @return
	 */
	public int getHighScore(){
		return 0;
		
	}
	/**
	 * Gets the link to the quiz.
	 * @return
	 */
	public String getLink(){
		return null;
		
	}
	

}
