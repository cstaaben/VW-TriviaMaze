package maze;

<<<<<<< HEAD:src/maze/Question.java
import jaco.mp3.player.MP3Player;
import java.io.File;
import java.io.Serializable;
=======
import java.util.StringTokenizer;
>>>>>>> b4fab4cc8f8f39baac49bc75c1e9b29aebdfd0b5:src/maze/MazeQuestion.java

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
<<<<<<< HEAD:src/maze/Question.java
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
=======
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
>>>>>>> b4fab4cc8f8f39baac49bc75c1e9b29aebdfd0b5:src/maze/MazeQuestion.java
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
