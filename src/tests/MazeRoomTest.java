package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import maze.Maze;
import maze.MazeRoom;

public class MazeRoomTest {

	MazeRoom test;
	static Maze maze;
	
	@BeforeClass
	public static void initMaze() {
		maze = Maze.getMaze(5);
	}
	
	@Before
	public void setUp() throws Exception {
		test = maze.getRoom(2,3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void doorNumTest() {
		assertEquals(4, test.getDoorNum());
	}
	
	@Test
	public void initDoor_validDoorTest() {
		// all 4 valid doors
		assertTrue(test.isValidDoor("n"));
		assertTrue(test.isValidDoor("s"));
		assertTrue(test.isValidDoor("w"));
		assertTrue(test.isValidDoor("e"));
		MazeRoom m1 = new MazeRoom(0,3); 
		MazeRoom m2 = new MazeRoom(0,0);
		MazeRoom m3 = new MazeRoom(0,4);
		MazeRoom m4 = new MazeRoom(4,4);
		MazeRoom m5 = new MazeRoom(4,2);
		MazeRoom m6 = new MazeRoom(3,0);
		MazeRoom m7 = new MazeRoom(3,4);
		MazeRoom m8 = new MazeRoom(4,0);
		// top row, mid column
		assertFalse(m1.isValidDoor("n"));
		assertTrue(m1.isValidDoor("s"));
		assertTrue(m1.isValidDoor("e"));
		assertTrue(m1.isValidDoor("w"));
		// top-left corner
		assertFalse(m2.isValidDoor("n"));
		assertFalse(m2.isValidDoor("w"));
		assertTrue(m2.isValidDoor("s"));
		assertTrue(m2.isValidDoor("e"));
		// top-right corner
		assertTrue(m3.isValidDoor("s"));
		assertTrue(m3.isValidDoor("w"));
		assertFalse(m3.isValidDoor("n"));
		assertFalse(m3.isValidDoor("e"));
		// bottom-left corner
		assertTrue(m8.isValidDoor("n"));
		assertTrue(m8.isValidDoor("e"));
		assertFalse(m8.isValidDoor("s"));
		assertFalse(m8.isValidDoor("w"));
		// bottom-right corner
		assertTrue(m4.isValidDoor("n"));
		assertTrue(m4.isValidDoor("w"));
		assertFalse(m4.isValidDoor("s"));
		assertFalse(m4.isValidDoor("e"));
		// left-mid column
		assertTrue(m6.isValidDoor("n"));
		assertTrue(m6.isValidDoor("s"));
		assertTrue(m6.isValidDoor("e"));
		assertFalse(m6.isValidDoor("w"));
		// right-mid column
		assertTrue(m7.isValidDoor("n"));
		assertTrue(m7.isValidDoor("s"));
		assertTrue(m7.isValidDoor("w"));
		assertFalse(m7.isValidDoor("e"));
		// bottom-mid row
		assertTrue(m5.isValidDoor("n"));
		assertTrue(m5.isValidDoor("e"));
		assertTrue(m5.isValidDoor("w"));
		assertFalse(m5.isValidDoor("s"));
	}
	
}
