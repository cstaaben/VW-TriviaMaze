package states;

import java.util.InputMismatchException;

public class MainMenuState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	private String menu = "1.) Start a new game\n"
						+ "2.) Load a game\n"
						+ "3.) Login to the question database\n"
						+ "4.) Exit game";
	
	public MainMenuState(TriviaMaze triviaMaze) {
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
	public void answerQuestion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mainMenu() {
		int input = 0;
		
		while(input != 4) {
			do {
				System.out.println(menu);
				try {
					input = TriviaMaze.KB.nextInt();
					if(input < 1 || input > 4) {
						throw new IndexOutOfBoundsException("Value entered is out of range of the menu.");
					}
					break;
				}
				catch(InputMismatchException e) {
					System.out.println("Value entered is not an integer.");
					TriviaMaze.KB.next();
					continue;
				}
				catch(IndexOutOfBoundsException i) {
					System.out.println(i.getMessage());
				}
			} while(input < 1 || input > 4);
		} // end while input
		
		switch(input) {
			case 1:
				triviaMaze.setState(triviaMaze.getNewGameState());
				break;
			case 2:
				triviaMaze.setState(triviaMaze.getLoadGameState());
				break;
			case 3:
				triviaMaze.setState(triviaMaze.getAdminDBState());
				break;
		} // end switch input
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
