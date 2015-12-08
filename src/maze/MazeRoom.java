/**
 * MazeRoom.java
 * Author: Corbin Staaben
 * Description: MazeRoom holds the doors that lead out of this room, and has the methods to prompt the user
 * with the question needed to advance through the chosen door
 */
package maze;

import states.TriviaMaze;
import java.util.EnumMap;
import java.util.Iterator;

public class MazeRoom implements MazeObject {
	
	private static final long serialVersionUID = -5854710716356049106L;
	public static final int NUM_DOORS = 4;
	private static final int INCORRECT_ANSWER = 0;
	private int availDoors;
	private EnumMap<MazeDirection, MazeDoor> doors;

	/**
	 * Constructor that initializes the EnumMap used to contain the doors and the count of available doors
	 */

	public MazeRoom() {
		this.doors = new EnumMap<MazeDirection, MazeDoor>(MazeDirection.class);
		
		this.availDoors = 0;
	}

	/**
	 * Returns the number of doors in this room that link to another room
	 * @return - the number of linked doors
	 */
	public int getDoorNum() { return this.availDoors; }

	/**
	 * Get an Iterator to move over the doors in this room
	 * @return - an Iterator of the MazeDoors in this room
	 */
	public Iterator<MazeDoor> doorIterator() {
		return doors.values().iterator();
	}

	/**
	 * Set the number of doors linked to other rooms
	 * @param availDoors - the number of doors that lead to other rooms
	 */
	public void setDoorNum(int availDoors) {
		this.availDoors = availDoors;
	}

	/**
	 * Add a door to the EnumMap in the corresponding direction and increment the number of available doors
	 * @param md - the direction the door is to be inserted
	 * @param door - the door to be inserted
	 */
	public void insertDoor(MazeDirection md, MazeDoor door) {
		doors.put(md, door);
		availDoors++;
	}

	/**
	 * Add a door to the EnumMap in the corresponding direction without incrementing the number of available doors
	 * @param md - the direction the door is to be inserted
	 * @param door - the door to be inserted
	 */
	public void insertEmptyDoor(MazeDirection md, MazeDoor door) {
		doors.put(md, door);
	}

	/**
	 * Checks if the door in the given direction is locked or open
	 * @param direction - String representing the direction the user wants to move
	 * @return - true if the door is not locked or if it is open, false otherwise
	 */
	public boolean isValidDoor(String direction) {
		MazeDirection md = MazeDirection.valueOf(direction.toUpperCase());
		
		return (!doors.get(md).isLocked()) || doors.get(md).isOpen();
	}

	/**
	 * Prompt the user with the question stored in the direction they wish to go, and return the number of points
	 * the user earned. All exceptions are handled in the method.
	 * @param direction - the direction the user wishes to go
	 * @return - an integer representing the number of points the user has earned
	 */
	public int questionPrompt(MazeDirection direction) {
		String answer = "";
		boolean result = false;
		
		System.out.println(doors.get(direction).display());
		try {
			answer = TriviaMaze.KB.nextLine();
		}
		catch(Exception e) {
			System.out.println("Invalid answer. Please try again.");
		}
		
		result = doors.get(direction).isCorrectAnswer(answer);
		
		doors.get(direction).setLocked(!result);
		doors.get(direction).setOpen(result);
		
		if(result) {
			System.out.println("Congratulations! That is correct!");
			return doors.get(direction).getDoorPoints();
		}
		else {
			System.out.println("I'm sorry, that is incorrect. This door is now locked.");
			return INCORRECT_ANSWER;
		}
	}

	/**
	 * Determine a character denoting the door's status of being either open or locked
	 * @param direction - an integer corresponding to a direction
	 * @return - a character representing the door's status
	 */
	public char checkDoor(int direction) {
		switch(direction) {
			case 0:
				if(((MazeDoor)doors.get(MazeDirection.NORTH)).isLocked()) {
					return 'l';
				}
				else if(((MazeDoor)doors.get(MazeDirection.NORTH)).isOpen()) {
					return 'o';
				}
				break;
			
			case 1:
				if(((MazeDoor)doors.get(MazeDirection.EAST)).isLocked()) {
					return 'l';
				}
				else if(((MazeDoor)doors.get(MazeDirection.EAST)).isOpen()) {
					return 'o';
				}
				break;
				
			case 2:
				if(((MazeDoor)doors.get(MazeDirection.SOUTH)).isLocked()) {
					return 'l';
				}
				else if(((MazeDoor)doors.get(MazeDirection.SOUTH)).isOpen()) {
					return 'o';
				}
				break;
				
			case 3:
				if(doors.get(MazeDirection.WEST).isLocked()) {
					return 'l';
				}
				else if(doors.get(MazeDirection.WEST).isOpen()) {
					return 'o';
				}
				break;
		}
		
		return 'c';
	}

	/**
	 * Generate a text representation of the room displaying the number of available doors, and the directions you
	 * can move
	 * @return - the String representation of the room
	 */
	@Override
	public String display() {
		String result = "There are " + availDoors + " doors. The unlocked doors are facing: \n";
		String direction = "";
		
		for(MazeDirection md : doors.keySet()) {
			if(!doors.get(md).isLocked()) {
				direction = md.toString().charAt(0) + md.toString().substring(1, md.toString().length()).toLowerCase();
				
				result += "\t-" + direction + "\n";
			}
		}
		
		return result;
	}

	/**
	 * Determines if the door has been previously selected. If it has, the door is either open or locked.
	 * @param direction - the direction the user wishes to move
	 * @return - true if the door is open and not locked; false otherwise
	 */
	public boolean isPreviouslyVisited(String direction) {
		MazeDirection md = MazeDirection.valueOf(direction.toUpperCase());
		
		return doors.get(md).isOpen() && !doors.get(md).isLocked();
	}

}
