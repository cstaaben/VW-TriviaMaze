package maze;

import java.util.Random;

public class Maze implements MazeObject {
	
	private static final long serialVersionUID = -1295493944705726960L;
	public static final int MAX_SIZE = 10;
	public static final int MIN_SIZE = 2;
	
	private int size;
	private MazeObject[][] maze;
	private MazeCoordinates start;
	private MazeCoordinates exit;
	private Random random = new Random();
	private int requiredPoints;
	public Maze(MazeObject[][] maze) {
		this.maze = maze;
		this.size = maze.length;
		
		start = getStartCoordinates();
		exit = getExitCoordinates();
		
		requiredPoints = (int)(.25 * doorCount(size));
	}
	
	private static int doorCount(int sideLength) {
		return 2 * (sideLength * (sideLength-1));
	}
	
	public int getDoorCount() {
		return doorCount(this.size);
	}
	
	public int getRequiredPoints() { return this.requiredPoints; }
	public int getSize() { return this.size; }

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
	
	private MazeCoordinates getStartCoordinates() {
		int row = 0, col = 0;
		
		row = random.nextInt(this.size);
		col = random.nextInt(this.size);
		
		return new MazeCoordinates(row, col);
	}
	
	private MazeCoordinates getExitCoordinates() {
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
