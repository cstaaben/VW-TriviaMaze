package maze;

import states.TriviaMaze;
import java.util.EnumMap;
import java.util.Iterator;

public class MazeRoom implements MazeObject {
	
	public static final int NUM_DOORS = 4;
	private int availDoors;
	private EnumMap<MazeDirection, MazeDoor> doors;
	
	public MazeRoom() {
		this.doors = new EnumMap<MazeDirection, MazeDoor>(MazeDirection.class);
		
		this.availDoors = 0;
	}
	
	public int getDoorNum() { return this.availDoors; }
	
	public Iterator<MazeDoor> doorIterator() {
		return doors.values().iterator();
	}
	
	public void setDoorNum(int availDoors) {
		this.availDoors = availDoors;
	}
	
	public void insertDoor(MazeDirection md, MazeDoor door) {
		doors.put(md, door);
		availDoors++;
	}
	
	public void insertEmptyDoor(MazeDirection md, MazeDoor door) {
		doors.put(md, door);
	}
	
	public boolean isValidDoor(String direction) {
		MazeDirection md = MazeDirection.valueOf(direction.toUpperCase());
		
		return (!doors.get(md).isLocked()) || doors.get(md).isOpen();
	}
	
	public void questionPrompt(MazeDirection direction) {
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
		
		if(result) {
			System.out.println("Congratulations! That is correct!");
		}
		else {
			System.out.println("I'm sorry, that is incorrect. This door is now locked.");
		}
		
		doors.get(direction).setLocked(!result);
		doors.get(direction).setOpen(result);
	}
	
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
				if(((MazeDoor)doors.get(MazeDirection.WEST)).isLocked()) {
					return 'l';
				}
				else if(((MazeDoor)doors.get(MazeDirection.WEST)).isOpen()) {
					return 'o';
				}
				break;
		}
		
		return 'c';
	}

	@Override
	public String display() {
		String result = "There are " + availDoors + " doors. The unlocked doors are facing: \n";
		String direction = "";
		
		for(MazeDirection md : doors.keySet()) {
			if(!((MazeDoor)doors.get(md)).isLocked()) {
				direction = md.toString().charAt(0) + md.toString().substring(1, md.toString().length()).toLowerCase();
				
				result += "\t-" + direction + "\n";
			}
		}
		
		return result;
	}

}
