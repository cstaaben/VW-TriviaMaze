/**
 * TriviaMaze.java
 * Author: Corbin Staaben & Caleb Jewett
 * Description: TriviaMaze controls the instantiation of the various states implemented here using the State pattern.
 * It controls game flow, provides a Scanner for user input, and contains references to all necessary objects for
 * any given state to function.
 */
package states;

import java.io.Serializable;
import java.util.*;

import maze.Maze;
import maze.MazeDoor;
import maze.MazePlayer;
import maze.MazeRoom;

public class TriviaMaze implements Serializable {
	
	public static final Scanner KB = new Scanner(System.in);
	
	private TriviaMazeState mainMenuState;
	private TriviaMazeState newGameState;
	private TriviaMazeState loadGameState;
	private TriviaMazeState adminDBState;
	private TriviaMazeState navigateMazeState;
	private TriviaMazeState answerQuestionState;
	private TriviaMazeState saveGameState;
	private TriviaMazeState exitMazeState;
	private TriviaMazeState endGameState;
	
	private TriviaMazeState currentState;
	
	private Maze maze;
	private MazePlayer player;

	/**
	 * TriviaMaze() instantiates each necessary state for the program to run and sets the current state to
	 * MainMenuState so the user may choose how to proceed.
	 */

	public TriviaMaze() {
		mainMenuState = new MainMenuState(this);
		newGameState = new NewGameState(this);
		loadGameState = new LoadGameState(this);
		adminDBState = new AdminDBState(this);
		navigateMazeState = new NavigateMazeState(this);
		answerQuestionState = new AnswerQuestionState(this);
		saveGameState = new SaveGameState(this);
		exitMazeState = new ExitMazeState(this);
		endGameState = new EndGameState();

		setState(mainMenuState);
	}

	/**
	 * setState() sets the current state of the TriviaMaze
	 * @param state - the state the current state will be set to.
	 */
	public void setState(TriviaMazeState state) {
		this.currentState = state;
	}

	/**
	 * getCurrentState() returns the current state of the TriviaMaze
	 * @return - the current state of this TriviaMaze
	 */
	public TriviaMazeState getCurrentState() { return this.currentState; }

	/**
	 * getMainMenuState() returns the MainMenuState initialized in the constructor
	 * @return - the MainMenuState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getMainMenuState() { return this.mainMenuState; }

	/**
	 * getNewGameState() returns the NewGameState initialized in the constructor
	 * @return - the NewGameState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getNewGameState() { return this.newGameState; }

	/**
	 * getLoadGameState() returns the LoadGameState initialized in the constructor
	 * @return - the LoadGameState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getLoadGameState() { return this.loadGameState; }

	/**
	 * getAdminDBState() returns the LoadGameState initialized in the constructor
	 * @return - the AdminDBState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getAdminDBState() { return this.adminDBState; }

	/**
	 * getNavigateMazeState() returns the NavigateMazeState initialized in the constructor
	 * @return - the NavigateMazeState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getNavigateMazeState() { return this.navigateMazeState; }

	/**
	 * getAnswerQuestionState() returns the AnswerQuestionState initialized in the constructor
	 * @return - the AnswerQuestionState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getAnswerQuestionState() { return this.answerQuestionState; }

	/**
	 * getSaveGameState() returns the SaveGameState initialized in the constructor
	 * @return - the SaveGameState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getSaveGameState() { return this.saveGameState; }

	/**
	 * getExitMazeState() returns the ExitMazeState initialized in the constructor
	 * @return - the ExitMazeState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getExitMazeState() { return exitMazeState; }

	/**
	 * getEndGameState() returns the ExitMazeState initialized in the constructor
	 * @return - the EndGameState object instantiated by this TriviaMaze
	 */
	public TriviaMazeState getEndGameState() { return endGameState; }

	/**
	 * getPlayer() returns the MazePlayer used to track user's points and location
	 * @return - the MazePlayer object used to track player's points and location in the Maze
	 */
	public MazePlayer getPlayer() { return this.player; }

	/**
	 * getMaze() returns the Maze object containing the maze
	 * @return - the Maze object containing the maze the user navigates through
	 */
	public Maze getMaze() { return this.maze; }

