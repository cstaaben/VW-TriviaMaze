package player;

import maze.MazeObject;
import maze.Maze.MazeCoordinates;

public class MazePlayer implements MazeObject {

	private MazeCoordinates currentCoordinates;
	private int currentPoints;
	
	public MazePlayer(MazeCoordinates start) {
		this.currentCoordinates = start;
		this.currentPoints = 0;
	}
	
	public String display() {
		return "You are currently at coordinates (" + currentCoordinates.getRow() + ", " + currentCoordinates.getCol() + ")."
				+ "\n\tYou currently have " + currentPoints + " points.";
	}
	
	public int getCurrentPoints() { return this.currentPoints; }
	
	public MazeCoordinates getCurrentCoordinates() { return this.currentCoordinates; }
	
	
	
}
