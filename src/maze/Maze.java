package maze;

import java.util.Random;

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
				
			}
		}
		
		return result;
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
	
	public MazeCoordinates initCurrentCoord() {
		return new MazeCoordinates(start.getRow(), start.getCol());
	}
	
	public void navigate() {
		String input = "";
		current.setCoordinates(start.getRow(), start.getCol());
		
		while(!input.equals("exit")) {
			System.out.println(maze[current.getRow()][current.getCol()].display());
			System.out.print("Where would you like to move?  (Type \"exit\" to exit.) ");
			input = MazeTest.KB.nextLine();
			
			while(!isValidInput(input)) {
				System.out.println("Invalid input. Please enter N, S, E, or W.");
				System.out.println("Where would you like to go? (Type \"exit\" to exit.) ");
				input = MazeTest.KB.nextLine();
			}
			
			if(input.toLowerCase().equals("n")) {
				current.setCoordinates(current.getRow()-1, current.getCol());
			}
			else if(input.toLowerCase().equals("s")) {
				current.setCoordinates(current.getRow()+1, current.getCol());
			}
			else if(input.toLowerCase().equals("e")) {
				current.setCoordinates(current.getRow(), current.getCol()+1);
			}
			else if(input.toLowerCase().equals("w")) {
				current.setCoordinates(current.getRow(), current.getCol()-1);
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
	private class MazeCoordinates {
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
	}

}
