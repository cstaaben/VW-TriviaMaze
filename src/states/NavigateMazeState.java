package states;

import maze.Maze;
import maze.MazePlayer;

public class NavigateMazeState implements TriviaMazeState {

	private TriviaMaze triviaMaze;
	private boolean exit;
	
	public NavigateMazeState(TriviaMaze triviaMaze) {
		this.triviaMaze = triviaMaze;
		
		this.exit = false;
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
		
		while(!direction.equals("exit") || triviaMaze.getCurrentState().equals(triviaMaze.getExitMazeState())) {
			System.out.println(maze.getRoom(player.getCurrentCoordinates()).display());
			System.out.print("Where would you like to move?  (Type \"exit\" to exit.) ");
			direction = TriviaMaze.KB.nextLine();
			
			while(!isValidInput(direction)) {
				System.out.println("\nInvalid direction. Please enter north, south, east, or west.");
				System.out.println("Where would you like to go? (Type \"exit\" to exit.) ");
				direction = TriviaMaze.KB.nextLine();
			}
			
			if(!exit) {
				if(!maze.getRoom(player.getCurrentCoordinates()).isValidDoor(direction)) {
					System.out.println("You can't move there!");
				}
				else {
					triviaMaze.setState(triviaMaze.getAnswerQuestionState());
					
					triviaMaze.answerQuestion(direction);
					
					if(maze.getRoom(player.getCurrentCoordinates()).isValidDoor(direction)) {
						player.move(direction);
					}
				}
			}
			
			if(player.getCurrentCoordinates().equals(maze.getEnd())) {
				triviaMaze.setState(triviaMaze.getExitMazeState());
			}
		} 
	}
	
	private boolean isValidInput(String input) {
		if(input.toLowerCase().equals("north") || input.toLowerCase().equals("east") || 
				input.toLowerCase().equals("south") || input.toLowerCase().equals("west")) {
			return triviaMaze.getMaze().getRoom(triviaMaze.getPlayer().getCurrentCoordinates()).isValidDoor(input);
		}
		else if(input.toLowerCase().equals("exit")) {
			this.exit = true;
			triviaMaze.setState(triviaMaze.getMainMenuState());
			return exit;
		}
		
		return false;
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
