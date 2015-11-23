package states;

import maze.MazeDirection;

public class AnswerQuestionState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	
	public AnswerQuestionState(TriviaMaze triviaMaze) {
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
		MazeDirection md = MazeDirection.valueOf(direction.toUpperCase());
		
		triviaMaze.getMaze().getRoom(triviaMaze.getPlayer().getCurrentCoordinates()).questionPrompt(md);
		
		triviaMaze.setState(triviaMaze.getNavigateMazeState());
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
