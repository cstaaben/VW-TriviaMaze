package states;

public class NewGameState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	
	public NewGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
		
		triviaMaze.setState(triviaMaze.getNavigateMazeState());
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
	public void answerQuestion() {
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