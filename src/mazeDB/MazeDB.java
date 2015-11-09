/*
 * MazeDB.java
 * Author: Clifton Caleb Jewett
 * Description: Class for a console interface with the SQLite database of trivia questions
 */

package mazeDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MazeDB {
	
	//main method makes sure mazeQuestions.db exists, and creates it otherwise
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		DatabaseMetaData dbm = null;
		ResultSet tables = null;
		Scanner kb;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:src/MazeDB/mazeQuestions.db");
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
				 stmt = c.createStatement();
			      String sql = "CREATE TABLE QUESTION " +
			                   "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
			                   " QUESTIONTYPE CHAR(1) NOT NULL, " + 
			                   " FILETYPE CHAR(1) NOT NULL, " + 
			                   " QUESTIONTEXT VARCHAR," + 
			                   " ANSWERTEXT VARCHAR NOT NULL," +
			                   " FILEPATH VARCHAR)"; 
			      stmt.executeUpdate(sql);
			      stmt.close();
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
		
		kb = new Scanner(System.in);
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
				+ "1. Add question\r\n"
				+ "2. Print all questions\r\n"
				+ "3. Delete question\r\n"
				+ "4. Exit program");
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
				+ "1. Add true or false question\r\n"
				+ "2. Add multiple choice question\r\n"
				+ "3. Add short answer question\r\n"
				+ "4. Exit to main menu");
	}

	private static void printAllQuestions() {
		// TODO Auto-generated method stub
		
	}
	
	private static void deleteQuestionMenu(Scanner kb) {
		// TODO Auto-generated method stub
		
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
		//need to check for SQL injection
		for(int i = 0; i < strings.length; i++) {
			if(strings[i] != null) {
				if(strings[i].trim().equalsIgnoreCase("NULL")){
					System.out.println("Encountered NULL input, returning to previous menu...");
					return;
				}
			}
		}
		
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
		//need to check for SQL injection
		for(int i = 0; i < strings.length; i++) {
			if(strings[i] != null) {
				if(strings[i].trim().equalsIgnoreCase("NULL")){
					System.out.println("Encountered NULL input, returning to previous menu...");
					return;
				}
			}
		}
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
		//need to check for SQL injection
		for(int i = 0; i < strings.length; i++) {
			if(strings[i] != null) {
				if(strings[i].trim().equalsIgnoreCase("NULL")){
					System.out.println("Encountered NULL input, returning to previous menu...");
					return;
				}
			}
		}
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
		String questionType = strings[0];
		String fileType = strings[1];
		String questionText = strings[2];
		String answerText = strings[3];
		String filePath = strings[4];
		
		Connection c = null;
	    Statement stmt = null;
		StringBuilder sb = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:mazeQuestions.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			sb = new StringBuilder("INSERT INTO QUESTION (QUESTIONTYPE,FILETYPE,QUESTIONTEXT,ANSWERTEXT,FILEPATH) ");
			sb.append("VALUES ('");
			sb.append(questionType);
	        sb.append("','");
	        sb.append(fileType);
	        sb.append("',");
	        if(questionText == null) {
	        	sb.append("NULL,'");
	        } else {
	        	sb.append("'" + questionText + "','");
	        }
	        sb.append(answerText);
	        sb.append("',");
	        if(filePath == null) {
	        	sb.append("NULL)");
	        } else {
	        	sb.append("'" + filePath + "')");
	        }
			stmt.executeUpdate(sb.toString());
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		if(sb != null) {
			System.out.println("SUCCESS: THE FOLLOWING WAS EXECUTED:");
			System.out.println(sb.toString());
		}
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
				+ "t. Text-only question\r\n"
				+ "v. Video question\r\n"
				+ "s. Sound question\r\n"
				+ "x. Cancel and exit to Add Question menu");
	}
}
