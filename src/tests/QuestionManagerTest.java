package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import maze.MazeQuestion;
import maze.QuestionManager;

public class QuestionManagerTest {
	
	QuestionManager qm;
	
	Iterator<MazeQuestion> questionIterator;
	
	@Before
	public void setUp() throws Exception {
		qm = QuestionManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testForDuplicateQuestions() {
		qm.initializeQuestionHashMap(190);
		Iterator<MazeQuestion> mazeQuestionIterator = qm.getQuestionIterator();
		HashMap<Integer, MazeQuestion> testMap = new HashMap<Integer, MazeQuestion>(190);
		while(mazeQuestionIterator.hasNext()) {
			MazeQuestion currentQuestion = mazeQuestionIterator.next();
			assertFalse(testMap.containsKey(currentQuestion.getQuestionID()));
			assertFalse(testMap.containsValue(currentQuestion));
			testMap.put(currentQuestion.getQuestionID(), currentQuestion);
		}
	}

}
