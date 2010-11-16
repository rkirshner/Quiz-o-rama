package social;
import junit.framework.TestCase;

import java.util.*;

public class Tester extends TestCase{
	User usera;
	protected void setUp() throws Exception {
	}
	 
//	public void testDBGetUser() {
//		System.out.println("contains: " + DBConnection.getUser("test1", "test1"));
//		System.out.println("does not contain: " + DBConnection.getUser("test1", "wrong password"));
//		System.out.println("does not contain: " + DBConnection.getUser("wrong username", "test1"));
//		System.out.println("does not contain: " + DBConnection.getUser("wrong username", "wrong password"));
//		
//	}
//	
//	public void testDBAddUser(){
//		//assertTrue(c.addUser("test2", "test2", false));
//		//assertFalse(c.addUser("test1", "test1", true));
//	}
//	
//	public void testDBGetUserFriends(){
//		List<String> friends = DBConnection.getUserFriends("test2");
//		for (int i = 0; i < friends.size(); i++){
//			System.out.println(friends.get(i));
//		}
//		
//		User user = new User("test2", "test2", true);
//		List<String> f = user.getFriends();
//		for (int i = 0; i < f.size(); i++){
//			System.out.println("user: " + f.get(i));
//		}
//	
//	}
//	
//	public void testDBGetHistory(){
//		List<History> h = DBConnection.getUserHistory("test1");
//		for (int i = 0; i < h.size(); i++){
//			System.out.println("link:" + h.get(i).getQuiz());
//			System.out.println("timestamp:" + h.get(i).getTimeStamp());
//			System.out.println("score:" + h.get(i).getScore());
//			
//		}
//	}
//	
//	public void testAddHistory(){
//		User a = new User("test1", "test1", false);
//		a.addToHistory("newlink", 250);
//	}
	public void testAchievements(){
		User a = new User("test1", "test1", false);
		Achievements b = a.getAchievements();
		for (int i = 0; i < 5; i++){
			System.out.println(b.progress(i));
		}
		b.addTakenQuiz();
		b.addWrittenQuiz();
		for (int i = 0; i < 5; i++){
			
			System.out.println(b.progress(i));
		}
	}
	
	
	
	
	
	
	
}
