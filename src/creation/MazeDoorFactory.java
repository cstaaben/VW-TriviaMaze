package creation;

import maze.MazeDoor;
import maze.MockQuestion;

public class MazeDoorFactory {
	
	private static MazeDoorFactory reference = null;
	
	public static MazeDoorFactory getReference() {
		if(reference == null) {
			reference = new MazeDoorFactory();
		}
		
		return reference;
	}
	
	public MazeDoor getDoor(MockQuestion question) {
		MazeDoor door = new MazeDoor();
		
		door.setQuestion(question);
		
		return door;
	}

}
