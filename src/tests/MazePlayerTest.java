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
		test = new MazePlayer(mc);
		test.setPoints(50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void pointsTest() {
		assertEquals(50, test.getCurrentPoints());
		try {
			test.incrementPoints(10);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		assertEquals(60, test.getCurrentPoints());
	}
	
	@Test
	public void coordinatesTest() {
		assertEquals(test.getCurrentCoordinates().getRow(), mc.getRow());
		assertEquals(test.getCurrentCoordinates().getCol(), mc.getCol());
	}

}
