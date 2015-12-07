package tests;

import static org.junit.Assert.*;

import creation.MazeFactory;
import maze.Maze;
import maze.MazeCoordinates;
import maze.MazePlayer;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import states.TriviaMaze;
import states.TriviaMazeState;

public class TriviaMazeTest {

	private TriviaMaze test;
	private TriviaMazeState menu, save, load, navigate, admin, answer, end, newGame, exit;
	private MazePlayer player;
	private Maze maze;

	@BeforeClass
	public void setUp() {
		test = new TriviaMaze();
		menu = test.getMainMenuState();
		save = test.getSaveGameState();
		load = test.getLoadGameState();
		navigate = test.getNavigateMazeState();
		admin = test.getAdminDBState();
		answer = test.getAnswerQuestionState();
		end = test.getEndGameState();
		newGame = test.getNewGameState();
		exit = test.getExitMazeState();

		maze = MazeFactory.getReference().getMaze(5);
		test.setMaze(maze);

		player = new MazePlayer(test.getMaze().getStart());
		test.setPlayer(player);
	}

	@After
	public void resetState() { test.setState(test.getMainMenuState()); }

	@Test
	public void testSetState() throws Exception {
		test.setState(test.getSaveGameState());

		assertEquals(test.getCurrentState(), test.getSaveGameState());
		assertEquals(save, test.getSaveGameState());
	}

	@Test
	public void testGetCurrentState() throws Exception {
		assertEquals(test.getCurrentState(), menu);
	}

	@Test
	public void testGetMainMenuState() throws Exception {
		assertEquals(test.getMainMenuState(), menu);
	}

	@Test
	public void testGetNewGameState() throws Exception {
		assertEquals(test.getNewGameState(), newGame);
	}

	@Test
	public void testGetLoadGameState() throws Exception {
		assertEquals(test.getLoadGameState(), load);
	}

	@Test
	public void testGetAdminDBState() throws Exception {
		assertEquals(test.getAdminDBState(), admin);
	}

	@Test
	public void testGetNavigateMazeState() throws Exception {
		assertEquals(test.getNavigateMazeState(), navigate);
	}

	@Test
	public void testGetAnswerQuestionState() throws Exception {
		assertEquals(test.getAnswerQuestionState(), answer);
	}

	@Test
	public void testGetSaveGameState() throws Exception {
		assertEquals(test.getSaveGameState(), save);
	}

	@Test
	public void testGetExitMazeState() throws Exception {
		assertEquals(test.getExitMazeState(), exit);
	}

	@Test
	public void testGetEndGameState() throws Exception {
		assertEquals(test.getEndGameState(), end);
	}

	@Test
	public void testGetPlayer() throws Exception {
		assertEquals(test.getPlayer(), player);
	}

	@Test
	public void testGetMaze() throws Exception {
		assertEquals(test.getMaze(), maze);
	}

	@Test
	public void testSetMaze() throws Exception {
		test.setMaze(MazeFactory.getReference().getMaze(3));
		assertNotEquals(test.getMaze(), maze);
	}

	@Test
	public void testSetPlayer() throws Exception {
		test.setPlayer(new MazePlayer(new MazeCoordinates(0,0)));
		assertNotEquals(test.getPlayer(), player);
	}

}