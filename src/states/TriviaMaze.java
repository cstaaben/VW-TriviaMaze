package states;

import java.util.Scanner;
import maze.Maze;
import maze.MazePlayer;

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
	
	public void answerQuestion() {
		currentState.answerQuestion();
	}
	
	public void saveGame() {
		currentState.saveGame();
	}
	
	public void endGame() {
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
	
}
