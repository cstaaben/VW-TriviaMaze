package states;

import java.util.InputMismatchException;

import creation.MazeFactory;
import maze.MazePlayer;

public class NewGameState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	
	public NewGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
	}
	
	private int sizePrompt() {
		int size = 0;
		
		while(size == 0) {
			System.out.print("Please enter a number between 2 and 10 for the size of the maze: ");
			
			try {
				size = TriviaMaze.KB.nextInt();
				if(size < 2 || size > 10) {
					throw new IndexOutOfBoundsException("Value entered is beyond specified range.");
				}
				break;
			}
			catch(InputMismatchException e) {
				System.out.println("Value entered is not an integer.");
				TriviaMaze.KB.next();
				size = 0;
				continue;
			}
			catch(IndexOutOfBoundsException i) {
				System.out.println(i.getMessage());
				TriviaMaze.KB.next();
				size = 0;
				continue;
			}
		}
		
		return size;
	}
	
	@Override
	public void newGame() {
		triviaMaze.setMaze(MazeFactory.getReference().getMaze(sizePrompt()));
		
		triviaMaze.setPlayer(new MazePlayer(triviaMaze.getMaze().getStart()));

		triviaMaze.setState(triviaMaze.getNavigateMazeState());
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
