package social;

import java.util.List;

public class Achievements {

	private int made;
	private int taken;
	private boolean highscore;
	private String user;

	public Achievements(String user, int made, int taken, boolean highscore){
		this.made = made;
		this.taken = taken;
		this.highscore = highscore;
		this.user = user;
	}

	public static final int TOTAL_ACHIEVMENTS = 5;
	public static final int AMATEUR_AUTHOR = 0;
	public static final int PROLIFIC_AUTHOR = 1;
	public static final int PRODIGIOUS_AUTHOR = 2;
	public static final int QUIZ_MACHINE = 3;
	public static final int I_AM_THE_GREATEST = 4;

	/**
	 * Adds 1 to the amount of quizzes created.
	 */
	public void addWrittenQuiz(){
		DBConnection.addWrittenQuiz(user, made);
		made++;
	}
	/**
	 * Adds 1 to the amount of quizzes taken.
	 */
	public void addTakenQuiz(){
		
		DBConnection.addTakenQuiz(user, taken);
		taken++;
	}

	/**
	 * Returns the percentage complete. Returns -1.0 if the type is not recognized.
	 * @param type
	 * @return percentage complete
	 */
	public double progress(int type){
		double ret = 0;
		switch (type){
		case AMATEUR_AUTHOR:
			ret = made / 1.0;
			break;
		case PROLIFIC_AUTHOR:
			ret = made / 5.0;
			break;
		case PRODIGIOUS_AUTHOR:
			ret = made / 10.0;
			break;
		case QUIZ_MACHINE:
			ret = taken / 10.0;
			break;
		case I_AM_THE_GREATEST:
			if (highscore) ret = 1;
			break;
		default:
			return -1.0;
		}
		if (ret > 1) return 100.0;
		return (ret * 100);
	}

	/**
	 * Lets you know if the achievement has been completed. Returns false if the type is not recognized.
	 * @param type
	 * @return
	 */
	public boolean achieved(int type){
		switch (type){
		case AMATEUR_AUTHOR:
			return made == 1;
		case PROLIFIC_AUTHOR:
			return made == 5;
		case PRODIGIOUS_AUTHOR:
			return made == 10;
		case QUIZ_MACHINE:
			return taken == 10;
		case I_AM_THE_GREATEST:
			return highscore;
		}
		return false;
	}
	/**
	 * Sets the achievement "I AM THE GREATEST" to true;
	 */
	public void reachedHighScore(){
		highscore = true;
		DBConnection.reachedHighScore(user);
	}




}
