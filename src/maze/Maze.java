package maze;

import java.util.Random;

import mazeTests.MazeNavigationTest;

public class Maze implements MazeObject {
	
	public static final int MAX_SIZE = 10;
	
	private int size;
	private MazeObject[][] maze;
	private MazeCoordinates start;
	private MazeCoordinates exit;
	private MazeCoordinates current;
	private Random random = new Random();
	
	private static Maze reference = null;
	
	private Maze(int size) {
		this.size = size;
		
		MazeMediator.createReference(this);
		
		initMaze();
		
		start = getStartCoord();
		exit = getExitCoord();
		current = initCurrentCoord();
	}
	
	public static Maze getMaze(int size) {
		if(reference == null) {
			reference = new Maze(size);
		}
		
		return reference;
	}
	
	public int getSize() { return this.size; }
	
	private void initMaze() {
		maze = new MazeObject[size][size];
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				maze[i][j] = new MazeRoom(i, j);
			}
		}
	}

	public String display() {
		String result = "";
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				for(int k = 0; k < MazeRoom.NUM_DOORS; k++) {
					result += getRoomLayout(((MazeRoom)maze[i][j]).checkDoor(k), k);
				} // end for k
			} // end for j
		} // end for i
		
		return result;
	}
	
	private String getRoomLayout(char status, int direction) {
		switch(direction) {
			case 0: // North
				switch(status) {
				case 'c':
					
					break;
				case 'o':
					
					break;
				case 'l':
					
					break;
				}
				break;
				
			case 1: // East
				switch(status) {
				case 'c':
					
					break;
				case 'o':
					
					break;
				case 'l':
					
					break;
				}
				break;
				
			case 2: // South
				switch(status) {
				case 'c':
					
					break;
				case 'o':
					
					break;
				case 'l':
					
					break;
				}
				break;
				
			case 3: // West
				switch(status) {
				case 'c':
					
					break;
				case 'o':
					
					break;
				case 'l':
					
					break;
				}
				break;
		} // end switch direction
		
		return "";
	}
	
	private MazeCoordinates getStartCoord() {
		int row = 0, col = 0;
		
		row = random.nextInt(this.size);
		col = random.nextInt(this.size);
		
		return new MazeCoordinates(row, col);
	}
	
	private MazeCoordinates getExitCoord() {
		int row = 0, col = 0;
		
		row = random.nextInt(this.size);
		while(row == start.getRow()) {
			row = random.nextInt(this.size);
		}
		
		col = random.nextInt(this.size);
		while(col == start.getCol()) {
			col = random.nextInt(this.size);
		}
		
		return new MazeCoordinates(row, col);
	}
	
	public MazeCoordinates getStart() { return this.start; }
	public MazeCoordinates getEnd() { return this.exit; }
	
	private MazeCoordinates initCurrentCoord() {
		return new MazeCoordinates(start.getRow(), start.getCol());
	}
	
	public void navigate() {
		String input = "";
		current.setCoordinates(start.getRow(), start.getCol());
		
		while(!input.equals("exit")) {
			System.out.println(maze[current.getRow()][current.getCol()].display());
			System.out.print("Where would you like to move?  (Type \"exit\" to exit.) ");
			input = MazeNavigationTest.KB.nextLine();
			
			while(!isValidInput(input)) {
				System.out.println("Invalid input. Please enter N, S, E, or W.");
				System.out.println("Where would you like to go? (Type \"exit\" to exit.) ");
				input = MazeNavigationTest.KB.nextLine();
			}
			
			if(input.toLowerCase().equals("n")) {
				if(((MazeRoom)maze[current.getRow()][current.getCol()]).questionPrompt(input)) {
					current.setCoordinates(current.getRow()-1, current.getCol());
				}
			}
			else if(input.toLowerCase().equals("s")) {
				if(((MazeRoom)maze[current.getRow()][current.getCol()]).questionPrompt(input)) {
					current.setCoordinates(current.getRow()+1, current.getCol());
				}
			}
			else if(input.toLowerCase().equals("e")) {
				if(((MazeRoom)maze[current.getRow()][current.getCol()]).questionPrompt(input)) {
					current.setCoordinates(current.getRow(), current.getCol()+1);
				}
			}
			else if(input.toLowerCase().equals("w")) {
				if(((MazeRoom)maze[current.getRow()][current.getCol()]).questionPrompt(input)) {
					current.setCoordinates(current.getRow(), current.getCol()-1);
				}
			}
		} // end while input
	}
	
	private boolean isValidInput(String input) {
		if(input.toLowerCase().equals("n") || input.toLowerCase().equals("e") || 
				input.toLowerCase().equals("s") || input.toLowerCase().equals("w")) {
			return ((MazeRoom)maze[current.getRow()][current.getCol()]).isValidDoor(input);
		}
		else if(input.toLowerCase().equals("exit")) {
			return true;
		}
		
		return false;
	}
	
//================================================================	
	public class MazeCoordinates {
		private int row;
		private int column;
		
		public MazeCoordinates(int row, int col) {
			this.row = row;
			this.column = col;
		}
		
		public int getRow() { return row; }
		public int getCol() { return column; }
		
		public void setCoordinates(int row, int col) {
			if(row > size-1 || col > size-1 || row < 0 || col < 0) {
				throw new IndexOutOfBoundsException("Value passed to setCoordinates() out of bounds.");
			}
			
			this.row = row;
			this.column = col;
		}
		
		public boolean equals(MazeCoordinates mc) {
			return mc.getRow() == row && mc.getCol() == column;
		}
		
		public boolean equals(int row, int col) {
			return this.row == row && col == column;
		}
	}

}
