package social;
import java.sql.*;
import java.util.*;


public class DBConnection {

	private String searchString;
	private static String account = "ccs108rbk";
	private static String password = "kusingee";
	private static String server = "mysql-user.stanford.edu";
	private static String database = "c_cs108_rbk";
	private Connection con;

	public DBConnection(){
		searchString = "";
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

	public User getUser(String username, String password){
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addUser(String username, String password, boolean admin){
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE user_id LIKE \"" + username+ "\";");
			if (rs.first()) throw new SQLException();
			int adminint = 0;
			if(admin) adminint = 1;
			stmt.executeUpdate("INSERT INTO Users VALUES(\"" + username +"\",\""+password+"\",\""+ adminint+"\")" );
		} catch (SQLException e) {
			return false;
		}
		return true;
	}


}
