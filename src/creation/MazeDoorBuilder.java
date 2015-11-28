package creation;

import maze.MazeDoor;
import maze.Question;
import maze.QuestionManager;
import maze.MazeObject;

import java.util.Iterator;

import maze.MazeDirection;

public class MazeDoorBuilder {
	
	private static MazeDoorBuilder reference = null;
	private static QuestionManager qm;
	private static Iterator<Question> questions;
	
	private MazeDoor door;
	
	private MazeDoorBuilder() {
		qm = QuestionManager.getInstance();
	}
	
	public static MazeDoorBuilder getReference() {
		if(reference == null) {
			reference = new MazeDoorBuilder();
		}
		
		return reference;
	}
	
	public void loadSize(int size) {
		qm.initializeQuestionHashMap(size);
		questions = qm.getQuestionIterator();
	}
	
	public MazeDoorBuilder newDoor() {
		this.door = new MazeDoor();
		
		return this;
	}
	
	public MazeDoorBuilder lesserDirection(MazeDirection md) {
		door.setLesserDirection(md);
		
		return this;
	}
	
	public MazeDoorBuilder lesserRoom(MazeObject room) {
		door.setLesserRoom(room);
		
		return this;
	}
	
	public MazeDoorBuilder greaterDirection(MazeDirection md) {
		door.setGreaterDirection(md);
		
		return this;
	}
	
	public MazeDoorBuilder greaterRoom(MazeObject room) {
		door.setGreaterRoom(room);
		
		return this;
	}
	
	public MazeDoor buildDoor() {
		door.setQuestion(questions.next());
		
		return door;
	}

}
