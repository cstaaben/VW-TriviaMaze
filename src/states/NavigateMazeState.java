/**
 * NavigateMazeState.java
 * Author: Corbin Staaben
 * Description: NavigateMazeState is a piece of the State pattern implemented to control game flow. It allows the user
 * to navigate from room to room in the maze and transitions them to the AnswerQuestionState state when necessary, then
 * back again to resume navigation. It also allows users to transition to the SaveGameState state when desired.
 */
package states;

import maze.Maze;
import maze.MazePlayer;
import java.io.Serializable;

public class NavigateMazeState implements TriviaMazeState, Serializable {

	private TriviaMaze triviaMaze;
	private boolean exit; // class level boolean to track user's desire to exit

	/**
	 * NavigateMazeState saves the reference of the TriviaMaze object that controls instantiation in this State
	 * pattern. It also initializes the class-level variable exit (described above).
	 * @param triviaMaze - the TriviaMaze object that contains necessary references and objects necessary for this
	 *                   state to function
	 */

	public NavigateMazeState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
		
		this.exit = false;
	}

	/**
	 * navigateMaze() allows the player move from room to room, transfers the game to AnswerQuestionState, then back,
	 * or to SaveGameState, then to MainMenuState, as appropriate. Input is checked using a helper method,
	 * isValidInput.
	 */
	@Override
	public void navigateMaze() {
		MazePlayer player = triviaMaze.getPlayer();
		Maze maze = triviaMaze.getMaze();

		String direction = "";

		while(!exit && !triviaMaze.getCurrentState().equals(triviaMaze.getExitMazeState())) {
			System.out.println(player.display());
			System.out.println(maze.getRoom(player.getCurrentCoordinates()).display());
			System.out.print("Where would you like to move?  (Type \"exit\" to exit or " +
					"\"save\" to save the game): ");
			direction = TriviaMaze.KB.nextLine();

			while(!isValidInput(direction)) {
				System.out.println("\nInvalid direction. Please enter north, south, east, or west.");
				System.out.println("Where would you like to go? (Type \"exit\" to exit.) ");
				direction = TriviaMaze.KB.nextLine();
			} // end while isValidInput

			if(!exit) {
				if(!maze.getRoom(player.getCurrentCoordinates()).isValidDoor(direction)) {
					System.out.println("You can't move there!");
				}
				else if(maze.getRoom(player.getCurrentCoordinates()).isPreviouslyVisited(direction)) {
					player.move(direction);
					if(player.getCurrentCoordinates().equals(maze.getEnd())) {
						player.discoverExit();
						System.out.println("You've discovered the exit! Unfortunately, you don't have enough"
								+ "points to unlock it yet. Keep going!");
					}
				}
				else {
					triviaMaze.setState(triviaMaze.getAnswerQuestionState());

					triviaMaze.answerQuestion(direction);

					if(maze.getRoom(player.getCurrentCoordinates()).isValidDoor(direction)) {
						player.move(direction);
					}
				}
			}

			if(player.getCurrentCoordinates().equals(maze.getEnd())) {
				triviaMaze.setState(triviaMaze.getExitMazeState());
				System.out.println("Congratulations, you've finished the maze!");
			}
			else if(triviaMaze.isGameOver()) {
				System.out.println("I'm sorry, there's no valid route to the exit. Game over.");
				triviaMaze.setState(triviaMaze.getExitMazeState());
			}
		} // end while exit
	}

	/**
	 * isValidInput is a helper method for navigateMaze() to check user input for valid input and switch the class-
	 * level boolean used to track the user's desire to exit the current "state".
	 * @param input - String containing user input.
	 * @return - true if valid input ("north", "south", "east", "west", "exit", "save"), otherwise false.
	 */
	private boolean isValidInput(String input) {
		if(input.toLowerCase().equals("north") || input.toLowerCase().equals("east") ||
				input.toLowerCase().equals("south") || input.toLowerCase().equals("west")) {
			return true;
		}
		else if(input.toLowerCase().equals("exit")) {
			this.exit = true;
			triviaMaze.setState(triviaMaze.getMainMenuState());
			return exit;
		}
		else if(input.toLowerCase().equals("save")) {
			this.exit = true;
			triviaMaze.setState(triviaMaze.getSaveGameState());
			return exit;
		}

		return false;
	}

	/**
	 * All methods beyond this point are stubbed out, as required for TriviaMazeState. They are not required for
	 * NavigateMazeState to function and as such, they are not, nor should they ever be, implemented here.
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
