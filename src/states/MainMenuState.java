package states;

import java.util.InputMismatchException;

public class MainMenuState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	private String menu = "1.) Start a new game\n"
						+ "2.) Load a game\n"
						+ "3.) Login to the database\n"
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
	public void answerQuestion(String direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mainMenu() {
		int input = 0;
		
		do {
			System.out.println(menu);
			
			try {
				input = TriviaMaze.KB.nextInt();
				if(input < 1 || input > 4) {
					throw new IndexOutOfBoundsException("Value entered is out of the range of the menu.");
				}
				break;
			}
			catch(InputMismatchException e) {
				System.out.println("Value entered is not an integer.");
				TriviaMaze.KB.next();
				input = 0;
				continue;
			}
			catch(IndexOutOfBoundsException i) {
				System.out.println(i.getMessage());
				continue;
			}
			
		} while(input < 1 || input > 4);
		
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
			case 4:
				triviaMaze.setState(triviaMaze.getEndGameState());
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
