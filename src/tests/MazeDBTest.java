package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
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

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	    System.setIn(null);
	}

	@Test
	public void testgetDBPath() {
		assertEquals("jdbc:sqlite:src/mazeDB/mazeQuestions.db", MazeDB.getDBPath());
	}
	
	@Test
	public void testDatabaseAdministration() {
		System.setIn(new ByteArrayInputStream("4\n".getBytes()));
		MazeDB.databaseAdministration();
		assertEquals("Opened database successfully\r\n" +
					"Table QUESTION found\r\n" +
					"Enter a number 1-4:\r\n" +
					"1.) Add question\r\n" +
					"2.) Print all questions\r\n" +
					"3.) Delete question\r\n" +
					"4.) Exit database admin\r\n" , outContent.toString());
	}
}
