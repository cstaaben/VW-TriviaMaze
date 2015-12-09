/**
 * DoorJam.java
 * Author: Corbin Staaben
 * Description: DoorJam provides the method for executing the program, instantiates the TriviaMaze object used to
 * handle game flow, and calls the various methods appropriate for each state of the program.
 */

import states.LoadGameState;
import states.TriviaMaze;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class DoorJam {

	private TriviaMaze maze;

	public DoorJam(String demo) {
		maze = new TriviaMaze(demo);
	}

	/**
	 * run() controls the game's flow - calling each appropriate method when the game is in each of its states
	 */
	public void run() {

		while(!maze.getCurrentState().equals(maze.getEndGameState())) {
			if(maze.getCurrentState().equals(maze.getNewGameState())) {
				maze.newGame();
			}
			else if(maze.getCurrentState().equals(maze.getLoadGameState())) {
				maze.loadGame();
			}
			else if(maze.getCurrentState().equals(maze.getAdminDBState())) {
				maze.adminDB();
			}
			else if(maze.getCurrentState().equals(maze.getMainMenuState())) {
				maze.mainMenu();
			}
			else if(maze.getCurrentState().equals(maze.getSaveGameState())) {
				maze.saveGame();
			}
			else if(maze.getCurrentState().equals(maze.getNavigateMazeState())) {
				maze.navigateMaze();
			}
			else if(maze.getCurrentState().equals(maze.getExitMazeState())) {
				maze.exitMaze();
			}
		} // while endGameState
		
		maze.endGame();
	}
}
