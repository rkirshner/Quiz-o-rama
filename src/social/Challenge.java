package social;

public class Challenge extends Message{
	
	
	private int highscore;
	private String link;
	
	
	/** 
	 * @param sender
	 * @param recipient
	 */
	public Challenge(String sender, String recipient,long time) {
		super(sender, recipient, time);
	}
	/**
	 * Sets the link to the quiz.
	 */
	public void setLink(String url){
		//TODO 
		link = url;
	}
	
	/**
	 * Sets the high score.
	 */
	public void setHighScore(int hs){
		//TODO 
		highscore = hs;
	}
	/**
	 * Gets the sender's high score on the quiz.
	 * @return
	 */
	public int getHighScore(){
		//TODO 
		return highscore;
		
	}
	/**
	 * Gets the link to the quiz.
	 * @return
	 */
	public String getLink(){
		//TODO 
		return link;
		
	}
	

}
