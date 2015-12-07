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

import java.util.Collections;

public class QuestionManager {
	
	private static QuestionManager uniqueInstance;
	
	private static HashMap<Integer, MazeQuestion> questionHashMap;
	
	private static Integer maxQuestionID;
	
	//number of questions needed for the maze size, should equal number of doors
	//exclusive upper bound for autoIncrementIndex
	private static Integer questionCount;
	
	private QuestionManager() {
	}
	
	public static QuestionManager getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new QuestionManager();
		}
		return uniqueInstance;
	}
	
	public Iterator<MazeQuestion> getQuestionIterator() {
		return questionHashMap.values().iterator();
	}
	
	//this must be called after the instance is instantiated and before getQuestionIterator
	public void initializeQuestionHashMap(int numberOfDoors) {
		
		questionCount = numberOfDoors;
		int i = 0;
		
		if(questionHashMap != null) {
			questionHashMap.clear();
		}
		
		questionHashMap = new HashMap<Integer, MazeQuestion>(numberOfDoors);
		
		/* ADDING DEMO SUPPORT HERE
		if(TriviaMaze.DEMO) {
			newQuestionToAdd = new MazeQuestion()
		}*/
		
		for(; i < questionCount; i++) {
			MazeQuestion newQuestionToAdd = uniqueInstance.getNewQuestion();
			Integer newQuestionKey = newQuestionToAdd.getQuestionID();
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
