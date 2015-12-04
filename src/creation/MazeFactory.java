package creation;

import maze.*;

public class MazeFactory {
	
	private static MazeFactory reference = null;
	public static final int MAX_SIZE = 10;
	public static final int MIN_SIZE = 2;
	
	private MazeDoorBuilder doorFactory;
	private MazeRoom[][] rooms;
	
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
		MazeDoor door;
		
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0 ; j < rooms[i].length; j++) {
				if(j == 0) {
					door = doorFactory.newEmptyDoor()
							.greaterDirection(MazeDirection.WEST)
							.greaterRoom(rooms[i][j])
							.buildEmptyDoor();
					
					rooms[i][j].insertEmptyDoor(MazeDirection.WEST, door);
				}
				
				if((j + 1) < rooms[i].length) {
					door = doorFactory.newDoor()
							.lesserRoom(rooms[i][j])
							.lesserDirection(MazeDirection.EAST)
							.greaterRoom(rooms[i][j+1])
							.greaterDirection(MazeDirection.WEST)
							.buildDoor();
					
					rooms[i][j].insertDoor(MazeDirection.EAST, door);
					rooms[i][j+1].insertDoor(MazeDirection.WEST, door);
				} // end if j+1
				else if((j + 1) == rooms[i].length) {
					door = doorFactory.newEmptyDoor()
							.lesserDirection(MazeDirection.EAST)
							.lesserRoom(rooms[i][j])
							.buildEmptyDoor();
					
					rooms[i][j].insertEmptyDoor(MazeDirection.EAST, door);
				}
				
				if(i == 0) {
					door = doorFactory.newEmptyDoor()
							.greaterDirection(MazeDirection.NORTH)
							.greaterRoom(rooms[i][j])
							.buildEmptyDoor();
					
					rooms[i][j].insertEmptyDoor(MazeDirection.NORTH, door);
				}
				
				if((i + 1) < rooms.length) {
					door = doorFactory.newDoor()
							.lesserRoom(rooms[i][j])
							.lesserDirection(MazeDirection.SOUTH)
							.greaterRoom(rooms[i+1][j])
							.greaterDirection(MazeDirection.NORTH)
							.buildDoor();
					
					rooms[i][j].insertDoor(MazeDirection.SOUTH, door);
					rooms[i+1][j].insertDoor(MazeDirection.NORTH, door);
				} // end if i+1
				else if((i + 1) == rooms.length) {
					door = doorFactory.newEmptyDoor()
							.lesserDirection(MazeDirection.SOUTH)
							.lesserRoom(rooms[i][j])
							.buildEmptyDoor();
					
					rooms[i][j].insertEmptyDoor(MazeDirection.SOUTH, door);
				}
			} // end for j
		} // end for i
	}
	
	public Maze getMaze(int size) {
		rooms = new MazeRoom[size][size];
		initRooms();
		
		doorFactory.loadSize(computeDoorNum(size));
		linkRooms();
		
		return new Maze(rooms);
	}

	private int computeDoorNum(int s) {
		return (2 * (s * (s - 1)));
	}
	
}
