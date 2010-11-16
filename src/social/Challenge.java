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
		// TODO Auto-generated constructor stub
	}
	/**
	 * Sets the link to the quiz.
	 */
	public void setLink(String url){
		link = url;
	}
	
	/**
	 * Sets the high score.
	 */
	public void setHighScore(int hs){
		highscore = hs;
	}
	/**
	 * Gets the sender's high score on the quiz.
	 * @return
	 */
	public int getHighScore(){
		return highscore;
		
	}
	/**
	 * Gets the link to the quiz.
	 * @return
	 */
	public String getLink(){
		return link;
		
	}
	

}
