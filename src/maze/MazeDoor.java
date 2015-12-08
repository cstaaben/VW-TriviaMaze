/**
 * MazeDoor.java
 * Author: Corbin Staaben
 * Description: MazeDoor contains references to the two rooms it is linked to, the direction it is situated in for
 * each room, and whether it is open or locked.
 */
package maze;

public class MazeDoor implements MazeObject {
	
	private static final long serialVersionUID = 1928962900649929811L;
	private MazeDirection lesserDirection, greaterDirection;
	private boolean open;
	private boolean locked;
	private MazeQuestion question;
	
	private MazeObject lesserRoom, greaterRoom;

	/**
	 * Constructor that initializes the booleans used to represent if the door is open or locked
	 */

	public MazeDoor() {
		this.open = false;
		this.locked = false;
	}

	/**
	 * Retrieves the direction the door is situated in the "lesser" room (i.e. left or upper room in the maze)
	 * @return - the direction the door is situated in the "lesser" room
	 */
	public MazeDirection getLesserDirection() { return lesserDirection; }

	/**
	 * Retrieves the direction the door is situated in the "greater" room (i.e. right or lower room in the maze)
	 * @return - the direction the door is situated in the "greater" room
	 */
	public MazeDirection getGreaterDirection() { return greaterDirection; }

	/**
	 * Retrieves the value of the boolean representing if the door is open
	 * @return - true if the door is open, false otherwise
	 */
	public boolean isOpen() { return open; }

	/**
	 * Retrieves the value of the boolean representing if the door is locked
	 * @return - true if the door is locked, false otherwise
	 */
	public boolean isLocked() { return locked; }

	/**
	 * Retrieve the "lesser" room the door is linked to (i.e. the left or upper room, depending on orientation)
	 * @return - the room linked to the door in the "lesser" direction
	 */
	public MazeRoom getLesserRoom() { return (MazeRoom) lesserRoom; }

	/**
	 * Retrieve the "greater" room the door is linked to (i.e. the right or lower room, depending on orientation)
	 * @return
	 */
	public MazeRoom getGreaterRoom() { return (MazeRoom) greaterRoom; }

	/**
	 * Set the direction the door is situated in inside the "lesser" room (i.e. left or upper room, depending on
	 * the orientation of the rooms)
	 * @param lesserDirection - the direction the door is situated
	 */
	public void setLesserDirection(MazeDirection lesserDirection) {
		this.lesserDirection = lesserDirection;
	}

	/**
	 * Set the direction to door is situated in inside the "greater" room (i.e. right or lower room, depending
	 * on the orientation of the rooms)
	 * @param greaterDirection
	 */
	public void setGreaterDirection(MazeDirection greaterDirection) {
		this.greaterDirection = greaterDirection;
	}

	/**
	 * Display the question stored in this MazeDoor object
	 * @return - the String representation of the MazeQuestion stored in this MazeDoor object
	 */
	@Override
	public String display() {
		// Display question
		return question.display();
	}

	/**
	 * Set the boolean switch that represents whether this door is open
	 * @param open - the new value of the door's open boolean
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * Set the boolean switch that represents whether this door is locked
	 * @param locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Determines if the given answer matches the MazeQuestion's answer field
	 * @param input - String of user input
	 * @return - true if the user input matches the question's answer
	 */
	public boolean isCorrectAnswer(String input) {
		return input.toLowerCase().equals(question.getAnswer().toLowerCase());
	}

	/**
	 * Set the "lesser" room the door is linked to (i.e. the left or upper room, depending on the orientation
	 * of the rooms)
	 * @param room
	 */
	public void setLesserRoom(MazeObject room) {
		this.lesserRoom = room;
	}

	/**
	 * Set the "greater" room the door is linked to (i.e. the right or lower room, depending on the orientation
	 * of the rooms)
	 * @param room
	 */
	public void setGreaterRoom(MazeObject room) {
		this.greaterRoom = room;
	}

	/**
	 * Set the question this door holds that the user must answer to advance through this door
	 * @param question - the question this door will hold
	 */
	public void setQuestion(MazeQuestion question) {
		this.question = question;
	}

	/**
	 * Retrieve the number of points the user gets for correctly answering the question
	 * @return - the number of points the question is worth
	 */
	public int getDoorPoints() {
		return this.question.getPoints();
	}
}
