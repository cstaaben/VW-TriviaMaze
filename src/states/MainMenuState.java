/**
 * MainMenuState.java
 * Author: Corbin Staaben
 * Description: MainMenuState is a piece of the State pattern implemented to control game flow. It displays the
 * choices a user has when starting up the program, or when not currently playing Door Jam.
 */
package states;

import java.io.Serializable;

public class MainMenuState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;
	private String menu = "1.) Start a new game\n"
						+ "2.) Load a game\n"
						+ "3.) Login to the database\n"
						+ "4.) Exit game";

	/**
	 * Constructor assigns the reference to the TriviaMaze that contains the necessary information for this state
	 * to function properly
	 * @param triviaMaze
	 */

	public MainMenuState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}

	/**
	 * Give the user their choice of actions to perform outside of the maze and database management and
	 * transition to the state they choose. All exceptions thrown by user input is handled inside the method.
	 */
	@Override
	public void mainMenu() {
		int input = 0;

		do {
			System.out.println(menu);

			try {
				input = Integer.parseInt(TriviaMaze.KB.nextLine());
				if(input < 1 || input > 4) {
					throw new IndexOutOfBoundsException("Value entered is out of the range of the menu.");
				}
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("Value entered is not an integer.");
				input = 0;
				continue;
			}
			catch(IndexOutOfBoundsException i) {
				System.out.println(i.getMessage());
				input = 0;
				continue;
			}
			catch(Exception e) {
				System.out.println("Invalid input: " + e.getMessage());
			}

		} while(input < 1 || input > 4);

		switch(input) {
			case 1:
				triviaMaze.setState(triviaMaze.getNewGameState());
				break;
			case 2:
				triviaMaze.setState(triviaMaze.getLoadGameState());
				break;
			case 3:
				triviaMaze.setState(triviaMaze.getAdminDBState());
				break;
			case 4:
				triviaMaze.setState(triviaMaze.getEndGameState());
				break;
		} // end switch input
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
	public void exitMaze() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void endGame() {
		// TODO Auto-generated method stub
	}

}
