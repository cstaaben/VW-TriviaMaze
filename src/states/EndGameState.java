/**
 * EndGameState.java
 * Author: Corbin Staaben
 * Description: EndGameState is a piece of the State pattern implemented to control game flow. It is the state that
 * represents the user's choice to exit the program.
 */
package states;

import java.io.Serializable;

public class EndGameState implements TriviaMazeState, Serializable {

	/**
	 * Stubbed out constructor, this state doesn't need a reference to the TriviaMaze because it needs no other
	 * information to function.
	 */

	public EndGameState() {
	}

	/**
	 * Displays an exit message
	 */
	@Override
	public void endGame() {
		System.out.println("Thanks for playing!");
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
	public void exitMaze() {
		// TODO Auto-generated method stub

	}

}
