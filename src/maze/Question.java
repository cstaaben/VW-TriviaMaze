package maze;

import jaco.mp3.player.MP3Player;
import java.io.File;
import java.io.Serializable;

public class Question implements MazeObject, Serializable {

	private static final int POINTS = 1;
	private static final long serialVersionUID = -806054378560451774L;
	private int questionID;
	private char questionType;
	private char fileType;
	private String questionText;
	private String answerText;
	private String filePath;
	
	public Question(int newQuestionID, char newQuestionType, char newFileType, String newQuestionText, String newAnswerText, String newFilePath) {
		this.questionID = newQuestionID;
		this.questionType = newQuestionType;
		this.fileType = newFileType;
		this.questionText = newQuestionText;
		this.answerText = newAnswerText;
		this.filePath = newFilePath;
	}
	
	public long getID() { return serialVersionUID; }
	
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
		
		return questionText;
	}
	
	public char getQuestionType() {
		return questionType;
	}
	
	public String getQuestion() {
		return questionText;
	}
	
	public String getAnswer() {
		return answerText;
	}
	
	public char getFileType() {
		return fileType;
	}
	
	public int getQuestionID() {
		return questionID;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public int getPoints() {
		return POINTS;
	}

}
