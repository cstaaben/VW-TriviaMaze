/**
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
	
	private static String DB_PATH = "jdbc:sqlite:src/mazeDB/mazeQuestions.db";
	private static boolean databaseIsLoaded = false;
	private static boolean questionIsAdded = false;
	private static boolean questionIsDeleted = false;
	private static boolean questionIsPrinted = false;
	
	/**
	 * The main method used for the independent database program.
	 * @param args - not used
	 */
	
	public static void main(String args[]) {
		MazeDB.databaseAdministration();
	}
	
	/**
	 * Sets DB_PATH to another path for the rest of the session for testing purposes.
	 * @param newPath - string path for a SQLite db file to be used temporarily for testing
	 */
	
	public static void setDBPathForTest(String newPath) {
		DB_PATH = newPath;
	}
	
	/**
	 * Gets the currently used database path
	 * @return - the currently used database path
	 */
	
	public static String getDBPath() { return MazeDB.DB_PATH; }
	
	/**
	 * Returns a boolean representing whether the database has been loaded.
	 * @return - boolean representing whether the database has been loaded
	 */
	
	public static boolean databaseIsLoaded() { return databaseIsLoaded; }
	
	/**
	 * Returns a boolean representing whether a question has been added this session.
	 * @return - boolean representing whether a question has been added this session
	 */
	
	public static boolean questionIsAdded() { return questionIsAdded; }
	
	/**
	 * Returns a boolean representing whether a question has been deleted this session.
	 * @return boolean representing whether a question has been deleted this session
	 */
	
	public static boolean questionIsDeleted() { return questionIsDeleted; }
	
	/**
	 * Returns a boolean representing whether a question has been printed this session.
	 * @return boolean representing whether a question has been printed this session
	 */
	
	public static boolean questionIsPrinted() { return questionIsPrinted; }
	
	/**
	 * Opens database connection, creates database if not present, and opens editing menu.
	 */
	
	public static void databaseAdministration() {
		Connection c = null;
		Statement statement = null;
		DatabaseMetaData dbm = null;
		ResultSet tables = null;
		
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
			      databaseIsLoaded = true;
			} else {
				System.out.println("Table QUESTION found");
				databaseIsLoaded = true;
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
		
		MazeDB.menu(TriviaMaze.KB);
	}
	
	/**
	 * Main menu functionality for database editing.
	 * @param kb - input Scanner
	 */
	
	private static void menu(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
	
	/**
	 * Prints main menu options
	 */
	
	private static void menuPrint() {
		System.out.println("Enter a number 1-4:\r\n"
				+ "1.) Add question\r\n"
				+ "2.) Print all questions\r\n"
				+ "3.) Delete question\r\n"
				+ "4.) Exit database admin");
	}
	
	/**
	 * Menu prompt for adding questions.
	 * @param kb - input Scanner
	 */
	
	private static void addQuestionMenu(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
	
	/**
	 * Prints menu for adding questions.
	 */
	
	private static void addQuestionMenuPrint() {
		System.out.println("Enter a number 1-4:\r\n"
				+ "1.) Add true or false question\r\n"
				+ "2.) Add multiple choice question\r\n"
				+ "3.) Add short answer question\r\n"
				+ "4.) Exit to DB menu");
	}
	
	/**
	 * Prints all questions in the database.
	 */
	
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
					questionIsPrinted = true;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	/**
	 * Prompts the user to delete a question by ID.
	 * @param kb - input Scanner
	 */
	
	private static void deleteQuestionMenu(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
							questionIsDeleted = true;
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
	
	/**
	 * Prompts the user to enter the parameters for a true or false question.
	 * @param kb - input Scanner
	 */
	
	private static void addTrueFalseQuestion(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
			filePath = enterFilePath(kb, fileType);
		}
	    String[] strings = new String[] {questionType, fileType, questionText, answerText, filePath};
		MazeDB.addQuestionToDatabase(strings);
	}
	
	//gets the answer string for a true or false question
	
	/**
	 * Prompts user for true or false questions answer text
	 * @param kb - input Scanner
	 * @return - answer text for true or false question
	 */
	
	private static String enterAnswerTextForTrueFalse(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
	
	/**
	 * Prints the prompt for an answer to a true or false question
	 */
	
	private static void enterTrueFalseQuestionTextPromptPrint() {
		System.out.println("Enter answer for true false question (t or f) or x to exit to Add Question Menu");
	}
	
	/**
	 * Prompts user for the parameters of a new multiple choice question.
	 * @param kb - input Scanner
	 */
	
	private static void addMultipleChoiceQuestion(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
			filePath = enterFilePath(kb, fileType);
		}
	    String[] strings = new String[] {questionType, fileType, questionText, answerText, filePath};
		MazeDB.addQuestionToDatabase(strings);
	}
	
	//gets the answer String for a multiple choice question
	
	/**
	 * Prompts user for the answer to a multiple choice question.
	 * @param kb - input Scanner
	 * @return - returns answer String for a new multiple choice question
	 */
	
	private static String enterAnswerTextForMultipleChoice(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
	
	/**
	 * Prints prompt for multiple choice answer.
	 */
	
	private static void enterMultipleChoiceQuestionTextPromptPrint() {
		System.out.println("Enter answer for multiple choice question\r\n"
						+ "(a, b, c, or d) or x to exit to Add Question Menu");
	}
	
	/**
	 * Prompts user for the parameters for a new short answer question.
	 * @param kb - input Scanner
	 */
	
	private static void addShortAnswerQuestion(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
			filePath = enterFilePath(kb, fileType);
		}
	    String[] strings = new String[] {questionType, fileType, questionText, answerText, filePath};
		MazeDB.addQuestionToDatabase(strings);
	}
	
	//gets the answer String for a short answer question
	
	/**
	 * Prompts the user for a short answer String.
	 * @param kb - input Scanner
	 * @return - String of answer text for a short answer question
	 */
	
	private static String enterAnswerTextForShortAnswer(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
		MazeDB.enterShortAnswerQuestionTextPromptPrint();
		String input = kb.nextLine();
		if(input.isEmpty()) {
			return null;
		}
		return input;
	}
	
	/**
	 * Prints the prompt for a short answer String.
	 */
	
	private static void enterShortAnswerQuestionTextPromptPrint() {
		System.out.println("Enter answer for short answer question\r\n"
				+ "or enter blank string to cancel and exit to Add Question Menu");
	}
	
	/**
	 * Adds a question to the database.
	 * @param strings - an array of String parameters for the new question
	 */
	
	private static void addQuestionToDatabase(String[] strings) {
		if(strings == null) {
			throw new RuntimeException("null String[] strings to be used to form question to add to database");
		}
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
		questionIsAdded = true;
		System.out.println("SUCCESS: THE FOLLOWING WAS EXECUTED:");
		System.out.println("INSERT INTO QUESTION (QUESTIONTYPE,FILETYPE,QUESTIONTEXT,ANSWERTEXT,FILEPATH) values "
				+ "(" + questionType + "," + fileType + "," + questionText + "," + answerText + "," + filePath + ")");
	}
	
	/**
	 * Prompts the user for a filepath for a media file for a question.
	 * @param kb - input Scanner
	 * @param fileType - String representing the type of file
	 * @return - String representing the filepath and filename of the media file
	 */
	
	private static String enterFilePath(Scanner kb, String fileType) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
		System.out.println("Enter path with filename for MP3 sound or MP4 video file");
		if(fileType.equals("s")) { //if sound
			System.out.println("srs/maze/mp3/<filename>.mp3 is recommended for sound files");
			return MazeDB.getMP3Path();
		} else if (fileType.equals("v")) { //if video
			System.out.println("srs/maze/mp4/<filename>.mp4 is recommended for video files");
			return MazeDB.getMP4Path();
		} else {
			throw new IllegalArgumentException("only fileType \"v\" or \"s\" should use enterFilePath");
		}
	}
	
	/**
	 * Prompts the user for a string ending with .mp3
	 * @return - a String ending with ".mp3"
	 */
	
	private static String getMP3Path() {
		boolean validSuffix = false;
		String mp3Path = "";
		while(!validSuffix) {
			mp3Path = TriviaMaze.KB.nextLine();
			String[] strings = mp3Path.split("\\."); //splits on ., which must be escaped
			if(strings.length > 0 && strings[strings.length - 1].equalsIgnoreCase("mp3")) {
				validSuffix = true;
			}
			if(!validSuffix) {
				System.out.println("File name must have .mp3 extension");
			}
		}
		return mp3Path;
	}
	
	/**
	 * Prompts the user for a string ending with .mp4
	 * @return - a String ending with ".mp4"
	 */
	
	private static String getMP4Path() {
		boolean validSuffix = false;
		String mp4Path = "";
		while(!validSuffix) {
			mp4Path = TriviaMaze.KB.nextLine();
			String[] strings = mp4Path.split("\\."); //splits on ., which must be escaped
			if(strings.length > 0 && strings[strings.length - 1].equalsIgnoreCase("mp4")) {
				validSuffix = true;
			}
			if(!validSuffix) {
				System.out.println("File name must have .mp4 extension");
			}
		}
		return mp4Path;
	}
	
	/**
	 * Prompts the user for the question text for a question object.
	 * @param fileType - String representing the media file type of the question.
	 * @param kb - input Scanner
	 * @return - question text String
	 */
	
	private static String enterQuestionText(String fileType, Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
		if(fileType == null) {
			throw new RuntimeException("null fileType provided to enterQuestionText()");
		}
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
	
	/**
	 * Prints the prompt for question text.
	 */
	
	private static void enterQuestionTextPromptPrint() {
		System.out.println("Enter the question text string (an empty text is only valid for sound and video questions):");
	}

	//gets the String representing the file type
	
	/**
	 * Prompts user for file type String
	 * @param kb - input Scanner
	 * @return - "t" for text-only, "v" for video, or "s" for sound (or null for cancel)
	 */
	
	private static String chooseFileType(Scanner kb) {
		if(kb == null) {
			throw new RuntimeException("null Scanner kb");
		}
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
	
	/**
	 * Prints prompt for file type.
	 */
	
	private static void chooseFileTypeMenuPrint() {
		System.out.println("Choose a file type:\r\n"
				+ "t.) Text-only question\r\n"
				+ "v.) Video question\r\n"
				+ "s.) Sound question\r\n"
				+ "x.) Cancel and exit to Add Question menu");
	}
}
