/*
 * MazeDB.java
 * Author: Clifton Caleb Jewett
 * Description: Class for a console interface with the SQLite database of trivia questions
 */

package mazeDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import states.TriviaMaze;

public class MazeDB {
	
	//main method makes sure mazeQuestions.db exists, and creates it otherwise
	
	private static final String DB_PATH = "jdbc:sqlite:src/mazeDB/mazeQuestions.db";
	
	public static void main(String args[]) {
		MazeDB.databaseAdministration();
	}
	
	public static String getDBPath() { return MazeDB.DB_PATH; }
	
	public static void databaseAdministration() {
		Connection c = null;
		Statement statement = null;
		DatabaseMetaData dbm = null;
		ResultSet tables = null;
		Scanner kb;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(MazeDB.DB_PATH);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		try {
			dbm = c.getMetaData();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		try {
			tables = dbm.getTables(null, null, "QUESTION", null);
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		try {
			if(!tables.next()) {
				 statement = c.createStatement();
			      String sql = "CREATE TABLE QUESTION " +
			                   "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
			                   " QUESTIONTYPE CHAR(1) NOT NULL, " + 
			                   " FILETYPE CHAR(1) NOT NULL, " + 
			                   " QUESTIONTEXT VARCHAR," + 
			                   " ANSWERTEXT VARCHAR NOT NULL," +
			                   " FILEPATH VARCHAR)"; 
			      statement.executeUpdate(sql);
			      statement.close();
			      System.out.println("Table QUESTION created successfully");
			} else {
				System.out.println("Table QUESTION found");
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		try {
			c.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		kb = TriviaMaze.KB;
		MazeDB.menu(kb);
	}
	
	//main menu for adding, printing, or deleting questions
	
	private static void menu(Scanner kb) {
		String input;
		
		MazeDB.menuPrint();
		
		input = kb.nextLine();
		
		while(true) {
			switch(input) {
				case "1": MazeDB.addQuestionMenu(kb);
					break;
				case "2": MazeDB.printAllQuestions();
					break;
				case "3": MazeDB.deleteQuestionMenu(kb);
					break;
				case "4": return;
				default:
					break;
			}
			MazeDB.menuPrint();
			input = kb.nextLine();
		}
	}
	
	//prints menu choices for Main Menu
	
	private static void menuPrint() {
		System.out.println("Enter a number 1-4:\r\n"
				+ "1.) Add question\r\n"
				+ "2.) Print all questions\r\n"
				+ "3.) Delete question\r\n"
				+ "4.) Exit database admin");
	}
	
	//menu for adding questions
	
	private static void addQuestionMenu(Scanner kb) {
		MazeDB.addQuestionMenuPrint();
		
		String input = kb.nextLine();
		
		while(true) {
			switch(input) {
				case "1": addTrueFalseQuestion(kb);
					break;
				case "2": addMultipleChoiceQuestion(kb);
					break;
				case "3": addShortAnswerQuestion(kb);
					break;
				case "4": return;
				default:
					break;
			}
			MazeDB.addQuestionMenuPrint();
			input = kb.nextLine();
		}
	}
	
	//prints menu choices for Add Question Menu
	
	private static void addQuestionMenuPrint() {
		System.out.println("Enter a number 1-4:\r\n"
				+ "1.) Add true or false question\r\n"
				+ "2.) Add multiple choice question\r\n"
				+ "3.) Add short answer question\r\n"
				+ "4.) Exit to DB menu");
	}

	//prints all questions in the database to stdout
	
	private static void printAllQuestions() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		try(Connection connection = DriverManager.getConnection(MazeDB.DB_PATH);) {
			connection.setAutoCommit(false);
			try(Statement statement = connection.createStatement();
				ResultSet questions = statement.executeQuery("SELECT * FROM QUESTION;");) {
				while(questions.next()) {
					int questionID = questions.getInt("ID");
					String questionType = questions.getString("QUESTIONTYPE");
					String fileType = questions.getString("FILETYPE");
					String questionText = questions.getString("QUESTIONTEXT");
					String answerText = questions.getString("ANSWERTEXT");
					String filePath = questions.getString("FILEPATH");
					
					//unescape SQL ' char
					questionText = questionText.replaceAll("''", "'");
					
					System.out.println("ID: " + questionID);
					System.out.println("Type: " + questionType);
					System.out.println("Question: " + questionText);
					System.out.println("Answer: " + answerText);
					System.out.println("File Type: " + fileType);
					if(filePath != null) {
						System.out.println("File Path: " + filePath);
					}
					System.out.println("=============================");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	//allows the user to delete a question by ID
	
	private static void deleteQuestionMenu(Scanner kb) {
		String input = "";
		int idToDelete = 0;
		boolean validIntGiven = false;
		int deletionOccurred = 0;
		
		System.out.println("Enter the ID of a question to delete, or \"exit\" to exit:");
		input = kb.nextLine();
		
		while(!input.equals("exit")) {
			validIntGiven = false;
			if(!input.equals("exit")) {
				try {
					idToDelete = Integer.parseInt(input);
					if(idToDelete > 0) {
						validIntGiven = true;
					}
					if(!validIntGiven) {
						System.out.println("Enter a positive integer.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Not a valid integer.");
				}
				if(validIntGiven) {
					try {
						Class.forName("org.sqlite.JDBC");
					} catch (Exception e) {
						System.err.println(e.getClass().getName() + ": " + e.getMessage());
						System.exit(0);
					}
					try(Connection connection = DriverManager.getConnection(MazeDB.DB_PATH);) {
						connection.setAutoCommit(false);
						try(PreparedStatement statement = connection.prepareStatement("DELETE FROM QUESTION WHERE ID = ?");) {
							statement.setInt(1, idToDelete);
							deletionOccurred = statement.executeUpdate();
						} catch (SQLException e) {
							System.err.println(e.getClass().getName() + ": " + e.getMessage());
							System.exit(0);
						}
						if(deletionOccurred > 0) {
							System.out.println("Question was deleted.");
						} else {
							System.out.println("Question was not found.");
						}
						connection.commit();
					} catch (SQLException e) {
						System.err.println(e.getClass().getName() + ": " + e.getMessage());
						System.exit(0);
					}
				}
				System.out.println("Enter the ID of a question to delete, or \"exit\" to exit:");
				input = kb.nextLine();
			}
		}
	}
	
	//series of prompts to add a true/false question
	
	private static void addTrueFalseQuestion(Scanner kb) {
		String questionType;
		String fileType;
		String questionText;
		String answerText;
		String filePath = null;
		
		questionType = "t";
		
		fileType = chooseFileType(kb);
		
		if(fileType == null) {
			return;
		}
		
		questionText = enterQuestionText(fileType, kb);
		
		answerText = enterAnswerTextForTrueFalse(kb);
		
		if(answerText == null) {
			return;
		}
		
		if(!fileType.equals("t")) {
			filePath = enterFilePath(kb);
		}
	    
	    String[] strings = new String[] {questionType, fileType, questionText, answerText, filePath};
		
		MazeDB.addQuestionToDatabase(strings);
	}
	
	//gets the answer string for a true or false question
	
	private static String enterAnswerTextForTrueFalse(Scanner kb) {
		while(true) {
			MazeDB.enterTrueFalseQuestionTextPromptPrint();
			String input = kb.nextLine();
			
			if(input.equalsIgnoreCase("t")) {
				return "t";
			}
			if (input.equalsIgnoreCase("f")) {
				return "f";
			}
			if (input.equalsIgnoreCase("x")) {//cancel and return to previous menu
				return null;
			}
		}
	}
	
	//prints the prompt for an answer to a true or false question
	
	private static void enterTrueFalseQuestionTextPromptPrint() {
		System.out.println("Enter answer for true false question (t or f) or x to exit to Add Question Menu");
	}
	
	//series of prompts to add a multiple choice question
	
	private static void addMultipleChoiceQuestion(Scanner kb) {
		String questionType;
		String fileType;
		String questionText;
		String answerText;
		String filePath = null;
		
		questionType = "m";
		
		fileType = chooseFileType(kb);
		
		if(fileType == null) {
			return;
		}
		
		questionText = enterQuestionText(fileType, kb);
		
		answerText = enterAnswerTextForMultipleChoice(kb);
		if(answerText == null) {
			return;
		}
		
		if(!fileType.equals("t")) {
			filePath = enterFilePath(kb);
		}
	    
	    String[] strings = new String[] {questionType, fileType, questionText, answerText, filePath};
		MazeDB.addQuestionToDatabase(strings);
	}
	
	//gets the answer String for a multiple choice question
	
	private static String enterAnswerTextForMultipleChoice(Scanner kb) {
		while(true) {
			MazeDB.enterMultipleChoiceQuestionTextPromptPrint();
			String input = kb.nextLine();
			
			if(input.equalsIgnoreCase("a")) {
				return "a";
			}
			if (input.equalsIgnoreCase("b")) {
				return "b";
			}
			if (input.equalsIgnoreCase("c")) {
				return "c";
			}
			if (input.equalsIgnoreCase("d")) {
				return "d";
			}
			if (input.equalsIgnoreCase("x")) {
				return null;
			}
		}
	}
	
	//prints the prompt for entering the answer to a multiple choice question
	
	private static void enterMultipleChoiceQuestionTextPromptPrint() {
		System.out.println("Enter answer for multiple choice question\r\n"
						+ "(a, b, c, or d) or x to exit to Add Question Menu");
	}
	
	//series of prompts to add a short answer question
	
	private static void addShortAnswerQuestion(Scanner kb) {
		String questionType;
		String fileType;
		String questionText;
		String answerText;
		String filePath = null;
		
		questionType = "s";
		
		fileType = chooseFileType(kb);
		
		if(fileType == null) {
			return;
		}
		
		questionText = enterQuestionText(fileType, kb);
		
		answerText = enterAnswerTextForShortAnswer(kb);
		if(answerText == null) {
			return;
		}
		
		if(!fileType.equals("t")) {
			filePath = enterFilePath(kb);
		}
	    
	    String[] strings = new String[] {questionType, fileType, questionText, answerText, filePath};
		MazeDB.addQuestionToDatabase(strings);
	}
	
	//gets the answer String for a short answer question
	
	private static String enterAnswerTextForShortAnswer(Scanner kb) {
		
		MazeDB.enterShortAnswerQuestionTextPromptPrint();
		String input = kb.nextLine();
		
		if(input.isEmpty()) {
			return null;
		}
		
		return input;
	}
	
	//prints the prompt for entering the answer to a short answer question
	
	private static void enterShortAnswerQuestionTextPromptPrint() {
		System.out.println("Enter answer for short answer question\r\n"
				+ "or enter blank string to cancel and exit to Add Question Menu");
	}
	
	//takes a string array and creates a new question in the database with it
	
	private static void addQuestionToDatabase(String[] strings) {
		if(strings.length != 5) {
			throw new IllegalArgumentException("String array supplied to addQuestionToDatabase must have 5 elements, given array had " + strings.length);
		}
		
		
		//escape ' for SQL
		for(int i = 0; i < strings.length; i++) {
			if(strings[i] != null) {
				strings[i] = strings[i].replaceAll("'", "''");
			}
		}// end for loop i
		
		
		String questionType = strings[0];
		String fileType = strings[1];
		String questionText = strings[2];
		String answerText = strings[3];
		String filePath = strings[4];
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		try (Connection connection = DriverManager.getConnection(MazeDB.DB_PATH);){
			connection.setAutoCommit(false);
			
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO QUESTION (QUESTIONTYPE,FILETYPE,QUESTIONTEXT,ANSWERTEXT,FILEPATH) values "
				+ "(?, ?, ?, ?, ?)");) {
		
				statement.setString(1, questionType);
				statement.setString(2, fileType);
				statement.setString(3, questionText);
				statement.setString(4, answerText);
				statement.setString(5, filePath);
				
				statement.executeUpdate();
				} catch (Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
					System.exit(0);
				}
			connection.commit();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("SUCCESS: THE FOLLOWING WAS EXECUTED:");
		System.out.println("INSERT INTO QUESTION (QUESTIONTYPE,FILETYPE,QUESTIONTEXT,ANSWERTEXT,FILEPATH) values "
				+ "(" + questionType + "," + fileType + "," + questionText + "," + answerText + "," + filePath + ")");
	}
	
	//prompts for and returns a String representing the relative path and filename for a media file
	
	private static String enterFilePath(Scanner kb) {
		System.out.println("Enter relative path with filename for sound or video file");
		return kb.nextLine();
	}
	
	//gets the String that asks the question and provides possible answers if applicable
	
	private static String enterQuestionText(String fileType, Scanner kb) {
		while(true) {
			MazeDB.enterQuestionTextPromptPrint();
			String input = kb.nextLine();
			
			if(fileType.equals("v") || fileType.equals("s")) {
				if(input.isEmpty()) {
					return null;
				} else {
					return input;
				}
			}
			if(!input.isEmpty()) {
				return input;
			}
		}
	}

	//prints the prompt to enter a question
	
	private static void enterQuestionTextPromptPrint() {
		System.out.println("Enter the question text string (an empty text is only valid for sound and video questions):");
	}

	//gets the String representing the file type
	
	private static String chooseFileType(Scanner kb) {
		MazeDB.chooseFileTypeMenuPrint();
		String input = kb.nextLine();
		
		while(true) {
			switch(input.toLowerCase()) {
				case "t": return "t";//text-only
				case "v": return "v";//video
				case "s": return "s";//sound
				case "x": return null;//cancel and return to previous menu
				default:
					break;
			}
			MazeDB.chooseFileTypeMenuPrint();
			input = kb.nextLine();
		}
	}

	//prints menu choices for fileType
	
	private static void chooseFileTypeMenuPrint() {
		System.out.println("Choose a file type:\r\n"
				+ "t.) Text-only question\r\n"
				+ "v.) Video question\r\n"
				+ "s.) Sound question\r\n"
				+ "x.) Cancel and exit to Add Question menu");
	}
}