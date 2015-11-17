package mazeTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import maze.MazeDirection;
import maze.MazeDoor;

public class MazeDoorTest {

	MazeDoor test;
	
	@Before
	public void setUp() throws Exception {
		//initial door
		test = new MazeDoor(MazeDirection.SOUTH);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void directionTest() {
		assertEquals("SOUTH", test.getDirection());
	}
	
	@Test
	public void openLockedTest() {
		assertEquals(false, test.isLocked());
		assertEquals(false, test.isOpen());
	}
	
	

}
