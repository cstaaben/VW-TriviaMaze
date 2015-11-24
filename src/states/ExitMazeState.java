package states;

public class ExitMazeState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	
	public ExitMazeState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}
	
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
		triviaMaze.setState(triviaMaze.getMainMenuState());
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
	}
	
}
