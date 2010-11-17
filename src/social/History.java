package social;

public class History {
	
	private String url;
	private int score;
	private long timestamp;
	/**
	 * Should not be called by a client.
	 * @param url
	 * @param score
	 */
	public History(String url, int score){
		this.url = url;
		this.score = score;
	}
	/**
	 * Should not be called by a client.
	 * @param url
	 * @param score
	 * @param timestamp
	 */
	public History(String url, int score, long timestamp){
		this.url = url;
		this.score = score;
		this.timestamp = timestamp;
	}
	/**
	 * Returns a link to the quiz.
	 * @return
	 */
	public String getQuiz(){
		return url;
	}
	/**
	 * Returns the time in milliseconds the quiz was completed since the epoch.
	 * @return
	 */
	public long getTimeStamp(){
		return timestamp;
	}
	/**
	 * Returns the players score on the quiz.
	 * @return
	 */
	public int getScore(){
		return score;
	}
}
