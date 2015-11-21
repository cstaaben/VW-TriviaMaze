package maze;

import maze.Maze.MazeCoordinates;

public class MazePlayer implements MazeObject {

	private MazeCoordinates currentCoordinates;
	private int currentPoints;
	
	public MazePlayer(MazeCoordinates start) {
		this.currentCoordinates = start;
		this.currentPoints = 0;
	}
	
	public String display() {
		return "You are currently at coordinates (" + currentCoordinates.getRow() + ", " 
				+ currentCoordinates.getCol() + "with " + currentPoints + " points.";
	}
	
	public int getCurrentPoints() { return this.currentPoints; }
	
	public MazeCoordinates getCurrentCoordinates() { return this.currentCoordinates; }
	
	public void setPoints(int points) {
		this.currentPoints = points;
	}
	
	public void incrementPoints(int points) throws IllegalArgumentException{
		if(points < 0) {
			throw new IllegalArgumentException("Negative value passed to increment points.");
		}
		
		this.currentPoints += points;
	}
	
	public void move(int row, int col) {
		currentCoordinates.setCoordinates(row, col);
	}
	
}
