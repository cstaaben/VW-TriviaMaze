package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import player.MazePlayer;
import maze.Maze;
import maze.Maze.MazeCoordinates;

public class MazePlayerTest {

	MazePlayer test;
	static Maze m;
	static MazeCoordinates mc;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		m = Maze.getMaze(4);
		mc = m.getStart();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
