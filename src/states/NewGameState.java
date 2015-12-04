package states;

import creation.MazeFactory;
import maze.MazePlayer;

public class NewGameState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	private MazeFactory mazeFactory;
	
	public NewGameState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
		this.mazeFactory = MazeFactory.getReference();
	}
	
	private int sizePrompt() {
		int size = 0;
		
		while(size == 0) {
			System.out.print("Please enter a number between 2 and 10 for the size of the maze: ");
			
			try {
				size = (int)Integer.parseInt(TriviaMaze.KB.nextLine());
				if(size < mazeFactory.MIN_SIZE || size > mazeFactory.MAX_SIZE) {
					throw new IndexOutOfBoundsException("Value entered is beyond specified range.");
				}
				System.out.println();
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("\nValue entered is not an integer.");
				size = 0;
				continue;
			}
			catch(IndexOutOfBoundsException i) {
				System.out.println("\n" + i.getMessage());
				size = 0;
				continue;
			}
		}
		
		return size;
	}
	
	@Override
	public void newGame() {
		triviaMaze.setMaze(mazeFactory.getMaze(sizePrompt()));
		
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
