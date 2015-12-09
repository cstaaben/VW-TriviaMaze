/**
 * AdminDBState.java
 * Author: Corbin Staaben & Caleb Jetwett
 * Description: AdminDBState is a piece of the State pattern implemented to control game flow. It represents the
 * state of the program where the user can manage the database of questions.
 */
package states;

import mazeDB.MazeDB;
import java.io.Serializable;

public class AdminDBState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;

	/**
	 * Constructor that saves the reference to the TriviaMaze object that holds references to all necessary
	 * information/objects this state needs to function.
	 * @param triviaMaze - the TriviaMaze object that instantiated this state
	 */

	public AdminDBState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}

	/**
	 * Calls the databaseAdministration() method in mazeDB.MazeDB where the database management is handled
	 */
	@Override
	public void adminDB() {

		MazeDB.databaseAdministration();
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

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
	}
	
}
