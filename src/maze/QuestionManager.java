/**
 * QuestionManager.java
 * Author: Clifton Caleb Jewett
 * Description: Class to query the database to construct the collection of questions for an instance of a maze.
 */

package maze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mazeDB.MazeDB;
import states.TriviaMaze;

import java.util.Collections;

public class QuestionManager {
	
	//the Singleton instance of QuestionManager
	
	private static QuestionManager uniqueInstance;
	
	//HashMap of questions taken from database
	
	private static HashMap<Integer, MazeQuestion> questionHashMap;
	
	//gets filled with the largest question ID in the database
	
	private static Integer maxQuestionID;
	
	//number of questions needed for the maze size, should equal number of doors
	//exclusive upper bound for autoIncrementIndex
	
	private static Integer questionCount;
	
	//private constructor for Singleton
	
	private QuestionManager() {
	}
	
	/**
	 * Returns Singleton instance
	 * @return - Singleton instance
	 */
	
	public static QuestionManager getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new QuestionManager();
		}
		return uniqueInstance;
	}
	
	/**
	 * Returns iterator of questions.
	 * @return - Iterator of questions
	 */
	
	public Iterator<MazeQuestion> getQuestionIterator() {
		return questionHashMap.values().iterator();
	}
	
	/**
	 * Called before getQuestionIterator to set its size and initialize it
	 * @param numberOfDoors - the number of questions needed for the current game
	 */
	
	public void initializeQuestionHashMap(int numberOfDoors) {
		
		questionCount = numberOfDoors;
		int i = 0;
		
		if(questionHashMap != null) {
			questionHashMap.clear();
		}
		
		questionHashMap = new HashMap<Integer, MazeQuestion>(numberOfDoors);
		
		MazeQuestion newQuestionToAdd;
		Integer newQuestionKey;
		
		if(TriviaMaze.DEMO) {
			if(numberOfDoors < 4) {
				throw new RuntimeException("there must be at least 4 doors to run the demo");
			}
			i = 3;
			newQuestionToAdd = new MazeQuestion(196, 'm', 'v', "Who is dodging punches in the video? a. Muhammad Ali b. George Foreman c. Mike Tyson d. Raging Bull" , "a", "srs/maze/mp4/0001.mp4");
			newQuestionKey = newQuestionToAdd.getQuestionID();
			questionHashMap.put(newQuestionKey, newQuestionToAdd);
			newQuestionToAdd = new MazeQuestion(192, 'm', 's', "What sort of bird makes this call? a. Ostrich b. Albatross c. Raven d. Robin" , "d", "src/maze/mp3/0001.mp3");
			newQuestionKey = newQuestionToAdd.getQuestionID();
			questionHashMap.put(newQuestionKey, newQuestionToAdd);
			newQuestionToAdd = new MazeQuestion(194, 'm', 's', "Who composed this piece? a. Wolfgang Amadeus Mozart b. Bob Dylan c. Johann Sebastian Bach d. Madonna" , "c", "src/maze/mp3/0002.mp3");
			newQuestionKey = newQuestionToAdd.getQuestionID();
			questionHashMap.put(newQuestionKey, newQuestionToAdd);
			newQuestionToAdd = new MazeQuestion(195, 'm', 's', "What school's fight song is this from? a. MIT b. Yale c. Notre Dame d. USC" , "c", "src/maze/mp3/0003.mp3");
			newQuestionKey = newQuestionToAdd.getQuestionID();
			questionHashMap.put(newQuestionKey, newQuestionToAdd);
			
		}
		
		for(; i < questionCount; i++) {
			newQuestionToAdd = uniqueInstance.getNewQuestion();
			newQuestionKey = newQuestionToAdd.getQuestionID();
			questionHashMap.put(newQuestionKey, newQuestionToAdd);
		}//end for loop i
		Set<Integer> setOfKeys = questionHashMap.keySet();
		List<MazeQuestion> collectionOfQuestions = new ArrayList<MazeQuestion>(questionHashMap.values());
		Collections.shuffle(collectionOfQuestions);
		
		HashMap<Integer, MazeQuestion> shuffledQuestionHashMap = new HashMap<Integer,MazeQuestion>(numberOfDoors);
		Iterator<MazeQuestion> questionIterator = collectionOfQuestions.iterator();
		for(Integer integer : setOfKeys) {
			shuffledQuestionHashMap.put(integer, questionIterator.next());
		}
		questionHashMap = shuffledQuestionHashMap;
	}
	
	/**
	 * Gets a question from the database not currently in the HashMap
	 * @return - a new question
	 */
	
	private MazeQuestion getNewQuestion() {
		maxQuestionID = getMaxQuestionID();
		Random rng = new Random(System.currentTimeMillis() + System.nanoTime());
		int modValue = uniqueInstance.getMaxQuestionID() + 1;
		
		MazeQuestion newQuestion = null;
		
		while(newQuestion == null) {
			int toTry = rng.nextInt(modValue);
			
			if(questionHashMap.containsKey(new Integer(toTry))) {
				continue;
			}
			
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			
			try(Connection connection = DriverManager.getConnection(MazeDB.getDBPath());) {
				connection.setAutoCommit(false);
				try(Statement statement = connection.createStatement();
					ResultSet rs = statement.executeQuery("SELECT * FROM QUESTION WHERE ID = " + toTry + ";");) {
					if(rs.next()) {

						int newQuestionID = rs.getInt(1);
						char newQuestionType = rs.getString(2).charAt(0);
						char newFileType = rs.getString(3).charAt(0);
						String newQuestionText = rs.getString(4);
						String newAnswerText = rs.getString(5);
						String newFilePath = rs.getString(6);
						
						//unescape SQL '
						newQuestionText = newQuestionText.replaceAll("''", "'");
						
						newQuestion = new MazeQuestion(newQuestionID, newQuestionType, newFileType, newQuestionText, newAnswerText, newFilePath);
						
					} else {
						continue;
					}
				}
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}
		return newQuestion;
	}
	
	/**
	 * Calculates the largest ID int in the database and returns it.
	 * @return - returns the largest ID int in the database
	 */
	
	private int getMaxQuestionID() {
		if(maxQuestionID != null) {
			return maxQuestionID;
		} else {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			
			try(Connection connection = DriverManager.getConnection("jdbc:sqlite:src/mazeDB/mazeQuestions.db");) {
				connection.setAutoCommit(false);
				try(Statement statement = connection.createStatement();
					ResultSet idResultSet = statement.executeQuery("SELECT max(ID) FROM QUESTION;");) {
					if(idResultSet.next()) {
						maxQuestionID = idResultSet.getInt(1);
					} else {
						throw new RuntimeException("Error attempting to query max QUESTION ID");
					}
				}
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}
		return maxQuestionID;
	}
}
