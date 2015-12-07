/**
 * ExitMazeState.java
 * Author: Corbin Staaben
 * Description: ExitMazeState is a piece of the State pattern implementation used to control game flow. It is used
 * to detect when the player has exited the maze, regardless of success.
 */
package states;

import java.io.Serializable;

public class ExitMazeState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;

	/**
	 * Constructor that saves the reference to the TriviaMaze that instantiated this state
	 * @param triviaMaze
	 */

	public ExitMazeState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}

	/**
	 * When the player exits the maze, automatically transition into the MainMenuState
	 */
	@Override
	public void exitMaze() {
		triviaMaze.setState(triviaMaze.getMainMenuState());
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
	public void answerQuestion(String direction) {
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
	public void endGame() {
		// TODO Auto-generated method stub
	}
	
}
