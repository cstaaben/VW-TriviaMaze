package states;

import maze.Maze;
import maze.MazePlayer;

import tests.MazeNavigationTest;

public class NavigateMazeState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	
	public NavigateMazeState(TriviaMaze triviaMaze) {
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
		MazePlayer player = triviaMaze.getPlayer();
		Maze maze = triviaMaze.getMaze()
		
		String input = "";
		
		while(!input.equals("exit")) {
			System.out.println(maze.getRoom(player.getCurrentCoordinates()).display());
			System.out.print("Where would you like to move?  (Type \"exit\" to exit.) ");
			input = MazeNavigationTest.KB.nextLine();
			
			while(!isValidInput(input)) {
				System.out.println("Invalid input. Please enter N, S, E, or W.");
				System.out.println("Where would you like to go? (Type \"exit\" to exit.) ");
				input = MazeNavigationTest.KB.nextLine();
			}
			
			if(input.toLowerCase().equals("n")) {
				if(maze.getRoom(player.getCurrentCoordinates()).questionPrompt(input)) {
					player.move(player.getCurrentCoordinates().getRow()-1, 
							player.getCurrentCoordinates().getCol());
				}
			}
			else if(input.toLowerCase().equals("s")) {
				if(maze.getRoom(player.getCurrentCoordinates()).questionPrompt(input)) {
					player.move(player.getCurrentCoordinates().getRow()+1, 
							player.getCurrentCoordinates().getCol());
				}
			}
			else if(input.toLowerCase().equals("e")) {
				if(maze.getRoom(player.getCurrentCoordinates()).questionPrompt(input)) {
					player.move(player.getCurrentCoordinates().getRow(), 
							player.getCurrentCoordinates().getCol()+1);
				}
			}
			else if(input.toLowerCase().equals("w")) {
				if(maze.getRoom(player.getCurrentCoordinates()).questionPrompt(input)) {
					player.move(player.getCurrentCoordinates().getRow(), 
							player.getCurrentCoordinates().getCol()-1);
				}
			}
		} // end while input
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
