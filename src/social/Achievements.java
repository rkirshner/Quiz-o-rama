package social;

public class Achievements {

	
	public static final int AMATEUR_AUTHOR = 0;
	public static final int PROLIFIC_AUTHOR = 1;
	public static final int PRODIGIOUS_AUTHOR = 2;
	public static final int QUIZ_MACHINE = 3;
	public static final int I_AM_THE_GREATEST = 4;
	public static final int PRACTICE_MAKES_PERFECT = 5;
	
	/**
	 * Adds 1 to the progress of the achievement
	 * @param type
	 */
	public void add(int type){
		
	}

	/**
	 * Returns the percentage complete. 
	 * @param type
	 * @return percentage complete
	 */
	public double progress(int type){
		return 0;
		
	}
	
	/**
	 * Lets you know if the achievement has been completed.
	 * @param type
	 * @return
	 */
	public boolean achieved(int type){
		return true;
	}
	
	/**
	 * Sets the achievement completion (must be done manually for high scores, etc).
	 * @param type
	 */
	public void setAchieved(int type){
		
	}
	/**
	 * Sets the picture given a link.
	 * @param type
	 * @param link
	 */
	public void setPicture(int type, String link){
		
	}
	/**
	 * Gets a link to the picture.
	 * @param type
	 * @return
	 */
	public String getPicture(int type){
		return null;
	}
	
	
	
	
	
}
