package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import maze.MazeQuestion;

public class QuestionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		MazeQuestion q = new MazeQuestion(1, 's', 'v', "Who is talking?", "Dan Tappan", "src/maze/mp4/test.mp4");
		System.out.println(q.display());
		while(true){}
	}

}
