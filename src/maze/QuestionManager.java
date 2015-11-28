package maze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class QuestionManager {
	
	private static QuestionManager uniqueInstance;
	
	private static HashMap<Integer, Question> questionHashMap;
	
	private static Integer maxQuestionID;
	
	//number of questions needed for the maze size, should equal number of doors
	private static Integer questionCount;
	
	private QuestionManager() {
	}
	
	public static QuestionManager getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new QuestionManager();
		}
		return uniqueInstance;
	}
	
	public Iterator<Question> getQuestionIterator() {
		return questionHashMap.values().iterator();
	}
	
	public void initializeQuestionHashMap(int numberOfDoors) {
		questionCount = numberOfDoors;
		
		if(questionHashMap != null) {
			questionHashMap.clear();
		}
		
		questionHashMap = new HashMap<Integer, Question>(numberOfDoors);
		
		for(int i = 0; i < questionCount; i++) {
			Question newQuestionToAdd = uniqueInstance.getNewQuestion();
			Integer newQuestionKey = newQuestionToAdd.getQuestionID();
			questionHashMap.put(newQuestionKey, newQuestionToAdd);
		}
		
	}
	
	private Question getNewQuestion() {
		maxQuestionID = getMaxQuestionID();
		Random rng = new Random(System.currentTimeMillis() + System.nanoTime());
		int modValue = uniqueInstance.getMaxQuestionID() + 1;
		
		Question newQuestion = null;
		
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
			
			try(Connection connection = DriverManager.getConnection("jdbc:sqlite:src/mazeDB/mazeQuestions.db");) {
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
						
						newQuestion = new Question(newQuestionID, newQuestionType, newFileType, newQuestionText, newAnswerText, newFilePath);
						
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
