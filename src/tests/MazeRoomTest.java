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
		test = new MazeRoom(2,3);
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
		assertTrue(test.isValidDoor("north"));
		assertTrue(test.isValidDoor("south"));
		assertTrue(test.isValidDoor("west"));
		assertTrue(test.isValidDoor("east"));
		MazeRoom m1 = new MazeRoom(0,3); 
		MazeRoom m2 = new MazeRoom(0,0);
		MazeRoom m3 = new MazeRoom(0,4);
		MazeRoom m4 = new MazeRoom(4,4);
		MazeRoom m5 = new MazeRoom(4,2);
		MazeRoom m6 = new MazeRoom(3,0);
		MazeRoom m7 = new MazeRoom(3,4);
		MazeRoom m8 = new MazeRoom(4,0);
		// top row, mid column
		assertFalse(m1.isValidDoor("north"));
		assertTrue(m1.isValidDoor("south"));
		assertTrue(m1.isValidDoor("east"));
		assertTrue(m1.isValidDoor("west"));
		// top-left corner
		assertFalse(m2.isValidDoor("north"));
		assertFalse(m2.isValidDoor("west"));
		assertTrue(m2.isValidDoor("south"));
		assertTrue(m2.isValidDoor("east"));
		// top-right corner
		assertTrue(m3.isValidDoor("south"));
		assertTrue(m3.isValidDoor("west"));
		assertFalse(m3.isValidDoor("north"));
		assertFalse(m3.isValidDoor("east"));
		// bottom-left corner
		assertTrue(m8.isValidDoor("north"));
		assertTrue(m8.isValidDoor("east"));
		assertFalse(m8.isValidDoor("south"));
		assertFalse(m8.isValidDoor("west"));
		// bottom-right corner
		assertTrue(m4.isValidDoor("north"));
		assertTrue(m4.isValidDoor("west"));
		assertFalse(m4.isValidDoor("south"));
		assertFalse(m4.isValidDoor("east"));
		// left-mid column
		assertTrue(m6.isValidDoor("north"));
		assertTrue(m6.isValidDoor("south"));
		assertTrue(m6.isValidDoor("east"));
		assertFalse(m6.isValidDoor("west"));
		// right-mid column
		assertTrue(m7.isValidDoor("north"));
		assertTrue(m7.isValidDoor("south"));
		assertTrue(m7.isValidDoor("west"));
		assertFalse(m7.isValidDoor("east"));
		// bottom-mid row
		assertTrue(m5.isValidDoor("north"));
		assertTrue(m5.isValidDoor("east"));
		assertTrue(m5.isValidDoor("west"));
		assertFalse(m5.isValidDoor("south"));
	}
	
}
