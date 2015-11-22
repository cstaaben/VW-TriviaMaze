package states;

public interface TriviaMazeState {

	public void newGame();
	public void loadGame();
	public void adminDB();
	public void navigateMaze();
	public void answerQuestion(String direction);
	public void saveGame();
	public void mainMenu();
	public void exitMaze();
	public void endGame();
}
