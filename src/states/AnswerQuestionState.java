/**
 * AnswerQuestionState.java
 * Author: Corbin Staaben
 * Description: AnswerQuestionState represents the state of the program wherein the user answers the question
 * stored inside the door in the direction they wish to move.
 */
package states;

import maze.MazeDirection;

import java.io.Serializable;

public class AnswerQuestionState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;

	/**
	 * Constructor that assigns the reference to the TriviaMaze object which contains all the necessary information
	 * for this state to function
	 * @param triviaMaze - the TriviaMaze object which contains all the information for this state to function
	 */

	public AnswerQuestionState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}

	/**
	 * Prompts the user with the question stored inside the door in the direction they wish to move, retrieves the
	 * points earned from that answer and increments the user's current points by that amount. It then sets the
	 * current state back to the NavigateMazeState to resume maze navigation
	 * @param direction - the String representing the direction the user wishes to move
	 */
	@Override
	public void answerQuestion(String direction) {
		MazeDirection md = MazeDirection.valueOf(direction.toUpperCase());

		int points = triviaMaze.getMaze().getRoom(triviaMaze.getPlayer().getCurrentCoordinates()).questionPrompt(md);
		triviaMaze.getPlayer().incrementPoints(points);

		triviaMaze.setState(triviaMaze.getNavigateMazeState());
	}

	/**
	 * All methods beyond this point are stubbed out as they are required by the TriviaMazeState interface
	 * and a part of the State pattern implementation. They are not, nor should they ever be, implemented here.
	 */

	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void adminDB() {
		// TODO Auto-generated method stub

	}

	@Override
	public void navigateMaze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mainMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitMaze() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
	}
}
