package social;
import java.sql.*;
import java.util.*;


public class DBConnection {

	private static String account = "ccs108rbk";
	private static String password = "kusingee";
	private static String server = "mysql-user.stanford.edu";
	private static String database = "c_cs108_rbk";
	private static Connection con;

	protected final static int BOTH = 0;
	protected final static int SENT = 1;
	protected final static int RECIEVED = 2;
	
	protected final static int ACCEPT = 3;
	protected final static int DECLINE = 4;
	
	private static void initializeConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + server, account ,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected static void setSubject(Message m){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("UPDATE social_mail SET subject = \"" + m.getSubject() + "\" WHERE sent_user_id = \"" + m.getSender() +"\" AND recieved_user_id = \"" +m.getRecipient()+ "\" AND timestamp = \""+ m.getTimeStamp()+"\";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void processFriendRequest(FriendRequest f, int response){
		if (f.processed()) return;
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			if (response == ACCEPT) stmt.executeUpdate("INSERT INTO social_friendships VALUES(\""+f.getSender()+"\",\""+f.getRecipient()+"\");");
			stmt.executeUpdate("DELETE FROM social_mail WHERE (sent_user_id =\"" + f.getSender()+"\" OR sent_user_id = \""+f.getRecipient()+"\") AND (recieved_user_id = \"" + f.getRecipient()+"\" OR recieved_user_id = \""+f.getSender()+"\") AND type = \"friend_request\";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void sendNote(Note n)throws RuntimeException{
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT user_id FROM users WHERE user_id LIKE \"" + n.getRecipient() +"\" ;");
			if (!rs.first()) throw new RuntimeException("Recipient does not exist.");
			String q = "";
			q += "INSERT INTO social_mail(sent_user_id, recieved_user_id, timestamp, r, type, note_message";
			if (n.getSubject() != null) q+= ", subject";
			q += ") VALUES(\"" + n.getSender() + "\",\""+n.getRecipient()+"\",\""+System.currentTimeMillis()+"\",\"0\",\"note\",\"" + n.getMessage();
			if (n.getSubject() != null) q+= "\",\"" + n.getSubject() ;
			q+= "\");";
			
			stmt.executeUpdate(q);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	protected static void sendFriendRequest(FriendRequest n)throws RuntimeException{
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT user_id FROM users WHERE user_id LIKE \"" + n.getRecipient() +"\" ;");
			if (!rs.first()) throw new RuntimeException("Recipient does not exist.");
			rs = stmt.executeQuery("SELECT user_a FROM social_friendships WHERE (user_a =\"" + n.getSender() + "\" AND user_b =\"" + n.getRecipient() + "\") OR (user_a = \"" + n.getRecipient() + "\" AND user_b =\"" + n.getSender() + "\");");
			if (rs.first()) return;
			
			rs = stmt.executeQuery("SELECT sent_user_id FROM social_mail WHERE (sent_user_id =\"" + n.getSender()+"\" OR sent_user_id = \""+n.getRecipient()+"\") AND (recieved_user_id = \"" + n.getRecipient()+"\" OR recieved_user_id = \""+n.getSender()+"\") AND type = \"friend_request\";");
			if(rs.first()) return;
			
			String q = "";
			q += "INSERT INTO social_mail(sent_user_id, recieved_user_id, timestamp, r, type";
			if (n.getSubject() != null) q+= ", subject";
			q+=") VALUES(\"" + n.getSender() + "\",\""+n.getRecipient()+"\",\""+n.getTimeStamp()+"\",\"0\",\"friend_request";
			if (n.getSubject() != null) q+= "\",\"" + n.getSubject() ;
			q+= "\");";
			
			
			
			stmt.executeUpdate(q);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	protected static void sendChallenge(Challenge c) throws RuntimeException{
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT user_id FROM users WHERE user_id LIKE \"" + c.getRecipient() +"\" ;");
			if (!rs.first()) throw new RuntimeException("Recipient does not exist.");
			
			String q = "";
			q += "INSERT INTO social_mail(sent_user_id, recieved_user_id, timestamp, r, type, challenge_link, challenge_high_score";
			if (c.getSubject() != null) q += ",subject";
			q+= ") VALUES(\"" + c.getSender() + "\",\""+c.getRecipient()+"\",\""+c.getTimeStamp()+"\",\"0\",\"challenge\",\""+c.getLink()+"\",\""+c.getHighScore();
			if (c.getSubject() != null) q += "\", \"" + c.getSubject();
			q+= "\");";
			
			
			stmt.executeUpdate(q);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
		
	protected static int getNumUnreadMessages(String user){
		initializeConnection();
		int ret = 0;
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT r FROM social_mail WHERE recieved_user_id LIKE \"" + user +"\" AND r = 1;");
			while(rs.next()){
				ret++;
			}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return ret;
	}
	
	protected static List<FriendRequest> getFriendRequests(String user, int modifier){
		initializeConnection();
		List<FriendRequest> ret = new ArrayList<FriendRequest>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = null;
			switch (modifier){
			case BOTH:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE (sent_user_id LIKE \"" + user +"\" OR recieved_user_id LIKE\"" + user +"\") AND type = \"friend_request\" ORDER BY timestamp DESC;");
				break;
			case SENT:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE sent_user_id LIKE \"" + user +"\" AND type = \"friend_request\" ORDER BY timestamp DESC;");
				break;
			case RECIEVED:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE recieved_user_id LIKE \"" + user +"\" AND type = \"friend_request\" ORDER BY timestamp DESC;");	
				break;
			}
			while (rs.next()){
				FriendRequest next = new FriendRequest(rs.getString("sent_user_id"), rs.getString("recieved_user_id"), rs.getLong("timestamp"));
				next.setRead(rs.getBoolean("r"));
				next.setSubject(rs.getString("subject"));
				ret.add(next);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;	
	}
	
