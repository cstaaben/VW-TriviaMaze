package maze;

import java.util.Random;

public class Maze implements MazeObject {
	
	public static final int MAX_SIZE = 10;
	private int size;
	private MazeObject[][] maze;
	private MazeCoordinates start;
	private MazeCoordinates exit;
	private Random random = new Random();
	
	public Maze(int size) {
		this.size = size;
		
		MazeMediator.createReference(this);
		
		start = getStartCoord();
		exit = getExitCoord();
	}
	
	public int getSize() { return this.size; }

	public String display() {
		String result = "";
		
		for(int i = 0; i < 5; i++) {
			for(int j = i; j == i; j++) {
				result += maze[i][j].display();
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
	
	private class MazeCoordinates {
		private int row;
		private int column;
		
		public MazeCoordinates(int row, int col) {
			this.row = row;
			this.column = col;
		}
		
		public int getRow() { return row; }
		public int getCol() { return column; }
	}

}
