package creation;

import maze.*;

public class MazeFactory {
	
	private static MazeFactory reference = null;
	
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
		MazeDoor door = null;
		NullMazeRoom emptyRoom = null;
		
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0 ; j < rooms[i].length; j++) {
				if(j == 0) {
					emptyRoom = new NullMazeRoom();
					
					door = doorFactory.newDoor()
							.lesserRoom(emptyRoom)
							.lesserDirection(MazeDirection.EAST)
							.greaterRoom(rooms[i][j])
							.greaterDirection(MazeDirection.WEST)
							.buildEmptyDoor();
					
					emptyRoom.setDoor(door);
					((MazeRoom)rooms[i][j]).insertEmptyDoor(MazeDirection.WEST, door);
				}
				
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
				else if((j + 1) == rooms[i].length) {
					emptyRoom = new NullMazeRoom();
					
					door = doorFactory.newDoor()
							.lesserRoom(rooms[i][j])
							.lesserDirection(MazeDirection.EAST)
							.greaterDirection(MazeDirection.WEST)
							.greaterRoom(emptyRoom)
							.buildEmptyDoor();
					
					emptyRoom.setDoor(door);
					((MazeRoom)rooms[i][j]).insertEmptyDoor(MazeDirection.EAST, door);
				}
				
				if(i == 0) {
					emptyRoom = new NullMazeRoom();
					
					door = doorFactory.newDoor()
							.lesserRoom(emptyRoom)
							.lesserDirection(MazeDirection.SOUTH)
							.greaterRoom(rooms[i][j])
							.greaterDirection(MazeDirection.NORTH)
							.buildEmptyDoor();
					
					emptyRoom.setDoor(door);
					((MazeRoom)rooms[i][j]).insertEmptyDoor(MazeDirection.NORTH, door);
				}
				
				if((i + 1) < rooms.length) {
					door = doorFactory.newDoor()
							.lesserRoom(rooms[i][j])
							.lesserDirection(MazeDirection.SOUTH)
							.greaterRoom(rooms[i+1][j])
							.greaterDirection(MazeDirection.NORTH)
							.buildDoor();
					
					((MazeRoom)rooms[i][j]).insertDoor(MazeDirection.SOUTH, door);
					((MazeRoom)rooms[i+1][j]).insertDoor(MazeDirection.NORTH, door);
				} // end if i+1
				else if((i + 1) == rooms.length) {
					emptyRoom = new NullMazeRoom();
					
					door = doorFactory.newDoor()
							.lesserRoom(rooms[i][j])
							.lesserDirection(MazeDirection.SOUTH)
							.greaterRoom(emptyRoom)
							.greaterDirection(MazeDirection.NORTH)
							.buildEmptyDoor();
					
					emptyRoom.setDoor(door);
					((MazeRoom)rooms[i][j]).insertEmptyDoor(MazeDirection.SOUTH, door);
				}
			} // end for j
		} // end for i
	}
	
	public Maze getMaze(int size) {
		rooms = new MazeRoom[size][size];
		initRooms();
		
		doorFactory.loadSize(size);
		linkRooms();
		
		return new Maze(rooms);
	}
	
}
