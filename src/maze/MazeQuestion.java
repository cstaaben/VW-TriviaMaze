package maze;

import java.io.Serializable;

public class MazeQuestion implements MazeObject, Serializable {

	private static final int POINTS = 1;
	private static final long serialVersionUID = -806054378560451774L;
	private int questionID;
	private char questionType;
	private char fileType;
	private String questionText;
	private String answerText;
	private String filePath;
	
	public MazeQuestion(int newQuestionID, char newQuestionType, char newFileType, String newQuestionText, String newAnswerText, String newFilePath) {
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
