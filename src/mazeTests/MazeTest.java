package mazeTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import maze.Maze;

public class MazeTest {

	Maze test;
	
	@Before
	public void setUp() throws Exception {
		test = Maze.getMaze(5);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getSizeTest() {
		assertEquals(5, test.getSize());
	}
	
	@Test
	public void startEndCoordinateTest() {
		assertFalse(test.getStart().equals(test.getEnd()));
	}

}
