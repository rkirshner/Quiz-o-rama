package social;

public class Challenge extends Message{
	
	
	private int highscore;
	private String link;
	
	
	/** 
	 * @param sender
	 * @param recipient
	 */
	public Challenge(String sender, String recipient){
		super(sender, recipient, System.currentTimeMillis());
		highscore = -1;
	}
	
	/**
	 * 
	 * @param sender
	 * @param recipient
	 * @param time
	 */
	public Challenge(String sender, String recipient,long time) {
		super(sender, recipient, time);
		highscore = -1;
	}
	/**
	 * Sets the link to the quiz.
	 * @param url
	 */
	public void setLink(String url){
		//TODO 
		link = url;
	}
	
	/**
	 * Sets the high score.
	 * @param highscore
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
	
	protected boolean readyToSend(){
		if (link != null && highscore != -1) return true;
		return false;
	}
	

}