	protected static List<Note> getNotes(String user, int modifier){
		initializeConnection();
		List<Note> ret = new ArrayList<Note>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = null;
			switch (modifier){
			case BOTH:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE (sent_user_id LIKE \"" + user +"\" OR recieved_user_id LIKE\"" + user +"\") AND type = \"note\" ORDER BY timestamp DESC;");
				break;
			case SENT:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE sent_user_id LIKE \"" + user +"\" AND type = \"note\" ORDER BY timestamp DESC;");
				break;
			case RECIEVED:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE recieved_user_id LIKE \"" + user +"\" AND type = \"note\" ORDER BY timestamp DESC;");	
				break;
			}
			while (rs.next()){
				Note next = new Note(rs.getString("sent_user_id"), rs.getString("recieved_user_id"), rs.getLong("timestamp"));
				next.setRead(rs.getBoolean("r"));
				next.setSubject(rs.getString("subject"));
				next.setMessage(rs.getString("note_message"));
				ret.add(next);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;	
	}
	
	protected static List<Challenge> getChallenges(String user, int modifier){
		initializeConnection();
		List<Challenge> ret = new ArrayList<Challenge>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = null;
			switch (modifier){
			case BOTH:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE (sent_user_id LIKE \"" + user +"\" OR recieved_user_id LIKE\"" + user +"\") AND type = \"note\" ORDER BY timestamp DESC;");
				break;
			case SENT:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE sent_user_id LIKE \"" + user +"\" AND type = \"note\" ORDER BY timestamp DESC;");
				break;
			case RECIEVED:
				rs = stmt.executeQuery("SELECT * FROM social_mail WHERE recieved_user_id LIKE \"" + user +"\" AND type = \"note\" ORDER BY timestamp DESC;");	
				break;
			}
			while (rs.next()){
				Challenge next = new Challenge(rs.getString("sent_user_id"), rs.getString("recieved_user_id"), rs.getLong("timestamp"));
				next.setRead(rs.getBoolean("r"));
				next.setSubject(rs.getString("subject"));
				next.setLink(rs.getString("challenge_link"));
				next.setHighScore(rs.getInt("challenge_high_score"));
				ret.add(next);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;	
	}
	
	protected static void addTakenQuiz(String user, int prev){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("UPDATE social_achievements SET quizzes_taken = \"" + (prev + 1) + "\" WHERE user_id = \"" + user + "\";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void addWrittenQuiz(String user, int prev){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("UPDATE social_achievements SET quizzes_made = \"" + (prev + 1) + "\" WHERE user_id = \"" + user + "\";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void reachedHighScore(String user){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("UPDATE social_achievements SET high_score_reached = \"1\" WHERE user_id = \"" + user + "\";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static Achievements getAchievements(User user){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM social_achievements WHERE user_id LIKE \"" + user.getName()+ "\";");
			if(rs.first()){
				return new Achievements(rs.getString("user_id"), rs.getInt("quizzes_made"), rs.getInt("quizzes_taken"), rs.getBoolean("high_score_reached"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	protected static User getUser(String username, String password){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_id LIKE \"" + username+ "\" AND password LIKE \"" + PasswordHandler.plainToHash(password) + "\" ;");

			if(rs.first()){
				return new User(rs.getString("user_id"), rs.getString("password"), rs.getBoolean("admin"), true);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected static User addUser(String username, String password, boolean admin){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_id LIKE \"" + username+ "\";");
			if (rs.first()) return null;
			int adminint = 0;
			if(admin) adminint = 1;
			stmt.executeUpdate("INSERT INTO users VALUES(\"" + username +"\",\""+PasswordHandler.plainToHash(password)+"\",\""+ adminint+"\")" );
			return new User(username, password, admin);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected static List<String> getUserFriends(String user){
		initializeConnection();
		List<String> ret = new ArrayList<String>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM social_friendships WHERE user_a LIKE \"" + user +"\" OR user_b LIKE\"" + user +"\";");
			while (rs.next()){
				if (rs.getString("user_a").equals(user)){
					ret.add(rs.getString("user_b"));
				} else{
					ret.add(rs.getString("user_a"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;		
	}
	
	protected static List<History> getUserHistory(String user){
		initializeConnection();
		List<History> ret = new ArrayList<History>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM social_history WHERE user_id LIKE \"" + user +"\" ORDER BY time DESC;");
			while (rs.next()){
				History next = new History(rs.getString("quiz_link"), rs.getInt("score"), rs.getLong("time"));
				ret.add(next);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	protected static void addToHistory(User user, String url, int score){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO social_history VALUES (\"" + user.getName() + "\",\"" + System.currentTimeMillis() + "\",\"" + url + "\",\"" + score + "\")");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void markAsRead(Message m, boolean r){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			String u = "UPDATE social_mail SET r = \"";
			if (r){
				u += "1";
			} else {
				u += "0";
			}
			u += "\" WHERE sent_user_id =\"" + m.getSender() + "\" AND recieved_user_id =\"" + m.getRecipient() + "\" AND timestamp =\"" + m.getTimeStamp() + "\";";
			stmt.executeUpdate(u);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

}
