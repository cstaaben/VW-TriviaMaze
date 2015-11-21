package creation;

import maze.*;

public class MazeFactory {
	
	private static MazeFactory reference = null;
	
	private MazeDoorFactory doorFactory;
	private MazeObject[][] rooms;
	
	private MazeFactory() {		
		doorFactory = MazeDoorFactory.getReference();
	}
	
	public static MazeFactory getReference() {
		if(reference == null) {
			reference = new MazeFactory();
		}
		
		return reference;
	}
	
	private void initRooms() {
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0; j < rooms[i].length; j++) {
				rooms[i][j] = new MazeRoom();
			} // end for j
		} // end for i
	}
	
	private void linkRooms() {
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0 ; j < rooms[i].length; j++) {
				
			} // end for j
		} // end for i
	}
	
	public Maze getMaze(int size) {
		rooms = new MazeRoom[size][size];
		initRooms();
		linkRooms();
		
		return null;
	}
	
}
