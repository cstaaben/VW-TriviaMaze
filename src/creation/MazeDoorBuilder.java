/**
 * MazeDoorBuilder.java
 * Author: Corbin Staaben
 * Description: MazeDoorBuilder implements the Builder pattern to "assemble" a MazeDoor and link it to its containing
 * MazeRoom(s). Any methods containing the phrase "Empty" are intended to insert/link NullMazeDoors on the edges of
 * the maze, where allowing the player to move to that location would lead to an ArrayIndexOutOfBoundsException.
 */
package creation;

import maze.MazeDoor;
import maze.MazeQuestion;
import maze.QuestionManager;
import maze.MazeObject;
import maze.NullMazeDoor;

import java.io.Serializable;
import java.util.Iterator;

import maze.MazeDirection;

public class MazeDoorBuilder implements Serializable {
	
	private static MazeDoorBuilder reference = null; //static reference for Singleton pattern
	private static QuestionManager qm; //QuestionManager queries the database and provides the returned questions
	private static Iterator<MazeQuestion> questions; //Iterator to... iterate over provided questions
	
	private MazeDoor door;

	/**
	 * private constructor for instantiation within the Singleton pattern
	 */

	private MazeDoorBuilder() {
		qm = QuestionManager.getInstance();
	}

	/**
	 * static method to obtain a reference to this MazeDoorBuilder
	 * @return a reference to this MazeDoorBuilder
	 */
	public static MazeDoorBuilder getReference() {
		if(reference == null) {
			reference = new MazeDoorBuilder();
		}
		
		return reference;
	}

	/**
	 * initializes the QuestionHashMap with the desired number of questions, fetches the Iterator for the questions
	 * @param size - the desired number of questions
	 */
	public void loadSize(int size) {
		qm.initializeQuestionHashMap(size);
		questions = qm.getQuestionIterator();
	}

	/**
	 * instantiates the MazeDoor to be "built"
	 * @return - a reference to this MazeDoorBuilder to continue "building"
	 */
	public MazeDoorBuilder newDoor() {
		this.door = new MazeDoor();
		
		return this;
	}

	/**
	 * instantiates a NullMazeDoor to be inserted on the edge of the maze
	 * @return - a reference to this MazeDoorBuilder to continue "building"
	 */
	public MazeDoorBuilder newEmptyDoor() {
		this.door = new NullMazeDoor();
		
		return this;
	}

	/**
	 * sets the direction the door is situated in the "lesser" room, i.e. the left room or the upper room, depending
	 * on the orientation of the linked rooms
	 * @param md - the direction the door is situated in the "lesser" room
	 * @return - a reference to this MazeDoorBuilder to continue "building"
	 */
	public MazeDoorBuilder lesserDirection(MazeDirection md) {
		door.setLesserDirection(md);
		
		return this;
	}

	/**
	 * sets the "lesser" room the door is linked to (i.e. the upper or left room, depending on the orientation of the
	 * two linked rooms)
	 * @param room - the room the door will be linked to
	 * @return - a reference to this MazeDoorBuilder to continue "building"
	 */
	public MazeDoorBuilder lesserRoom(MazeObject room) {
		door.setLesserRoom(room);
		
		return this;
	}

	/**
	 * sets the direction the door is situated in the "greater" room (i.e. the lower or right room, depending on the
	 * orientation of the two linked rooms)
	 * @param md - the direction the door is situated in the "greater" room
	 * @return - a reference to this MazeDoorBuilder to continue "building"
	 */
	public MazeDoorBuilder greaterDirection(MazeDirection md) {
		door.setGreaterDirection(md);
		
		return this;
	}

	/**
	 * sets the "greater" room the door is linked to (i.e. the lower or right room, depending on the orientation of
	 * the two linked rooms)
	 * @param room - the room the door will be linked to
	 * @return - a reference to this MazeDoorBuilder to continue "building"
	 */
	public MazeDoorBuilder greaterRoom(MazeObject room) {
		door.setGreaterRoom(room);
		
		return this;
	}

	/**
	 * inserts the question provided by the Iterator from QuestionManager and returns the reference to the
	 * "built" door
	 * @return - the finished MazeDoor object
	 */
	public MazeDoor buildDoor() {
		door.setQuestion(questions.next());
		
		return door;
	}

	/**
	 * returns a NullMazeDoor with no question, since it will be inserted at the edge of the maze
	 * @return - the finished NullMazeDoor object
	 */
	public MazeDoor buildEmptyDoor() {
		return door;
	}

}
