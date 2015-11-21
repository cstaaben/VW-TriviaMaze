package creation;

import maze.*;

public class MazeFactory {
	
	private static MazeFactory reference = null;
	
	private MazeDoorBuilder doorFactory;
	private MazeObject[][] rooms;
	
	private MazeFactory() {		
		doorFactory = MazeDoorBuilder.getReference();
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
		MazeDoor door = null;
		
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0 ; j < rooms[i].length; j++) {
				if((j + 1) < rooms[i].length) {
					door = doorFactory.newDoor()
							.lesserRoom(rooms[i][j])
							.lesserDirection(MazeDirection.EAST)
							.greaterRoom(rooms[i][j+1])
							.greaterDirection(MazeDirection.WEST)
							.buildDoor();
					
					((MazeRoom)rooms[i][j]).insertDoor(MazeDirection.EAST, door);
					((MazeRoom)rooms[i][j+1]).insertDoor(MazeDirection.WEST, door);
				} // end if j+1
				
				if((i + 1) < rooms.length) {
					door = doorFactory.newDoor()
							.lesserRoom(rooms[i][j])
							.lesserDirection(MazeDirection.SOUTH)
							.greaterRoom(rooms[i+1][j])
							.greaterDirection(MazeDirection.NORTH)
							.buildDoor();
				} // end if i+1
			} // end for j
		} // end for i
	}
	
	public Maze getMaze(int size) {
		rooms = new MazeRoom[size][size];
		initRooms();
		linkRooms();
		
		return new Maze(rooms);
	}
	
}
