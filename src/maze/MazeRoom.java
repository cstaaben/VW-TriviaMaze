package maze;

public class MazeRoom implements MazeObject {
	
	public static final int NUM_DOORS = 4;
	private int availDoors;
	private MazeObject[] doors;
	
	private MazeMediator mediator;
	
	public MazeRoom(int row, int col) {
		this.doors = new MazeDoor[NUM_DOORS];
		
		this.availDoors = 0;
		
		mediator = MazeMediator.getReference();
		
		initializeDoors(row, col);
		
	}
	
	public int getDoorNum() { return this.availDoors; }
	
	public void initializeDoors(int row, int col) {
		
		if(row == 0) {
			this.doors[MazeDirection.NORTH.ordinal()] = new NullMazeDoor();
			this.doors[MazeDirection.SOUTH.ordinal()] = new MazeDoor(MazeDirection.SOUTH);
			this.availDoors++;
		}
		else if(row == mediator.getMazeSize()-1) {
			this.doors[MazeDirection.SOUTH.ordinal()] = new NullMazeDoor();
			this.doors[MazeDirection.NORTH.ordinal()] = new MazeDoor(MazeDirection.NORTH);
			this.availDoors++;
		}
		else {
			this.doors[MazeDirection.NORTH.ordinal()] = new MazeDoor(MazeDirection.NORTH);
			this.doors[MazeDirection.SOUTH.ordinal()] = new MazeDoor(MazeDirection.SOUTH);
			this.availDoors += 2;
		}
		
		if(col == mediator.getMazeSize()-1) {
			this.doors[MazeDirection.EAST.ordinal()] = new NullMazeDoor();
			this.doors[MazeDirection.WEST.ordinal()] = new MazeDoor(MazeDirection.WEST);
			this.availDoors++;
		}
		else if(col == 0) {
			this.doors[MazeDirection.WEST.ordinal()] = new NullMazeDoor();
			this.doors[MazeDirection.EAST.ordinal()] = new MazeDoor(MazeDirection.EAST);
			this.availDoors++;
		}
		else {
			this.doors[MazeDirection.EAST.ordinal()] = new MazeDoor(MazeDirection.EAST);
			this.doors[MazeDirection.WEST.ordinal()] = new MazeDoor(MazeDirection.WEST);
			this.availDoors += 2;
		}
	}
	
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
	
	public boolean questionPrompt(String input) {
		String answer = "";
		
		if(input.toLowerCase().equals("n")) {
			if(!((MazeDoor)doors[MazeDirection.NORTH.ordinal()]).isLocked() && 
					((MazeDoor)doors[MazeDirection.NORTH.ordinal()]).isOpen()) {
				return true;
			}
			
			System.out.println(((MazeDoor)doors[MazeDirection.NORTH.ordinal()]).display());
			answer = MazeTest.KB.nextLine();
			return ((MazeDoor)doors[MazeDirection.NORTH.ordinal()]).isCorrectAnswer(answer);
		}
		else if(input.toLowerCase().equals("e")) {
			if(!((MazeDoor)doors[MazeDirection.EAST.ordinal()]).isLocked() && 
					((MazeDoor)doors[MazeDirection.EAST.ordinal()]).isOpen()) {
				return true;
			}
			
			System.out.println(((MazeDoor)doors[MazeDirection.EAST.ordinal()]).display());
			answer = MazeTest.KB.nextLine();
			return ((MazeDoor)doors[MazeDirection.EAST.ordinal()]).isCorrectAnswer(answer);
		}
		else if(input.toLowerCase().equals("s")) {
			if(!((MazeDoor)doors[MazeDirection.SOUTH.ordinal()]).isLocked() && 
					((MazeDoor)doors[MazeDirection.SOUTH.ordinal()]).isOpen()) {
				return true;
			}
			
			System.out.println(((MazeDoor)doors[MazeDirection.SOUTH.ordinal()]).display());
			answer = MazeTest.KB.nextLine();
			return ((MazeDoor)doors[MazeDirection.SOUTH.ordinal()]).isCorrectAnswer(answer);
		}
		else if(input.toLowerCase().equals("w")) {
			if(!((MazeDoor)doors[MazeDirection.WEST.ordinal()]).isLocked() && 
					((MazeDoor)doors[MazeDirection.WEST.ordinal()]).isOpen()) {
				return true;
			}
			
			System.out.println(((MazeDoor)doors[MazeDirection.WEST.ordinal()]).display());
			answer = MazeTest.KB.nextLine();
			return ((MazeDoor)doors[MazeDirection.WEST.ordinal()]).isCorrectAnswer(answer);
		}
		
		return false;
	}
	
	public char checkDoor(int direction) {
		switch(direction) {
			case 0:
				if(((MazeDoor)doors[MazeDirection.NORTH.ordinal()]).isLocked()) {
					return 'l';
				}
				else if(((MazeDoor)doors[MazeDirection.NORTH.ordinal()]).isOpen()) {
					return 'o';
				}
				break;
			
			case 1:
				if(((MazeDoor)doors[MazeDirection.EAST.ordinal()]).isLocked()) {
					return 'l';
				}
				else if(((MazeDoor)doors[MazeDirection.EAST.ordinal()]).isOpen()) {
					return 'o';
				}
				break;
				
			case 2:
				if(((MazeDoor)doors[MazeDirection.SOUTH.ordinal()]).isLocked()) {
					return 'l';
				}
				else if(((MazeDoor)doors[MazeDirection.SOUTH.ordinal()]).isOpen()) {
					return 'o';
				}
				break;
				
			case 3:
				if(((MazeDoor)doors[MazeDirection.WEST.ordinal()]).isLocked()) {
					return 'l';
				}
				else if(((MazeDoor)doors[MazeDirection.WEST.ordinal()]).isOpen()) {
					return 'o';
				}
				break;
		}
		
		return 'c';
	}

	@Override
	public String display() {
		String result = "There are " + availDoors + " doors. You can move: \n";
		
		for(int i = 0; i < NUM_DOORS; i++) {
			if(!((MazeDoor)doors[i]).getDirection().equalsIgnoreCase("null")) {
				result += "\t-" + ((MazeDoor)doors[i]).getDirection() + "\n";
			}
		}
		
		return result;
	}

}
