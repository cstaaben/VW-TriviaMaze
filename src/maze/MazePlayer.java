/**
 * MazePlayer.java
 * Author: Corbin Staaben
 * Description: MazePlayer contains all relevant information pertaining to the user, including: points earned,
 * current position in the maze, exit room coordinates if discovered
 */
package maze;

public class MazePlayer implements MazeObject {

	private static final long serialVersionUID = 2800610953134040139L;
	private MazeCoordinates currentCoordinates;
	private MazeCoordinates exitCoordinates;
	private int currentPoints;

	/**
	 * Constructor creates a MazePlayer object at the provided coordinates
	 * @param start - the coordinates of the MazePlayer object
	 */

	public MazePlayer(MazeCoordinates start) {
		this.currentCoordinates = start;
		this.exitCoordinates = null;
		this.currentPoints = 0;
	}

	/**
	 * Display the current coordinates and the current points
	 * @return - the String representation of the MazePlayer object
	 */
	public String display() {
		String result = "You are currently at coordinates " + currentCoordinates.display() 
			+ " with " + currentPoints + " points.\n";
		
		if(exitCoordinates != null) {
			result += "The exit is at " + exitCoordinates.display() + ".";
		}
		
		return result;
	}

	/**
	 * Retrieve the number of points the player has earned
	 * @return - the current number of points the player has
	 */
	public int getCurrentPoints() { return this.currentPoints; }

	/**
	 * Retrieve the current position of the player
	 * @return - the current coordinates of the player
	 */
	public MazeCoordinates getCurrentCoordinates() { return this.currentCoordinates; }

	/**
	 * Set the current number of points
	 * @param points - the new number of points the player has
	 */
	public void setPoints(int points) {
		this.currentPoints = points;
	}

	/**
	 * Increment points by the specified amount. Throws an exception if the given point value is negative.
	 * @param points - integer the current points will be incremented by
	 * @throws IllegalArgumentException - thrown when the supplied argument is a negative number
	 */
	public void incrementPoints(int points) throws IllegalArgumentException{
		if(points < 0) {
			throw new IllegalArgumentException("Negative value passed to increment points.");
		}
		
		this.currentPoints += points;
	}

	/**
	 * converts the given String into a MazeDirection and moves the player in the corresponding direction
	 * @param direction - the String direction the player wishes to move
	 */
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

	/**
	 * Set the exit coordinates once they have been discovered by the player (only called if player doesn't have
	 * enough points to exit the maze when they discover the exit)
	 */
	public void discoverExit() {
		exitCoordinates.setCoordinates(currentCoordinates);
	}
	
}