	/**
	 * calls the current state's mainMenu()
	 */
	public void mainMenu() {
		currentState.mainMenu();
	}

	/**
	 * calls the current state's newGame()
	 */
	public void newGame() {
		currentState.newGame();
	}

	/**
	 * calls the current state's loadGame()
	 */
	public void loadGame() {
		currentState.loadGame();
	}

	/**
	 * calls the current state's adminDB()
	 */
	public void adminDB() {
		currentState.adminDB();
	}

	/**
	 * calls the current state's navigateMaze()
	 */
	public void navigateMaze() {
		currentState.navigateMaze();
	}


	/**
	 *  calls the current state's answerQuestion()
	 * @param direction	- String containing the direction the user wishes to move
	 */
	public void answerQuestion(String direction) {
		currentState.answerQuestion(direction);
	}

	/**
	 * calls the current state's saveGame()
	 */
	public void saveGame() {
		currentState.saveGame();
	}

	/**
	 * calls the current state's endGame() and closes the Scanner used for user input
	 */
	public void endGame() {
		KB.close();
		currentState.endGame();
	}

	/**
	 * calls the current state's exitMaze()
	 */
	public void exitMaze() {
		currentState.exitMaze();
	}

	/**
	 * provides a way for MazeFactory and LoadGameState to supply the created Maze object to TriviaMaze
	 * @param maze - the maze to be used in the program for user navigation
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	/**
	 * provides NewGameState and LoadGameState a method to provide TriviaMaze with the MazePlayer object it instantiates
	 * @param player - the MazePlayer object that will be used to track the player
	 */
	public void setPlayer(MazePlayer player) {
		this.player = player;
	}

	/**
	 * Determines if there is a valid path left from the player to the exit
	 * @return - true if a valid path remains, false otherwise
	 */
	public boolean isGameOver() {
		int potentialPoints = player.getCurrentPoints();
		boolean exitFound = false;
		
		Deque<MazeRoom> stack = new LinkedList<MazeRoom>();
		HashMap<Integer, MazeRoom> checkedRooms = new HashMap<Integer, MazeRoom>(maze.getSize());
		HashMap<Integer, MazeDoor> countedDoors = new HashMap<Integer, MazeDoor>(maze.getDoorCount());
		
		MazeRoom currentRoom = maze.getRoom(player.getCurrentCoordinates());
		
		if(!checkedRooms.containsValue(currentRoom)) {
			checkedRooms.put(currentRoom.hashCode(), currentRoom);
			stack.push(currentRoom);
		}
		
		while(!stack.isEmpty()) {
			currentRoom = stack.pop();
			
			if(currentRoom.equals(maze.getRoom(maze.getEnd()))) {
				exitFound = true;
			}
			
			Iterator<MazeDoor> doorIterator = currentRoom.doorIterator();
			
			while(doorIterator.hasNext()) {
				MazeDoor currentDoor = doorIterator.next();
				if(!currentDoor.isLocked() && !countedDoors.containsValue(currentDoor)) {
					if(currentDoor.getLesserRoom() != null && !checkedRooms.containsValue(currentDoor.getLesserRoom())) {
						checkedRooms.put(currentDoor.getLesserRoom().hashCode(), currentDoor.getLesserRoom());
						stack.push(currentDoor.getLesserRoom());
					} else if (currentDoor.getGreaterRoom() != null && !checkedRooms.containsValue(currentDoor.getGreaterRoom())) {
						checkedRooms.put(currentDoor.getGreaterRoom().hashCode(), currentDoor.getGreaterRoom());
						stack.push(currentDoor.getGreaterRoom());
					}
					//the player can only potentially gain more points from a door that is neither locked nor open
					if(!currentDoor.isOpen()) {
						potentialPoints += currentDoor.getDoorPoints();
					}
					countedDoors.put(currentDoor.hashCode(), currentDoor);
				}
			}
		}
		
		if(exitFound && (potentialPoints >= maze.getRequiredPoints())) {
			return false;
		} else {
			return true;
		}
	}
}
