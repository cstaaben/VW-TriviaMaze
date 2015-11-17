package maze;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public void test() {
		fail("Not yet implemented");
	}

}
