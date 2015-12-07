/**
 * Maze.java
 * Author: Corbin Staaben
 * Description: Maze contains the square 2D array of MazeObject objects that represents the maze the user will
 * navigate. It randomly generates two different coordinates for a start space and end space, and tracks the
 * required number of points to exit the maze.
 */
package maze;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

public class Maze implements MazeObject, Serializable {
	
	private static final long serialVersionUID = -1295493944705726960L;

	private int size;
	private MazeObject[][] maze;
	private MazeCoordinates start;
	private MazeCoordinates exit;
	private Random random = new Random();
	private int requiredPoints;

	/**
	 * Constructor that saves the provided 2D array of MazeObject objects, computes the size of the maze, retrieves
	 * the start and end coordinates and computes the required number of points to finish the maze.
	 * @param maze - the 2D array of MazeObject objects that represent the maze
	 */

	public Maze(MazeObject[][] maze) {
		this.maze = maze;
		this.size = maze.length;
		
		start = getStartCoordinates();
		exit = getExitCoordinates();
		
		requiredPoints = (int)(.25 * doorCount(size));
	}

	/**
	 * Helper method to compute the number of doors in the maze
	 * @param sideLength - the size of one size of the maze (an nxn 2D array)
	 * @return - the computed number of doors in the maze
	 */
	private static int doorCount(int sideLength) {
		return 2 * (sideLength * (sideLength-1));
	}

	/**
	 * Method to retrieve the number of doors in the maze
	 * @return - the number of doors returned from doorCount()
	 */
	public int getDoorCount() {
		return doorCount(this.size);
	}

	/**
	 * Method to retrieve the required number of points to finish the maze
	 * @return - the required number of points to finish the maze
	 */
	public int getRequiredPoints() { return this.requiredPoints; }

	/**
	 * Method to retrieve the size of each 1D array in the 2D array
	 * @return - the size of one side of the 2D array
	 */
	public int getSize() { return this.size; }

	/**
	 * Method to generate the String representation of this Maze object
	 * @return
	 */
	public String display() {
		String result = "";
		Iterator<MazeDoor> doors;
		MazeDoor d;
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				doors = getRoom(i,j).doorIterator();

				while(doors.hasNext()) {

				}
			} // end for j
		} // end for i
		
		return result;
	}

	/**
	 * Helper method to determine String representation of each room in the maze
	 * @return - the String representation of the room
	 */
	private String getRoomLayout() {
		StringBuilder sb = new StringBuilder();


		
		return "";
	}

	/**
	 * Helper method to generate the start space coordinates
	 * @return - the MazeCoordinates object representing the start space coordinates
	 */
	private MazeCoordinates getStartCoordinates() {
		int row, col;
		
		row = random.nextInt(this.size);
		col = random.nextInt(this.size);
		
		return new MazeCoordinates(row, col);
	}

	/**
	 * Helper method to generate the end space coordinates with checks to ensure they are different from the
	 * start space coordinates
	 * @return - the MazeCoordinates object representing the end space coordinates
	 */
	private MazeCoordinates getExitCoordinates() {
		int row, col;
		
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

	/**
	 * Retrieve the start space coordinates of the maze
	 * @return - the MazeCoordinates object representing the start space coordinates
	 */
	public MazeCoordinates getStart() { return this.start; }

	/**
	 * Retrieve the end space coordinates of the maze
	 * @return - the MazeCoordinates object representing the end space coordinates
	 */
	public MazeCoordinates getEnd() { return this.exit; }

	/**
	 * Retrieve the specified room from the maze
	 * @param row - the "row" of the desired room (i.e. y-coordinate)
	 * @param col - the "column" of the desired room (i.e. x-coordinate)
	 * @return - the desired MazeRoom located at [row][col]
	 */
	public MazeRoom getRoom(int row, int col) {
		return (MazeRoom)maze[row][col];
	}

	/**
	 * Retrieve the specified room from the maze
	 * @param mc - the MazeCoordinates object representing the desired room's coordinates
	 * @return - the desired MazeRoom
	 */
	public MazeRoom getRoom(MazeCoordinates mc) {
		return (MazeRoom)maze[mc.getRow()][mc.getCol()];
	}
}
