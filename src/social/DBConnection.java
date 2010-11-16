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
	
	protected final static int ALL = 3;
	protected final static int CHALLENGE = 4;
	protected final static int NOTE = 5;
	protected final static int FRIEND_REQUEST = 6;
	
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
	
	protected static int getNumUnreadMessages(String user){
		initializeConnection();
		int ret = 0;
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT r FROM Mail WHERE recieved_user_id LIKE \"" + user +"\" AND r = 1;");
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
				rs = stmt.executeQuery("SELECT * FROM Mail WHERE sent_user_id LIKE \"" + user +"\" OR recieved_user_id LIKE\"" + user +"\" AND type = \"friend_request\" ORDER BY timestamp DESC;");
				break;
			case SENT:
				rs = stmt.executeQuery("SELECT * FROM Mail WHERE sent_user_id LIKE \"" + user +"\" AND type = \"friend_request\" ORDER BY timestamp DESC;");
				break;
			case RECIEVED:
				rs = stmt.executeQuery("SELECT * FROM Mail WHERE recieved_user_id LIKE \"" + user +"\" AND type = \"friend_request\" ORDER BY timestamp DESC;");	
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
	
	
	
	
	
	protected static void addTakenQuiz(String user, int prev){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("UPDATE Achievements SET quizzes_taken = \"" + (prev + 1) + "\" WHERE user_id = \"" + user + "\";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void addWrittenQuiz(String user, int prev){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("UPDATE Achievements SET quizzes_made = \"" + (prev + 1) + "\" WHERE user_id = \"" + user + "\";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void reachedHighScore(String user){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("UPDATE Achievements SET high_score_reached = \"1\" WHERE user_id = \"" + user + "\";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static Achievements getAchievements(User user){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM Achievements WHERE user_id LIKE \"" + user.getName()+ "\";");
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE user_id LIKE \"" + username+ "\" AND password LIKE \"" + password + "\" ;");

			if(rs.first()){
				System.out.println(rs.getString("user_id"));
				System.out.println(rs.getString("password"));
				System.out.println(rs.getInt("admin"));
				return new User(rs.getString("user_id"), rs.getString("password"), rs.getBoolean("admin"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected static boolean addUser(String username, String password, boolean admin){
		initializeConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE user_id LIKE \"" + username+ "\";");
			if (rs.first()) throw new SQLException();
			int adminint = 0;
			if(admin) adminint = 1;
			stmt.executeUpdate("INSERT INTO Users VALUES(\"" + username +"\",\""+password+"\",\""+ adminint+"\")" );
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	protected static List<String> getUserFriends(String user){
		initializeConnection();
		List<String> ret = new ArrayList<String>();
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM Friendships WHERE user_a LIKE \"" + user +"\" OR user_b LIKE\"" + user +"\";");
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM User_History WHERE user_id LIKE \"" + user +"\" ORDER BY time DESC;");
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
			stmt.executeUpdate("INSERT INTO User_History VALUES (\"" + user.getName() + "\",\"" + System.currentTimeMillis() + "\",\"" + url + "\",\"" + score + "\")");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
