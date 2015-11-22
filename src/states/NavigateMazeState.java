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
		Maze maze = triviaMaze.getMaze();
		
		String direction = "";
		
		while(!direction.equals("exit")) {
			System.out.println(maze.getRoom(player.getCurrentCoordinates()).display());
			System.out.print("Where would you like to move?  (Type \"exit\" to exit.) ");
			direction = MazeNavigationTest.KB.nextLine();
			
			while(!isValidInput(direction)) {
				System.out.println("Invalid direction. Please enter north, south, east, or west.");
				System.out.println("Where would you like to go? (Type \"exit\" to exit.) ");
				direction = MazeNavigationTest.KB.nextLine();
			}
			
			if(!maze.getRoom(player.getCurrentCoordinates()).isValidDoor(direction)) {
				System.out.println("You can't move there!");
			}
			else {
				triviaMaze.setState(triviaMaze.getAnswerQuestionState());
				
				triviaMaze.answerQuestion(direction);
				
				player.move(direction);
			}
		} // end while direction
	}
	
	private boolean isValidInput(String input) {
		if(input.toLowerCase().equals("north") || input.toLowerCase().equals("east") || 
				input.toLowerCase().equals("south") || input.toLowerCase().equals("west")) {
			return triviaMaze.getMaze().getRoom(triviaMaze.getPlayer().getCurrentCoordinates()).isValidDoor(input);
		}
		else {
			return input.toLowerCase().equals("exit");
		}
		
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
