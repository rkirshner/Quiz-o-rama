package social;
import junit.framework.TestCase;

import java.util.*;

public class Tester extends TestCase{
	DBConnection c;
	User usera;
	protected void setUp() throws Exception {
		c = new DBConnection();
		
	}
	 
	public void testDBGetUser() {
		System.out.println("contains: " + c.getUser("test1", "test1"));
		System.out.println("does not contain: " + c.getUser("test1", "wrong password"));
		System.out.println("does not contain: " + c.getUser("wrong username", "test1"));
		System.out.println("does not contain: " + c.getUser("wrong username", "wrong password"));
		
	}
	
	public void testDBAddUser(){
		assertTrue(c.addUser("test2", "test2", false));
		assertFalse(c.addUser("test1", "test1", true));
	}
	
	
	
	
	
}
