import states.TriviaMaze;

public class ProjectMain {
	
	public static void main(String[] args) {
		TriviaMaze maze = new TriviaMaze();
		
		while(!maze.getCurrentState().equals(maze.getEndGameState())) {
			if(maze.getCurrentState().equals(maze.getNewGameState())) {
				maze.newGame();
			}
			else if(maze.getCurrentState().equals(maze.getLoadGameState())) {
				maze.loadGame();
			}
			else if(maze.getCurrentState().equals(maze.getAdminDBState())) {
				maze.adminDB();
			}
			else if(maze.getCurrentState().equals(maze.getMainMenuState())) {
				maze.mainMenu();
			}
			else if(maze.getCurrentState().equals(maze.getSaveGameState())) {
				maze.saveGame();
			}
			else if(maze.getCurrentState().equals(maze.getNavigateMazeState())) {
				maze.navigateMaze();
			}
			else if(maze.getCurrentState().equals(maze.getExitMazeState())) {
				maze.exitMaze();
			}
		} // while endGameState
		
		maze.endGame();
	}

}
