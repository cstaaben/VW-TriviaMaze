/**
 * MazeQuestion.java
 * Author: Caleb Jewett & Corbin Staaben
 * Description: MazeQuestion contains a question from the database, the correct answer, the points the question
 * is worth, the type of question, the type of file associated with the question, and the file path to the file
 * if the question is an audio or video question.
 */
package maze;

import jaco.mp3.player.MP3Player;
import tests.Question;

import java.io.File;
import java.io.Serializable;

import java.util.StringTokenizer;

public class MazeQuestion implements MazeObject {

	private static final int POINTS = 1;
	private static final long serialVersionUID = -806054378560451774L;
	private static final int LINE_WIDTH = 70;
	
	private int questionID;
	private char questionType;
	private char fileType;
	private String questionText;
	private String answerText;
	private String filePath;

	/**
	 * Constructor that sets the question ID, the question type, file type, question, answer, and file path
	 * @param newQuestionID - this MazeQuestion's ID
	 * @param newQuestionType - the type of this question (multiple choice, short answer, t/f, etc.)
	 * @param newFileType - the type of file associated with this question
	 * @param newQuestionText - The text of the question itself
	 * @param newAnswerText - the correct answer
	 * @param newFilePath - path to the file associated with this question
	 */

	public MazeQuestion(int newQuestionID, char newQuestionType, char newFileType, String newQuestionText, String newAnswerText, String newFilePath) {
		this.questionID = newQuestionID;
		this.questionType = newQuestionType;
		this.fileType = newFileType;
		this.questionText = newQuestionText;
		this.answerText = newAnswerText;
		this.filePath = newFilePath;
	}

	/**
	 * Retrieve the unique ID
	 * @return - the unique serial version ID
	 */
	public long getUID() { return serialVersionUID; }

	/**
	 * Generate a String representation of the question
	 * @return - the String representation of the question (if applicable)
	 */
	@Override
	public String display() {
		if(fileType == 's') {
			new MP3Player(new File(filePath)).play();
		} if (fileType == 'v') {
			String command = "java -cp ./bin maze.VideoPlayer ";
			command = command + this.getFilePath();
			try {
				Process videoProcess = Runtime.getRuntime().exec(command);
			} catch (Exception e) {
				System.out.println("failed attempt to play video with command: " + command);
				e.printStackTrace();
			}
		}
		
		String result = "", line = "", s = "";
		StringTokenizer tokenizer = new StringTokenizer(questionText);
		
		while(result.length() < questionText.length() && tokenizer.hasMoreTokens()) {
					
			while(line.length() < LINE_WIDTH && tokenizer.hasMoreTokens()) {
				s = tokenizer.nextToken(" ") + " ";
				
				if(s.length() + line.length() <= LINE_WIDTH) {
					line += s;
				}
				else {
					result += line + "\n";
					line = s;
				} // end else
			} // end line.length()
			
		} // end result.length()
		
		result += line;
		
		return result;
	}

	/**
	 * Retrieve the type of question
	 * @return - character representing the question type
	 */
	public char getQuestionType() {
		return questionType;
	}

	/**
	 * Retrieve the question itself
	 * @return - the question text
	 */
	public String getQuestion() {
		return questionText;
	}

	/**
	 * Retrieve the correct answer to the question
	 * @return - the correct answer
	 */
	public String getAnswer() {
		return answerText;
	}

	/**
	 * Retrieve the type of file associated with the question
	 * @return - a character representing the type of file
	 */
	public char getFileType() {
		return fileType;
	/**
	 * Retrieve the ID given to this question
	 * @return
	 */
	public int getQuestionID() { return questionID; }

	/**
	 * Retrieve the path to the file associated with this question
	 * @return - the String representation of the file path
	 */
	public String getFilePath() { return filePath;	}

	/**
	 * Retrieve the number of points this question is worth
	 * @return - the integer value of the points this question is worth
	 */
	public int getPoints() {	return POINTS;	}
}
