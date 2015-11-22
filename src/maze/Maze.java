package maze;

import java.util.Random;

public class Maze implements MazeObject {
	
	public static final int MAX_SIZE = 10;
	public static final int MIN_SIZE = 2;
	
	private int size;
	private MazeObject[][] maze;
	private MazeCoordinates start;
	private MazeCoordinates exit;
	private Random random = new Random();
	
	public Maze(MazeObject[][] maze) {
		this.maze = maze;
		this.size = maze.length;
		
		start = getStartCoord();
		exit = getExitCoord();
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
	
	public MazeRoom getRoom(int row, int col) {
		return (MazeRoom)maze[row][col];
	}
	
	public MazeRoom getRoom(MazeCoordinates mc) {
		return (MazeRoom)maze[mc.getRow()][mc.getCol()];
	}

}
