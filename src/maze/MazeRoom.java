package maze;

import tests.MazeNavigationTest;
import java.util.EnumMap;

public class MazeRoom implements MazeObject {
	
	public static final int NUM_DOORS = 4;
	private int availDoors;
	private EnumMap<MazeDirection, MazeObject> doors;
	
	public MazeRoom() {
		this.doors = new EnumMap<MazeDirection, MazeObject>(MazeDirection.class);
		
		this.availDoors = 0;
	}
	
	public int getDoorNum() { return this.availDoors; }
	
	public void setDoorNum(int availDoors) {
		this.availDoors = availDoors;
	}
	
	public void insertDoor(MazeDirection md, MazeDoor door) {
		doors.put(md, door);
		availDoors++;
	}
	
	public boolean isValidDoor(String direction) {
		MazeDirection md = MazeDirection.valueOf(direction.toUpperCase());
		
		return (!((MazeDoor)doors.get(md)).isLocked()) || ((MazeDoor)doors.get(md)).isOpen();
	}
	
	public void questionPrompt(MazeDirection direction) {
		String answer = "";
		boolean result = false;
		
		System.out.println(((MazeDoor)doors.get(direction)).display());
		answer = MazeNavigationTest.KB.nextLine();
		
		result = ((MazeDoor)doors.get(direction)).isCorrectAnswer(answer);
		
		if(result) {
			System.out.println("Congratulations! That is correct!");
		}
		else {
			System.out.println("I'm sorry, that is incorrect. This door is now locked.");
		}
		
		((MazeDoor)doors.get(direction)).setLocked(!result);
		((MazeDoor)doors.get(direction)).setOpen(result);
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
		String result = "There are " + availDoors + " doors. You can move: \n";
		
		/*
		for(int i = 0; i < NUM_DOORS; i++) {
			if(!((MazeDoor)doors[i]).getDirection().equalsIgnoreCase("null")) {
				result += "\t-" + ((MazeDoor)doors[i]).getDirection() + "\n";
			}
		}
		*/
		
		return result;
	}

}
