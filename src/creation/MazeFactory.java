/**
 * MazeFactory.java
 * Author: Corbin Staaben
 * Description: MazeFactory creates a 2D array of MazeRooms, links them together through MazeDoors (using a
 * MazeDoorBuilder), and inserts the doors used to link the rooms together into the MazeRoom. It also implements a
 * Singleton pattern to eliminate duplicate instances of the class in a nonconcurrent setting.
 */
package creation;

import maze.*;

import java.io.Serializable;

public class MazeFactory implements Serializable {
	
	private static MazeFactory reference = null;
	public static final int MAX_SIZE = 10;
	public static final int MIN_SIZE = 2;
	
	private MazeDoorBuilder doorFactory;
	private MazeRoom[][] rooms;

	/**
	 * Private constructor for internal use in obtaining a reference to this MazeFactory; it also obtains a
	 * reference to the MazeDoorBuilder instance for instantiating and "building" MazeDoors
	 */

	private MazeFactory() {		
		doorFactory = MazeDoorBuilder.getReference();
	}

	/**
	 * Implementation of the Singleton pattern. It is a static method used to obtain a reference to this MazeFactory
	 * @return - a reference to this MazeFactory
	 */
	public static MazeFactory getReference() {
		if(reference == null) {
			reference = new MazeFactory();
		}
		
		return reference;
	}

	/**
	 * A helper method to initialize all MazeRooms in the 2D array
	 */
	private void initRooms() {
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0; j < rooms[i].length; j++) {
				rooms[i][j] = new MazeRoom();
			} // end for j
		} // end for i
	}

	/**
	 * A helper method to link two rooms together and give each room a reference to the door that links them
	 */
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

	/**
	 * Method called from other classes to obtain a new Maze object of the provided size
	 * @param size - the desired size of the square array (i.e. of the nxn array)
	 * @return - a created and assembled Maze object
	 */
	public Maze getMaze(int size) {
		rooms = new MazeRoom[size][size];
		initRooms();
		
		doorFactory.loadSize(computeDoorNum(size));
		linkRooms();
		
		return new Maze(rooms);
	}

	/**
	 * A helper method to compute the number of questions, based on the size of the Maze, necessary to fill all
	 * MazeDoor objects in the Maze object
	 * @param s - the size of the square Maze object
	 * @return - the computed number corresponding to the number of questions necessary to "fill" the Maze object
	 */
	private int computeDoorNum(int s) {
		return (2 * (s * (s - 1)));
	}
	
}
