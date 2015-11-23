package maze;

import maze.MazeCoordinates;

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
	
	public void move(String direction) {
		MazeCoordinates newCoordinates = null;
		
		switch(MazeDirection.valueOf(direction.toUpperCase())) {
			case NORTH:
				newCoordinates = new MazeCoordinates(currentCoordinates.getRow()-1,
						currentCoordinates.getCol());
				break;
			case SOUTH:
				newCoordinates = new MazeCoordinates(currentCoordinates.getRow()+1,
						currentCoordinates.getCol());
				break;
			case EAST:
				newCoordinates = new MazeCoordinates(currentCoordinates.getRow(),
						currentCoordinates.getCol()+1);
				break;
			case WEST:
				newCoordinates = new MazeCoordinates(currentCoordinates.getRow(),
						currentCoordinates.getCol()-1);
				break;
			default:
				newCoordinates = currentCoordinates;
				break;
		}
		
		currentCoordinates.setCoordinates(newCoordinates);
	}
	
}
