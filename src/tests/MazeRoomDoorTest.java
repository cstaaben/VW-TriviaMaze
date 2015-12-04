package tests;

import static org.junit.Assert.*;

import creation.MazeFactory;
import maze.Maze;
import maze.MazeRoom;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import maze.MazeDirection;
import maze.MazeDoor;

import java.util.Iterator;

public class MazeRoomDoorTest {

	static Maze maze;
	MazeRoom test;

	@BeforeClass
	public static void createMaze() {
		maze = MazeFactory.getReference().getMaze(3);
	}
	
	@Before
	public void setUp() throws Exception {
		//get door
		test = maze.getRoom(1,1);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void middleTest() {
		assertTrue(test.isValidDoor("north"));
		assertTrue(test.isValidDoor("south"));
		assertTrue(test.isValidDoor("east"));
		assertTrue(test.isValidDoor("west"));
	}

	@Test
	public void northeastTest() {
		test = maze.getRoom(0,2);

		assertTrue(test.isValidDoor("south"));
		assertTrue(test.isValidDoor("west"));
		assertFalse(test.isValidDoor("north"));
		assertFalse(test.isValidDoor("east"));
	}

	@Test
	public void northwestTest() {
		test = maze.getRoom(0,0);

		assertTrue(test.isValidDoor("south"));
		assertTrue(test.isValidDoor("east"));
		assertFalse(test.isValidDoor("north"));
		assertFalse(test.isValidDoor("west"));
	}

	@Test
	public void southwestTest() {
		test = maze.getRoom(2,0);

		assertTrue(test.isValidDoor("north"));
		assertTrue(test.isValidDoor("east"));
		assertFalse(test.isValidDoor("south"));
		assertFalse(test.isValidDoor("west"));
	}

	@Test
	public void southeastTest() {
		test = maze.getRoom(2,2);

		assertTrue(test.isValidDoor("north"));
		assertTrue(test.isValidDoor("west"));
		assertFalse(test.isValidDoor("south"));
		assertFalse(test.isValidDoor("east"));
	}

	@Test
	public void topMidTest() {
		test = maze.getRoom(0,1);

		assertTrue(test.isValidDoor("south"));
		assertTrue(test.isValidDoor("east"));
		assertTrue(test.isValidDoor("west"));
		assertFalse(test.isValidDoor("north"));
	}

	@Test
	public void leftMidTest() {
		test = maze.getRoom(1,0);

		assertTrue(test.isValidDoor("south"));
		assertTrue(test.isValidDoor("east"));
		assertTrue(test.isValidDoor("north"));
		assertFalse(test.isValidDoor("west"));
	}

	@Test
	public void rightMidTest() {
		test = maze.getRoom(1,2);

		assertTrue(test.isValidDoor("north"));
		assertTrue(test.isValidDoor("east"));
		assertTrue(test.isValidDoor("west"));
		assertFalse(test.isValidDoor("south"));
	}

	@Test
	public void bottomMidTest() {
		test = maze.getRoom(2,1);

		assertTrue(test.isValidDoor("north"));
		assertTrue(test.isValidDoor("east"));
		assertTrue(test.isValidDoor("west"));
		assertFalse(test.isValidDoor("south"));
	}

}
