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
	
	/*
	public boolean isValidDoor(String direction) {
		if(direction.toLowerCase().equals("n")) {
			return !((MazeDoor)doors[MazeDirection.NORTH.ordinal()]).getDirection().toLowerCase().equals("null") 
					&& !((MazeDoor)doors[MazeDirection.NORTH.ordinal()]).isLocked();
		}
		else if(direction.toLowerCase().equals("e")) {
			return !((MazeDoor)doors[MazeDirection.EAST.ordinal()]).getDirection().toLowerCase().equals("null")
					&& !((MazeDoor)doors[MazeDirection.EAST.ordinal()]).isLocked();
		}
		else if(direction.toLowerCase().equals("s")) {
			return !((MazeDoor)doors[MazeDirection.SOUTH.ordinal()]).getDirection().toLowerCase().equals("null")
					&& !((MazeDoor)doors[MazeDirection.SOUTH.ordinal()]).isLocked();
		}
		else if(direction.toLowerCase().equals("w")) {
			return !((MazeDoor)doors[MazeDirection.WEST.ordinal()]).getDirection().toLowerCase().equals("null")
					&& !((MazeDoor)doors[MazeDirection.WEST.ordinal()]).isLocked();
		}
		
		return false;
	}
	*/
	
	public boolean questionPrompt(String input) {
		String answer = "";
		
		if(input.toLowerCase().equals("n")) {
			if(!((MazeDoor)doors.get(MazeDirection.NORTH)).isLocked() && 
					((MazeDoor)doors.get(MazeDirection.NORTH)).isOpen()) {
				return true;
			}
			
			System.out.println(((MazeDoor)doors.get(MazeDirection.NORTH)).display());
			answer = MazeNavigationTest.KB.nextLine();
			return ((MazeDoor)doors.get(MazeDirection.NORTH)).isCorrectAnswer(answer);
		}
		else if(input.toLowerCase().equals("e")) {
			if(!((MazeDoor)doors.get(MazeDirection.EAST)).isLocked() && 
					((MazeDoor)doors.get(MazeDirection.EAST)).isOpen()) {
				return true;
			}
			
			System.out.println(((MazeDoor)doors.get(MazeDirection.EAST)).display());
			answer = MazeNavigationTest.KB.nextLine();
			return ((MazeDoor)doors.get(MazeDirection.EAST)).isCorrectAnswer(answer);
		}
		else if(input.toLowerCase().equals("s")) {
			if(!((MazeDoor)doors.get(MazeDirection.SOUTH)).isLocked() && 
					((MazeDoor)doors.get(MazeDirection.SOUTH)).isOpen()) {
				return true;
			}
			
			System.out.println(((MazeDoor)doors.get(MazeDirection.SOUTH)).display());
			answer = MazeNavigationTest.KB.nextLine();
			return ((MazeDoor)doors.get(MazeDirection.SOUTH)).isCorrectAnswer(answer);
		}
		else if(input.toLowerCase().equals("w")) {
			if(!((MazeDoor)doors.get(MazeDirection.WEST)).isLocked() && 
					((MazeDoor)doors.get(MazeDirection.WEST)).isOpen()) {
				return true;
			}
			
			System.out.println(((MazeDoor)doors.get(MazeDirection.WEST)).display());
			answer = MazeNavigationTest.KB.nextLine();
			return ((MazeDoor)doors.get(MazeDirection.WEST)).isCorrectAnswer(answer);
		}
		
		return false;
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
