package states;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import maze.Maze;
import maze.MazeDoor;
import maze.MazePlayer;
import maze.MazeRoom;

public class TriviaMaze {
	
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
	
	public TriviaMaze() {
		mainMenuState = new MainMenuState(this);
		newGameState = new NewGameState(this);
		loadGameState = new LoadGameState(this);
		adminDBState = new AdminDBState(this);
		navigateMazeState = new NavigateMazeState(this);
		answerQuestionState = new AnswerQuestionState(this);
		saveGameState = new SaveGameState(this);
		exitMazeState = new ExitMazeState(this);
		endGameState = new EndGameState(this);
		
		setState(mainMenuState);
	}
	
	public void setState(TriviaMazeState state) {
		this.currentState = state;
	}
	
	public TriviaMazeState getCurrentState() { return this.currentState; }
	public TriviaMazeState getMainMenuState() { return this.mainMenuState; }
	public TriviaMazeState getNewGameState() { return this.newGameState; }
	public TriviaMazeState getLoadGameState() { return this.loadGameState; }
	public TriviaMazeState getAdminDBState() { return this.adminDBState; }
	public TriviaMazeState getNavigateMazeState() { return this.navigateMazeState; }
	public TriviaMazeState getAnswerQuestionState() { return this.answerQuestionState; }
	public TriviaMazeState getSaveGameState() { return this.saveGameState; }
	public TriviaMazeState getExitMazeState() { return exitMazeState; }
	public TriviaMazeState getEndGameState() { return endGameState; }
	
	public MazePlayer getPlayer() { return this.player; }
	public Maze getMaze() { return this.maze; }
	
	public void mainMenu() {
		currentState.mainMenu();
	}
	
	public void newGame() {
		currentState.newGame();
	}
	
	public void loadGame() {
		currentState.loadGame();
	}
	
	public void adminDB() {
		currentState.adminDB();
	}
	
	public void navigateMaze() {
		currentState.navigateMaze();
	}
	
	public void answerQuestion(String direction) {
		currentState.answerQuestion(direction);
	}
	
	public void saveGame() {
		currentState.saveGame();
	}
	
	public void endGame() {
		KB.close();
		currentState.endGame();
	}
	
	public void exitMaze() {
		currentState.exitMaze();
	}
	
	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	public void setPlayer(MazePlayer player) {
		this.player = player;
	}
	
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
