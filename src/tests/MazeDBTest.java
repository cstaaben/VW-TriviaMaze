package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import mazeDB.MazeDB;

public class MazeDBTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    System.setIn(inContent);
	}
	
	@Before
	public void setUp() {
		MazeDB.setDBPathForTest("jdbc:sqlite:src/mazeDB/testQuestions.db");
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	    System.setIn(null);
	}

	@Test
	public void testgetDBPath() {
		assertEquals("jdbc:sqlite:src/mazeDB/testQuestions.db", MazeDB.getDBPath());
	}
	
	
	@Test
	public void testDatabaseAdministration() {
		assertFalse(MazeDB.databaseIsLoaded());
		assertFalse(MazeDB.questionIsAdded());
		assertFalse(MazeDB.questionIsPrinted());
		System.setIn(new ByteArrayInputStream("1\n1\nt\nIs this a test question?\nt\n4\n2\n4\n".getBytes()));
		MazeDB.databaseAdministration();
		assertTrue(MazeDB.databaseIsLoaded());
		assertTrue(MazeDB.questionIsAdded());
		assertTrue(MazeDB.questionIsPrinted());
	}
}
