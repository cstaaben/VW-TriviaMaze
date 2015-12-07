/**
 * TriviaMazeState.java
 * Author: Corbin Staaben
 * Description: TriviaMazeState is the interface used for implementing the State pattern to control game flow. It
 * defines a method for each state's main purpose. Detailed descriptions of each state are available in the .java
 * state class corresponding to each method.
 */
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
