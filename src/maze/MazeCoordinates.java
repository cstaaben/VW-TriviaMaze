/**
 * MazeCoordinates.java
 * Author: Corbin Staaben
 * Description: MazeCoordinates is a data class used to encapsulate "coordinates" in the maze for more convenient
 * access, use, and modification.
 */
package maze;

public class MazeCoordinates implements MazeObject {
	
	private static final long serialVersionUID = 6341209524058039126L;
	private int row;
	private int column;

	/**
	 * Explicit value constructor to return a MazeCoordinate object representing the given coordinates
	 * @param row - the "row" of the maze (y-coordinate)
	 * @param col - the "column" of the maze (x-coordinate)
	 */

	public MazeCoordinates(int row, int col) {
		this.row = row;
		this.column = col;
	}

	/**
	 * Retrieves the row index (y-coordinate) from this MazeCoordinate object
	 * @return - this MazeCoordinate's row index (y-coordinate)
	 */
	public int getRow() { return row; }

	/**
	 * Retrieves the column index (x-coordinate) from this MazeCoordinate object
	 * @return - this MazeCoordinate's column index (x-coordinate)
	 */
	public int getCol() { return column; }

	/**
	 * Set this object's "coordinates" equal to those of the provided MazeCoordinate
	 * @param mc - the MazeCoordinate object containing the new desired "coordinates"
	 */
	public void setCoordinates(MazeCoordinates mc) {
		this.row = mc.getRow();
		this.column = mc.getCol();
	}

	/**
	 * Compares this object against the provided MazeCoordinates object
	 * @param mc - the MazeCoordinate object this object will be compared against
	 * @return - true if the both coordinates are equal, false otherwise
	 */
	public boolean equals(MazeCoordinates mc) {
		return mc.getRow() == row && mc.getCol() == column;
	}

	/**
	 * Compares the given integers against the stored row and column integers
	 * @param i - the given "row" coordinate
	 * @param j - the given "column" coordinate
	 * @return - true if i is the same value stored in row and j is the same value stored in column, false otherwise
	 */
	public boolean equals(int i, int j) { return i == row && j == column; }

	/**
	 * Generate a String representation of the coordinates
	 * @return - the String representation of this MazeCoordinates object
	 */
	public String display() {
		return "(" + row + ", " + column + ")";
	}
}