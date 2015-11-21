package maze;

import java.io.Serializable;

public class MockQuestion implements MazeObject, Serializable {

	private static final long serialVersionUID = -806054378560451774L;
	private String question;
	private String answer;
	private char questionType;
	private int points;
	
	public MockQuestion() {
		question = getQuestion();
		questionType = getQuestionType();
		answer = getAnswer();
		this.points = getPoints();
	}
	
	public long getID() { return serialVersionUID; }
	
	@Override
	public String display() {
		return question + " " + answer + " " + questionType + " " + points;
	}
	
	private char getQuestionType() {
		return 'm';
	}
	
	private String getQuestion() {
		return "Question";
	}
	
	public void setQuestionType(char type) {
		this.questionType = type;
	}
	
	public String getAnswer() {
		return "Answer";
	}
	
	public int getPoints() {
		return 5;
	}

}
