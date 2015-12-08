/**
 * NullMazeDoor.java
 * Author: Corbin Staaben
 * Description: NullMazeDoor is used to prevent the user from moving out of the maze, which would cause an
 * ArrayIndexOutOfBoundsException to be thrown.
 */
package maze;

public class NullMazeDoor extends MazeDoor {
	
	private static final long serialVersionUID = 6143575408807179137L;

	/**
	 * Stubbed out constructor
	 */

	public NullMazeDoor() {
	}

	/**
	 * NullMazeDoors are always locked
	 * @return - always returns true
	 */
	@Override
	public boolean isLocked() { return true; }

	/**
	 * NullMazeDoors are never open
	 * @return - always returns false
	 */
	@Override
	public boolean isOpen() { return false; }

	/**
	 * NullMazeDoors have nothing to display, so an arbitrary String is returned to denote a NullMazeDoor
	 * @return - "null" to denote an empty door
	 */
	@Override
	public String display() {
		return "null";
	}

}
