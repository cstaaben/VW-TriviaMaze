package creation;

import maze.MazeDoor;
import maze.MockQuestion;
import maze.MazeObject;
import maze.MazeDirection;

public class MazeDoorBuilder {
	
	private static MazeDoorBuilder reference = null;
	
	private MazeDoor door;
	
	public static MazeDoorBuilder getReference() {
		if(reference == null) {
			reference = new MazeDoorBuilder();
		}
		
		return reference;
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
		door.setQuestion(new MockQuestion());
		
		return door;
	}

}
