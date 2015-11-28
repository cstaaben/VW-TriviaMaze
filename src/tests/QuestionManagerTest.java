package tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import maze.Question;
import maze.QuestionManager;

public class QuestionManagerTest {
	
	QuestionManager qm;
	
	Iterator<Question> questionIterator;
	
	@Before
	public void setUp() throws Exception {
		qm = QuestionManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println("Random Question Set From DB 1:");
		qm.initializeQuestionHashMap(5);
		questionIterator = qm.getQuestionIterator();
		while(questionIterator.hasNext()) {
			Question q = questionIterator.next();
			System.out.println(q.getQuestion());
		}
		System.out.println("Random Question Set From DB 2:");
				qm.initializeQuestionHashMap(5);
				questionIterator = qm.getQuestionIterator();
				while(questionIterator.hasNext()) {
					Question q = questionIterator.next();
					System.out.println(q.getQuestion());
				}
				System.out.println("Random Question Set From DB 3:");
						qm.initializeQuestionHashMap(5);
						questionIterator = qm.getQuestionIterator();
						while(questionIterator.hasNext()) {
							Question q = questionIterator.next();
							System.out.println(q.getQuestion());
						}
	}

}
